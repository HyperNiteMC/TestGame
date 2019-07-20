package com.ericlam.mc.testgame;

import com.ericlam.mc.minigames.core.SectionTask;
import com.ericlam.mc.minigames.core.arena.ArenaConfig;
import com.ericlam.mc.minigames.core.arena.ArenaMechanic;
import com.ericlam.mc.minigames.core.game.GameMechanic;
import com.ericlam.mc.minigames.core.game.InGameState;
import com.ericlam.mc.minigames.core.manager.PlayerManager;
import com.ericlam.mc.testgame.state.*;
import org.bukkit.plugin.Plugin;

import java.util.LinkedHashMap;

public class TestGameMechanic implements GameMechanic {

    private final PlayerManager playerManager;
    private final ArenaConfig arenaConfig;
    private final ArenaMechanic arenaMechanic;
    private LinkedHashMap<InGameState, SectionTask> tasks = new LinkedHashMap<>();

    public TestGameMechanic(Plugin plugin){
        this.playerManager = new TestPlayerManager();
        this.arenaConfig = new GameArenaConfig(plugin);
        this.arenaMechanic = new TestArenaMechanic(plugin);
        State1 one = new State1();
        State2 two = new State2();
        State3 three = new State3();
        tasks.put(one, new Task1(playerManager,two));
        tasks.put(two, new Task2(playerManager, three));
        tasks.put(three, new Task3(playerManager, null));
    }

    @Override
    public ArenaConfig getArenaConfig() {
        return arenaConfig;
    }

    @Override
    public LinkedHashMap<InGameState, SectionTask> getProgramTasks() {
        return tasks;
    }

    @Override
    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    @Override
    public ArenaMechanic getArenaMechanic() {
        return arenaMechanic;
    }
}
