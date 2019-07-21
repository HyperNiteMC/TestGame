package com.ericlam.mc.testgame;

import com.ericlam.mc.minigames.core.arena.Arena;
import com.ericlam.mc.minigames.core.arena.ArenaMechanic;
import com.ericlam.mc.minigames.core.arena.CreateArena;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;

public class TestArenaMechanic implements ArenaMechanic {


    @Override
    public CreateArena loadCreateArena(FileConfiguration fileConfiguration, Arena arena) {
        boolean sendTitle = fileConfiguration.getBoolean("send-title");
        return new GameCreateArena(arena.getLocationsMap(), arena.getAuthor(), arena.getWorld(), arena.getArenaName(), arena.getDisplayName(), sendTitle);
    }


    @Override
    public CreateArena createArena(@Nonnull String s, @Nonnull Player player) {
        World world = player.getWorld();
        String author = player.getDisplayName();
        return new GameCreateArena(author, world, s, s);
    }

    @Override
    public void saveExtraArenaSetting(FileConfiguration fileConfiguration, Arena arena) {
        fileConfiguration.set("send-title", ((GameCreateArena)arena).isSendTitle());
    }
}
