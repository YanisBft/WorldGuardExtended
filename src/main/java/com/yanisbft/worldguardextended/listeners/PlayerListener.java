package com.yanisbft.worldguardextended.listeners;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.util.Location;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.BooleanFlag;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.session.SessionManager;
import com.yanisbft.worldguardextended.flags.Flags;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.*;
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

        handleBlock(event, localPlayer, clickedBlock, DaylightDetector.class, Flags.DISABLE_DAYLIGHT_DETECTORS);
        handleBlock(event, localPlayer, clickedBlock, DecoratedPot.class, Flags.DISABLE_DECORATED_POTS);
        handleBlock(event, localPlayer, clickedBlock, Door.class, Flags.DISABLE_DOORS);
        handleBlock(event, localPlayer, clickedBlock, Gate.class, Flags.DISABLE_FENCE_GATES);
        handleBlock(event, localPlayer, clickedBlock, TrapDoor.class, Flags.DISABLE_TRAPDOORS);
    }

    private void handleBlock(PlayerInteractEvent event, LocalPlayer player, Block block, Class<? extends BlockData> blockType, BooleanFlag flag) {
        if (!blockType.isInstance(block.getBlockData())) {
            return;
        }

        Location blockLocation = BukkitAdapter.adapt(block.getLocation());
        ApplicableRegionSet regions = regionContainer.createQuery().getApplicableRegions(blockLocation);

        Boolean disabled = regions.queryValue(player, flag);
        if (disabled != null) {
            event.setCancelled(disabled);
        }
    }
}
