package com.ericlam.mc.testgame;

import com.ericlam.mc.minigames.core.character.GamePlayer;
import org.bukkit.entity.Player;

public class TestGamePlayer implements GamePlayer {

    private final Player player;
    private Status status;

    public TestGamePlayer(Player player, Status status) {
        this.player = player;
        this.status = status;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public void setStatus(Status status) {
        this.status = status;
    }

}
