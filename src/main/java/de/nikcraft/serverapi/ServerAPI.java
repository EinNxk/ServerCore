package de.nikcraft.serverapi;

import de.nikcraft.serverapi.commands.*;
import de.nikcraft.serverapi.db.MySQL;
import de.nikcraft.serverapi.util.LobbyScoreBoard;
import de.nikcraft.serverapi.util.ShopInteract;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.Reader;

public final class ServerAPI extends JavaPlugin {

    public final static String prefix = "§7| §eNikcraft.de §8» ";
    private static ServerAPI instance;
    public static String url;
    public static String port;
    public static String database;
    public static String user;
    public static String pw;

    //Scoreboard api
    @Override
    public void onEnable() {
        instance = this;

        setupDefaultConfig();

        FileConfiguration config = getConfig();
        url = config.getString("mysql.host");
        port = config.getString("mysql.port");
        database = config.getString("mysql.database");
        user = config.getString("mysql.user");
        pw = config.getString("mysql.pw");

        getServer().getScheduler().runTaskAsynchronously(getInstance(), MySQL::createTables);

        Bukkit.getPluginManager().registerEvents(new Event(), this);
        Bukkit.getPluginManager().registerEvents(new ShopInteract(), this);
        getCommand("coins").setExecutor(new CoinsCommand());
        getCommand("tokens").setExecutor(new TokensCommand());
        getCommand("playtime").setExecutor(new PlayTimeCommand());
        getCommand("currency").setExecutor(new CurrencyCommand());
        getCommand("shop").setExecutor(new ShopCommand());

        new BukkitRunnable(){

            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()){
                    MySQL.addPlayTimeUUID(player.getUniqueId(), 1);
                }
            }
        }.runTaskTimer(this, 0L, 20L * 60);

        /*
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    LobbyScoreBoard.updateBoard(p);
                }
            }
        }.runTaskTimer(this, 0L, 20L);

        Bukkit.getPluginManager().registerEvents(new org.bukkit.event.Listener() {
            @EventHandler
            public void onJoin(org.bukkit.event.player.PlayerJoinEvent e) {
                LobbyScoreBoard.createBoard(e.getPlayer());
            }
        }, this);
         */
    }

    @Override
    public void onDisable() {
    }

    public static ServerAPI getInstance() {
        return instance;
    }

    private void setupDefaultConfig() {
        FileConfiguration config = getConfig();
        Reader defaultConfig = getTextResource("config.yml");

        if(defaultConfig != null) {
            config.setDefaults(YamlConfiguration.loadConfiguration(defaultConfig));
            config.options().copyDefaults(true);
            saveConfig();
        }
    }
}