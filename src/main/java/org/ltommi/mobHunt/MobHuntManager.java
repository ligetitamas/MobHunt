package org.ltommi.mobHunt;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.ltommi.mobHunt.utils.PlayerPoints;
import org.ltommi.mobHunt.utils.TextFormatter;
import org.yaml.snakeyaml.util.ArrayStack;

import java.util.*;

public class MobHuntManager {
    private Main main;
    private TextFormatter textFormatter;
    private HashMap<String, Integer> playerList;
    private boolean isHuntStarted = false;

    public MobHuntManager(Main main){
        this.main = main;
        this.textFormatter = main.GetTextFormatter();
        this.playerList = new HashMap<>();
    }
    public void StartMobHunt(){
        isHuntStarted = true;
        Bukkit.broadcastMessage(textFormatter.GetMessage("mobHuntStart"));
        Bukkit.getLogger().info("MobHunt has started");
    }
    public void EndMobHunt(){
        GiveRewards();
        playerList.clear();
        isHuntStarted = false;
        Bukkit.broadcastMessage(textFormatter.GetMessage("mobHuntEnd"));
        Bukkit.getLogger().info("MobHunt has ended");
    }
    public void AddPlayer(Player player){
        if(isHuntStarted){
            if( !playerList.containsKey(player.getName())){
                playerList.put(player.getName(),0);
                player.sendMessage(textFormatter.GetMessage("mobHuntJoin"));
            }
            else{
                player.sendMessage(textFormatter.GetMessage("mobHuntAlreadyJoined"));
            }
        }
        else{
           player.sendMessage(textFormatter.GetMessage("mobHuntJoinNotActive"));
        }
    }
    public void RemovePlayer(Player player){
        playerList.remove(player.getName());
        player.sendMessage(textFormatter.GetMessage("mobHuntLeave"));
    }
    public void AddPoint(String player, int point){
        playerList.put(player, playerList.get(player) + point);
    }
    public int GetPlayerPoint(String player){
        return playerList.get(player);
    }
    private ArrayList<PlayerPoints> SortPlayers(){
        ArrayList<PlayerPoints> topPlayers = new ArrayList<>();
        for(String sortPlayer : playerList.keySet()){
            topPlayers.add(new PlayerPoints(sortPlayer, playerList.get(sortPlayer)));
        }
        Collections.sort(topPlayers, Comparator.comparing(PlayerPoints::GetPoints));
        return topPlayers;
    }
    public void SendTopList(Player player){
        if(!isHuntStarted){
            player.sendMessage(textFormatter.GetMessage("mobHuntTopNotActive"));
            return;
        }
        ArrayList<PlayerPoints> topPlayers = SortPlayers();
        Collections.reverse(topPlayers);
        ArrayList<String> messages = new ArrayList<>();
        messages.add(textFormatter.GetMessage("mobHuntTopHeader"));
        int topCount = 5;
        if(topPlayers.size()<topCount) topCount = topPlayers.size();

        for(int i =0; i< topCount; i++){
            String message = textFormatter.GetMessage("mobHuntTopList");
            message = message.replace("%player%", topPlayers.get(i).GetPlayer());
            message = message.replace("%points%", String.valueOf(topPlayers.get(i).GetPoints()));
            messages.add(message);

        }
        for(String line : messages){
            player.sendMessage(line);
        }
    }
    private void GiveRewards(){
        ConfigurationSection rewardSection = main.getConfig().getConfigurationSection("rewards");
        ArrayList<PlayerPoints> topPlayers = SortPlayers();
        if(topPlayers.size()>0){
            Player player = Bukkit.getPlayer(topPlayers.get(0).GetPlayer());
            if(playerList.get(player.getName()) >= main.getConfig().getInt("minimumPoints")) {
                player.sendMessage(textFormatter.GetMessage("mobHuntFirstPlace"));
                List<String> commands = rewardSection.getStringList("first");
                Reward(player.getName(), commands);
            }
            else{
                player.sendMessage(textFormatter.GetMessage("mobHuntNotEnoughPoints"));
            }
        }
        if(topPlayers.size()>1){
            Player player = Bukkit.getPlayer(topPlayers.get(1).GetPlayer());
            if(playerList.get(player.getName()) >= main.getConfig().getInt("minimumPoints")) {
                player.sendMessage(textFormatter.GetMessage("mobHuntSecondPlace"));
                List<String> commands = rewardSection.getStringList("second");
                Reward(player.getName(), commands);
            }
            else{
                player.sendMessage(textFormatter.GetMessage("mobHuntNotEnoughPoints"));
            }

        }
        if(topPlayers.size()>2){
            Player player = Bukkit.getPlayer(topPlayers.get(2).GetPlayer());
            if(playerList.get(player.getName()) >= main.getConfig().getInt("minimumPoints")) {
                player.sendMessage(textFormatter.GetMessage("mobHuntThirdPlace"));
                List<String> commands = rewardSection.getStringList("third");
                Reward(player.getName(), commands);
            }
            else{
                player.sendMessage(textFormatter.GetMessage("mobHuntNotEnoughPoints"));
            }

        }
    }
    private void Reward(String player, List<String> commands) {
        for (String command : commands) {
            String filledCommand = command.replace("%player%", player);
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), filledCommand);
        }
    }
    public void Reload(Player player){
        main.reloadConfig();
        player.sendMessage(textFormatter.GetMessage("mobHuntConfigReloaded"));
    }

    public boolean IsHuntStarted(){
        return isHuntStarted;
    }
    public boolean ContainsPlayer(String player){
        return playerList.containsKey(player);
    }
}
