package com.ericlam.mc.testgame.states;

import com.ericlam.mc.minigames.core.game.InGameState;

public class Game3State implements InGameState {
    @Override
    public String getStateName() {
        return "game-three";
    }

    @Override
    public String getMotd() {
        return "three";
    }
}
