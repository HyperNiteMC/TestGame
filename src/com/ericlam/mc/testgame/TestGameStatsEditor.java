package com.ericlam.mc.testgame;

import com.ericlam.mc.minigames.core.gamestats.GameStatsEditor;

public class TestGameStatsEditor implements GameStatsEditor {

    private int kills;
    private int deaths;
    private int played;
    private int wins;

    public TestGameStatsEditor(int kills, int deaths, int played, int wins) {
        this.kills = kills;
        this.deaths = deaths;
        this.played = played;
        this.wins = wins;
    }

    public TestGameStatsEditor(){
        this(0,0,0,0);
    }

    @Override
    public void setKills(int i) {
        this.kills = i;
    }

    @Override
    public void setDeaths(int i) {
        this.deaths = i;
    }

    @Override
    public void setPlayed(int i) {
        this.played = i;
    }

    @Override
    public void setWins(int i) {
        this.wins = i;
    }

    @Override
    public int getPlayed() {
        return played;
    }

    @Override
    public int getKills() {
        return kills;
    }

    @Override
    public int getDeaths() {
        return deaths;
    }

    @Override
    public int getWins() {
        return wins;
    }

    @Override
    public double getScores() {
        return kills *10 - deaths *4;
    }

    @Override
    public String[] getInfo() {
        return new String[]{
                "Kills: "+kills,
                "Deaths: "+deaths,
                "Played: "+played,
                "Wins: "+wins,
                "Scores: "+getScores()
        };
    }
}
