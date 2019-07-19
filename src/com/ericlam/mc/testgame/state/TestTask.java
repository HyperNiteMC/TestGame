package com.ericlam.mc.testgame.state;

import com.ericlam.mc.minigames.core.SectionTask;
import com.ericlam.mc.minigames.core.game.InGameState;
import com.ericlam.mc.minigames.core.manager.PlayerManager;

public abstract class TestTask implements SectionTask {

    protected final PlayerManager playerManager;
    private final InGameState nextState;
    private boolean running;

    public TestTask(PlayerManager playerManager, InGameState nextState) {
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
