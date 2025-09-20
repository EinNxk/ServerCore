package de.nikcraft.serverapi.util;

import de.nikcraft.serverapi.ServerAPI;
import de.nikcraft.serverapi.db.MySQL;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.UUID;

public class ShopInteract implements Listener {

    @EventHandler
    public void onInteract(InventoryClickEvent e){
        if (e.getCurrentItem() == null) return;

        Player player = (Player) e.getWhoClicked();
        UUID uuid = player.getUniqueId();
        if (!(e.getView().getTitle().equalsIgnoreCase("§8Shop") || e.getView().getTitle().equalsIgnoreCase("§8Model Auswählen"))) return;

        e.setCancelled(true);
        if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§eRänge")){
            player.sendMessage(ServerAPI.prefix + "§7Besuche unseren Online Shop um Ränge zu erhalten §e" + ChatColor.ITALIC + "https://shop.nikcraft.de");
            player.closeInventory();
        }

        if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§eCoins")){
            player.sendMessage(ServerAPI.prefix + "§7Besuche unseren Online Shop um Coins zu erhalten §e" + ChatColor.ITALIC + "https://shop.nikcraft.de");
            player.closeInventory();
        }

        if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cTokens")){
            InventoryCreator.createBuyScreen(player);
        }

        if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§c§lZurück")){
            InventoryCreator.createDefaultShopInv(player);
        }

        if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§d5 Tokens")){
            if (MySQL.getCoinsUUID(uuid) < 12500){
                int raw = MySQL.getCoinsUUID(uuid);
                int count = 12500 - raw;
                player.sendMessage(ServerAPI.prefix + "§cDir fehlen §e" + count + " Coins §cum das zu kaufen!");
                return;
            }

            MySQL.removeCoinsUUID(uuid, 12500);
            MySQL.addTokensUUID(uuid, 5);
            player.closeInventory();
            player.sendMessage(ServerAPI.prefix + "§7Du hast §d5 Tokens §7für 12.000 Coins gekauft");
        }

        if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§d10 Tokens")){
            if (MySQL.getCoinsUUID(uuid) < 25000){
                int raw = MySQL.getCoinsUUID(uuid);
                int count = 25000 - raw;
                player.sendMessage(ServerAPI.prefix + "§cDir fehlen §e" + count + " Coins §cum das zu kaufen!");
                return;
            }

            MySQL.removeCoinsUUID(uuid, 25000);
            MySQL.addTokensUUID(uuid, 10);
            player.closeInventory();
            player.sendMessage(ServerAPI.prefix + "§7Du hast §d10 Tokens §7für 25.000 Coins gekauft");
        }

        if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§d15 Tokens")){
            if (MySQL.getCoinsUUID(uuid) < 38000){
                int raw = MySQL.getCoinsUUID(uuid);
                int count = 38000 - raw;
                player.sendMessage(ServerAPI.prefix + "§cDir fehlen §e" + count + " Coins §cum das zu kaufen!");
                return;
            }

            MySQL.removeCoinsUUID(uuid, 38000);
            MySQL.addTokensUUID(uuid, 15);
            player.closeInventory();
            player.sendMessage(ServerAPI.prefix + "§7Du hast §d15 Tokens §7für 38.000 Coins gekauft");
        }

        if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§d25 Tokens")){
            if (MySQL.getCoinsUUID(uuid) < 72000){
                int raw = MySQL.getCoinsUUID(uuid);
                int count = 72000 - raw;
                player.sendMessage(ServerAPI.prefix + "§cDir fehlen §e" + count + " Coins §cum das zu kaufen!");
                return;
            }

            MySQL.removeCoinsUUID(uuid, 72000);
            MySQL.addTokensUUID(uuid, 25);
            player.closeInventory();
            player.sendMessage(ServerAPI.prefix + "§7Du hast §d25 Tokens §7für 72.000 Coins gekauft");
        }
    }
}