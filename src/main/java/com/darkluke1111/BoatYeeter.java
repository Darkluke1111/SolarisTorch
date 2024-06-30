package com.darkluke1111;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockType;
import org.bukkit.entity.Boat;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.vehicle.VehicleBlockCollisionEvent;
import org.bukkit.event.vehicle.VehicleCollisionEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

public class BoatYeeter implements Listener {

    public static double YEET_SPEED = 1;
    Plugin plugin = Bukkit.getPluginManager().getPlugin(SolarisTorch.PLUGIN_NAME);

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
}
