package org.ltommi.mobHunt.runnableTasks;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;
import org.ltommi.mobHunt.Main;

public class TimeCheckTask extends BukkitRunnable {
    private Main main;
    private World world;
    public TimeCheckTask(Main main){
        this.main = main;
        this.world = Bukkit.getWorld(main.getConfig().getString("world"));
    }
    @Override
    public void run() {
        //night is between 13000 and 26000
        if (world.getTime()> 13000){
            if (!main.GetMobHuntManager().IsHuntStarted()) {
                main.GetMobHuntManager().StartMobHunt();
            }
        }
        else{
            if (main.GetMobHuntManager().IsHuntStarted()){
                main.GetMobHuntManager().EndMobHunt();
            }
        }
    }
}
