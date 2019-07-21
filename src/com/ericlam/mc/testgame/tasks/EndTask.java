package com.ericlam.mc.testgame.tasks;

import com.ericlam.mc.minigames.core.arena.Arena;
import com.ericlam.mc.minigames.core.game.GameState;
import com.ericlam.mc.minigames.core.game.InGameState;
import com.ericlam.mc.minigames.core.main.MinigamesCore;
import com.ericlam.mc.minigames.core.manager.PlayerManager;
import com.ericlam.mc.testgame.GameCreateArena;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import javax.annotation.Nullable;

public class EndTask extends TestTask {

    public EndTask(PlayerManager playerManager, @Nullable InGameState nextState) {
        super(playerManager, nextState);
    }

    @Override
    public void initTimer() {
        MinigamesCore.getApi().getGameManager().setState(GameState.ENDED);
        playerManager.getTotalPlayers().forEach(p->playerManager.setSpectator(p.getPlayer()));
        Bukkit.broadcastMessage("Game END");
        Arena arena = MinigamesCore.getApi().getArenaManager().getFinalArena();
        GameCreateArena gameArena = (GameCreateArena) arena;
        if (gameArena.isSendTitle()){
            playerManager.getTotalPlayers().forEach(p->p.getPlayer().sendTitle("","§aGAME END",20, 40, 20));
        }
    }

    @Override
    public void teleport(PlayerManager playerManager) {
    }

    @Override
    public void onCancel() {
    }

    @Override
    public void onFinish() {
        Bukkit.broadcastMessage("Game has ended. send back you to lobby");
        Location lobby = MinigamesCore.getApi().getGameManager().getGameMechanic().getArenaConfig().getLobbyLocation();
        playerManager.getTotalPlayers().forEach(p-> {
            p.getPlayer().teleportAsync(lobby);
            playerManager.setWaitingPlayer(p.getPlayer());
        });
    }

    @Override
    public void run(long l) {
        playerManager.getSpectators().forEach(p->{
            p.getPlayer().sendActionBar(l+"");
            if (l <= 5){
                p.getPlayer().sendMessage("Game Ended in "+l+" secs");
            }
        });
    }

    @Override
    public long getTotalTime() {
        return 10;
    }

    @Override
    public boolean shouldCancel() {
        return false;
    }
}
