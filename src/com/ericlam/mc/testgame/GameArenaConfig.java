package com.ericlam.mc.testgame;

import com.ericlam.mc.minigames.core.arena.ArenaConfig;
import com.google.common.collect.ImmutableMap;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class GameArenaConfig implements ArenaConfig {

    private final File folder;
    private final String prefix;
    private final GameConfig gameConfig;

    public GameArenaConfig(Plugin plugin, GameConfig gameConfig) {
        this.gameConfig = gameConfig;
        this.prefix = gameConfig.getPrefix();
        this.folder = new File(plugin.getDataFolder(), "Arenas");
    }


    @Override
    public File getArenaFolder() {
        return folder;
    }

    @Override
    public int getMaxLoadArena() {
        return gameConfig.maxArenas;
    }

    @Override
    public void setExtraWorldSetting(@Nonnull World world) {
        //
    }

    @Override
    public ImmutableMap<String, Integer> getAllowWarps() {
        return ImmutableMap.of(

                "tp-one", 3,
                "tp-two", 3,
                "tp-three", 3
        );
    }

    @Override
    public Location getLobbyLocation() {
        return gameConfig.lobby;
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
        return CompletableFuture.supplyAsync(() -> {
            gameConfig.lobby = location;
            try {
                gameConfig.save();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        });
    }
}
