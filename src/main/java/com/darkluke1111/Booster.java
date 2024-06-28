package com.darkluke1111;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Booster {

    OfflinePlayer owner;
    Location location;
    double radius;
    double power;
    List<Player> recentlyBoosted;

    public Booster(OfflinePlayer owner, Location location, double radius, double power) {
        this.owner = owner;
        this.location = location;
        this.radius = radius;
        this.power = power;
        this.recentlyBoosted = new ArrayList<>();
    }
}