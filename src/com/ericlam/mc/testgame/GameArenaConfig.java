package com.ericlam.mc.testgame;

import com.ericlam.mc.minigames.core.arena.Arena;
import com.ericlam.mc.minigames.core.arena.ArenaConfig;
import com.hypernite.mc.hnmc.core.config.ConfigSetter;
import com.hypernite.mc.hnmc.core.main.HyperNiteMC;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameArenaConfig extends ConfigSetter implements ArenaConfig {

    public GameArenaConfig(Plugin plugin) {
        super(plugin, "arena.yml");
        HyperNiteMC.getAPI().registerConfig(this);
    }

    private List<Location> locMap = new ArrayList<>();


    private List<Arena> arenas = new ArrayList<>();

    @Override
    public void loadArenas() {
        this.arenas.add(new GameArena(locMap));
    }

    @Override
    public List<Arena> getArenas() {
        return arenas;
    }

    @Override
    public File getArenaFolder() {
        return new File(getPlugin().getDataFolder(), "ArenaData");
    }

    @Override
    public Location getLobbyLocation() {
        return Bukkit.getWorlds().get(0).getSpawnLocation();
    }

    @Override
    public void loadConfig(Map<String, FileConfiguration> map) {
        FileConfiguration arena = map.get("arena.yml");
        for (String key : arena.getKeys(false)) {
            String name = arena.getString(key+".world");
            World world = Bukkit.getWorld(name);
            double x = arena.getDouble(key+".x");
            double y = arena.getDouble(key+".y");
            double z = arena.getDouble(key+".z");
            this.locMap.add(new Location(world, x, y, z));
        }
    }
}
