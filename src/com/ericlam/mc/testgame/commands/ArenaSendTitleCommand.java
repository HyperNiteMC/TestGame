package com.ericlam.mc.testgame.commands;

import com.ericlam.mc.minigames.core.commands.ArenaCommandNode;
import com.ericlam.mc.minigames.core.exception.arena.create.ArenaNotExistException;
import com.ericlam.mc.minigames.core.manager.ArenaCreateManager;
import com.ericlam.mc.testgame.GameCreateArena;
import com.ericlam.mc.testgame.GameMessageConfig;
import com.hypernite.mc.hnmc.core.managers.YamlManager;
import com.hypernite.mc.hnmc.core.misc.commands.CommandNode;
import com.hypernite.mc.hnmc.core.misc.permission.Perm;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import java.util.List;

public class ArenaSendTitleCommand extends ArenaCommandNode {

    private final GameMessageConfig langConfig;

    public ArenaSendTitleCommand(YamlManager configManager, CommandNode parent) {
        super(parent, "settitle", Perm.OWNER, "設置是否發送 title", "<arena> <boolean>", "set-title");
        this.langConfig = configManager.getConfigAs(GameMessageConfig.class);
    }


    @Override
    protected boolean executeArenaOperation(@Nonnull Player player, @Nonnull List<String> list, @Nonnull ArenaCreateManager arenaCreateManager) throws ArenaNotExistException {
        final String arena = list.get(0);
        final boolean bool = Boolean.parseBoolean(list.get(1));
        GameCreateArena createArena = arenaCreateManager.getCreateArena(arena).castTo(GameCreateArena.class);
        createArena.setSendTitle(bool);
        createArena.setChanged(true);
        final String path = "arena.set-title";
        player.sendMessage(langConfig.get(path).replace("<arena>", arena).replace("<bool>", bool + ""));
        return true;
    }
}
