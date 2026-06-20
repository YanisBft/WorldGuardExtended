package com.yanisbft.worldguardextended;

import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.registry.FlagRegistry;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.session.SessionManager;
import com.yanisbft.worldguardextended.flags.Flags;
import com.yanisbft.worldguardextended.listeners.PlayerListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class WorldGuardExtended extends JavaPlugin {

    private WorldGuard worldGuard;
    private WorldGuardPlugin worldGuardPlugin;

    @Override
    public void onLoad() {
        worldGuard = WorldGuard.getInstance();
        worldGuardPlugin = (WorldGuardPlugin) Bukkit.getPluginManager().getPlugin("WorldGuard");

        FlagRegistry flagRegistry = worldGuard.getFlagRegistry();
        flagRegistry.register(Flags.DISABLE_TRAPDOORS);
    }

    @Override
    public void onEnable() {
        RegionContainer regionContainer = worldGuard.getPlatform().getRegionContainer();
        SessionManager sessionManager = worldGuard.getPlatform().getSessionManager();

        Bukkit.getPluginManager().registerEvents(new PlayerListener(worldGuardPlugin, regionContainer, sessionManager), this);
    }
}
