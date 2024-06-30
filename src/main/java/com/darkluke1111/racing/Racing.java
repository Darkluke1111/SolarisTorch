package com.darkluke1111.racing;

import com.darkluke1111.CustomItemRegistry;
import com.darkluke1111.Feature;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Racing implements Feature {

    public ParticleSpawner spawner;
    public BoosterManager boosterManager;

    Plugin plugin;

    @Override
    public void onEnable(JavaPlugin plugin) {
        this.plugin = plugin;
        spawner = new ParticleSpawner(plugin);
        boosterManager = new BoosterManager(plugin, spawner);
        boosterManager.start();

        plugin.getServer().getPluginManager().registerEvents(boosterManager, plugin);

        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(plugin, "booster_wand"), CustomItemRegistry.INSTANCE.BOOSTER_WAND);
        recipe.shape("XXX","XXX", "XXX");
        recipe.setIngredient('X', Material.DIAMOND_BLOCK);
        Bukkit.addRecipe(recipe);
    }
}
