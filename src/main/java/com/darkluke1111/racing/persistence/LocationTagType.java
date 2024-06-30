package com.darkluke1111.racing.persistence;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

public class LocationTagType implements PersistentDataType<PersistentDataContainer, Location> {
private Plugin plugin;

private final NamespacedKey WORLD_KEY;
private final NamespacedKey POSITION_KEY;
private final NamespacedKey DIRECTION_KEY;

private final UUIDTagType UUID_TAG_TYPE;
private final VectorTagType VECTOR_TAG_TYPE;

public LocationTagType(Plugin plugin) {
    this.plugin = plugin;
    this.UUID_TAG_TYPE = new UUIDTagType();
    this.VECTOR_TAG_TYPE = new VectorTagType(plugin);

    WORLD_KEY = key("world");
    POSITION_KEY = key("pos");
    DIRECTION_KEY = key("dir");
}

@Override
public Class<PersistentDataContainer> getPrimitiveType() {
    return PersistentDataContainer.class;
}

@Override
public Class<Location> getComplexType() {
    return Location.class;
}

@Override
public PersistentDataContainer toPrimitive(Location complex, PersistentDataAdapterContext context) {
    PersistentDataContainer container = context.newPersistentDataContainer();
    container.set(WORLD_KEY, UUID_TAG_TYPE, complex.getWorld().getUID());
    container.set(POSITION_KEY, VECTOR_TAG_TYPE, complex.toVector());
    container.set(DIRECTION_KEY, VECTOR_TAG_TYPE, complex.getDirection());
    return container;
}

@Override
public Location fromPrimitive(PersistentDataContainer primitive, PersistentDataAdapterContext context) {
    World world = Bukkit.getWorld(primitive.get(WORLD_KEY, UUID_TAG_TYPE));
    Vector position = primitive.get(POSITION_KEY, VECTOR_TAG_TYPE);
    Vector direction = primitive.get(DIRECTION_KEY, VECTOR_TAG_TYPE);
    Location location =  new Location(world,position.getX(), position.getY(), position.getZ());
    location.setDirection(direction);
    return location;
}

private NamespacedKey key(String key) {
    return new NamespacedKey(plugin, key);
}
}
