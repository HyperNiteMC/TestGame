package com.ericlam.mc.testgame.main;

import com.ericlam.mc.minigames.core.MinigamesAPI;
import com.ericlam.mc.minigames.core.Registration;
import com.ericlam.mc.minigames.core.arena.ArenaConfig;
import com.ericlam.mc.minigames.core.event.section.GamePreEndEvent;
import com.ericlam.mc.minigames.core.main.MinigamesCore;
import com.ericlam.mc.minigames.core.manager.PlayerManager;
import com.ericlam.mc.testgame.GameArenaConfig;
import com.ericlam.mc.testgame.TestGameMechanic;
import com.ericlam.mc.testgame.commands.GameDefaultCommand;
import com.ericlam.mc.testgame.states.Game1State;
import com.ericlam.mc.testgame.states.Game2State;
import com.ericlam.mc.testgame.states.Game3State;
import com.ericlam.mc.testgame.tasks.*;
import com.hypernite.mc.hnmc.core.config.ConfigSetter;
import com.hypernite.mc.hnmc.core.main.HyperNiteMC;
import com.hypernite.mc.hnmc.core.managers.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.stream.Collectors;

public class TestGame extends JavaPlugin implements Listener {


    @Override
    public void onEnable() {
        ArenaConfig config = new GameArenaConfig(this);
        ConfigManager configManager = HyperNiteMC.getAPI().registerConfig((ConfigSetter) config);
        configManager.setMsgConfig("config.yml");
        TestGameMechanic mechanic = new TestGameMechanic(config);
        PlayerManager playerManager = mechanic.getPlayerManager();
        MinigamesCore.getRegistration().registerGame(mechanic);
        this.getLogger().info("Mechanics Registered");
        Registration api = MinigamesCore.getRegistration();
        String prefix = configManager.getData("prefix", String.class).orElse("");
        api.registerArenaCommand(new GameDefaultCommand(configManager), prefix, this);
        this.getLogger().info("Command registered");
        api.registerGameTask(new Game1State(),new Game1Task(playerManager));
        api.registerGameTask(new Game2State(), new Game2Task(playerManager));
        api.registerGameTask(new Game3State(), new Game3Task(playerManager));
        api.registerEndTask(new EndTask(playerManager));
        api.registerLobbyTask(new VotingTask(playerManager));
        this.getLogger().info("Game Task registered");
        this.getServer().getPluginManager().registerEvents(this ,this);
    }

    @EventHandler
    public void onGameEnd(GamePreEndEvent e){
        Bukkit.broadcastMessage("Game End, Winners: "+e.getWinners().stream().map(p->p.getPlayer().getName()).collect(Collectors.joining(", ")));
        Bukkit.broadcastMessage("Winner Team: "+e.getWinnerTeam());
    }

}
