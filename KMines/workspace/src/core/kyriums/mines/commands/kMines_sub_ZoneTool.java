package core.kyriums.mines.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class kMines_sub_ZoneTool  {

	//Colors
	private ChatColor RED = ChatColor.RED;
	private ChatColor GREEN = ChatColor.GREEN;

	//Vars
	@SuppressWarnings("unused")
	private final Plugin plugin;
	private FileConfiguration lang;
	private CommandSender sender;
	@SuppressWarnings("unused")
	private String commandLabel;
	@SuppressWarnings("unused")
	private String[] args;
	@SuppressWarnings("unused")
	private Command cmd;

	public kMines_sub_ZoneTool(CommandSender sender, Command cmd, String commandLabel, String[] args, Plugin plugin, FileConfiguration lang) {
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
    	PlayerInventory inv = p.getInventory();
    	
    	ItemStack item = new ItemStack(Material.DIAMOND_BLOCK);
    	ItemMeta item_meta = item.getItemMeta();
    	item_meta.addEnchant(Enchantment.DIG_SPEED, 10, true);
    	item_meta.setDisplayName(GREEN + "[KMines] ZoneTool Punkt 1");
    	item.setItemMeta(item_meta);
    	
    	inv.addItem(item);
    	
    	item = new ItemStack(Material.GOLD_BLOCK);
    	item_meta = item.getItemMeta();
    	item_meta.addEnchant(Enchantment.DIG_SPEED, 10, true);
    	item_meta.setDisplayName(GREEN + "[KMines] ZoneTool Punkt 2");
    	item.setItemMeta(item_meta);
    	
    	inv.addItem(item);
		return true;
    }
}
