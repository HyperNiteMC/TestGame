package com.ericlam.mc.testgame;

import com.ericlam.mc.minigames.core.SectionTask;
import com.ericlam.mc.minigames.core.arena.ArenaConfig;
import com.ericlam.mc.minigames.core.arena.ArenaMechanic;
import com.ericlam.mc.minigames.core.game.GameMechanic;
import com.ericlam.mc.minigames.core.game.InGameState;
import com.ericlam.mc.minigames.core.manager.PlayerManager;
import com.ericlam.mc.testgame.states.*;
import com.ericlam.mc.testgame.tasks.*;

import java.util.LinkedHashMap;

public class TestGameMechanic implements GameMechanic {

    private final PlayerManager playerManager;
    private final ArenaConfig arenaConfig;
    private final ArenaMechanic arenaMechanic;
    private LinkedHashMap<InGameState, SectionTask> tasks = new LinkedHashMap<>();

    public TestGameMechanic(ArenaConfig arenaConfig){
        this.playerManager = new TestPlayerManager();
        this.arenaConfig = arenaConfig;
        this.arenaMechanic = new TestArenaMechanic();
        LobbyState lobby = new LobbyState();
        Game1State one = new Game1State();
        Game2State two = new Game2State();
        Game3State three = new Game3State();
        EndState end = new EndState();
        tasks.put(lobby, new VotingTask(playerManager,one));
        tasks.put(one, new Game1Task(playerManager, two));
        tasks.put(two, new Game2Task(playerManager, three));
        tasks.put(three, new Game3Task(playerManager, end));
        tasks.put(end, new EndTask(playerManager, null));
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
