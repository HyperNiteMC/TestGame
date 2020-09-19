package com.ericlam.mc.testgame.commands;

import com.hypernite.mc.hnmc.core.managers.YamlManager;
import com.hypernite.mc.hnmc.core.misc.commands.DefaultCommand;
import com.hypernite.mc.hnmc.core.misc.permission.Perm;

public class GameDefaultCommand extends DefaultCommand {

    public GameDefaultCommand(YamlManager configManager) {
        super(null, "testgame", Perm.OWNER, "Test Game 測試指令", "tg", "testg");
        this.addSub(new ArenaSendTitleCommand(configManager, this));
    }
}
