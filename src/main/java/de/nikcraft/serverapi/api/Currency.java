package de.nikcraft.serverapi.api;

import de.nikcraft.serverapi.db.MySQL;

import java.util.UUID;

public class Currency {

    public static void addUserToTableTokens(UUID uuid, String name, int tokens){
        MySQL.addDataToToken_Data(uuid, name, tokens);
    }

    public static void addUserToTableCoins(UUID uuid, String name, int coins){
        MySQL.addDataToCoins_Data(uuid, name, coins);
    }

    public static void addUserToTablePlayTime(UUID uuid, String name, int playtime){
        MySQL.addDataToPlayTime_Data(uuid, name, playtime);
    }

    public static boolean existsUUIDinTokens(UUID uuid){
        return MySQL.existsUUIDTokens(uuid);
    }

    public static boolean existsUUIDinCoins(UUID uuid){
        return MySQL.existsUUIDCoins(uuid);
    }

    public static boolean existsUUIDinPlayTime(UUID uuid){
        return MySQL.existsUUIDPlayTime(uuid);
    }

    public static void addCoinsToUUID(UUID uuid, int amount){
        MySQL.addCoinsUUID(uuid, amount);
    }

    public static void addTokensToUUID(UUID uuid, int amount){
        MySQL.addTokensUUID(uuid, amount);
    }

    public static void addPlayTimeToUUID(UUID uuid, int amount){
        MySQL.addPlayTimeUUID(uuid, amount);
    }

    public static void removeCoinsFromUUID(UUID uuid, int amount){
        MySQL.removeCoinsUUID(uuid, amount);
    }

    public static void removeTokensFromUUID(UUID uuid, int amount){
        MySQL.removeTokensUUID(uuid, amount);
    }

    public static void removePlayTimeFromUUID(UUID uuid, int amount){
        MySQL.removePlayTimeUUID(uuid, amount);
    }

    public static void setTokensForUUID(UUID uuid, int amount){
        MySQL.setTokensUUID(uuid, amount);
    }

    public static void setCoinsForUUID(UUID uuid, int coins){
        MySQL.setCoinsUUID(uuid, coins);
    }

    public static void setPlayTimeForUUID(UUID uuid, int amount){
        MySQL.setPlayTimeUUID(uuid, amount);
    }

    public static int getCoinsForUUID(UUID uuid){
        return MySQL.getCoinsUUID(uuid);
    }

    public static int getTokensForUUID(UUID uuid){
        return MySQL.getTokensUUID(uuid);
    }

    public static int getPlayTimeForUUID(UUID uuid){
        return MySQL.getPlayTimeUUID(uuid);
    }
}