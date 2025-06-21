package org.ltommi.mobHunt;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();

        getLogger().info("MobHunt has been successfully loaded!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
