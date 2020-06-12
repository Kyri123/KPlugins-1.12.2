package core.kyriums.mines.commands;

import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class kMines_sub_Remove  {

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

	public kMines_sub_Remove(CommandSender sender, Command cmd, String commandLabel, String[] args, Plugin plugin, FileConfiguration lang) {
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

    	if(args.length == 2) {
        	String name_region = args[1];
        	
    		if(plugin.getConfig().isConfigurationSection("KMinesRegion." + name_region)) {
    	        FileConfiguration yaml = plugin.getConfig();
    	        
    	        yaml.set("KMinesRegion." + name_region, null);
    	    	
    	    	try {
    	            yaml.save(plugin.getDataFolder() + "/config.yml");
    	        } catch (IOException e) {
    	            e.printStackTrace();
    	        }

        		plugin.reloadConfig();
        		p.sendMessage(DARK_PURPLE + lang.getString("Config.Plugin.Prefix")+" " + GOLD + name_region + GREEN + lang.getString("Commands.remove.isTrue"));
    	    	return true;
    		}
        	else {
        		p.sendMessage(DARK_PURPLE + lang.getString("Config.Plugin.Prefix")+" " + GOLD + name_region  + DARK_RED + lang.getString("Commands.remove.errorFormat_2"));
        		return false;
        	}
    	}
    	else {
    		p.sendMessage(DARK_PURPLE + lang.getString("Config.Plugin.Prefix")+" " + DARK_RED + lang.getString("Commands.remove.errorFormat_1"));
    		return false;
    	}
    		
    }
}
