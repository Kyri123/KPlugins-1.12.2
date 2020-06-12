package core.kyriums.mines.events;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;

public class event_onBreak extends BukkitRunnable {

    private final Material mat;
    private final Location pos;
    private final World welt;

    public event_onBreak(Material mat,Location pos,World welt) {
        this.mat = mat;
        this.pos = pos;
        this.welt = welt;
    }

    @Override
    public void run() {
        // Nach X ticks:
    	welt.getBlockAt(pos).setType(mat);
		cancel();
    }

}
