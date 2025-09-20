package de.nikcraft.serverapi.commands;

import de.nikcraft.serverapi.ServerAPI;
import de.nikcraft.serverapi.api.Formatter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CoinsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)){
            return true;
        }

        player.sendMessage(ServerAPI.prefix + "§7Deine Coins §e" + Formatter.getFormatCoins(player.getUniqueId()));
        return true;
    }
}