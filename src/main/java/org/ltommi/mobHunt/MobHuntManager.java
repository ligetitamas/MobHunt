package org.ltommi.mobHunt;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class MobHuntManager {
    private Main main;
    private HashMap<Player, Float> playerList;
    private boolean isHuntStarted = false;

    public MobHuntManager(Main main){
        this.main = main;
    }
    public void StartMobHunt(){
        isHuntStarted = true;
        Bukkit.broadcastMessage("The MobHunt has started");
        Bukkit.getLogger().info("MobHunt has started");
    }
    public void EndMobHunt(){
        playerList.clear();
        isHuntStarted = false;
        Bukkit.broadcastMessage("The MobHunt has ended");
        Bukkit.getLogger().info("MobHunt has ended");
    }
    public void AddPlayer(Player player){
        if(isHuntStarted){
            if( !playerList.containsKey(player)){
                playerList.put(player,0f);
                player.sendMessage("You have successfully joined the mobhunt");
            }
            else{
                player.sendMessage("You have already joined the mobhunt");
            }
        }
        else{
           player.sendMessage("You cannot join the mobhunt while it's not active");
        }
    }
    public void RemovePlayer(Player player){
        playerList.remove(player);
        player.sendMessage("You have successfilly left the mobhunt");
    }
    public void AddPoint(Player player, float point){
        playerList.put(player, playerList.get(player) + point);
    }
    public boolean IsHuntStarted(){
        return isHuntStarted;
    }
    public boolean ContainsPlayer(Player player){
        return playerList.containsKey(player);
    }
}
