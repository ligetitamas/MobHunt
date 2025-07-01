package org.ltommi.mobHunt.utils;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.ltommi.mobHunt.Main;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextFormatter {
    private Main main;
    private YamlConfiguration messages;
    public TextFormatter(Main main){
        this.main = main;
        LoadMessages();
    }
    public void LoadMessages(){
        File messagesFile = new File(main.getDataFolder(), "messages.yml");
        if (!messagesFile.exists()) {
            main.saveResource("messages.yml", false);
        }
        messages = YamlConfiguration.loadConfiguration(messagesFile);
    }
    public String Color(String message){
        String coloredMessage=message;
        coloredMessage = ChatColor.translateAlternateColorCodes('&',coloredMessage);
        coloredMessage = TranslateHexColorCodes("&#","",coloredMessage);
        return coloredMessage;
    }
    public String GetMessage(String messageID){
        String message = messages.getString(messageID);
        message = Color(message);
        return message;
    }
    private static String TranslateHexColorCodes(String startTag, String endTag, String message)
    {
        final Pattern hexPattern = Pattern.compile(startTag + "([A-Fa-f0-9]{6})" + endTag);
        Matcher matcher = hexPattern.matcher(message);
        StringBuffer buffer = new StringBuffer(message.length() + 4 * 8);
        while (matcher.find())
        {
            String group = matcher.group(1);
            matcher.appendReplacement(buffer, ChatColor.COLOR_CHAR + "x"
                    + ChatColor.COLOR_CHAR + group.charAt(0) + ChatColor.COLOR_CHAR + group.charAt(1)
                    + ChatColor.COLOR_CHAR + group.charAt(2) + ChatColor.COLOR_CHAR + group.charAt(3)
                    + ChatColor.COLOR_CHAR + group.charAt(4) + ChatColor.COLOR_CHAR + group.charAt(5)
            );
        }
        return matcher.appendTail(buffer).toString();
    }
}

