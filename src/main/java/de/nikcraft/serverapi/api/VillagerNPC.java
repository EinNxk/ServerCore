package de.nikcraft.serverapi.api;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;

public class VillagerNPC {

    private final JavaPlugin plugin;

    public VillagerNPC(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void spawnStaticVillager(Location loc, String displayName, String id, Villager.Profession profession, Villager.Type type) {
        Villager v = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);

        if (displayName != null && !displayName.isEmpty()) {
            v.setCustomName(displayName);
            v.setCustomNameVisible(true);
        }

        v.setAdult();
        v.setAI(false);
        v.setGravity(false);
        v.setCollidable(false);
        v.setInvulnerable(true);
        v.setSilent(true);
        v.setCanPickupItems(false);
        v.setRemoveWhenFarAway(false);
        v.setProfession(profession);
        v.setAgeLock(true);
        v.setVillagerType(type);

        v.setMetadata(id, new FixedMetadataValue(plugin, true));
    }
}