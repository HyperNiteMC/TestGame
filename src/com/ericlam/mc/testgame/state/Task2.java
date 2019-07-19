package com.ericlam.mc.testgame.state;

import com.ericlam.mc.minigames.core.game.GameState;
import com.ericlam.mc.minigames.core.game.InGameState;
import com.ericlam.mc.minigames.core.main.MinigamesCore;
import com.ericlam.mc.minigames.core.manager.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class Task2 extends TestTask {

    public Task2(PlayerManager playerManager, InGameState nextState) {
        super(playerManager, nextState);
    }

    @Override
    public void initTimer() {
        MinigamesCore.getApi().getGameManager().setState(GameState.IN_GAME);
        playerManager.getWaitingPlayer().forEach(p->playerManager.setGamePlayer(p.getPlayer()));
        Bukkit.broadcastMessage("Task2 started");
    }

    @Override
    public void teleport(PlayerManager playerManager) {
        Location one = MinigamesCore.getApi().getGameManager().getArenaManager().getFinalArena().getLocationsMap().get("test").get(0);
        playerManager.getTotalPlayers().forEach(p->p.getPlayer().teleportAsync(one));
    }

    @Override
    public void onCancel() {
        Bukkit.broadcastMessage("Task2 countdown cancelled");
    }

    @Override
    public void onFinish() {
        Bukkit.broadcastMessage("Task2 countdown finished");
    }

    @Override
    public void run(long l) {
        playerManager.getGamePlayer().forEach(p->{
            p.getPlayer().setLevel((int)l);
            if (l <= 10){
                p.getPlayer().sendMessage("Task2 end in "+l+" secs");
            }
        });
    }

    @Override
    public long getTotalTime() {
        return 25;
    }

    @Override
    public boolean shouldCancel() {
        return false;
    }
}
