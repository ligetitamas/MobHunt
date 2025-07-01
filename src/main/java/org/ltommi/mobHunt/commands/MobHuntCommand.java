package org.ltommi.mobHunt.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.ltommi.mobHunt.Main;
import org.ltommi.mobHunt.utils.TextFormatter;

public class MobHuntCommand implements CommandExecutor {
    private Main main;
    private TextFormatter textFormatter;
    public MobHuntCommand(Main main){
        this.main = main;
        this.textFormatter = main.GetTextFormatter();
    }
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender instanceof Player){
            Player player = (Player)commandSender;
            if(args.length == 1 && args[0].equals("join")){
                main.GetMobHuntManager().AddPlayer(player);
                return true;
            }
            if(args.length == 1 && args[0].equals("leave")){
                main.GetMobHuntManager().RemovePlayer(player);
                return true;
            }
            if(args.length == 1 && args[0].equals("top")){
                main.GetMobHuntManager().SendTopList(player);
                return true;
            }
            if( player.hasPermission("mobhunt.admin") && args.length == 1 && args[0].equals("reload")){
                main.GetMobHuntManager().Reload(player);
                return true;
            }
            player.sendMessage(textFormatter.GetMessage("mobHuntHelp"));
            return true;
        }
        Bukkit.getLogger().info(textFormatter.GetMessage("runAsConsoleError"));
        return false;
    }
}
