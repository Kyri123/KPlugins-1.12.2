package core.kyriums.api.plugin;
 
import org.bukkit.plugin.java.JavaPlugin;

public class core extends JavaPlugin {

    @Override
    public void onEnable() {
        //Done
        this.getLogger().info("[KApi] wurde Erfolgreich geladen!");
    }
 
    public void onDisable() {
    	//Close
        this.getLogger().info("[KApi] wurde Beendet!");
    }
}