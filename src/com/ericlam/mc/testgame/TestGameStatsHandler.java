package com.ericlam.mc.testgame;

import com.ericlam.mc.minigames.core.gamestats.GameStats;
import com.ericlam.mc.minigames.core.gamestats.GameStatsEditor;
import com.ericlam.mc.minigames.core.gamestats.GameStatsHandler;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class TestGameStatsHandler implements GameStatsHandler {

    @Nonnull
    @Override
    public GameStatsEditor loadGameStatsData(@Nonnull Player player) {
        return new TestGameStatsEditor();
    }

    @Override
    public CompletableFuture<Void> saveGameStatsData(OfflinePlayer offlinePlayer, GameStats gameStats) {
        Bukkit.broadcastMessage("====== GameStats of "+offlinePlayer.getName()+" ======");
        Arrays.asList(gameStats.getInfo()).forEach(Bukkit::broadcastMessage);
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public CompletableFuture<Void> saveGameStatsData(Map<OfflinePlayer, GameStats> map) {
        map.forEach((offlinePlayer, gameStats)->{
            Bukkit.broadcastMessage("====== GameStats of "+offlinePlayer.getName()+" ======");
            Arrays.asList(gameStats.getInfo()).forEach(Bukkit::broadcastMessage);
        });
        return CompletableFuture.completedFuture(null);
    }
}
