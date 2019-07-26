package com.ericlam.mc.testgame.tasks;

import com.ericlam.mc.minigames.core.SectionTask;
import com.ericlam.mc.minigames.core.manager.PlayerManager;
import com.ericlam.mc.testgame.TestPlayerHandler;

public abstract class TestTask implements SectionTask {

    private boolean running;

    public TestTask() {
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
