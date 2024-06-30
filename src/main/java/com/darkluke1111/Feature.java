package com.darkluke1111;

import org.bukkit.plugin.java.JavaPlugin;

public interface Feature {

    void onEnable(JavaPlugin plugin);
    default void onDisable() {}

}
