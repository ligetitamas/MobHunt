package org.ltommi.mobHunt;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import org.ltommi.mobHunt.runnableTasks.TimeCheckTask;
import org.ltommi.mobHunt.commands.MobHuntCommand;

public final class Main extends JavaPlugin {

    private MobHuntManager mobHuntManager;
    private BukkitTask timeCheckTask;
    @Override
    public void onEnable() {
        saveDefaultConfig();

        mobHuntManager = new MobHuntManager(this);
        timeCheckTask  = new TimeCheckTask(this).runTaskLater(this, 20);

        this.getCommand("mobhunt").setExecutor(new MobHuntCommand(this));

        getLogger().info("MobHunt has been successfully loaded!");
    }

    @Override
    public void onDisable() {
        timeCheckTask.cancel();
        getLogger().info("MobHunt has been disabled.");
    }
    public MobHuntManager GetMobHuntManager(){
        return mobHuntManager;
    }
}
