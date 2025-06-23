package org.ltommi.mobHunt.events;

import de.tr7zw.nbtapi.NBT;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.SpawnerSpawnEvent;

public class onSpawnerSpawn implements Listener {
    @EventHandler
    public void onSpawnerSpawn(SpawnerSpawnEvent event){
        NBT.modifyPersistentData(event.getEntity(), nbt -> {
            nbt.setBoolean("fromSpawner", true);
        });
    }
}
