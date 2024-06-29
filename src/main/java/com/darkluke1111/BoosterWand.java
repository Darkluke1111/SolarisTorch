package com.darkluke1111;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;


public class BoosterWand implements Listener {
    private final SolarisTorch plugin;

    public BoosterWand(SolarisTorch plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onItemUse(PlayerInteractEvent event) {
        if(event.getItem() == null) return;
        if(!event.getItem().isSimilar(CustomItemRegistry.INSTANCE.BOOSTER_WAND)) return;

        event.setCancelled(true);
        Player p = event.getPlayer();

        if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Location spawnLocation = p.getLocation().add(0,1,0).add(p.getLocation().getDirection());
            plugin.boosterManager.addBooster(new Booster(spawnLocation,5.0,1.0));
        }

        if(event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
            plugin.boosterManager.removeBoostersAt(p.getLocation());
        }
    }
}
