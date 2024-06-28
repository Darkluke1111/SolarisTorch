package com.darkluke1111;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class FortuneCookieListener implements Listener {

    @EventHandler
    public void onPlayerEat(FoodLevelChangeEvent event) {
        if(event.getEntityType() != EntityType.PLAYER) return;
        Player player = (Player) event.getEntity();
        if(!player.getInventory().getItemInMainHand().isSimilar(CustomItemRegistry.INSTANCE.FORTUNE_COOKIE)) return;
        player.getInventory().addItem(CustomItemRegistry.INSTANCE.FORTUNE_MESSAGE);
    }
}
