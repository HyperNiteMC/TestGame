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
    public void setSpectator(Player player) {
        GamePlayer gamePlayer = new TestGamePlayer(player, GamePlayer.Status.SPECTATING);
        this.replacePlayer(gamePlayer);
        Bukkit.broadcastMessage("Set "+player.getName()+" to Spectator");
        player.setGameMode(GameMode.SPECTATOR);
    }

    @Override
    public void setGamePlayer(Player player) {
        GamePlayer gamePlayer = new TestGamePlayer(player, GamePlayer.Status.GAMING);
        this.replacePlayer(gamePlayer);
        Bukkit.broadcastMessage("Set "+player.getName()+" to GamePlayer");
        player.setGameMode(GameMode.ADVENTURE);
    }

    @Override
    public void setWaitingPlayer(Player player) {
        GamePlayer gamePlayer = new TestGamePlayer(player, GamePlayer.Status.WAITING);
        this.replacePlayer(gamePlayer);
        Bukkit.broadcastMessage("Set "+player.getName()+" to WaitingPlayer");
        player.setGameMode(GameMode.ADVENTURE);
    }

    private void replacePlayer(GamePlayer player){
        this.gamePlayers.removeIf(p->p.getPlayer().equals(player.getPlayer()));
        this.gamePlayers.add(player);
    }

    public void testCasted(){
        Bukkit.broadcastMessage("I am TestPlayerManager");
    }


    @Override
    public boolean shouldStart() {
        return this.getWaitingPlayer().size() >= 1;
    }

    @Override
    public void removePlayer(Player player) {
        this.gamePlayers.removeIf(p->p.getPlayer().equals(player));
    }
}
