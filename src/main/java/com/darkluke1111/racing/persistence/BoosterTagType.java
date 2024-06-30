package com.darkluke1111.racing.persistence;

import com.darkluke1111.racing.Booster;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

public class BoosterTagType implements PersistentDataType<PersistentDataContainer, Booster> {

    private final Plugin plugin;

    private final NamespacedKey LOCATION_KEY;
    private final NamespacedKey RADIUS_KEY;
    private final NamespacedKey POWER_KEY;

    private final LocationTagType LOCATION_TAG_TYPE;

    public BoosterTagType(Plugin plugin) {
        this.plugin = plugin;
        this.LOCATION_TAG_TYPE = new LocationTagType(plugin);

        this.LOCATION_KEY = key("location");
        this.RADIUS_KEY = key("radius");
        this.POWER_KEY = key("power");
    }

    @Override
    public Class<PersistentDataContainer> getPrimitiveType() {
        return PersistentDataContainer.class;
    }

    @Override
    public Class<Booster> getComplexType() {
        return Booster.class;
    }

    @Override
    public PersistentDataContainer toPrimitive(Booster complex, PersistentDataAdapterContext context) {
        PersistentDataContainer container = context.newPersistentDataContainer();
        container.set(LOCATION_KEY, LOCATION_TAG_TYPE, complex.getLocation());
        container.set(RADIUS_KEY, PersistentDataType.DOUBLE, complex.getRadius());
        container.set(POWER_KEY, PersistentDataType.DOUBLE, complex.getPower());
        return container;
    }

    @Override
    public Booster fromPrimitive(PersistentDataContainer primitive, PersistentDataAdapterContext context) {
        Location location = primitive.get(LOCATION_KEY, LOCATION_TAG_TYPE);
        double radius = primitive.get(RADIUS_KEY, PersistentDataType.DOUBLE);
        double power = primitive.get(POWER_KEY, PersistentDataType.DOUBLE);
        return new Booster(location,radius,power);
    }

    private NamespacedKey key(String key) {
        return new NamespacedKey(plugin, key);
    }
}
