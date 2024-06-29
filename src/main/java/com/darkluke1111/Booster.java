package com.darkluke1111;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class Booster {

    private Location location;
    private double radius;
    private double power;
    private List<Player> recentlyBoosted;

    public double getPower() {
        return power;
    }

    public double getRadius() {
        return radius;
    }

    public Booster(Location location, double radius, double power) {
        this.location = location;
        this.radius = radius;
        this.power = power;
        this.recentlyBoosted = new ArrayList<>();
    }

    public Location getLocation() {
        return location;
    }

    public boolean isInRange(Location location) {
        return this.location.distance(location) < this.radius;
    }

    public boolean canBeUsedBy(Player p) {
        return !recentlyBoosted.contains(p);
    }

    public void boostPlayer(Player player) {
        Vector vel = player.getVelocity();
        player.setVelocity(vel.add(location.getDirection().multiply(power)));
    }

    public void flagPlayer(Player player, Booster booster) {
        Plugin plugin = Bukkit.getPluginManager().getPlugin(SolarisTorch.PLUGIN_NAME);
        if(!booster.recentlyBoosted.contains(player)) {
            booster.recentlyBoosted.add(player);
            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin,() -> {
                booster.recentlyBoosted.remove(player);
            },10);
        }
    }
}