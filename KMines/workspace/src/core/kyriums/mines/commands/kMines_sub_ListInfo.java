package core.kyriums.mines.commands;

import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class kMines_sub_ListInfo {

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

	public kMines_sub_ListInfo(CommandSender sender, Command cmd, String commandLabel, String[] args, Plugin plugin, FileConfiguration lang) {
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
    		if(plugin.getConfig().isConfigurationSection("KMinesRegion." + args[1])) {
    			
    			
    			p.sendMessage(DARK_PURPLE + lang.getString("Config.Plugin.Prefix")+" " + GREEN + lang.getString("Commands.info.infoFormat_1").replace("{region}", args[1]));
    			p.sendMessage(GOLD + lang.getString("Commands.info.infoFormat_Line_1"));
    			
    			p.sendMessage(GOLD + "name: " + GREEN + plugin.getConfig().getString("KMinesRegion." + args[1] + ".name"));
    			p.sendMessage(GOLD + "location_1: " + GREEN + plugin.getConfig().getString("KMinesRegion." + args[1] + ".location_1"));
    			p.sendMessage(GOLD + "location_2: " + GREEN + plugin.getConfig().getString("KMinesRegion." + args[1] + ".location_2"));
    			p.sendMessage(GOLD + "world: " + GREEN + plugin.getConfig().getString("KMinesRegion." + args[1] + ".world"));
    			p.sendMessage(GOLD + "useCancelEvent: " + GREEN + plugin.getConfig().getString("KMinesRegion." + args[1] + ".useCancelEvent"));
    			p.sendMessage(GOLD + "mats_destroy: " + GREEN); 
    			

    			ConfigurationSection Section = plugin.getConfig().getConfigurationSection("KMinesRegion." + args[1] + ".mats_destroy");
    			Map<String, Object> map = Section.getValues(true);
    			String end = "";
    			int i = 0;
    			
    			if(!map.isEmpty()) {
    				for(Entry<String, Object> entry : map.entrySet()) {

    					//lese den String aus
    					if(!entry.getKey().contains(".")) {
    						String replacer = plugin.getConfig().getString("KMinesRegion." + args[1] + ".mats_destroy." + entry.getKey() + ".replacer");
    						String timer = plugin.getConfig().getString("KMinesRegion." + args[1] + ".mats_destroy." + entry.getKey() + ".timer");
    						p.sendMessage(GREEN + "   [" + GOLD + i + GREEN + "] " + RED + entry.getKey() + GREEN + " [" + replacer + " | " + timer + " Ticks]");
    						end = end + " " + entry.getKey();
    						i++;
    					}
    				}
    			}
    	    	return true;
    		}
    		else {
        		p.sendMessage(DARK_PURPLE + lang.getString("Config.Plugin.Prefix")+" " + DARK_RED + lang.getString("Commands.info.errorFormat_2"));
        		return false;
    		}
    	}
    	else {
    		p.sendMessage(DARK_PURPLE + lang.getString("Config.Plugin.Prefix")+" " + DARK_RED + lang.getString("Commands.info.errorFormat_1"));
    		return false;
    	}

    }
}
