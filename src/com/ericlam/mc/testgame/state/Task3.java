package com.ericlam.mc.testgame.state;

import com.ericlam.mc.minigames.core.game.GameState;
import com.ericlam.mc.minigames.core.game.InGameState;
import com.ericlam.mc.minigames.core.main.MinigamesCore;
import com.ericlam.mc.minigames.core.manager.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class Task3 extends TestTask {
    public Task3(PlayerManager playerManager, InGameState nextState) {
        super(playerManager, nextState);
    }

    @Override
    public void initTimer() {
        MinigamesCore.getApi().getGameManager().setState(GameState.ENDED);
        playerManager.getGamePlayer().forEach(p->playerManager.setSpectator(p.getPlayer()));
        Bukkit.broadcastMessage("Task3 is started");
    }

    @Override
    public void teleport(PlayerManager playerManager) {
        Location three = MinigamesCore.getApi().getGameManager().getArenaManager().getFinalArena().getLocationsMap().get("test").get(2);
        playerManager.getTotalPlayers().forEach(p->p.getPlayer().teleportAsync(three));
    }

    @Override
    public void onCancel() {
        Bukkit.broadcastMessage("Task3 countdown cancelled");
    }

    @Override
    public void onFinish() {
        Bukkit.broadcastMessage("Task3 countdown finished");
        Bukkit.broadcastMessage("Game End");
    }

    @Override
    public void run(long l) {
        playerManager.getSpectators().forEach(p->{
            p.getPlayer().setLevel((int)l);
            if (l <= 10){
                p.getPlayer().sendMessage("Task3 end in "+l+" secs");
            }
        });
    }

    @Override
    public long getTotalTime() {
        return 15;
    }

    @Override
    public boolean shouldCancel() {
        return false;
    }
}
