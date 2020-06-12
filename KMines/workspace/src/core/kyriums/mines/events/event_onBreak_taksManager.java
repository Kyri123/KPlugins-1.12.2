package core.kyriums.mines.events;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class event_onBreak_taksManager extends BukkitRunnable {

    private final Material replace_mat;
    private final Location pos;
    private final World welt;
	private Plugin plugin;
	private Material blocktype;
	private int timer;

    public event_onBreak_taksManager(Material blocktype,Location pos,World welt,Plugin plugin, Material replace_mat, int timer) {
        this.replace_mat = replace_mat;
        this.blocktype = blocktype;
        this.pos = pos;
        this.welt = welt;
        this.plugin = plugin;
        this.timer = timer;
    }

    @Override
    public void run() {
    	welt.getBlockAt(pos).setType(replace_mat);
		BukkitTask takslook = new event_onBreak(blocktype, pos, welt).runTaskLater(this.plugin, timer);
		if(takslook.isCancelled()) {
			cancel();
		}
    }

}
