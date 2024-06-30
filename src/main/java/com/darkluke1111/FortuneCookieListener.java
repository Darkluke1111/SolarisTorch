package com.darkluke1111;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

public class FortuneCookieListener implements Listener, Feature {

    @EventHandler
    public void onPlayerEat(FoodLevelChangeEvent event) {
        if(event.getEntityType() != EntityType.PLAYER) return;
        Player player = (Player) event.getEntity();
        if(!player.getInventory().getItemInMainHand().isSimilar(CustomItemRegistry.INSTANCE.FORTUNE_COOKIE)) return;
        player.getInventory().addItem(CustomItemRegistry.INSTANCE.FORTUNE_MESSAGE);
    }

    @Override
    public void onEnable(JavaPlugin plugin) {
        ShapedRecipe fortune_cookie_recipe = new ShapedRecipe(new NamespacedKey(plugin, "fortune_cookie"), CustomItemRegistry.INSTANCE.FORTUNE_COOKIE);
        fortune_cookie_recipe.shape("XXX","XXX", "XXX");
        fortune_cookie_recipe.setIngredient('X', Material.COOKIE);
        Bukkit.addRecipe(fortune_cookie_recipe);

        plugin.getServer().getPluginManager().registerEvents(new FortuneCookieListener(), plugin);
    }
}
