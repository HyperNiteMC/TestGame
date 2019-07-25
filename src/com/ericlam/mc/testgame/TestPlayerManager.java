package com.ericlam.mc.testgame;

import com.ericlam.mc.minigames.core.character.GamePlayer;
import com.ericlam.mc.minigames.core.manager.PlayerManager;
import com.google.common.collect.ImmutableList;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TestPlayerManager implements PlayerManager {

    private List<GamePlayer> gamePlayers = new ArrayList<>();

    @Override
    public ImmutableList<GamePlayer> getTotalPlayers() {
        return ImmutableList.copyOf(gamePlayers);
    }

    @Override
    public GamePlayer buildGamePlayer(Player player) {
        TestGamePlayer gamePlayer = new TestGamePlayer(player, null);
        gamePlayers.add(gamePlayer);
        return gamePlayer;
    }

    @Override
    public void setGamePlayer(GamePlayer gamePlayer) {
        gamePlayer.setStatus(GamePlayer.Status.GAMING);
        Bukkit.broadcastMessage("Set "+gamePlayer.getPlayer().getName()+" to GamePlayer");
        gamePlayer.getPlayer().setGameMode(GameMode.ADVENTURE);
    }

    @Override
    public void setWaitingPlayer(GamePlayer gamePlayer) {
        gamePlayer.setStatus(GamePlayer.Status.WAITING);
        Bukkit.broadcastMessage("Set "+gamePlayer.getPlayer().getName()+" to WaitingPlayer");
        gamePlayer.getPlayer().setGameMode(GameMode.ADVENTURE);
    }

    @Override
    public void setSpectator(GamePlayer gamePlayer) {
        gamePlayer.setStatus(GamePlayer.Status.SPECTATING);
        Bukkit.broadcastMessage("Set "+gamePlayer.getPlayer().getName()+" to Spectator");
        gamePlayer.getPlayer().setGameMode(GameMode.SPECTATOR);
    }

    public void testCasted(){
        Bukkit.broadcastMessage("I am TestPlayerManager");
    }


    @Override
    public boolean shouldStart() {
        return this.getWaitingPlayer().size() >= 1;
    }

    @Override
    public void removePlayer(GamePlayer gamePlayer) {
        gamePlayers.remove(gamePlayer);
    }
}
