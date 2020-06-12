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

public class kMines_sub_List {

	//Colors
	private ChatColor DARK_PURPLE = ChatColor.DARK_PURPLE;
	private ChatColor RED = ChatColor.RED;
	private ChatColor GREEN = ChatColor.GREEN;
	private ChatColor GOLD = ChatColor.GOLD;

	//Vars
	private final Plugin plugin;
	private FileConfiguration lang;
	private CommandSender sender;
	@SuppressWarnings("unused")
	private String commandLabel;
	@SuppressWarnings("unused")
	private String[] args;
	@SuppressWarnings("unused")
	private Command cmd;

	public kMines_sub_List(CommandSender sender, Command cmd, String commandLabel, String[] args, Plugin plugin, FileConfiguration lang) {
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

		ConfigurationSection Section = plugin.getConfig().getConfigurationSection("KMinesRegion");
		Map<String, Object> map = Section.getValues(true);

		if(!map.isEmpty()) {
			
    		p.sendMessage(DARK_PURPLE + lang.getString("Config.Plugin.Prefix")+" " + GREEN +  lang.getString("Commands.list.infoFormat_2"));
    		p.sendMessage(DARK_PURPLE + lang.getString("Config.Plugin.Prefix")+" " + GREEN +  lang.getString("Commands.list.infoFormat_3"));
    		
			int zaehler = 0;
			for(Entry<String, Object> entry : map.entrySet()) {

				//lese den String aus
				if(!entry.getKey().contains(".")) {
					p.sendMessage(GREEN + "[" + GOLD + zaehler + GREEN + "]   " + GOLD + entry.getKey());
					zaehler++;
				}
			}
		}
		else {
    		p.sendMessage(DARK_PURPLE + lang.getString("Config.Plugin.Prefix")+" " + GREEN +  lang.getString("Commands.list.infoFormat_2"));
    		p.sendMessage(DARK_PURPLE + lang.getString("Config.Plugin.Prefix")+" " + GOLD +  lang.getString("Commands.list.infoFormat_1"));
		}
    	
    	return true;

    }
}
