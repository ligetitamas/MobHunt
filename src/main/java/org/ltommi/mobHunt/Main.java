package org.ltommi.mobHunt;

import com.tchristofferson.configupdater.ConfigUpdater;
import de.tr7zw.nbtapi.NBT;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import org.ltommi.mobHunt.commands.MHCommandTabCompleter;
import org.ltommi.mobHunt.events.onEntityDeath;
import org.ltommi.mobHunt.events.onSpawnerSpawn;
import org.ltommi.mobHunt.runnableTasks.TimeCheckTask;
import org.ltommi.mobHunt.commands.MobHuntCommand;
import org.ltommi.mobHunt.utils.TextFormatter;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public final class Main extends JavaPlugin {

    private MobHuntManager mobHuntManager;
    private TextFormatter textFormatter;
    private BukkitTask timeCheckTask;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        UpdateConfig();
        textFormatter = new TextFormatter(this);
        mobHuntManager = new MobHuntManager(this);
        timeCheckTask  = new TimeCheckTask(this).runTaskTimer(this,0, 20);

        this.getCommand("mobhunt").setExecutor(new MobHuntCommand(this));
        this.getCommand("mobhunt").setTabCompleter(new MHCommandTabCompleter());

        getServer().getPluginManager().registerEvents(new onSpawnerSpawn(), this);
        getServer().getPluginManager().registerEvents(new onEntityDeath(this), this);

        getLogger().info("MobHunt has been successfully loaded!");
    }

    @Override
    public void onDisable() {
        timeCheckTask.cancel();
        getLogger().info("MobHunt has been disabled.");
    }
    private void UpdateConfig(){
        File configFile = new File(getDataFolder(), "config.yml");

        try {
            ConfigUpdater.update(this, "config.yml", configFile);
        } catch (IOException e) {
            getLogger().info(String.valueOf(e));
        }
        reloadConfig();
    }
    public TextFormatter GetTextFormatter(){
        return textFormatter;
    }
    public MobHuntManager GetMobHuntManager(){
        return mobHuntManager;
    }
}
