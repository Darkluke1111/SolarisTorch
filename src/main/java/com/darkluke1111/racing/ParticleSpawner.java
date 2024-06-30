package com.darkluke1111.racing;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class ParticleSpawner {

    private Plugin plugin;

    public static ParticleSpawner instance;

    public  ParticleSpawner(JavaPlugin plugin) {
        this.plugin = plugin;
        instance = this;
    }

    public void spawnParticleCircleAt(Location location, int particleCount, double radius) {
        World world = location.getWorld();
        Particle particle = Particle.GLOW;
        Vector circleNormal = location.getDirection();

        Vector cross = new Vector(0,1,0).crossProduct(circleNormal);
        double angle = new Vector(0,1,0).angle(circleNormal);


        for(int i = 0 ; i < particleCount ; i++) {
            double radians = 2.0 * Math.PI / particleCount * i;
            Vector offset = new Vector(Math.sin(radians) * radius, 0 , Math.cos(radians) * radius);
            offset.rotateAroundAxis(cross, angle);
            world.spawnParticle(particle,location.clone().add(offset),1,0,0,0,0,null,true);
        }

    }
}
