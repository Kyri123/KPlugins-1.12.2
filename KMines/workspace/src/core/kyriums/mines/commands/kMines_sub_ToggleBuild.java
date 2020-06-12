package core.kyriums.mines.commands;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class kMines_sub_ToggleBuild  {

	//Colors
	private ChatColor DARK_PURPLE = ChatColor.DARK_PURPLE;
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

	public kMines_sub_ToggleBuild(CommandSender sender, Command cmd, String commandLabel, String[] args, Plugin plugin, FileConfiguration lang) {
		this.sender = sender;
		this.cmd = cmd;
		this.commandLabel = commandLabel;
		this.args = args;
		this.plugin = plugin;
		this.lang = lang;
	}

	
    //Commands
    public boolean execute() {
    	Player p = (Player) sender;
		File Yml = new File(this.plugin.getDataFolder()+ "\\" + p.getDisplayName() + ".yml");
		FileConfiguration ymlcfg = YamlConfiguration.loadConfiguration(Yml);
		
		//set default YML data
		if(!ymlcfg.isConfigurationSection("opt")) ymlcfg.createSection("opt");
		if(!ymlcfg.isSet("opt.canbuild")) ymlcfg.set("opt.canbuild", false);
		
		boolean bool = (!ymlcfg.getBoolean("opt.canbuild")) ? true : false;
		String is = (!ymlcfg.getBoolean("opt.canbuild")) ? "isTrue" : "isFalse";
        p.sendMessage(DARK_PURPLE + lang.getString("Config.Plugin.Prefix")+" "  + GREEN + lang.getString("Commands.togglebuild." + is));
		
		ymlcfg.set("opt.canbuild", bool);

		try {
			ymlcfg.save(Yml);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.plugin.saveConfig();
		this.plugin.reloadConfig();
		
    	return true;
    }
}
