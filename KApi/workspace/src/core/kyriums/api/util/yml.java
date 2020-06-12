package core.kyriums.api.util;
 
import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class yml {
	private String file;
	private Plugin plugin;
	private FileConfiguration endfile;
	private File isfile;

	public yml(String file, Plugin plugin) {
		this.file = file;
		this.plugin = plugin;
		this.isfile = new File(plugin.getDataFolder() + "\\" + file);
		if(!isfile.exists()) {
			try {
				isfile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		this.endfile = YamlConfiguration.loadConfiguration(this.isfile);
	}
	
	public FileConfiguration load() {
		FileConfiguration filecf = null;
		plugin.getConfig();
		return filecf;
	}
	
	public boolean save() {
		try {
			endfile.save(plugin.getDataFolder() + "/" + file);
		} catch (IOException e) {
			return false;
		}
		return true;
	}
	
	public boolean reload() {
		plugin.reloadConfig();
		endfile = YamlConfiguration.loadConfiguration(isfile);
		return true;
	}
}
