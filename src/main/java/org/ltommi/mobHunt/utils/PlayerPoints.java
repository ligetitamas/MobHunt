package org.ltommi.mobHunt.utils;

import org.bukkit.entity.Player;

public class PlayerPoints {
    private Player player;
    private int points;
    public PlayerPoints(Player player, int points){
        this.player = player;
        this.points = points;
    }
    public Player GetPlayer(){
        return player;
    }
    public int GetPoints(){
        return points;
    }
}
