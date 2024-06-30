package com.darkluke1111;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Boat;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class BoatYeeter implements Listener, Feature {

    public static double YEET_SPEED = 1;
    Plugin plugin;

//    @EventHandler
//    public void onPlayerMove(PlayerMoveEvent event) {
//
//        Player p = event.getPlayer();
//        if(!(p.getVehicle() instanceof Boat)) return;
//        Boat boat = (Boat) p.getVehicle();
//        World w = event.getTo().getWorld();
//        Block b = w.getBlockAt(event.getTo().clone().subtract(0,1,0));
//        if( !b.getType().equals(Material.BUBBLE_COLUMN) ) return;
//        System.out.println("YEET!");
//        Vector current = boat.getVelocity().clone();
//        boat.setVelocity(new Vector(current.getX(),YEET_SPEED,current.getZ()));
//    }

    @EventHandler
    public void onBlockBreak(EntityChangeBlockEvent event) {
        if (event.getBlock().getType() != Material.LILY_PAD) return;
        if(!(event.getEntity() instanceof Boat)) return;
        event.setCancelled(true);
        Bukkit.getScheduler().runTaskLater(plugin,() -> {
            Vector current = event.getEntity().getVelocity();
            Vector direction = event.getEntity().getLocation().getDirection();
            event.getEntity().setVelocity(new Vector(0,YEET_SPEED,0).add(direction));

        },1);
    }

    @Override
    public void onEnable(JavaPlugin plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this,plugin);
    }
}
