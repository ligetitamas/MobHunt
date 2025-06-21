package org.ltommi.mobHunt;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class MobHuntManager {
    private Main main;
    private ArrayList<Player> playerList;
    public MobHuntManager(Main main){
        this.main = main;
        this.playerList = new ArrayList<>();
    }
    public void StartMobHunt(){
        Bukkit.getLogger().info("MobHunt has been started");
    }
    public void EndMobHunt(){
        Bukkit.getLogger().info("MobHunt has been ended");
    }
    public void AddPlayer(Player player){
        if(!playerList.contains(player)){
            playerList.add(player);
        }
    }
    public void RemovePlayer(Player player){
        playerList.remove(player);
    }
}
