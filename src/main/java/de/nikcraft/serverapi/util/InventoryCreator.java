package de.nikcraft.serverapi.util;

import de.nikcraft.serverapi.api.Formatter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.UUID;

public class InventoryCreator {

    public static void createDefaultShopInv(Player player){
        Inventory inventory = Bukkit.createInventory(null, 4*9, "§8Shop");

        ItemStack filler = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta fm = filler.getItemMeta();
        fm.setDisplayName(" ");
        filler.setItemMeta(fm);

        ItemStack ranks = new ItemStack(Material.CHEST);
        ItemMeta rm = ranks.getItemMeta();
        rm.setDisplayName("§eRänge");
        rm.setLore(Arrays.asList(
                "§7Mit dem §eVip §7Rang kannst du",
                "§7eigene §eEvent-Server §7starten",
                "§7oder mit dem §ePremium §7rang kannst",
                "§7du eigene §ekleine Runden §7erstellen",
                "§7Natürlich erhältst du noch §eviel mehr§7..."
        ));
        ranks.setItemMeta(rm);

        ItemStack coins = new ItemStack(Material.GOLD_INGOT);
        ItemMeta cm = coins.getItemMeta();
        cm.setDisplayName("§eCoins");
        cm.setLore(Arrays.asList(
                "§7Coins sind unsere Ingame §eWährung",
                "§7damit kannst du §eRänge §7kaufen §7oder",
                "§eEvent-Server §7Tokens.",
                "§7Du kannst §eCoins §7auch durchs §eSpielen",
                "§7sammeln"
        ));
        coins.setItemMeta(cm);

        ItemStack tokens = new ItemStack(Material.PAPER);
        ItemMeta tm = tokens.getItemMeta();
        tm.addItemFlags(ItemFlag.HIDE_ARMOR_TRIM);
        tm.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        tm.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        tm.addItemFlags(ItemFlag.HIDE_STORED_ENCHANTS);
        tm.addItemFlags(ItemFlag.HIDE_DYE);
        tm.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        tm.setDisplayName("§cTokens");
        tm.setLore(Arrays.asList(
                "§7Mit §cTokens §7kannst du §cCoins-",
                "§cTruhen §7öffnen. Du kannst entweder",
                "§7deine §cCoins §7vermehren."
        ));
        tokens.setItemMeta(tm);

        inventory.setItem(0, filler);
        inventory.setItem(1, filler);
        inventory.setItem(2, filler);
        inventory.setItem(3, filler);
        inventory.setItem(4, filler);
        inventory.setItem(5, filler);
        inventory.setItem(6, filler);
        inventory.setItem(7, filler);
        inventory.setItem(8, filler);
        inventory.setItem(21, ranks);
        inventory.setItem(22, coins);
        inventory.setItem(23, tokens);

        player.openInventory(inventory);
    }

    public static void createBuyScreen(Player player){
        UUID uuid = player.getUniqueId();
        Inventory inventory = Bukkit.createInventory(null, 4*9, "§8Model Auswählen");

        ItemStack b = new ItemStack(Material.SPRUCE_DOOR);
        ItemMeta bm = b.getItemMeta();
        bm.setDisplayName("§c§lZurück");
        b.setItemMeta(bm);

        ItemStack filler = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta fm = filler.getItemMeta();
        fm.setDisplayName(" ");
        filler.setItemMeta(fm);

        String coins = Formatter.getFormatCoins(uuid);
        String size = Formatter.getFormatTokens(uuid);
        if (player.hasPermission("ServerAPI.tokens.infinity")){
            size = "∞";
        }
        ItemStack tokens = new ItemStack(Material.PAPER);
        ItemMeta tm = tokens.getItemMeta();
        tm.addItemFlags(ItemFlag.HIDE_ARMOR_TRIM);
        tm.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        tm.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        tm.addItemFlags(ItemFlag.HIDE_STORED_ENCHANTS);
        tm.addItemFlags(ItemFlag.HIDE_DYE);
        tm.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        tm.setDisplayName("§cTokens");
        tm.setLore(Arrays.asList(
                "§7Mit §cTokens §7kannst du §cCoins-",
                "§cTruhen §7öffnen. Du kannst entweder",
                "§7deine §cCoins §7vermehren.",
                " ",
                "§7Deine Coins: §e" + coins,
                "§7Deine Tokens: §c" + size
        ));
        tokens.setItemMeta(tm);

        ItemStack l = new ItemStack(Material.PAPER);
        ItemMeta lm = l.getItemMeta();
        lm.setDisplayName("§d5 Tokens");
        lm.setLore(Arrays.asList(
                "§7Preis: §e12.500 Coins",
                " ",
                "§3<Klicke zum Kaufen>"
        ));
        l.setItemMeta(lm);

        ItemStack m = new ItemStack(Material.PAPER);
        ItemMeta mm = m.getItemMeta();
        mm.setDisplayName("§d10 Tokens");
        mm.setLore(Arrays.asList(
                "§7Preis: §e25.500 Coins",
                " ",
                "§3<Klicke zum Kaufen>"
        ));
        m.setItemMeta(mm);

        ItemStack x = new ItemStack(Material.PAPER);
        ItemMeta xm = x.getItemMeta();
        xm.setDisplayName("§d15 Tokens");
        xm.setLore(Arrays.asList(
                "§7Preis: §e38.000 Coins",
                " ",
                "§3<Klicke zum Kaufen>"
        ));
        x.setItemMeta(xm);

        ItemStack xl = new ItemStack(Material.PAPER);
        ItemMeta xml = xl.getItemMeta();
        xml.setDisplayName("§d25 Tokens");
        xml.setLore(Arrays.asList(
                "§7Preis: §e72.000 Coins",
                " ",
                "§3<Klicke zum Kaufen>"
        ));
        xl.setItemMeta(xml);

        inventory.setItem(0, b);
        inventory.setItem(1, filler);
        inventory.setItem(2, filler);
        inventory.setItem(3, filler);
        inventory.setItem(4, tokens);
        inventory.setItem(5, filler);
        inventory.setItem(6, filler);
        inventory.setItem(7, filler);
        inventory.setItem(8, filler);
        inventory.setItem(19, l);
        inventory.setItem(21, m);
        inventory.setItem(23, x);
        inventory.setItem(25, xl);

        player.openInventory(inventory);
    }
}