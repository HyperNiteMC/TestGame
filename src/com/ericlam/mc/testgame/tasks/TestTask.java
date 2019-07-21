package com.ericlam.mc.testgame.tasks;

import com.ericlam.mc.minigames.core.SectionTask;
import com.ericlam.mc.minigames.core.game.InGameState;
import com.ericlam.mc.minigames.core.manager.PlayerManager;

import javax.annotation.Nullable;

public abstract class TestTask implements SectionTask {

    protected final PlayerManager playerManager;
    private final InGameState nextState;
    private boolean running;

    public TestTask(PlayerManager playerManager, @Nullable InGameState nextState) {
        this.playerManager = playerManager;
        this.nextState = nextState;
        this.running = false;
    }

    @Override
    public InGameState next() {
        return nextState;
    }


    @Override
    public boolean isRunning() {
        return running;
    }


    @Override
    public void setRunning(boolean running) {
        this.running = running;
    }
}
