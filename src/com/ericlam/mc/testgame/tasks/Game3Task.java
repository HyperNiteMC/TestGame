package com.ericlam.mc.testgame.tasks;

import com.ericlam.mc.minigames.core.arena.Arena;
import com.ericlam.mc.minigames.core.character.GamePlayer;
import com.ericlam.mc.minigames.core.game.GameState;
import com.ericlam.mc.minigames.core.game.InGameState;
import com.ericlam.mc.minigames.core.main.MinigamesCore;
import com.ericlam.mc.minigames.core.manager.PlayerManager;
import com.ericlam.mc.testgame.GameCreateArena;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.List;

public class Game3Task extends TestTask {
    private PlayerManager playerManager;
    @Override
    public void initTimer(PlayerManager playerManager) {
        Bukkit.broadcastMessage("Game Section 3 Task started");
        Arena arena = MinigamesCore.getApi().getArenaManager().getFinalArena();
        GameCreateArena gameArena = arena.castTo(GameCreateArena.class);
        if (gameArena.isSendTitle()){
            playerManager.getTotalPlayers().forEach(p->p.getPlayer().sendTitle("","§aSection 3 Started",20, 40, 20));
        }
        this.playerManager = playerManager;
        List<Location> one = MinigamesCore.getApi().getArenaManager().getFinalArena().getLocationsMap().get("tp-three");
        List<GamePlayer> players = playerManager.getTotalPlayers();
        for (int i = 0; i < players.size(); i++) {
            if (i == one.size()) break;
            players.get(i).getPlayer().teleportAsync(one.get(i));
        }
    }

    @Override
    public void onCancel() {
    }

    @Override
    public void onFinish() {
        Bukkit.broadcastMessage("Game Section 3 Task countdown finished");
    }

    @Override
    public long run(long l) {
        playerManager.getGamePlayer().forEach(p->{
            p.getPlayer().setLevel((int)l);
            if (l <= 10){
                p.getPlayer().sendMessage("Game Section 3 Task end in "+l+" secs");
            }
        });
        return l;
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
