package com.darkluke1111;

import com.darkluke1111.racing.Racing;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class SolarisTorch extends JavaPlugin {
    public static final String PLUGIN_NAME = "Solaris_Torch";

    @Override
    public void onEnable() {
        Bukkit.getLogger().info(ChatColor.GREEN + "Enabled " + this.getName());
        enableFeatures();
    }

    private void enableFeatures() {
        new BoatYeeter().onEnable(this);
        new AlternateTNT().onEnable(this);
        new Racing().onEnable(this);
        new FortuneCookieListener().onEnable(this);
        new StinkDetector().onEnable(this);
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info(ChatColor.RED + "Disabled " + this.getName());
    }
}