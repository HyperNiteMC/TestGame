package com.ericlam.mc.testgame;

import com.ericlam.mc.minigames.core.SectionTask;
import com.ericlam.mc.minigames.core.arena.ArenaConfig;
import com.ericlam.mc.minigames.core.game.GameMechanic;
import com.ericlam.mc.minigames.core.game.InGameState;
import com.ericlam.mc.minigames.core.manager.ArenaManager;
import com.ericlam.mc.minigames.core.manager.PlayerManager;
import com.ericlam.mc.testgame.state.*;
import org.bukkit.plugin.Plugin;

import java.util.LinkedHashMap;
import java.util.Map;

public class TestGameMechanic implements GameMechanic {

    private Plugin plugin;
    private PlayerManager playerManager;
    private LinkedHashMap<InGameState, SectionTask> tasks = new LinkedHashMap<>();

    public TestGameMechanic(Plugin plugin){
        this.plugin = plugin;
        this.playerManager = new TestPlayerManager();
        State1 one = new State1();
        State2 two = new State2();
        State3 three = new State3();
        tasks.put(one, new Task1(playerManager,two));
        tasks.put(two, new Task2(playerManager, three));
        tasks.put(three, new Task3(playerManager, null));
    }

    @Override
    public ArenaConfig getArenaConfig() {
        return new GameArenaConfig(plugin);
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
    public void initializeArena(ArenaManager arenaManager) {
        arenaManager.loadMaxArenas(3);
        arenaManager.unloadWorlds(Map.of());
    }
}
