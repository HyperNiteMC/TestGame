package com.ericlam.mc.testgame;

import com.ericlam.mc.minigames.core.arena.CreateArena;
import org.apache.commons.lang.ArrayUtils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GameCreateArena implements CreateArena {

    private boolean changed;
    private Map<String, List<Location>> locations;
    private String author;
    private World world;
    private String arenaName;
    private String displayName;
    private final List<String> description;

    //Custom setting
    private boolean sendTitle;

    // load
    public GameCreateArena(Map<String, List<Location>> locations, String author, World world, String arenaName, String displayName, boolean sendTitle, List<String> description) {
        this.locations = locations;
        this.author = author;
        this.world = world;
        this.arenaName = arenaName;
        this.displayName = displayName;
        this.sendTitle = sendTitle;
        this.description = description;
    }

    // new
    public GameCreateArena(String author, World world, String arenaName, String displayName) {
        this(new LinkedHashMap<>(), author, world, arenaName, displayName, false, new ArrayList<>());
    }

    public boolean isSendTitle() {
        return sendTitle;
    }

    public void setSendTitle(boolean sendTitle) {
        this.sendTitle = sendTitle;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public World getWorld() {
        return world;
    }

    @Override
    public String getArenaName() {
        return arenaName;
    }

    @Override
    public String getDisplayName() {
        return ChatColor.translateAlternateColorCodes('&', displayName);
    }

    @Override
    public Map<String, List<Location>> getLocationsMap() {
        return locations;
    }

    @Override
    public List<String> getDescription() {
        return description;
    }

    @Override
    public String[] getInfo() {
        String[] info = new String[]{
                "arena: " + getArenaName(),
                "author: " + getAuthor(),
                "display: " + getDisplayName(),
                "world: " + getWorld().getName(),
                "sendTitle: " + isSendTitle(),
                "warps: " + locations.keySet().stream().map(l -> l.concat("(" + locations.get(l).size() + ")")).collect(Collectors.joining(", ")),
        };
        String[] story = this.getDescription().toArray(String[]::new);
        return (String[]) ArrayUtils.addAll(info, story);
    }

    @Override
    public void setAuthor(String s) {
        this.author = s;
    }

    @Override
    public void setWorld(World world) {
        this.world = world;
    }

    @Override
    public void setArenaName(String s) {
        this.arenaName = s;
    }

    @Override
    public void setDisplayName(String s) {
        this.displayName = s;
    }

    @Override
    public void setLocationMap(Map<String, List<Location>> map) {
        this.locations = map;
    }

    @Override
    public boolean isChanged() {
        return changed;
    }

    @Override
    public void setChanged(Boolean aBoolean) {
        this.changed = aBoolean;
    }

    @Override
    public boolean isSetupCompleted() {
        boolean warps = this.locations.size() >= 3;
        boolean locations = true;
        for (String s : this.locations.keySet()) {
            locations = locations && this.locations.get(s).size() > 0;
        }
        boolean authorName = getAuthor() != null;
        boolean displayName = getDisplayName() != null;
        boolean world = getWorld() != null;
        return warps && locations && authorName && displayName && world;
    }
}
