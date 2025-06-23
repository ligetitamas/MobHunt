package org.ltommi.mobHunt.events;

import de.tr7zw.nbtapi.NBT;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.ltommi.mobHunt.Main;
import org.ltommi.mobHunt.utils.TextFormatter;

import java.util.HashMap;

public class onEntityDeath implements Listener {
    private Main main;
    private TextFormatter textFormatter;
    private World world;
    private HashMap<EntityType, Integer> mobList;
    public onEntityDeath(Main main){
        this.main = main;
        this.textFormatter = main.GetTextFormatter();
        this.world = Bukkit.getWorld(main.getConfig().getString("world"));
        ConfigurationSection mobListSection = main.getConfig().getConfigurationSection("mobList");
        mobList = new HashMap<>();
        for(String mob : mobListSection.getKeys(false)){
            mobList.put(EntityType.valueOf(mob),mobListSection.getInt(mob));
        }
    }
    @EventHandler
    public void onEntityDeath(EntityDeathEvent event){
        if(!main.GetMobHuntManager().IsHuntStarted()){
            return;
        }
        if(event.getEntity().getWorld() != world){
            return;
        }
        if(event.getEntity().getKiller() == null){
            return;
        }
        boolean fromSpawner = NBT.getPersistentData(event.getEntity(), nbt -> (boolean) nbt.getBoolean("fromSpawner"));
        Bukkit.getLogger().info(String.valueOf(fromSpawner));
        if(fromSpawner){
            return;
        }
        Player killer = event.getEntity().getKiller();
        if (main.GetMobHuntManager().ContainsPlayer(killer.getName())){
            if(mobList.containsKey(event.getEntityType())){
                main.GetMobHuntManager().AddPoint(killer.getName(), mobList.get(event.getEntityType()));
                String message = textFormatter.GetMessage("actionBarPoints");
                message = message.replace("%pointsGained%", String.valueOf(mobList.get(event.getEntityType())));
                message = message.replace("%totalPoints%", String.valueOf(main.GetMobHuntManager().GetPlayerPoint(killer.getName())));
                killer.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacy(message));
                killer.playSound(killer.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP,1,1);
            }
        }

    }
}
