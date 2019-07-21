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

public class Game2Task extends TestTask {
    public Game2Task(PlayerManager playerManager, InGameState nextState) {
        super(playerManager, nextState);
    }

    @Override
    public void initTimer() {
        MinigamesCore.getApi().getGameManager().setState(GameState.IN_GAME);
        Bukkit.broadcastMessage("Game Section 2 Task is started");
        Arena arena = MinigamesCore.getApi().getArenaManager().getFinalArena();
        GameCreateArena gameArena = (GameCreateArena) arena;
        if (gameArena.isSendTitle()){
            playerManager.getTotalPlayers().forEach(p->p.getPlayer().sendTitle("","§aSection 2 Started",20, 40, 20));
        }
    }

    @Override
    public void teleport(PlayerManager playerManager) {
        List<Location> one = MinigamesCore.getApi().getArenaManager().getFinalArena().getLocationsMap().get("tp-two");
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
        Bukkit.broadcastMessage("Game Section 2 Task countdown finished");
    }

    @Override
    public void run(long l) {
        playerManager.getGamePlayer().forEach(p->{
            p.getPlayer().setLevel((int)l);
            if (l <= 10){
                p.getPlayer().sendMessage("Game Section 2 Task end in "+l+" secs");
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
