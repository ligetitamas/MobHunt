package org.ltommi.mobHunt.events;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.ltommi.mobHunt.Main;

import java.util.HashMap;

public class onEntityDeath implements Listener {
    private Main main;
    private World world;
    private HashMap<EntityType, Integer> mobList;
    public onEntityDeath(Main main){
        this.main = main;
        this.world = Bukkit.getWorld(main.getConfig().getString("world"));
        ConfigurationSection mobListSection = main.getConfig().getConfigurationSection("mobList");
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
        Player killer = event.getEntity().getKiller();
        if (main.GetMobHuntManager().ContainsPlayer(killer)){

        }

    }
}
