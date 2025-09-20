package de.nikcraft.serverapi.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import de.nikcraft.serverapi.ServerAPI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class MySQL {

    private static MySQL instance;

    private final HikariDataSource db;

    private MySQL(){
        String host = ServerAPI.url;
        String port = ServerAPI.port;
        String database = ServerAPI.database;
        String user = ServerAPI.user;
        String pw = ServerAPI.pw;

        String url = "jdbc:mysql://" + host + ":" + port + "/" + database + "?useSSL=false&&allowPublicKeyRetrieval=true";

        HikariConfig dbConfig = new HikariConfig();
        dbConfig.setJdbcUrl(url);
        dbConfig.setUsername(user);
        dbConfig.setPassword(pw);
        dbConfig.addDataSourceProperty("cachePrepStmts", "true");
        dbConfig.addDataSourceProperty("prepStmtCacheSize", "10");
        dbConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        db = new HikariDataSource(dbConfig);
    }

    private static MySQL getInstance() {
        return instance == null ? instance = new MySQL() : instance;
    }

    private static void logException(SQLException e){
        ServerAPI.getInstance().getLogger().severe(e.getMessage());
    }

    private static void logInfo(String info){
        ServerAPI.getInstance().getLogger().info(info);
    }

    public static void createTables() {
        MySQL mySQL = getInstance();

        String sql = "CREATE TABLE IF NOT EXISTS token_data (" +
                "uuid CHAR(36) NOT NULL PRIMARY KEY," +
                "name VARCHAR(16) NOT NULL," +
                "tokens INT NOT NULL DEFAULT 0" +
                ");";
        try (Connection con = mySQL.db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.executeUpdate();
        } catch (SQLException e) {
            logException(e);
        }

        String coins = "CREATE TABLE IF NOT EXISTS coins_data (" +
                "uuid CHAR(36) NOT NULL PRIMARY KEY," +
                "name VARCHAR(16) NOT NULL," +
                "coins INT NOT NULL DEFAULT 0" +
                ");";
        try (Connection con = mySQL.db.getConnection();
             PreparedStatement ps = con.prepareStatement(coins)) {
            ps.executeUpdate();
        } catch (SQLException e) {
            logException(e);
        }

        String pl = "CREATE TABLE IF NOT EXISTS playtime_data (" +
                "uuid CHAR(36) NOT NULL PRIMARY KEY," +
                "name VARCHAR(16) NOT NULL," +
                "playtime_minutes INT NOT NULL DEFAULT 0" +
                ");";
        try (Connection con = mySQL.db.getConnection();
             PreparedStatement ps = con.prepareStatement(pl)) {
            ps.executeUpdate();
        } catch (SQLException e) {
            logException(e);
        }

        logInfo("Alle Tabellen wurde erfolgreich erstellt oder geladen");
    }


    public static void addDataToToken_Data(UUID uuid, String name, int tokens) {
        MySQL mySQL = getInstance();

        String sql = "INSERT INTO token_data (uuid, name, tokens) VALUES (?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE name = VALUES(name), tokens = VALUES(tokens)";
        try (Connection con = mySQL.db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, uuid.toString());
            ps.setString(2, name.toLowerCase());
            ps.setInt(3, tokens);
            ps.executeUpdate();
        } catch (SQLException e) {
            logException(e);
        }
    }

    public static void addTokensUUID(UUID uuid, int amount) {
        MySQL mySQL = getInstance();

        String sql = "UPDATE token_data SET tokens = tokens + ? WHERE uuid = ?";
        try (Connection con = mySQL.db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, amount);
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            logException(e);
        }
    }

    public static void removeTokensUUID(UUID uuid, int amount) {
        MySQL mySQL = getInstance();

        String sql = "UPDATE token_data SET tokens = GREATEST(tokens - ?, 0) WHERE uuid = ?";
        try (Connection con = mySQL.db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, amount);
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            logException(e);
        }
    }

    public static int getTokensUUID(UUID uuid) {
        MySQL mySQL = getInstance();

        String sql = "SELECT tokens FROM token_data WHERE uuid = ?";
        try (Connection con = mySQL.db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, uuid.toString());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("tokens");
                }
            }
        } catch (SQLException e) {
            logException(e);
        }
        return 0;
    }

    public static boolean existsUUIDTokens(UUID uuid) {
        MySQL mySQL = getInstance();

        String sql = "SELECT 1 FROM token_data WHERE uuid = ? LIMIT 1";
        try (Connection con = mySQL.db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, uuid.toString());
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            logException(e);
        }
        return false;
    }

    public static void setTokensUUID(UUID uuid, int value) {
        MySQL mySQL = getInstance();

        String sql = "UPDATE token_data SET tokens = ? WHERE uuid = ?";
        try (Connection con = mySQL.db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, value);
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            logException(e);
        }
    }

    public static void addDataToCoins_Data(UUID uuid, String name, int coins) {
        MySQL mySQL = getInstance();

        String sql = "INSERT INTO coins_data (uuid, name, coins) VALUES (?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE name = VALUES(name), coins = VALUES(coins)";
        try (Connection con = mySQL.db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, uuid.toString());
            ps.setString(2, name.toLowerCase());
            ps.setInt(3, coins);
            ps.executeUpdate();
        } catch (SQLException e) {
            logException(e);
        }
    }

    public static void addCoinsUUID(UUID uuid, int amount) {
        MySQL mySQL = getInstance();

        String sql = "UPDATE coins_data SET coins = coins + ? WHERE uuid = ?";
        try (Connection con = mySQL.db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, amount);
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            logException(e);
        }
    }

    public static void removeCoinsUUID(UUID uuid, int amount) {
        MySQL mySQL = getInstance();

        String sql = "UPDATE coins_data SET coins = GREATEST(coins - ?, 0) WHERE uuid = ?";
        try (Connection con = mySQL.db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, amount);
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            logException(e);
        }
    }

    public static int getCoinsUUID(UUID uuid) {
        MySQL mySQL = getInstance();

        String sql = "SELECT coins FROM coins_data WHERE uuid = ?";
        try (Connection con = mySQL.db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, uuid.toString());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("coins");
                }
            }
        } catch (SQLException e) {
            logException(e);
        }
        return 0;
    }

    public static boolean existsUUIDCoins(UUID uuid) {
        MySQL mySQL = getInstance();

        String sql = "SELECT 1 FROM coins_data WHERE uuid = ? LIMIT 1";
        try (Connection con = mySQL.db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, uuid.toString());
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            logException(e);
        }
        return false;
    }

    public static void setCoinsUUID(UUID uuid, int value) {
        MySQL mySQL = getInstance();

        String sql = "UPDATE token_data SET tokens = ? WHERE uuid = ?";
        try (Connection con = mySQL.db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, value);
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            logException(e);
        }
    }

    public static void addDataToPlayTime_Data(UUID uuid, String name, int minutes) {
        MySQL mySQL = getInstance();

        String sql = "INSERT INTO playtime_data (uuid, name, playtime_minutes) VALUES (?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE name = VALUES(name), playtime_minutes = VALUES(playtime_minutes)";
        try (Connection con = mySQL.db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, uuid.toString());
            ps.setString(2, name.toLowerCase());
            ps.setInt(3, minutes);
            ps.executeUpdate();
        } catch (SQLException e) {
            logException(e);
        }
    }

    public static void addPlayTimeUUID(UUID uuid, int amount) {
        MySQL mySQL = getInstance();

        String sql = "UPDATE playtime_data SET playtime_minutes = playtime_minutes + ? WHERE uuid = ?";
        try (Connection con = mySQL.db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, amount);
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            logException(e);
        }
    }

    public static void removePlayTimeUUID(UUID uuid, int amount) {
        MySQL mySQL = getInstance();

        String sql = "UPDATE playtime_data SET playtime_minutes = GREATEST(playtime_minutes - ?, 0) WHERE uuid = ?";
        try (Connection con = mySQL.db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, amount);
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            logException(e);
        }
    }

    public static int getPlayTimeUUID(UUID uuid) {
        MySQL mySQL = getInstance();

        String sql = "SELECT playtime_minutes FROM playtime_data WHERE uuid = ?";
        try (Connection con = mySQL.db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, uuid.toString());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("playtime_minutes");
                }
            }
        } catch (SQLException e) {
            logException(e);
        }
        return 0;
    }

    public static boolean existsUUIDPlayTime(UUID uuid) {
        MySQL mySQL = getInstance();

        String sql = "SELECT 1 FROM playtime_data WHERE uuid = ? LIMIT 1";
        try (Connection con = mySQL.db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, uuid.toString());
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            logException(e);
        }
        return false;
    }

    public static void setPlayTimeUUID(UUID uuid, int value) {
        MySQL mySQL = getInstance();

        String sql = "UPDATE playtime_data SET playtime_minutes = ? WHERE uuid = ?";
        try (Connection con = mySQL.db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, value);
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            logException(e);
        }
    }
}