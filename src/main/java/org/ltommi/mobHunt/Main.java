package org.ltommi.mobHunt;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private MobHuntManager mobHuntManager;
    @Override
    public void onEnable() {
        saveDefaultConfig();
        mobHuntManager = new MobHuntManager(this);
        getLogger().info("MobHunt has been successfully loaded!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
