package com.ericlam.mc.testgame;

import com.ericlam.mc.minigames.core.arena.Arena;
import com.ericlam.mc.minigames.core.arena.ArenaMechanic;
import com.ericlam.mc.minigames.core.arena.CreateArena;
import com.ericlam.mc.minigames.core.exception.arena.create.ArenaNotExistException;
import org.bukkit.GameRule;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import javax.annotation.Nonnull;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class TestArenaMechanic implements ArenaMechanic {

    private final Plugin plugin;

    public TestArenaMechanic(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public CompletableFuture<CreateArena> loadArenaFromFile(File file, String s) throws ArenaNotExistException {
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public CompletableFuture<List<CreateArena>> loadArenasFromFile(File file) {
        return CompletableFuture.completedFuture(new ArrayList<>());
    }

    @Override
    public CompletableFuture<Boolean> deleteArena(File file, Arena arena) {
        return CompletableFuture.completedFuture(false);
    }

    @Override
    public CompletableFuture<Boolean> saveArena(File file, Arena arena) {
        return CompletableFuture.completedFuture(false);
    }

    @Override
    public int getMaxLoadArena() {
        return 1;
    }

    @Override
    public <T> Map<GameRule<T>, T> getWorldGameRule() {
        return Map.of();
    }

    @Override
    public CreateArena createArena(@Nonnull String s, @Nonnull Player player) {
        return null;
    }
}
