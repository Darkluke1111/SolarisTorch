package com.darkluke1111;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class AlternateTNT implements Feature {

    private Plugin plugin;
    private static final String eggGunpowderKey = "egg_Gunpowder";
    private static final String sandGravelKey = "sand_gravel";
    private static final String netherrackGravelKey = "netherrack_gravel";

    @Override
    public void onEnable(JavaPlugin plugin) {
        this.plugin = plugin;
        registerEggGunpowder(plugin);
        registerSandGravel(plugin);
        registerNetherrackGravel(plugin);
    }

    public void onDisable () {
        Bukkit.removeRecipe(new NamespacedKey(plugin, eggGunpowderKey));
        Bukkit.removeRecipe(new NamespacedKey(plugin, sandGravelKey));
        Bukkit.removeRecipe(new NamespacedKey(plugin, netherrackGravelKey));
    }

    private static void registerEggGunpowder(JavaPlugin plugin) {
        ShapelessRecipe eggGunpowder = new ShapelessRecipe(new NamespacedKey(plugin, eggGunpowderKey), new ItemStack(Material.GUNPOWDER, 1));
        eggGunpowder.addIngredient(Material.NETHERRACK);
        eggGunpowder.addIngredient(Material.COAL);
        eggGunpowder.addIngredient(2, Material.EGG);
        Bukkit.addRecipe(eggGunpowder);
    }

    private static void registerSandGravel(JavaPlugin plugin) {
        ShapelessRecipe sandGravel = new ShapelessRecipe(new NamespacedKey(plugin, sandGravelKey), new ItemStack(Material.SAND, 1));
        sandGravel.addIngredient(1, Material.GRAVEL);
        Bukkit.addRecipe(sandGravel);
    }

    private static void registerNetherrackGravel(JavaPlugin plugin) {
        ShapelessRecipe netherrackGravel = new ShapelessRecipe(new NamespacedKey(plugin, netherrackGravelKey), new ItemStack(Material.GRAVEL, 1));
        netherrackGravel.addIngredient(1, Material.NETHERRACK);
        Bukkit.addRecipe(netherrackGravel);
    }

}
