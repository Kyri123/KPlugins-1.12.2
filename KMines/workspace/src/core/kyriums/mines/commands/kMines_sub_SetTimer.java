package core.kyriums.mines.commands;

import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class kMines_sub_SetTimer  {

	//Colors
	private ChatColor DARK_PURPLE = ChatColor.DARK_PURPLE;
	private ChatColor RED = ChatColor.RED;
	private ChatColor DARK_RED = ChatColor.DARK_RED;
	private ChatColor GREEN = ChatColor.GREEN;
	private ChatColor GOLD = ChatColor.GOLD;

	//Vars
	private final Plugin plugin;
	private FileConfiguration lang;
	private CommandSender sender;
	@SuppressWarnings("unused")
	private String commandLabel;
	private String[] args;
	@SuppressWarnings("unused")
	private Command cmd;

	public kMines_sub_SetTimer(CommandSender sender, Command cmd, String commandLabel, String[] args, Plugin plugin, FileConfiguration lang) {
		this.sender = sender;
		this.cmd = cmd;
		this.commandLabel = commandLabel;
		this.args = args;
		this.plugin = plugin;
		this.lang = lang;
	}

	
    //Commands
    public boolean execute() {
    	if(!(sender instanceof Player)) {
    		sender.sendMessage(RED + lang.getString("Config.Plugin.Prefix") + lang.getString("Commands.defaultHelp.onlyPlayer"));
    		return false;
    	}
    	Player p = (Player) sender;
    	@SuppressWarnings("deprecation")
		ItemStack item = p.getItemInHand();
    	Material mat = item.getType();
    	if(args.length == 3) {
        	String name_region = args[1];
        	
        	if(!(isInteger(args[2]))) {
        		p.sendMessage(DARK_PURPLE + lang.getString("Config.Plugin.Prefix")+" " + DARK_RED + lang.getString("Commands.settimer.errorFormat_2"));
        		return false;
        	}
        	else {
        		if(plugin.getConfig().isConfigurationSection("KMinesRegion." + name_region)) {
	    	        FileConfiguration yaml = plugin.getConfig();
	    	        if(mat.isBlock() && mat.name() != "AIR") {
	    	        	if(plugin.getConfig().isConfigurationSection("KMinesRegion." + name_region + ".mats_destroy." + mat.name())) {
	    	    	        yaml.set("KMinesRegion." + name_region + ".mats_destroy." + mat.name() + ".timer", Integer.valueOf(args[2]));


	    	    	    	try {
	    	    	            yaml.save(plugin.getDataFolder() + "/config.yml");
		    	        		plugin.reloadConfig();
		    	        		p.sendMessage(DARK_PURPLE + lang.getString("Config.Plugin.Prefix")+" " + GOLD + name_region + GREEN + lang.getString("Commands.settimer.isTrue"));
	    		    	    	return true;
	    	    	        } catch (IOException e) {
	    	    	            e.printStackTrace();
	    		        		p.sendMessage(DARK_PURPLE + lang.getString("Config.Plugin.Prefix")+" " + DARK_RED + lang.getString("Commands.settimer.errorFormat_3"));
	    		    	    	return false;
	    	    	        }
	    	        	}
	    	        	else {
	    	        		p.sendMessage(DARK_PURPLE + lang.getString("Config.Plugin.Prefix")+" " + GREEN + lang.getString("Commands.settimer.itemnotfound"));
    		    	    	return false;
	    	        	}
	    	        }
	    	        else {
    	        		p.sendMessage(DARK_PURPLE + lang.getString("Config.Plugin.Prefix")+" " + GREEN + lang.getString("Commands.settimer.itemisnblock"));
		    	    	return false;
	    	        }
	    		}
	        	else {
	        		p.sendMessage(DARK_PURPLE + lang.getString("Config.Plugin.Prefix")+" " + GOLD + name_region  + DARK_RED + lang.getString("Commands.settimer.errorFormat_3"));
	        		return false;
	        	}
        	}
        	
    		
    	}
    	else {
    		p.sendMessage(DARK_PURPLE + lang.getString("Config.Plugin.Prefix")+" " + DARK_RED + lang.getString("Commands.settimer.errorFormat_1"));
    		return false;
    	}
    }


	private boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
	}
}
