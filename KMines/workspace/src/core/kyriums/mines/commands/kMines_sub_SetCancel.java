package core.kyriums.mines.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import core.kyriums.api.util.yml;

public class kMines_sub_SetCancel {

	//Colors
	private ChatColor DARK_PURPLE = ChatColor.DARK_PURPLE;
	private ChatColor RED = ChatColor.RED;
	private ChatColor DARK_RED = ChatColor.DARK_RED;
	private ChatColor GREEN = ChatColor.GREEN;
	//Vars
	private final Plugin plugin;
	private FileConfiguration lang;
	private CommandSender sender;
	@SuppressWarnings("unused")
	private String commandLabel;
	private String[] args;
	@SuppressWarnings("unused")
	private Command cmd;

	public kMines_sub_SetCancel(CommandSender sender, Command cmd, String commandLabel, String[] args, Plugin plugin, FileConfiguration lang) {
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
    	String name = args[1];
    	String boo = args[2];
    	if(args.length == 1 || !(plugin.getConfig().isConfigurationSection("KMinesRegion." + name))) {
    		p.sendMessage(DARK_PURPLE + lang.getString("Config.Plugin.Prefix")+" " + DARK_RED + lang.getString("Commands.setcancel.errorFormat_1"));
    		return false;
    	}
    	else if((args.length == 3 && boo.equals("true")) || (args.length == 3 && boo.equals("false"))) {

    		boolean bool = Boolean.parseBoolean(boo);
    		yml config = new yml("config.yml", plugin);
	        FileConfiguration yaml = config.load();
	        
	        yaml.set("KMinesRegion." + name + ".useCancelEvent", bool);
	    	
	    	config.save();
	    	if(config.reload()) {
	    		p.sendMessage(DARK_PURPLE + lang.getString("Config.Plugin.Prefix")+" " + GREEN + lang.getString("Commands.setcancel.isTrue").replace("{result}", boo));
		    	return true;
	    	}
	    	else {
	    		return false;
	    	}
    	}
    	else {
    		p.sendMessage(DARK_PURPLE + lang.getString("Config.Plugin.Prefix")+" " + DARK_RED + lang.getString("Commands.setcancel.errorFormat_2"));
    		return false;
    	}
    		
    }
}
