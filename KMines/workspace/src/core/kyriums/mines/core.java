package core.kyriums.mines;


import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import core.kyriums.mines.commands.KMines_main;
import core.kyriums.mines.events.event_getPlacePoint_listener;
import core.kyriums.mines.events.event_onBreak_listener;

public final class core<Mines> extends JavaPlugin implements CommandExecutor, Listener{
	
	//vars
    private static Plugin plugin;
	private FileConfiguration lang;
	
	// Gibt aus wenn Plugin aktiviert wird
	@Override
	public void onEnable() {
		//check is API
		if(getServer().getPluginManager().getPlugin("KApi")!=null) {
			//Configs & RegisterEvent
			plugin = this;
			Bukkit.getServer().getPluginManager().registerEvents(this, this);
	
	        // ---------------------------------
			//Lade Config.yml
			File config_file = new File(this.getDataFolder(), "config.yml");
			if(!config_file.exists()) {
				config_file.getParentFile().mkdirs();
	            saveResource("config.yml", false);
			}
			
			File langYml = new File(this.getDataFolder()+"/lang_" + plugin.getConfig().getString("lang") + ".yml");
			if(!langYml.exists()) {
				langYml.getParentFile().mkdirs();
	            saveResource("lang_de_de.yml", false);
			}
	        saveResource("lang_de_de.yml", true);
			lang = YamlConfiguration.loadConfiguration(langYml);
	        
	        
	        
	        //pluginManager.registerEvents(muteCommand, this);
	
	        // ---------------------------------
			//Lade BlockBreaker für Replacement
	        new event_onBreak_listener(this, lang);
	        new event_getPlacePoint_listener(this, lang);
			
			//Lade Commands
	        // ---------------------------------
	        //kMines
	        KMines_main helpCommand = new KMines_main(this, lang);
	        getCommand("kmines").setExecutor(helpCommand);
		}
		else {
			this.getLogger().info("[KMines] -------------------------------------");
			this.getLogger().info("[KMines] Alle funktionen Deaktiviert");
			this.getLogger().info("[KMines] Installiere bitte KApi!");
			this.getLogger().info("[KMines] -------------------------------------");
		}
	}

	// Gibt aus wenn Plugin deaktiviert wird
    @Override
    public void onDisable() {
	}


}