package core.kyriums.mines.commands;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import core.kyriums.mines.config.addMines;

public class kMines_sub_RegionAdd {

	//Colors
	private ChatColor DARK_PURPLE = ChatColor.DARK_PURPLE;
	private ChatColor RED = ChatColor.RED;
	private ChatColor DARK_RED = ChatColor.DARK_RED;
	private ChatColor GREEN = ChatColor.GREEN;
	private ChatColor GOLD = ChatColor.GOLD;
	private ChatColor BOLD = ChatColor.BOLD;
	
	//Vars
	private final Plugin plugin;
	private FileConfiguration lang;
	private CommandSender sender;
	@SuppressWarnings("unused")
	private String commandLabel;
	private String[] args;
	@SuppressWarnings("unused")
	private Command cmd;

	public kMines_sub_RegionAdd(CommandSender sender, Command cmd, String commandLabel, String[] args, Plugin plugin, FileConfiguration lang) {
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

    	// Lade wenn /kmines set <name> <x1/y1/z1> <x2/y2/z2>
    	if(args[0].equals("regionadd")) {
			if(args.length > 1) {
				
				File Yml = new File(this.plugin.getDataFolder()+ "\\" + p.getDisplayName() + ".yml");
				FileConfiguration ymlcfg = YamlConfiguration.loadConfiguration(Yml);
				
        		String name = args[1];
        		
        		if(ymlcfg.getString("tool.loc_2") != "" && ymlcfg.getString("tool.loc_1") != "") {
            		
            		String loc_1 = ymlcfg.getString("tool.loc_1");
            		String loc_2 = ymlcfg.getString("tool.loc_2");
            		String world = ymlcfg.getString("tool.world");
    				
            		//Coordinaten eintragen
                    addMines addMine = new addMines(loc_1, loc_2, world, name, plugin, plugin.getConfig());
                    addMine.save();
                    p.sendMessage(DARK_PURPLE + lang.getString("Config.Plugin.Prefix")+" "  + GOLD + "" + name + "" + GREEN + lang.getString("Commands.set.isTrue"));
            		plugin.reloadConfig();
            		
            		return true;
        		}
        		else {
    				p.sendMessage(DARK_PURPLE + "" + BOLD + lang.getString("Config.Plugin.Prefix")+" " + DARK_RED + lang.getString("Commands.set.errorFormat_4"));
        		}
        	}
        	else {
				p.sendMessage(DARK_PURPLE + "" + BOLD + lang.getString("Config.Plugin.Prefix")+" " + DARK_RED + lang.getString("Commands.set.errorFormat_3"));
        	}
    		return false;
    	}
		return false;
	}
	
}
