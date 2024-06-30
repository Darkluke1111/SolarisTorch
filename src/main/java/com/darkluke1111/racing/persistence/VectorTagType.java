package com.darkluke1111.racing.persistence;

import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

public class VectorTagType implements PersistentDataType<PersistentDataContainer, Vector> {

    private Plugin plugin;

    private NamespacedKey Y_KEY;
    private NamespacedKey X_KEY;
    private NamespacedKey Z_KEY;

    public VectorTagType(Plugin plugin) {
        this.plugin = plugin;

        Y_KEY = key("y");
        X_KEY = key("x");
        Z_KEY = key("z");
    }

    @Override
    public Class<PersistentDataContainer> getPrimitiveType() {
        return PersistentDataContainer.class;
    }

    @Override
    public Class<Vector> getComplexType() {
        return Vector.class;
    }

    @Override
    public PersistentDataContainer toPrimitive(Vector complex, PersistentDataAdapterContext context) {
        PersistentDataContainer container = context.newPersistentDataContainer();
        container.set(X_KEY, PersistentDataType.DOUBLE, complex.getX());
        container.set(Y_KEY, PersistentDataType.DOUBLE, complex.getY());
        container.set(Z_KEY, PersistentDataType.DOUBLE, complex.getZ());
        return container;
    }

    @Override
    public Vector fromPrimitive(PersistentDataContainer primitive, PersistentDataAdapterContext context) {
        double x = primitive.get(X_KEY, PersistentDataType.DOUBLE);
        double y = primitive.get(Y_KEY, PersistentDataType.DOUBLE);
        double z = primitive.get(Z_KEY, PersistentDataType.DOUBLE);
        return new Vector(x,y,z);
    }

    private NamespacedKey key(String key) {
        return new NamespacedKey(plugin, key);
    }
}
