package com.ericlam.mc.testgame.state;

import com.ericlam.mc.minigames.core.arena.Arena;
import com.ericlam.mc.minigames.core.game.InGameState;
import com.ericlam.mc.minigames.core.main.MinigamesCore;
import com.ericlam.mc.minigames.core.manager.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import javax.annotation.Nullable;

public class Task1 extends TestTask {

    public Task1(PlayerManager playerManager, @Nullable InGameState nextState) {
        super(playerManager, nextState);
    }

    @Override
    public void initTimer() {
        Bukkit.broadcastMessage("Task1 countdown started");
    }

    @Override
    public void teleport(PlayerManager playerManager) {
        Location lobby = MinigamesCore.getApi().getGameManager().getGameMechanic().getArenaConfig().getLobbyLocation();
        playerManager.getTotalPlayers().forEach(p->p.getPlayer().teleportAsync(lobby));
    }

    @Override
    public void onCancel() {
        Bukkit.broadcastMessage("Task1 countdown cancelled");
    }

    @Override
    public void onFinish() {
        Bukkit.broadcastMessage("Task1 countdown finished");
        Arena finalArena = MinigamesCore.getApi().getGameManager().getLobbyManager().getFinalResult();
        MinigamesCore.getApi().getGameManager().getArenaManager().setFinalArena(finalArena);
    }

    @Override
    public void run(long l) {
        playerManager.getWaitingPlayer().forEach(p->{
            p.getPlayer().setLevel((int)l);
            if (l <= 10){
                p.getPlayer().sendMessage("Task1 end in "+l+" secs");
            }
        });
    }

    @Override
    public long getTotalTime() {
        return 30;
    }

    @Override
    public boolean shouldCancel() {
        return playerManager.getWaitingPlayer().size() < 1;
    }
}
