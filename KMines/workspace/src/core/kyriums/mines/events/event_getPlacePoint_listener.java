package core.kyriums.mines.events;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;


//Event Listener
public class event_getPlacePoint_listener implements Listener {
	//Colors
	private ChatColor DARK_PURPLE = ChatColor.DARK_PURPLE;
	private ChatColor DARK_RED = ChatColor.DARK_RED;
	private ChatColor BOLD = ChatColor.BOLD;
	
	//Vars
	private final Plugin plugin;
	private FileConfiguration lang;

	public event_getPlacePoint_listener(Plugin plugin, FileConfiguration lang) {
      this.plugin = plugin;
      this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
      this.lang = lang;
	}
	
	@EventHandler
	public void onBreak(BlockPlaceEvent event) throws IOException {
		boolean Cancel = false;
		boolean save = false;
		String cfgloc = null;
		String point = null;
		Player player = event.getPlayer();
		World welt = player.getWorld();
		Location loc = event.getBlock().getLocation();
		ItemStack mainhand = player.getInventory().getItemInMainHand();
		

		File Yml = new File(this.plugin.getDataFolder()+ "\\" + player.getDisplayName() + ".yml");
		FileConfiguration ymlcfg = YamlConfiguration.loadConfiguration(Yml);
		
		//set default YML data
		if(!ymlcfg.isConfigurationSection("tool")) ymlcfg.createSection("tool");
		if(!ymlcfg.isSet("tool.loc_1")) ymlcfg.set("tool.loc_1", "");
		if(!ymlcfg.isSet("tool.loc_2")) ymlcfg.set("tool.loc_2", "");
		if(!ymlcfg.isSet("tool.world")) ymlcfg.set("tool.world", "");		
		
		String name = null;
		if(mainhand.hasItemMeta()) {
			if(mainhand.getItemMeta().hasDisplayName()) name = mainhand.getItemMeta().getDisplayName();
		}

		if(name != null && player.hasPermission("kMines.use.zonetool")) {
			if(name.contains("[KMines] ZoneTool Punkt 1")) {
				point = "1";
				cfgloc = loc.getBlockX() + "/" + loc.getBlockY() + "/" + loc.getBlockZ();
				Cancel = true;
				save = true;
			}
			else if(name.contains("[KMines] ZoneTool Punkt 2")) {
				point = "2";
				cfgloc = loc.getBlockX() + "/" + loc.getBlockY() + "/" + loc.getBlockZ();
				Cancel = true;
				save = true;
			}
		}
		
		if(save) {
			player.sendMessage(DARK_PURPLE + "" + BOLD + lang.getString("Config.Plugin.Prefix")+" " + DARK_RED + "{POS: " + point + "}    " + cfgloc);
			ymlcfg.set("tool.loc_" + point, cfgloc);
			ymlcfg.set("tool.world", welt.getName());
		}
		

		ConfigurationSection Section = plugin.getConfig().getConfigurationSection("KMinesRegion");
		Map<String, Object> map = Section.getValues(true);
		
		boolean is = false;
		String is_mine = null;
		
		if(!map.isEmpty()) {
			for(Entry<String, Object> entry : map.entrySet()) {
	
				//lese den String aus
				if(!entry.getKey().contains(".")) {
					String[] split_c1 = plugin.getConfig().getString("KMinesRegion." + entry.getKey() + ".location_1").split("/");
					String[] split_c2 = plugin.getConfig().getString("KMinesRegion." + entry.getKey() + ".location_2").split("/");
					String welt_file = plugin.getConfig().getString("KMinesRegion." + entry.getKey() + ".world");
					
					//erzeuge Location
					Location loc1 = new Location(Bukkit.getWorld(welt_file), Integer.valueOf(split_c1[0]), Integer.valueOf(split_c1[1]), Integer.valueOf(split_c1[2]));
	        		Location loc2 = new Location(Bukkit.getWorld(welt_file), Integer.valueOf(split_c2[0]), Integer.valueOf(split_c2[1]), Integer.valueOf(split_c2[2]));
	        		
					//erzeuge min & max value
					double minx = Math.min(loc1.getBlockX(), loc2.getBlockX()),
					miny = Math.min(loc1.getBlockY(), loc2.getBlockY()),
					minz = Math.min(loc1.getBlockZ(), loc2.getBlockZ()),
					maxx = Math.max(loc1.getBlockX(), loc2.getBlockX()),
					maxy = Math.max(loc1.getBlockY(), loc2.getBlockY()),
					maxz = Math.max(loc1.getBlockZ(), loc2.getBlockZ());
					
					if(loc.getX() > minx && loc.getX() < maxx) {
						if(loc.getY() > miny && loc.getY() < maxy) {
							if(loc.getZ() > minz && loc.getZ() < maxz) {
								is = true;
								is_mine = entry.getKey();
								break;
							}
						}
					}
				}
			}
		}
		
		if(is == true && plugin.getConfig().getBoolean("KMinesRegion." + is_mine + ".usePlaceCancelEvent") == true) Cancel = true;
		if(ymlcfg.getBoolean("opt.canbuild") && !save) Cancel = false;
		
		ymlcfg.save(Yml);
		
		this.plugin.saveConfig();
		this.plugin.reloadConfig();
		
		event.setCancelled(Cancel);
	}
}
