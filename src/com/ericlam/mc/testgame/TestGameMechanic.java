package com.ericlam.mc.testgame;

import com.ericlam.mc.minigames.core.arena.ArenaConfig;
import com.ericlam.mc.minigames.core.arena.ArenaMechanic;
import com.ericlam.mc.minigames.core.game.GameMechanic;
import com.ericlam.mc.minigames.core.manager.PlayerManager;

public class TestGameMechanic implements GameMechanic {

    private final PlayerManager playerManager;
    private final ArenaConfig arenaConfig;
    private final ArenaMechanic arenaMechanic;

    public TestGameMechanic(ArenaConfig arenaConfig){
        this.playerManager = new TestPlayerManager();
        this.arenaConfig = arenaConfig;
        this.arenaMechanic = new TestArenaMechanic();
    }

    @Override
    public ArenaConfig getArenaConfig() {
        return arenaConfig;
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
