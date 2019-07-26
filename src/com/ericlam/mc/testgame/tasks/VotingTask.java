package com.ericlam.mc.testgame.tasks;

import com.ericlam.mc.minigames.core.arena.Arena;
import com.ericlam.mc.minigames.core.main.MinigamesCore;
import com.ericlam.mc.minigames.core.manager.PlayerManager;
import com.ericlam.mc.testgame.GameCreateArena;
import org.bukkit.Bukkit;

public class VotingTask extends TestTask {

    private PlayerManager playerManager;

    @Override
    public void initTimer(PlayerManager playerManager) {
        Bukkit.broadcastMessage("VotingTask started");
        this.playerManager = playerManager;
    }


    @Override
    public void onCancel() {
        Bukkit.broadcastMessage("VotingTask cancelled");
    }

    @Override
    public void onFinish() {
        Bukkit.broadcastMessage("VotingTask countdown finished");
    }

    @Override
    public void run(long l) {
        playerManager.getWaitingPlayer().forEach(p->{
            p.getPlayer().setLevel((int)l);
            if (l == 13){
                MinigamesCore.getApi().getLobbyManager().runFinalResult();
                Arena arena = MinigamesCore.getApi().getArenaManager().getFinalArena();
                Bukkit.broadcastMessage("Final Arena is: "+arena.getDisplayName());
                GameCreateArena gameArena = (GameCreateArena) arena;
                if (gameArena.isSendTitle()) p.getPlayer().sendTitle("","§aThis arena will send Title!", 20, 50, 20);
            }else if (l <= 10){
                p.getPlayer().sendMessage("VotingTask end in "+l+" secs");
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
