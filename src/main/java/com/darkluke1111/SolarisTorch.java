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
    public static final String PLUGIN_NAME = "Solaris_Torch";

    public ParticleSpawner spawner;
    public BoosterWand boosterWand;
    public BoosterManager boosterManager;

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
        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(this, "booster_wand"), CustomItemRegistry.INSTANCE.BOOSTER_WAND);
        recipe.shape("XXX","XXX", "XXX");
        recipe.setIngredient('X', Material.OAK_PLANKS);
        Bukkit.addRecipe(recipe);

        ShapedRecipe fortune_cookie_recipe = new ShapedRecipe(new NamespacedKey(this, "fortune_cookie"), CustomItemRegistry.INSTANCE.FORTUNE_COOKIE);
        fortune_cookie_recipe.shape("XXX","XXX", "XXX");
        fortune_cookie_recipe.setIngredient('X', Material.COOKIE);
        Bukkit.addRecipe(fortune_cookie_recipe);
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info(ChatColor.RED + "Disabled " + this.getName());
    }

    void registerEvents() {
        getServer().getPluginManager().registerEvents(boosterWand,this);
        getServer().getPluginManager().registerEvents(boosterManager, this);
        getServer().getPluginManager().registerEvents(new FortuneCookieListener(), this);
        getServer().getPluginManager().registerEvents(new BoatYeeter(), this);
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