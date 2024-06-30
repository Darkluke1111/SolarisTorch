package com.darkluke1111.racing;

import com.darkluke1111.CustomItemRegistry;
import com.darkluke1111.SolarisTorch;
import com.darkluke1111.racing.persistence.BoosterTagType;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoosterManager implements Listener {

    private Plugin plugin;
    private ParticleSpawner spawner;
    private Map<World,List<Booster>> boosters;

    private static NamespacedKey BOOSTER_KEY;

    public static int PARTICLE_COUNT = 100;

    public void start() {
        loadBoostersFromWorlds();
        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            for (World world : Bukkit.getWorlds()) {
                List<Booster> worldList = boosters.get(world);
                if(worldList == null) continue;
                worldList.forEach(booster -> {
                    spawner.spawnParticleCircleAt(booster.getLocation(), PARTICLE_COUNT, booster.getRadius());
                });
        }
        },0,20);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if(!player.isGliding()) return;
        List<Booster> worldList = boosters.get(player.getWorld());
        if(worldList == null) return;
        for (Booster booster : worldList) {
            if(booster.isInRange(player.getLocation()) && booster.canBeUsedBy(player)) {
                booster.boostPlayer(player);
                booster.flagPlayer(player,booster);
            }
        }
    }

    public BoosterManager(Plugin plugin, ParticleSpawner spawner) {
        boosters = new HashMap<>();
        this.plugin = plugin;
        this.spawner = spawner;
        BOOSTER_KEY = new NamespacedKey(plugin, "boosters");
    }

    public void addBooster(Booster b) {
        World world = b.getLocation().getWorld();
        boosters.putIfAbsent(world, new ArrayList<>());

        List<Booster> worldList = boosters.get(world);
        worldList.add(b);
        Bukkit.getLogger().warning(Integer.toString(worldList.size()));
        saveBoostersToWorlds();
    }

    public void removeBoostersAt(Location location) {
        World world = location.getWorld();
        List<Booster> worldList = boosters.get(world);
        if(worldList == null) return;
        worldList.removeIf(b -> b.isInRange(location));
        saveBoostersToWorlds();
    }

    private void saveBoostersToWorlds() {
        for (World world : Bukkit.getWorlds()) {
            List<Booster> worldList = boosters.get(world);
            if(worldList == null) continue;
            PersistentDataContainer container = world.getPersistentDataContainer();
            container.set(BOOSTER_KEY, PersistentDataType.LIST.listTypeFrom(new BoosterTagType(plugin)), worldList);
        }
    }

    private void loadBoostersFromWorlds() {
        for (World world : Bukkit.getWorlds()) {
            PersistentDataContainer container = world.getPersistentDataContainer();
            List<Booster> worldList = container.get(BOOSTER_KEY, PersistentDataType.LIST.listTypeFrom(new BoosterTagType(plugin)));
            worldList = worldList == null ? new ArrayList<>() : new ArrayList<>(worldList);
            boosters.put(world, worldList);
        }
    }

    @EventHandler
    public void onItemUse(PlayerInteractEvent event) {
        if(event.getItem() == null) return;
        if(!event.getItem().isSimilar(CustomItemRegistry.INSTANCE.BOOSTER_WAND)) return;

        event.setCancelled(true);
        Player p = event.getPlayer();

        if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Location spawnLocation = p.getLocation().add(0,1,0).add(p.getLocation().getDirection());
            addBooster(new Booster(spawnLocation,5.0,1.0));
        }

        if(event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
            removeBoostersAt(p.getLocation());
        }
    }
}
