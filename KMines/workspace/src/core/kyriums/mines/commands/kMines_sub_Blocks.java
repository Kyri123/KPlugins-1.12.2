package core.kyriums.mines.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import core.kyriums.api.util.inter;

public class kMines_sub_Blocks {

	//Colors
	private ChatColor DARK_PURPLE = ChatColor.DARK_PURPLE;
	private ChatColor RED = ChatColor.RED;
	@SuppressWarnings("unused")
	private ChatColor DARK_RED = ChatColor.DARK_RED;
	@SuppressWarnings("unused")
	private ChatColor GREEN = ChatColor.GREEN;
	@SuppressWarnings("unused")
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

	public kMines_sub_Blocks(CommandSender sender, Command cmd, String commandLabel, String[] args, Plugin plugin, FileConfiguration lang) {
		this.sender = sender;
		this.cmd = cmd;
		this.commandLabel = commandLabel;
		this.args = args;
		this.plugin = plugin;
		this.lang = lang;
	}
	
	public boolean execute() {
		Player p = (Player) sender;
		
		if(args.length > 2) {
			
			inter API = new inter();
			
			String name = args[1];
			String arg = args[2];
			
			ItemStack offitem = p.getInventory().getItemInOffHand();
			ItemStack item = p.getInventory().getItemInMainHand();
			
			//p.sendMessage(offitem.getConfig().name());
			//p.sendMessage(item.getConfig().name());
			
	    	Material mat = item.getType();
	    	Material matoff = offitem.getType();
			FileConfiguration cfg = plugin.getConfig();
			FileConfiguration Yaml = plugin.getConfig();
			
			
			if(cfg.isConfigurationSection("KMinesRegion." + name)) {
				if(arg.equals("set") || arg.equals("remove")) {
					if(mat.isBlock() && mat.name() != "AIR") {
						// Hinzufügen
						if(arg.equals("set")) {
							if(matoff.isBlock() && mat.isBlock()) {
								int timer = 200;
								if(args.length > 3 && API.checkint(args[3])) {
									timer = Integer.valueOf(args[3]);
								}
								
								if(!cfg.isConfigurationSection("KMinesRegion." + name + ".mats_destroy." + mat.name())) Yaml.createSection("KMinesRegion." + name + ".mats_destroy." + mat.name()); // erstelle wenn noch nicht vorhanden
								Yaml.set("KMinesRegion." + name + ".mats_destroy." + mat.name() + ".replacer", matoff.name());
								Yaml.set("KMinesRegion." + name + ".mats_destroy." + mat.name() + ".timer", timer);
								Yaml.set("KMinesRegion." + name + ".mats_destroy." + mat.name() + ".meta", "SOON");
								
								plugin.saveConfig();
								plugin.reloadConfig();
								
								p.sendMessage(DARK_PURPLE + lang.getString("Config.Plugin.Prefix") + GREEN + lang.getString("Commands.blocks.add").replace("{mat}", mat.name()).replace("{region}", name));
								return true;
							}
							else {
								p.sendMessage(DARK_PURPLE + lang.getString("Config.Plugin.Prefix") + GREEN + lang.getString("Commands.blocks.errorFormat_4"));
								return false;
							}
						}
						
						// Entfernen
						if(arg.equals("remove")) {
							Yaml.set("KMinesRegion." + name + ".mats_destroy." + mat.name(), null);
							
							plugin.saveConfig();
							plugin.reloadConfig();
							
							p.sendMessage(DARK_PURPLE + lang.getString("Config.Plugin.Prefix") + GREEN + lang.getString("Commands.blocks.remove").replace("{mat}", mat.name()).replace("{region}", name));
							return true;
						}
					}
					else {
    	        		p.sendMessage(DARK_PURPLE + lang.getString("Config.Plugin.Prefix") + GREEN + lang.getString("Commands.blocks.itemisnblock"));
		    	    	return false;
					}
				}
				else {
					p.sendMessage(DARK_PURPLE + lang.getString("Config.Plugin.Prefix") + RED + lang.getString("Commands.blocks.errorFormat_3"));
					return false;
				}
			}
			else {
				p.sendMessage(DARK_PURPLE + lang.getString("Config.Plugin.Prefix") + RED + lang.getString("Commands.blocks.errorFormat_2"));
				return false;
			}
		}
		else {
			p.sendMessage(DARK_PURPLE + lang.getString("Config.Plugin.Prefix") + RED + lang.getString("Commands.blocks.errorFormat_1"));
			return false;
		}

		return false;
	}
}
