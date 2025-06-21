package org.ltommi.mobHunt;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class MobHuntManager {
    public enum MobHuntState {
        START,
        ENABLED,
        DISABLED
    }
    private Main main;
    private HashMap<Player, Float> playerList;
    private MobHuntState mobHuntState = MobHuntState.DISABLED;

    public MobHuntManager(Main main){
        this.main = main;
    }
    public void StartMobHunt(){
        Bukkit.getLogger().info("MobHunt has been started");
    }
    public void EndMobHunt(){
        Bukkit.getLogger().info("MobHunt has been ended");
    }
    public void AddPlayer(Player player){
        if(mobHuntState!= MobHuntState.DISABLED){
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
}
