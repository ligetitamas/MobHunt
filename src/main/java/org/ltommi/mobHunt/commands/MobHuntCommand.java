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
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender instanceof Player){
            Player player = (Player)commandSender;
            if(args.length == 1 && args[0].equals("join")){
                main.GetMobHuntManager().AddPlayer(player);
            }
            if(args.length == 1 && args[0].equals("leave")){
                main.GetMobHuntManager().RemovePlayer(player);
            }
        }
        Bukkit.getLogger().info("Error: You cannot run this command from the consol.");
        return false;
    }
}
