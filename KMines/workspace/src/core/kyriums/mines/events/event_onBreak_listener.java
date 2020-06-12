package core.kyriums.mines.events;

import java.io.File;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;


//Event Listender
public class event_onBreak_listener implements Listener {
	
	//Vars
	private final Plugin plugin;
	@SuppressWarnings("unused")
	private FileConfiguration lang;

	public event_onBreak_listener(Plugin plugin, FileConfiguration lang) {
      this.plugin = plugin;
      this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
      this.lang = lang;
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent event) {
		boolean Cancel = true;
		Player player = event.getPlayer();
		World welt = player.getWorld();
		ConfigurationSection Section = plugin.getConfig().getConfigurationSection("KMinesRegion");
		Map<String, Object> map = Section.getValues(true);
		Location loc = event.getBlock().getLocation();

		File Yml = new File(this.plugin.getDataFolder()+ "\\" + player.getDisplayName() + ".yml");
		FileConfiguration ymlcfg = YamlConfiguration.loadConfiguration(Yml);
		
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
			
			
			//script zum replace
			if(is == true) {
				boolean canBreak = false;
				
				//Setze Eventdaten
				Material blocktype = event.getBlock().getType();
        		
        		//check new
				Material replace_mat = null;
        		Integer timer = null;
				if(plugin.getConfig().isConfigurationSection("KMinesRegion." + is_mine + ".mats_destroy")) {
        			ConfigurationSection mat = plugin.getConfig().getConfigurationSection("KMinesRegion." + is_mine + ".mats_destroy");
        			if(mat.contains(blocktype.name())) {
        				String matstring = plugin.getConfig().getString("KMinesRegion." + is_mine + ".mats_destroy." + blocktype.name() + ".replacer");
        				if(Material.getMaterial(matstring) != null) {
            				replace_mat = Material.getMaterial(matstring);
            				canBreak = true;
        				}
        				timer = plugin.getConfig().getInt("KMinesRegion." + is_mine + ".mats_destroy." + blocktype.name() + ".timer");
        			}
        			else {
        				canBreak = false;
        				replace_mat = null;
        				timer = null;
        			}
        		}
        		
        		//BreakEvent (End)
				if(canBreak == true && !ymlcfg.getBoolean("opt.canbuild")) {
					BukkitTask task = new event_onBreak_taksManager(blocktype, loc, welt, plugin, replace_mat, timer).runTask(this.plugin);
					task.isCancelled();
					Cancel = false;
				}
				if(plugin.getConfig().getBoolean("KMinesRegion." + is_mine + ".useBreakCancelEvent") == true && !ymlcfg.getBoolean("opt.canbuild")) {
					event.setCancelled(Cancel);
				}
			}
		}
	}
}
