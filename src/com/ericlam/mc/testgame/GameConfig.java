package com.ericlam.mc.testgame;

import com.hypernite.mc.hnmc.core.config.yaml.MessageConfiguration;
import com.hypernite.mc.hnmc.core.config.yaml.Prefix;
import com.hypernite.mc.hnmc.core.config.yaml.Resource;
import org.bukkit.Location;

@Prefix(path = "prefix")
@Resource(locate = "config.yml")
public class GameConfig extends MessageConfiguration {

    public int maxArenas;
    public Location lobby;

}
