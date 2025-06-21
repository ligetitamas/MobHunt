package org.ltommi.mobHunt.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.ltommi.mobHunt.Main;

public class MobHuntCommand implements CommandExecutor {
    private Main main;
    public MobHuntCommand(Main main){
        this.main = main;
    }
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player){
            Player player = (Player)commandSender;

        }
        Bukkit.getLogger().info("Error: You cannot run this command from the consol.");
        return false;
    }
}
