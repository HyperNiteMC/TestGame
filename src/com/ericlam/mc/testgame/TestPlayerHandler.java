package com.ericlam.mc.testgame;

import com.ericlam.mc.minigames.core.character.GamePlayer;
import com.ericlam.mc.minigames.core.character.GamePlayerHandler;
import com.google.common.collect.ImmutableList;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class TestPlayerHandler implements GamePlayerHandler {

    @Override
    public void onPlayerStatusChange(GamePlayer gamePlayer, GamePlayer.Status status) {
        Bukkit.broadcastMessage(gamePlayer.getPlayer().getName()+" is now "+status.toString());
        if (status == GamePlayer.Status.SPECTATING) gamePlayer.getPlayer().setGameMode(GameMode.SPECTATOR);
    }

    @Override
    public void onPlayerRemove(GamePlayer gamePlayer) {

    }

    @Override
    public GamePlayer createGamePlayer(Player player) {
        return new TestGamePlayer(player, null);
    }

    @Override
    public int requireStart() {
        return 1;
    }
}
