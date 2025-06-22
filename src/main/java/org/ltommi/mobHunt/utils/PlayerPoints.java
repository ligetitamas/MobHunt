package org.ltommi.mobHunt.utils;

import org.bukkit.entity.Player;

public class PlayerPoints {
    private String player;
    private int points;
    public PlayerPoints(String player, int points){
        this.player = player;
        this.points = points;
    }
    public String GetPlayer(){
        return player;
    }
    public int GetPoints(){
        return points;
    }
}
