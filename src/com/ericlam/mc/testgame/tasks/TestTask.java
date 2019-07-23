package com.ericlam.mc.testgame.tasks;

import com.ericlam.mc.minigames.core.SectionTask;
import com.ericlam.mc.minigames.core.manager.PlayerManager;
import com.ericlam.mc.testgame.TestPlayerManager;

public abstract class TestTask implements SectionTask {

    protected final TestPlayerManager playerManager;
    private boolean running;

    public TestTask(PlayerManager playerManager) {
        this.playerManager = playerManager.castTo(TestPlayerManager.class);
        this.running = false;
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
