package core.kyriums.mines.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class kMines_sub_ConfigReload  {

	//Colors
	private ChatColor DARK_PURPLE = ChatColor.DARK_PURPLE;
	private ChatColor RED = ChatColor.RED;
	private ChatColor GREEN = ChatColor.GREEN;

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

	public kMines_sub_ConfigReload(CommandSender sender, Command cmd, String commandLabel, String[] args, Plugin plugin, FileConfiguration lang) {
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
    	
		plugin.reloadConfig();
		p.sendMessage(DARK_PURPLE + lang.getString("Config.Plugin.Prefix")+" " + GREEN + lang.getString("Commands.cfgreload.isTrue"));
    	return true;
    		
    }
}
