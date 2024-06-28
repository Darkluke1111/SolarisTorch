package com.darkluke1111;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class BoosterWand implements Listener {
    public static int CUSTOM_MARKER_INT = 1;
    private final SolarisTorch plugin;

    public BoosterWand(SolarisTorch plugin) {
        this.plugin = plugin;
    }

    public ItemStack getBoosterWand() {
        ItemStack item =  new ItemStack(Material.WOODEN_HOE,1);
        ItemMeta  meta = item.getItemMeta();
        meta.addEnchant(Enchantment.UNBREAKING,1,false);
        meta.setDisplayName("Booster Wand");
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.setCustomModelData(CUSTOM_MARKER_INT);
        item.setItemMeta(meta);
        return  item;
    }

    public boolean isWand(ItemStack stack) {
        return stack.getType() == Material.WOODEN_HOE && stack.getItemMeta().getCustomModelData() == CUSTOM_MARKER_INT;
    }


    @EventHandler
    public void onItemUse(PlayerInteractEvent event) {
        if(event.getItem() == null) return;
        if(!this.isWand(event.getItem())) return;

        event.setCancelled(true);
        Player p = event.getPlayer();
        Location spawnLocation = p.getLocation().add(0,1,0).add(p.getLocation().getDirection());

        plugin.boosterManager.addBooster(new Booster(p,spawnLocation,5.0,1.0));
    }
}
