package org.ltommi.mobHunt;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import org.ltommi.mobHunt.commands.MHCommandTabCompleter;
import org.ltommi.mobHunt.events.onEntityDeath;
import org.ltommi.mobHunt.runnableTasks.TimeCheckTask;
import org.ltommi.mobHunt.commands.MobHuntCommand;
import org.ltommi.mobHunt.utils.TextFormatter;

import java.io.File;

public final class Main extends JavaPlugin {

    private MobHuntManager mobHuntManager;
    private TextFormatter textFormatter;
    private BukkitTask timeCheckTask;
    private YamlConfiguration messages;
    @Override
    public void onEnable() {
        saveDefaultConfig();
        LoadMessages();

        textFormatter = new TextFormatter(messages);
        mobHuntManager = new MobHuntManager(this);

        timeCheckTask  = new TimeCheckTask(this).runTaskTimer(this,0, 20);

        this.getCommand("mobhunt").setExecutor(new MobHuntCommand(this));
        this.getCommand("mobhunt").setTabCompleter(new MHCommandTabCompleter());

        getServer().getPluginManager().registerEvents(new onEntityDeath(this), this);

        getLogger().info("MobHunt has been successfully loaded!");
    }

    @Override
    public void onDisable() {
        timeCheckTask.cancel();
        getLogger().info("MobHunt has been disabled.");
    }
    private void LoadMessages(){
        File messagesFile = new File(getDataFolder(), "messages.yml");
        if (!messagesFile.exists()) {
            saveResource("messages.yml", false);
        }
        messages = YamlConfiguration.loadConfiguration(messagesFile);
    }
    public YamlConfiguration GetMessages(){
        return messages;
    }
    public TextFormatter GetTextFormatter(){
        return textFormatter;
    }
    public MobHuntManager GetMobHuntManager(){
        return mobHuntManager;
    }
}
