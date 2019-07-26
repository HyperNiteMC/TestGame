package com.ericlam.mc.testgame.main;

import com.ericlam.mc.minigames.core.arena.ArenaConfig;
import com.ericlam.mc.minigames.core.arena.ArenaMechanic;
import com.ericlam.mc.minigames.core.character.GamePlayerHandler;
import com.ericlam.mc.minigames.core.event.section.GamePreEndEvent;
import com.ericlam.mc.minigames.core.event.section.GameStartEvent;
import com.ericlam.mc.minigames.core.event.state.InGameStateSwitchEvent;
import com.ericlam.mc.minigames.core.factory.GameFactory;
import com.ericlam.mc.minigames.core.factory.compass.CompassTracker;
import com.ericlam.mc.minigames.core.factory.scoreboard.GameBoard;
import com.ericlam.mc.minigames.core.game.GameState;
import com.ericlam.mc.minigames.core.game.InGameState;
import com.ericlam.mc.minigames.core.main.MinigamesCore;
import com.ericlam.mc.minigames.core.manager.PlayerManager;
import com.ericlam.mc.minigames.core.registable.Compulsory;
import com.ericlam.mc.minigames.core.registable.Voluntary;
import com.ericlam.mc.testgame.GameArenaConfig;
import com.ericlam.mc.testgame.TestArenaMechanic;
import com.ericlam.mc.testgame.TestGameStatsHandler;
import com.ericlam.mc.testgame.TestPlayerHandler;
import com.ericlam.mc.testgame.commands.GameDefaultCommand;
import com.ericlam.mc.testgame.states.Game1State;
import com.ericlam.mc.testgame.states.Game2State;
import com.ericlam.mc.testgame.states.Game3State;
import com.ericlam.mc.testgame.tasks.*;
import com.hypernite.mc.hnmc.core.builders.InventoryBuilder;
import com.hypernite.mc.hnmc.core.builders.ItemStackBuilder;
import com.hypernite.mc.hnmc.core.config.ConfigSetter;
import com.hypernite.mc.hnmc.core.main.HyperNiteMC;
import com.hypernite.mc.hnmc.core.managers.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.stream.Collectors;

public class TestGame extends JavaPlugin implements Listener {

    private GameBoard game1Board;
    private GameBoard game2Board;
    private GameBoard game3Board;
    private final InGameState game1State = new Game1State();
    private final InGameState game2State = new Game2State();
    private final InGameState game3State = new Game3State();
    private CompassTracker compassTracker;

    @Override
    public void onEnable() {
        ArenaConfig config = new GameArenaConfig(this);
        ConfigManager configManager = HyperNiteMC.getAPI().registerConfig((ConfigSetter) config);
        configManager.setMsgConfig("config.yml");
        Compulsory com = MinigamesCore.getRegistration().getCompulsory();
        GamePlayerHandler gamePlayerHandler = new TestPlayerHandler();
        ArenaMechanic arenaMechanic = new TestArenaMechanic();
        com.registerGamePlayerHandler(gamePlayerHandler);
        com.registerArenaMechanic(arenaMechanic);
        com.registerArenaConfig(config);
        com.registerGameStatsHandler(new TestGameStatsHandler());
        this.getLogger().info("Mechanics Registered");
        Voluntary vol = MinigamesCore.getRegistration().getVoluntary();
        com.registerArenaCommand(new GameDefaultCommand(configManager), this);
        this.getLogger().info("Command registered");
        vol.registerGameTask(game1State,new Game1Task());
        vol.registerGameTask(game2State, new Game2Task());
        vol.registerGameTask(game3State, new Game3Task());
        com.registerEndTask(new EndTask());
        com.registerLobbyTask(new VotingTask());
        com.registerVoteGUI(new InventoryBuilder(1, "&a地圖投票"), 3, 5, 7);
        this.getLogger().info("Game Task registered");
        this.getServer().getPluginManager().registerEvents(this ,this);
        GameFactory factory = MinigamesCore.getProperties().getGameFactory();
        this.game1Board = factory.getScoreboardFactory()
                .setTitle("&e第一場的計分版")
                .addLine("&a這是第一行", 12)
                .addLine("&c這是第二行", 11)
                .addLine("&b這是第三行", 10)
                .addLine("&d去你嗎的", 9)
                .setLine("3", "這是第四行", 8).build();
        this.game2Board = factory.getScoreboardFactory()
                .setTitle("&d第二場的計分版")
                .addLine("&a這是第一行", 12)
                .addLine("&c這是第二行", 11)
                .addLine("&b這是第三行", 10).build();
        this.game3Board = factory.getScoreboardFactory()
                .setTitle("&b第三場的計分版")
                .addLine("&a這是第一行", 12)
                .addLine("&c這是第二行", 11)
                .addLine("&b這是第三行", 10).build();
        this.compassTracker = factory.getCompassFactory().setTrackerRange(30).setCaughtText("&e玩家&f <target> &7- &a距離 &f<distance>")
                .setSearchingText("&b&l搜&r&7索中...", "&7搜&b&l索&r&7中...", "&7搜索&b&l中&r&7...").build();
        vol.addGameItem(8, new ItemStackBuilder(Material.COMPASS).build());
    }

    @EventHandler
    public void onGameEnd(GamePreEndEvent e){
        Bukkit.broadcastMessage("Game End, Winners: "+e.getWinners().stream().map(p->p.getPlayer().getName()).collect(Collectors.joining(", ")));
        Bukkit.broadcastMessage("Winner Team: "+e.getWinnerTeam());
        compassTracker.destroy();
    }

    @EventHandler
    public void onGameStart(GameStartEvent e){
        compassTracker.launch();
    }

    @EventHandler
    public void onInGameStateSwitch(InGameStateSwitchEvent e){
        if (e.getInGameState() == null) return;
        InGameState inGameState = e.getInGameState();
        if (inGameState == game1State){
            e.getGamingPlayer().forEach(p->game1Board.addPlayer(p));
        }else if (inGameState == game2State){
            game1Board.destroy();
            e.getGamingPlayer().forEach(p->game2Board.addPlayer(p));
        }else if (inGameState == game3State){
            game2Board.destroy();
            e.getGamingPlayer().forEach(p->game3Board.addPlayer(p));
        }else if (e.getGameState() == GameState.PREEND){
            game3Board.destroy();
        }
    }

}
