package com.darkluke1111;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class BoosterManager implements Listener {
    private SolarisTorch plugin;
    private List<Booster> boosters;

    public BoosterManager(SolarisTorch plugin) {
        boosters = new ArrayList<>();
        this.plugin = plugin;
    }

    public void addBooster(Booster b) {
        boosters.add(b);
    }

    public void removeBoostersAt(Location location) {
        boosters.removeIf(b -> b.location.distance(location) < b.radius);
    }

    public void start() {

        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            boosters.forEach(bd -> {
                plugin.spawner.spawnParticleCircleAt(bd.location,100,bd.radius);
            });
        },0,20);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if(!player.isGliding()) return;
        for (Booster booster : boosters) {
            if(booster.location.distance(player.getLocation()) < booster.radius && !booster.recentlyBoosted.contains(player)) {
                Vector vel = player.getVelocity();
                player.setVelocity(vel.add(booster.location.getDirection().multiply(booster.power)));

                // Using the FireworkRocket Boost api is pretty limited so better use setVelocity on player

//                ItemStack firework = new ItemStack(Material.FIREWORK_ROCKET);
//                FireworkMeta meta = (FireworkMeta) firework.getItemMeta();
//                meta.setPower((int) booster.power);
//                firework.setItemMeta(meta);
//                player.fireworkBoost(firework);
                flagPlayer(player,booster);
            }
        }
    }

    private void flagPlayer(Player player, Booster booster) {
        if(!booster.recentlyBoosted.contains(player)) {
            booster.recentlyBoosted.add(player);
            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin,() -> {
                booster.recentlyBoosted.remove(player);
            },10);
        }
    }
}
