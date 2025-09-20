package de.nikcraft.serverapi.api;

import de.nikcraft.serverapi.db.MySQL;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.UUID;

public class Formatter {

    public static String getFormatCoins(UUID uuid){
        if (!(MySQL.existsUUIDCoins(uuid))){
            return "0";
        }

        int value = MySQL.getCoinsUUID(uuid);
        NumberFormat format = NumberFormat.getNumberInstance(Locale.GERMANY);
        return format.format(value);
    }

    public static String getFormatTokens(UUID uuid){
        if (!(MySQL.existsUUIDTokens(uuid))){
            return "0";
        }

        int value = MySQL.getTokensUUID(uuid);
        NumberFormat format = NumberFormat.getNumberInstance(Locale.GERMANY);
        return format.format(value);
    }

    public static String getSbFormat(UUID uuid){
        if (!(MySQL.existsUUIDPlayTime(uuid))){
            return "00m";
        }

        int totalSeconds = MySQL.getPlayTimeUUID(uuid) * 60;
        int days = totalSeconds / (24 * 3600);
        totalSeconds %= (24 * 3600);

        int hours = totalSeconds / 3600;
        totalSeconds %= 3600;

        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;

        StringBuilder sb = new StringBuilder();

        if (days > 0) {
            sb.append(days).append("d ");
        }
        if (hours > 0 || days > 0) {
            sb.append(hours).append("h ");
        }
        if (minutes > 0 || hours > 0 || days > 0) {
            sb.append(minutes).append("m ");
        }

        return sb.toString().trim();
    }

    public static String getCommandFormat(UUID uuid){
        if (!(MySQL.existsUUIDPlayTime(uuid))){
            return "0";
        }

        int totalSeconds = MySQL.getPlayTimeUUID(uuid) * 60;
        int days = totalSeconds / (24 * 3600);
        totalSeconds %= (24 * 3600);

        int hours = totalSeconds / 3600;
        totalSeconds %= 3600;

        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;

        StringBuilder sb = new StringBuilder();

        if (days > 0) {
            sb.append(days).append(" §7Tage §a");
        }
        if (hours > 0 || days > 0) {
            sb.append(hours).append(" §7Stunden §a");
        }
        if (minutes > 0 || hours > 0 || days > 0) {
            sb.append(minutes).append(" §7Minuten §a");
        }
        sb.append(seconds).append(" §7Sekunden");

        return sb.toString().trim();
    }
}