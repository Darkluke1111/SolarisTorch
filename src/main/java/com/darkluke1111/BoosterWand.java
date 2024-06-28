package com.darkluke1111;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;


public class BoosterWand implements Listener {
    public static int CUSTOM_MARKER_INT = 1;

    public static ItemStack getBoosterWand() {
        ItemStack item =  new ItemStack(Material.WOODEN_HOE,1);
        ItemMeta  meta = item.getItemMeta();
        meta.addEnchant(Enchantment.UNBREAKING,1,false);
        meta.setDisplayName("Booster Wand");
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.setCustomModelData(CUSTOM_MARKER_INT);
        item.setItemMeta(meta);
        return  item;
    }

    public static boolean isWand(ItemStack stack) {
        return stack.getType() == Material.WOODEN_HOE && stack.getItemMeta().getCustomModelData() == CUSTOM_MARKER_INT;
    }


    @EventHandler
    public void onItemUse(PlayerInteractEvent event) {
        if(event.getItem() == null) return;
        if(!BoosterWand.isWand(event.getItem())) return;

        event.setCancelled(true);
        Bukkit.broadcastMessage(event.getPlayer() + " used the Booster Wand!");
    }
}
