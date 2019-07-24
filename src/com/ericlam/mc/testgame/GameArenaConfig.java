package com.ericlam.mc.testgame;

import com.ericlam.mc.minigames.core.arena.ArenaConfig;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.hypernite.mc.hnmc.core.config.ConfigSetter;
import com.hypernite.mc.hnmc.core.config.Extract;
import com.hypernite.mc.hnmc.core.utils.converters.LocationSerializer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameRule;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class GameArenaConfig extends ConfigSetter implements ArenaConfig {

    private final File folder;
    private int maxLoadArenas;

    private String prefix;

    private FileConfiguration config;

    private ConfigurationSection lobby;

    public GameArenaConfig(Plugin plugin) {
        super(plugin, "config.yml");
        folder = new File(getPlugin().getDataFolder(), "ArenaData");
        if (!folder.exists()) folder.mkdirs();
    }

    @Override
    public void loadConfig(Map<String, FileConfiguration> map) {
        config = map.get("config.yml");
        this.maxLoadArenas = config.getInt("max-arenas");
        lobby = config.getConfigurationSection("lobby");
        this.prefix = ChatColor.translateAlternateColorCodes('&', Optional.ofNullable(config.getString("prefix")).orElse(""));
    }

    @Override
    public File getArenaFolder() {
        return folder;
    }

    @Override
    public int getMaxLoadArena() {
        return maxLoadArenas;
    }

    @Override
    public <T> Map<GameRule<T>, T> getWorldGameRule() {
        return Map.of();
    }

    @Override
    public ImmutableMap<String, Integer> getAllowWarps() {
        return ImmutableMap.of(

                "tp-one", 3,
                "tp-two",3,
                "tp-three",3
        );
    }

    @Override
    public Location getLobbyLocation() {
        return lobby == null ? null : LocationSerializer.mapToLocation(lobby).orElse(null);
    }

    @Override
    public String getFallBackServer() {
        return "lobby";
    }

    @Override
    public String getGamePrefix() {
        return prefix;
    }

    @Override
    public CompletableFuture<Boolean> setLobbyLocation(Location location) {
        return CompletableFuture.supplyAsync(()->{
            config.createSection("lobby", LocationSerializer.locToConfigSection(location));
            try {
                config.save(new File(getPlugin().getDataFolder(), "config.yml"));
                this.lobby = config.getConfigurationSection("lobby");
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        });
    }
}
