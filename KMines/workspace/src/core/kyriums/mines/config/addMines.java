package core.kyriums.mines.config;

import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public class addMines {
	private String loc_1;
	private String loc_2;
	private String world;
	private String name;
	private Plugin plugin;
	private FileConfiguration conf;
    
	public addMines(String loc_1, String loc_2, String world, String name, Plugin plugin, FileConfiguration conf) {
        this.loc_1 = loc_1;
        this.loc_2 = loc_2;
        this.world = world;
        this.name = name;
        this.plugin = plugin;
        this.conf = conf;
	}

	public boolean save(){  
		
        FileConfiguration yaml = conf;
        yaml.createSection("KMinesRegion." + name);

        yaml.set("KMinesRegion." + name + ".name", name);
        yaml.set("KMinesRegion." + name + ".location_1", loc_1);
        yaml.set("KMinesRegion." + name + ".location_2", loc_2);
        yaml.set("KMinesRegion." + name + ".world", world);
        yaml.set("KMinesRegion." + name + ".useBreakCancelEvent", true);
        yaml.set("KMinesRegion." + name + ".useBuildCancelEvent", true);
        yaml.createSection("KMinesRegion." + name + ".mats_destroy");
        yaml.createSection("KMinesRegion." + name + ".mats_destroy.DIRT");
        yaml.set("KMinesRegion." + name + ".mats_destroy.DIRT.replacer", "AIR");
        yaml.set("KMinesRegion." + name + ".mats_destroy.DIRT.timer", 60);
        yaml.set("KMinesRegion." + name + ".mats_destroy.DIRT.meta", "SOON");
    	
    	try {
            yaml.save(plugin.getDataFolder() + "/config.yml");
    		return true;
        } catch (IOException e) {
            e.printStackTrace();
    		return false;
        }
    	

    	
    }
}
