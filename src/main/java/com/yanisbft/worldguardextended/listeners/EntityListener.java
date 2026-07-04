package com.yanisbft.worldguardextended.listeners;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.util.Location;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.yanisbft.worldguardextended.flags.Flags;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDropItemEvent;

public class EntityListener implements Listener {

    private final RegionContainer regionContainer;

    public EntityListener(RegionContainer regionContainer) {
        this.regionContainer = regionContainer;
    }

    @EventHandler
    public void onChickenLay(EntityDropItemEvent event) {
        Entity entity = event.getEntity();

        if (entity instanceof Chicken) {
            Location location = BukkitAdapter.adapt(entity.getLocation());
            ApplicableRegionSet regions = regionContainer.createQuery().getApplicableRegions(location);

            StateFlag.State chickenLaying = regions.queryState(null, Flags.CHICKEN_LAYING);
            if (chickenLaying == StateFlag.State.DENY) {
                event.setCancelled(true);
            }
        }
    }
}
