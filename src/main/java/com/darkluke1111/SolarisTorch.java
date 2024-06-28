package com.darkluke1111;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Objects;
import java.util.Random;



public class SolarisTorch extends JavaPlugin {

    public static final String NAMESPACE = "booster_wand";
    public static final String PLUGIN_NAME = "Solaris_Torch";

    public ParticleSpawner spawner;
    public BoosterWand boosterWand;
    public BoosterManager boosterManager;

    NamespacedKey key = new NamespacedKey(this, NAMESPACE);

    @Override
    public void onEnable() {
        Bukkit.getLogger().info(ChatColor.GREEN + "Enabled " + this.getName());
        spawner = new ParticleSpawner(this);
        boosterWand = new BoosterWand(this);
        boosterManager = new BoosterManager(this);
        registerCommands();
        registerRecipes();
        registerEvents();

        boosterManager.start();
    }

    private void registerRecipes() {
        ShapedRecipe recipe = new ShapedRecipe(key, boosterWand.getBoosterWand());
        recipe.shape("XXX","XXX", "XXX");
        recipe.setIngredient('X', Material.OAK_PLANKS);
        Bukkit.addRecipe(recipe);
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info(ChatColor.RED + "Disabled " + this.getName());
    }

    void registerEvents() {
        getServer().getPluginManager().registerEvents(boosterWand,this);
        getServer().getPluginManager().registerEvents(boosterManager, this);
    }

    void registerCommands() {
        Objects.requireNonNull(this.getCommand("werstinkt")).setExecutor((commandSender, command, s, strings) ->  {
            if(!(commandSender instanceof Player)) return false;
            String sender = commandSender.getName();
            List<String> players = Bukkit.getOnlinePlayers().stream().map(Player::getName).toList();
            Random rand = new Random();
            String stinker = players.get(rand.nextInt() % players.size());
            Bukkit.broadcastMessage(ChatColor.BLUE + sender + " hat gefragt wer heute stinkt und es ist definitiv " + stinker + ".");
            return true;
        });
    }
}