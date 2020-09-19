package com.ericlam.mc.testgame.tasks;

import com.ericlam.mc.minigames.core.arena.Arena;
import com.ericlam.mc.minigames.core.game.GameState;
import com.ericlam.mc.minigames.core.main.MinigamesCore;
import com.ericlam.mc.minigames.core.manager.PlayerManager;
import com.ericlam.mc.testgame.GameCreateArena;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;

public class EndTask extends TestTask {


    private PlayerManager playerManager;
    @Override
    public void initTimer(PlayerManager playerManager) {
        MinigamesCore.getApi().getGameManager().endGame(playerManager.getGamePlayer(),null, false);
        playerManager.getTotalPlayers().forEach(playerManager::setSpectator);
        Bukkit.broadcastMessage("Game END");
        Arena arena = MinigamesCore.getApi().getArenaManager().getFinalArena();
        GameCreateArena gameArena = arena.castTo(GameCreateArena.class);
        if (gameArena.isSendTitle()){
            playerManager.getTotalPlayers().forEach(p->p.getPlayer().sendTitle("","§aGAME END",20, 40, 20));
        }
        this.playerManager = playerManager;
    }


    @Override
    public void onCancel() {
    }

    @Override
    public void onFinish() {
        Bukkit.broadcastMessage("Game has ended. send back you to lobby");
        playerManager.getTotalPlayers().forEach(p-> {
            MinigamesCore.getApi().getLobbyManager().tpLobbySpawn(p.getPlayer());
            p.getPlayer().setGameMode(GameMode.ADVENTURE);
            p.getPlayer().getInventory().clear();
        });
        MinigamesCore.getApi().getGameManager().setState(GameState.STOPPED);
    }

    @Override
    public long run(long l) {
        playerManager.getSpectators().forEach(p->{
            p.getPlayer().sendActionBar(l+"");
            if (l <= 5){
                p.getPlayer().sendMessage("Game Ended in "+l+" secs");
            }
        });
        return l;
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
