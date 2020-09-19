package com.ericlam.mc.testgame;

import com.hypernite.mc.hnmc.core.config.yaml.MessageConfiguration;
import com.hypernite.mc.hnmc.core.config.yaml.Prefix;
import com.hypernite.mc.hnmc.core.config.yaml.Resource;

@Prefix(path = "prefix")
@Resource(locate = "lang.yml")
public class GameMessageConfig extends MessageConfiguration {
}
