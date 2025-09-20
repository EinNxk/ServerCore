package de.nikcraft.serverapi.commands;

import de.nikcraft.serverapi.ServerAPI;
import de.nikcraft.serverapi.db.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CurrencyCommand implements CommandExecutor, TabExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)){
            return true;
        }

        if (!(player.hasPermission("ServerAPi.setCurrency"))){
            return true;
        }

        if (Objects.equals(args[0], "coins")){
            if (args.length <= 3){
                player.sendMessage(ServerAPI.prefix + "§c/currency coins <set/add/remove> <Amount> <(Spieler)>");
                return true;
            }

            if (Objects.equals(args[1], "set")){
                String TargetName = args[3];
                Player target = Bukkit.getPlayer(TargetName);

                if (target == null){
                    player.sendMessage(ServerAPI.prefix + "§cDieser Spieler konnte nicht gefunden werden");
                    return true;
                }

                String value = args[2];
                MySQL.setCoinsUUID(target.getUniqueId(), Integer.parseInt(value));
            }

            if (Objects.equals(args[1], "add")){
                String TargetName = args[3];
                Player target = Bukkit.getPlayer(TargetName);

                if (target == null){
                    player.sendMessage(ServerAPI.prefix + "§cDieser Spieler konnte nicht gefunden werden");
                    return true;
                }

                String value = args[2];
                MySQL.addCoinsUUID(target.getUniqueId(), Integer.parseInt(value));
            }

            if (Objects.equals(args[1], "remove")){
                String TargetName = args[3];
                Player target = Bukkit.getPlayer(TargetName);

                if (target == null){
                    player.sendMessage(ServerAPI.prefix + "§cDieser Spieler konnte nicht gefunden werden");
                    return true;
                }

                String value = args[2];
                MySQL.removeCoinsUUID(target.getUniqueId(), Integer.parseInt(value));
            }
            return true;
        }

        if (Objects.equals(args[0], "tokens")){
            if (args.length <= 3){
                player.sendMessage(ServerAPI.prefix + "§c/currency tokens <set/add/remove> <Amount> <(Spieler)>");
                return true;
            }

            if (Objects.equals(args[1], "set")){
                String TargetName = args[3];
                Player target = Bukkit.getPlayer(TargetName);

                if (target == null){
                    player.sendMessage(ServerAPI.prefix + "§cDieser Spieler konnte nicht gefunden werden");
                    return true;
                }

                String value = args[2];
                MySQL.setTokensUUID(target.getUniqueId(), Integer.parseInt(value));
            }

            if (Objects.equals(args[1], "add")){
                String TargetName = args[3];
                Player target = Bukkit.getPlayer(TargetName);

                if (target == null){
                    player.sendMessage(ServerAPI.prefix + "§cDieser Spieler konnte nicht gefunden werden");
                    return true;
                }

                String value = args[2];
                MySQL.addTokensUUID(target.getUniqueId(), Integer.parseInt(value));
            }

            if (Objects.equals(args[1], "remove")){
                String TargetName = args[3];
                Player target = Bukkit.getPlayer(TargetName);

                if (target == null){
                    player.sendMessage(ServerAPI.prefix + "§cDieser Spieler konnte nicht gefunden werden");
                    return true;
                }

                String value = args[2];
                MySQL.removeTokensUUID(target.getUniqueId(), Integer.parseInt(value));
            }
            return true;
        }

        if (Objects.equals(args[0], "remove")){
            if (args.length <= 3){
                player.sendMessage(ServerAPI.prefix + "§c/currency playtime <set/add/remove> <Amount> <(Spieler)>");
                return true;
            }

            if (Objects.equals(args[1], "set")){
                String TargetName = args[3];
                Player target = Bukkit.getPlayer(TargetName);

                if (target == null){
                    player.sendMessage(ServerAPI.prefix + "§cDieser Spieler konnte nicht gefunden werden");
                    return true;
                }

                String value = args[2];
                MySQL.setPlayTimeUUID(target.getUniqueId(), Integer.parseInt(value));
            }

            if (Objects.equals(args[1], "add")){
                String TargetName = args[3];
                Player target = Bukkit.getPlayer(TargetName);

                if (target == null){
                    player.sendMessage(ServerAPI.prefix + "§cDieser Spieler konnte nicht gefunden werden");
                    return true;
                }

                String value = args[2];
                MySQL.addPlayTimeUUID(target.getUniqueId(), Integer.parseInt(value));
            }

            if (Objects.equals(args[1], "remove")){
                String TargetName = args[3];
                Player target = Bukkit.getPlayer(TargetName);

                if (target == null){
                    player.sendMessage(ServerAPI.prefix + "§cDieser Spieler konnte nicht gefunden werden");
                    return true;
                }

                String value = args[2];
                MySQL.removePlayTimeUUID(target.getUniqueId(), Integer.parseInt(value));
            }
            return true;
        }

        player.sendMessage(ServerAPI.prefix + "§fUsage /currency <Coins/Tokens/PlayTime> <set/add/remove> <Amount> <(Spieler)>");
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender player, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        List<String> suggestions = new ArrayList<>();
        if (!(player.hasPermission("ServerAPi.setCurrency"))){
            return suggestions;
        }

        if (args.length == 1){
            suggestions.add("coins");
            suggestions.add("tokens");
            suggestions.add("playtime");
        }

        if (args.length == 2){
            suggestions.add("set");
            suggestions.add("add");
            suggestions.add("remove");
        }

        if (args.length == 3){
            suggestions.add("100");
            suggestions.add("500");
            suggestions.add("1000");
        }

        if (args.length == 4){
            for (Player players : Bukkit.getOnlinePlayers()){
                suggestions.add(players.getName());
            }
        }
        return suggestions;
    }
}