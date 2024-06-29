package com.darkluke1111;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class CustomItemRegistry {

    public final static CustomItemRegistry INSTANCE = new CustomItemRegistry();

    public final ItemStack FORTUNE_COOKIE;
    public final ItemStack BOOSTER_WAND;
    public final ItemStack FORTUNE_MESSAGE;

    private CustomItemRegistry() {
        BOOSTER_WAND = new ItemBuilder(Material.WOODEN_HOE)
                .addEntchantment(Enchantment.BREACH,1,false)
                .setModelData(1)
                .setDisplayName("Booster Wand")
                .changeMeta(m -> m.addItemFlags(ItemFlag.HIDE_ENCHANTS))
                .setLore(List.of("To the moon and back!"))
                .build();

        FORTUNE_COOKIE = new ItemBuilder(Material.COOKIE)
                .setModelData(1)
                .setDisplayName("Fortune Cookie")
                .setLore(List.of("Are you Lucky?"))
                .addEntchantment(Enchantment.FORTUNE, 100, true)
                .build();

        FORTUNE_MESSAGE = new ItemBuilder(Material.PAPER)
                .setModelData(1)
                .setDisplayName("Cookie Message")
                .setLore(List.of("You are Lucky!"))
                .build();
    }


}
