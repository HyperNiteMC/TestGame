package com.ericlam.mc.testgame;

import com.ericlam.mc.minigames.core.arena.Arena;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameArena implements Arena {

    private Map<String, List<Location>> locations = new HashMap<>();

    public GameArena(List<Location> locations) {
        this.locations.put("test", locations);
    }

    @Override
    public String getAuthor() {
        return "test";
    }

    @Override
    public World getWorld() {
        return Bukkit.getWorlds().get(0);
    }

    @Override
    public String getArenaName() {
        return "TestGame";
    }

    @Override
    public String getDisplayName() {
        return ChatColor.GOLD +  "TestGame's Arena";
    }

    @Override
    public Map<String, List<Location>> getLocationsMap() {
        return locations;
    }

    @Override
    public List<Location> getWarp(String s) {
        return this.locations.get(s);
    }

    @Override
    public String[] getInfo() {
        return new String[]{"arena: "+getArenaName(), "author: "+getAuthor(), "display: "+getArenaName(), "world: "+getWorld().getName()};
    }
}
