package com.yanisbft.worldguardextended.listeners;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.util.Location;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.session.SessionManager;
import com.yanisbft.worldguardextended.flags.Flags;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.TrapDoor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerListener implements Listener {

    private final WorldGuardPlugin worldGuardPlugin;
    private final RegionContainer regionContainer;
    private final SessionManager sessionManager;

    public PlayerListener(WorldGuardPlugin worldGuardPlugin, RegionContainer regionContainer, SessionManager sessionManager) {
        this.worldGuardPlugin = worldGuardPlugin;
        this.regionContainer = regionContainer;
        this.sessionManager = sessionManager;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onBlockInteract(PlayerInteractEvent event) {
        Block clickedBlock = event.getClickedBlock();
        Player player = event.getPlayer();
        LocalPlayer localPlayer = worldGuardPlugin.wrapPlayer(player);

        if (sessionManager.hasBypass(localPlayer, localPlayer.getWorld())) {
            return;
        }

        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }

        if (clickedBlock == null) {
            return;
        }

        handleTrapdoors(event, localPlayer, clickedBlock);
    }

    private void handleTrapdoors(PlayerInteractEvent event, LocalPlayer player, Block block) {
        if (!(block.getBlockData() instanceof TrapDoor)) {
            return;
        }

        Location blockLocation = BukkitAdapter.adapt(block.getLocation());
        ApplicableRegionSet regions = regionContainer.createQuery().getApplicableRegions(blockLocation);

        Boolean disableTrapdoors = regions.queryValue(player, Flags.DISABLE_TRAPDOORS);
        if (disableTrapdoors != null) {
            event.setCancelled(disableTrapdoors);
        }
    }
}
