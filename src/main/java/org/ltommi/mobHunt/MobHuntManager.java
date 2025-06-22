package org.ltommi.mobHunt;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.ltommi.mobHunt.utils.PlayerPoints;
import org.yaml.snakeyaml.util.ArrayStack;

import java.util.*;

public class MobHuntManager {
    private Main main;
    private HashMap<Player, Integer> playerList;
    private boolean isHuntStarted = false;

    public MobHuntManager(Main main){
        this.main = main;
        playerList = new HashMap<>();
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
                playerList.put(player,0);
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
    public void AddPoint(Player player, int point){
        playerList.put(player, playerList.get(player) + point);
    }
    private ArrayList<PlayerPoints> SortPlayers(){
        ArrayList<PlayerPoints> topPlayers = new ArrayList<>();
        for(Player sortPlayer : playerList.keySet()){
            topPlayers.add(new PlayerPoints(sortPlayer, playerList.get(sortPlayer)));
        }
        Collections.sort(topPlayers, Comparator.comparing(PlayerPoints::GetPoints));
        return topPlayers;
    }
    public void SendTopList(Player player){
        ArrayList<PlayerPoints> topPlayers = SortPlayers();
        ArrayList<String> messages = new ArrayList<>();
        messages.add("The top 5 players are:");
        int topCount = 5;
        if(topPlayers.size()<topCount) topCount = topPlayers.size();

        for(int i =0; i< topCount; i++){
            messages.add(topPlayers.get(i).GetPlayer().getName()+" - "+topPlayers.get(i).GetPoints());
        }
        for(String line : messages){
            player.sendMessage(line);
        }
    }


    public boolean IsHuntStarted(){
        return isHuntStarted;
    }
    public boolean ContainsPlayer(Player player){
        return playerList.containsKey(player);
    }
}
