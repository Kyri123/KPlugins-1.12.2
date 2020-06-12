package core.kyriums.mines.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class KMines_main implements CommandExecutor {

	//Colors
	private ChatColor DARK_PURPLE = ChatColor.DARK_PURPLE;
	private ChatColor RED = ChatColor.RED;
	private ChatColor GREEN = ChatColor.GREEN;
	private ChatColor WHITE = ChatColor.WHITE;
	private ChatColor GOLD = ChatColor.GOLD;
	private ChatColor BOLD = ChatColor.BOLD;
	
	//Vars
	private final Plugin plugin;
	private FileConfiguration lang;

	public KMines_main(Plugin plugin, FileConfiguration lang) {
		this.plugin = plugin;
		this.lang = lang;
	}

    //Commands
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
    	if(!(sender instanceof Player)) {
    		sender.sendMessage(RED + lang.getString("Config.Plugin.Prefix") + lang.getString("Commands.all.onlyPlayer"));
    		return false;
    	}
    	Player p = (Player) sender;

    	//KMines <regionadd>
    	if(args.length > 0 && args[0].equals("regionadd")) {
    		kMines_sub_RegionAdd kMines_sub_Set = new kMines_sub_RegionAdd(sender, cmd, commandLabel, args, plugin, lang);
    		if(!(p.hasPermission("kMines.cmd.regionadd"))) {
    			p.sendMessage(DARK_PURPLE + lang.getString("Config.Plugin.Prefix")+" " + lang.getString("Config.Plugin.hasNoPerm") + GOLD + " | /KMines regionadd");
    			return false;
    		}
    		else {
    			if(kMines_sub_Set.execute()) {
        			return true;
        		}
        		else {
            		p.sendMessage(DARK_PURPLE + lang.getString("Config.Plugin.Prefix")+" " + GREEN + "/KMines regionadd <name>");
        			return false;
        		}
    		}
    	} 

    	//KMines <remove>
    	else if(args.length > 0 && args[0].equals("remove")) {
    		kMines_sub_Remove kMines_sub_Remove = new kMines_sub_Remove(sender, cmd, commandLabel, args, plugin, lang);
    		if(!(p.hasPermission("kMines.cmd.remove"))) {
    			p.sendMessage(DARK_PURPLE + lang.getString("Config.Plugin.Prefix")+" " + lang.getString("Config.Plugin.hasNoPerm") + GOLD + " | /KMines remove");
    			return false;
    		}
    		else {
    			if(kMines_sub_Remove.execute()) {
        			return true;
        		}
        		else {
            		p.sendMessage(DARK_PURPLE + lang.getString("Config.Plugin.Prefix")+" " + GREEN + "/KMines remove <name>");
        			return false;
        		}
    		}
    	} 

    	//KMines <list>
    	else if(args.length > 0 && args[0].equals("list")) {
    		kMines_sub_List kMines_sub_List = new kMines_sub_List(sender, cmd, commandLabel, args, plugin, lang);
    		if(!(p.hasPermission("kMines.cmd.list"))) {
    			p.sendMessage(DARK_PURPLE + lang.getString("Config.Plugin.Prefix")+" " + lang.getString("Config.Plugin.hasNoPerm") + GOLD + " | /KMines list");
    			return false;
    		}
    		else {
    			if(kMines_sub_List.execute()) {
        			return true;
        		}
        		else {
            		p.sendMessage(DARK_PURPLE + lang.getString("Config.Plugin.Prefix")+" " + GREEN + "/KMines list");
        			return false;
        		}
    		}
    	} 

    	//KMines <info>
    	else if(args.length > 0 && args[0].equals("info")) {
    		kMines_sub_ListInfo kMines_sub_ListInfo = new kMines_sub_ListInfo(sender, cmd, commandLabel, args, plugin, lang);
    		if(!(p.hasPermission("kMines.cmd.info"))) {
    			p.sendMessage(DARK_PURPLE + lang.getString("Config.Plugin.Prefix")+" " + lang.getString("Config.Plugin.hasNoPerm") + GOLD + " | /KMines info <name>");
    			return false;
    		}
    		else {
    			if(kMines_sub_ListInfo.execute()) {
        			return true;
        		}
        		else {
            		p.sendMessage(DARK_PURPLE + lang.getString("Config.Plugin.Prefix")+" " + GREEN + "/KMines info <name>");
        			return false;
        		}
    		}
    	} 

    	//KMines <reload>
    	else if(args.length > 0 && args[0].equals("configreload")) {
    		kMines_sub_ConfigReload kMines_sub_ConfigReload = new kMines_sub_ConfigReload(sender, cmd, commandLabel, args, plugin, lang);
    		if(!(p.hasPermission("kMines.cmd.configreload"))) {
    			p.sendMessage(DARK_PURPLE + lang.getString("Config.Plugin.Prefix")+" " + lang.getString("Config.Plugin.hasNoPerm") + GOLD + " | /KMines configreload");
    			return false;
    		}
    		else {
    			if(kMines_sub_ConfigReload.execute()) {
        			return true;
        		}
        		else {
            		p.sendMessage(DARK_PURPLE + lang.getString("Config.Plugin.Prefix")+" " + GREEN + "/KMines configreload");
        			return false;
        		}
    		}
    	} 


    	//KMines <debug>
    	else if(args.length > 0 && args[0].equals("debug")) {
    		kMines_sub_Debug kMines_sub_Debug = new kMines_sub_Debug(sender, cmd, commandLabel, args, plugin, lang);
    		if(!(p.hasPermission("kMines.cmd.debug"))) {
    			p.sendMessage(DARK_PURPLE + lang.getString("Config.Plugin.Prefix")+" " + lang.getString("Config.Plugin.hasNoPerm") + GOLD + " | /KMines debug");
    			return false;
    		}
    		else {
    			if(kMines_sub_Debug.execute()) {
        			return true;
        		}
        		else {
            		p.sendMessage(DARK_PURPLE + lang.getString("Config.Plugin.Prefix")+" " + GREEN + "/KMines debug <true/false>");
        			return false;
        		}
    		}
    	} 


    	//KMines <blocks>
    	else if(args.length > 0 && args[0].equals("blocks")) {
    		kMines_sub_Blocks kMines_sub_SetMat = new kMines_sub_Blocks(sender, cmd, commandLabel, args, plugin, lang);
    		if(!(p.hasPermission("kMines.cmd.blocks"))) {
    			p.sendMessage(DARK_PURPLE + lang.getString("Config.Plugin.Prefix")+" " + lang.getString("Config.Plugin.hasNoPerm") + GOLD + " | /KMines blocks ");
    			return false;
    		}
    		else {
    			if(kMines_sub_SetMat.execute()) {
        			return true;
        		}
        		else {
            		p.sendMessage(DARK_PURPLE + lang.getString("Config.Plugin.Prefix")+" " + GREEN + "/KMines blocks <region> <set|remove> <timer>");
        			return false;
        		}
    		}
    	} 


    	//KMines <togglebuild>
    	else if(args.length > 0 && args[0].equals("togglebuild")) {
    		kMines_sub_ToggleBuild kMines_sub_ToggleBuild = new kMines_sub_ToggleBuild(sender, cmd, commandLabel, args, plugin, lang);
    		if(!(p.hasPermission("kMines.cmd.togglebuild"))) {
    			p.sendMessage(DARK_PURPLE + lang.getString("Config.Plugin.Prefix")+" " + lang.getString("Config.Plugin.hasNoPerm") + GOLD + " | /KMines togglebuild ");
    			return false;
    		}
    		else {
    			if(kMines_sub_ToggleBuild.execute()) {
        			return true;
        		}
        		else {
            		p.sendMessage(DARK_PURPLE + lang.getString("Config.Plugin.Prefix")+" " + GREEN + "/KMines togglebuild");
        			return false;
        		}
    		}
    	} 


    	//KMines <zonetool>
    	else if(args.length > 0 && args[0].equals("zonetool")) {
    		kMines_sub_ZoneTool kMines_sub_ZoneTool = new kMines_sub_ZoneTool(sender, cmd, commandLabel, args, plugin, lang);
    		if(!(p.hasPermission("kMines.cmd.zonetool"))) {
    			p.sendMessage(DARK_PURPLE + lang.getString("Config.Plugin.Prefix")+" " + lang.getString("Config.Plugin.hasNoPerm") + GOLD + " | /KMines zonetool ");
    			return false;
    		}
    		else {
    			if(kMines_sub_ZoneTool.execute()) {
        			return true;
        		}
        		else {
            		p.sendMessage(DARK_PURPLE + lang.getString("Config.Plugin.Prefix")+" " + GREEN + "/KMines zonetool");
        			return false;
        		}
    		}
    	} 
    	
    	//KMines <help> | Default
    	else {
    		if(!(p.hasPermission("kMines.cmd.help"))) {
    			p.sendMessage(DARK_PURPLE + lang.getString("Config.Plugin.Prefix")+" " + lang.getString("Config.Plugin.hasNoPerm") + GOLD + " | /KMines help");
    			return false;
    		}
    		else {
        		p.sendMessage(DARK_PURPLE + lang.getString("Commands.defaultHelp.Line_1"));
        		p.sendMessage(DARK_PURPLE + "" + BOLD + "[KyriumMines] / [KMines]");
        		p.sendMessage(RED + lang.getString("Commands.defaultHelp.Line_2"));
        		p.sendMessage(DARK_PURPLE + lang.getString("Commands.defaultHelp.Line_3"));
        		
        		//help
        		p.sendMessage(GREEN + "/KMines [help]");
        		p.sendMessage(WHITE + lang.getString("Commands.defaultHelp.Cmd_1"));
        		
        		//regionadd
        		p.sendMessage(GREEN + "/KMines regionadd <name>");
        		p.sendMessage(WHITE + lang.getString("Commands.defaultHelp.Cmd_2"));
        		
        		//zonetool
        		p.sendMessage(GREEN + "/KMines zonetool");
        		p.sendMessage(WHITE + lang.getString("Commands.defaultHelp.Cmd_9"));
        		
        		//remove
        		p.sendMessage(GREEN + "/KMines remove <name>");
        		p.sendMessage(WHITE + lang.getString("Commands.defaultHelp.Cmd_3"));
        		
        		//list
        		p.sendMessage(GREEN + "/KMines list");
        		p.sendMessage(WHITE + lang.getString("Commands.defaultHelp.Cmd_4"));
        		
        		//info
        		p.sendMessage(GREEN + "/KMines info <name>");
        		p.sendMessage(WHITE + lang.getString("Commands.defaultHelp.Cmd_5"));
        		
        		//blocks
        		p.sendMessage(GREEN + "/KMines blocks <region> <set|remove> <timer>");
        		p.sendMessage(WHITE + lang.getString("Commands.defaultHelp.Cmd_6"));
        		
        		//configreload
        		p.sendMessage(GREEN + "/KMines configreload");
        		p.sendMessage(WHITE + lang.getString("Commands.defaultHelp.Cmd_10"));
        		
        		//debug
        		p.sendMessage(GREEN + "/KMines debug <true/false>");
        		p.sendMessage(WHITE + lang.getString("Commands.defaultHelp.Cmd_8"));
        		
        		//togglebuild
        		p.sendMessage(GREEN + "/KMines togglebuild");
        		p.sendMessage(WHITE + lang.getString("Commands.defaultHelp.Cmd_7"));
				
        		
        		return true;
    		}
    	}
	}
}
