package org.ltommi.mobHunt;

import org.bukkit.plugin.java.JavaPlugin;
import org.ltommi.mobHunt.commands.MobHuntCommand;

public final class Main extends JavaPlugin {

    private MobHuntManager mobHuntManager;
    @Override
    public void onEnable() {
        saveDefaultConfig();

        mobHuntManager = new MobHuntManager(this);

        this.getCommand("mobhunt").setExecutor(new MobHuntCommand(this));

        getLogger().info("MobHunt has been successfully loaded!");
    }

    @Override
    public void onDisable() {
    }
    public MobHuntManager GetMobHuntManager(){
        return mobHuntManager;
    }
}
