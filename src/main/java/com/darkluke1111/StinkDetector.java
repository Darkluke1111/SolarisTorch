package com.darkluke1111;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Objects;
import java.util.Random;

public class StinkDetector implements Feature{
    @Override
    public void onEnable(JavaPlugin plugin) {

        Objects.requireNonNull(plugin.getCommand("werstinkt")).setExecutor((commandSender, command, s, strings) ->  {
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
