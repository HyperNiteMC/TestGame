package com.ericlam.mc.testgame.main;

import com.ericlam.mc.minigames.core.main.MinigamesCore;
import com.ericlam.mc.testgame.TestGameMechanic;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class TestGame extends JavaPlugin {
    @Override
    public void onLoad() {
        MinigamesCore.getApi().registerGame(new TestGameMechanic(this));
        this.getLogger().info("Mechanics Registered");
    }

    @Override
    public void onEnable() {
    }
}
