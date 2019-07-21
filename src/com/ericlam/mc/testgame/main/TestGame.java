package com.ericlam.mc.testgame.main;

import com.ericlam.mc.minigames.core.arena.ArenaConfig;
import com.ericlam.mc.minigames.core.main.MinigamesCore;
import com.ericlam.mc.testgame.GameArenaConfig;
import com.ericlam.mc.testgame.TestGameMechanic;
import com.ericlam.mc.testgame.commands.GameDefaultCommand;
import com.hypernite.mc.hnmc.core.config.ConfigSetter;
import com.hypernite.mc.hnmc.core.main.HyperNiteMC;
import com.hypernite.mc.hnmc.core.managers.ConfigManager;
import org.bukkit.plugin.java.JavaPlugin;

public class TestGame extends JavaPlugin {


    private int maxLoc;
    private ConfigManager configManager;

    public int getMaxLoc() {
        return maxLoc;
    }

    @Override
    public void onLoad() {
        ArenaConfig config = new GameArenaConfig(this);
        configManager = HyperNiteMC.getAPI().registerConfig((ConfigSetter)config);
        configManager.setMsgConfig("config.yml");
        maxLoc = configManager.getData("max-loc", int.class).orElse(3);
        MinigamesCore.getApi().registerGame(new TestGameMechanic(config));
        this.getLogger().info("Mechanics Registered");
    }

    @Override
    public void onEnable() {
        String prefix = configManager.getData("prefix", String.class).orElse("");
        MinigamesCore.getApi().registerArenaCommand(new GameDefaultCommand(configManager), prefix, this);
        this.getLogger().info("Command registered");
    }
}
