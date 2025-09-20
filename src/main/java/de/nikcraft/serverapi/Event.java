package de.nikcraft.serverapi;

import de.nikcraft.serverapi.db.MySQL;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class Event implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();
        UUID uuid = player.getUniqueId();

        if (!(MySQL.existsUUIDCoins(uuid))){
            MySQL.addDataToCoins_Data(uuid, player.getName(), 0);
        }

        if (!(MySQL.existsUUIDTokens(uuid))){
            MySQL.addDataToToken_Data(uuid, player.getName(), 0);
        }

        if (!(MySQL.existsUUIDPlayTime(uuid))){
            MySQL.addDataToPlayTime_Data(uuid, player.getName(), 0);
        }
    }
}