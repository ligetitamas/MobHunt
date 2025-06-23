package org.ltommi.mobHunt.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class MHCommandTabCompleter implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        ArrayList<String> commands = new ArrayList<>();
        if(commandSender instanceof Player){
            if(args.length == 1){
                commands.add("join");
                commands.add("leave");
                commands.add("top");
                return commands;
            }
        }
        return null ;
    }
}
