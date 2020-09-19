package com.ericlam.mc.testgame;

import com.hypernite.mc.hnmc.core.config.yaml.Configuration;
import com.hypernite.mc.hnmc.core.config.yaml.Resource;
import org.bukkit.Location;

@Resource(locate = "config.yml")
public class GameConfig extends Configuration {

    public int maxArenas;
    public Location lobby;

}
