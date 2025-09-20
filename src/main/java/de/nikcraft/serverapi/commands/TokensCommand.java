package de.nikcraft.serverapi.commands;

import de.nikcraft.serverapi.ServerAPI;
import de.nikcraft.serverapi.api.Formatter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TokensCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!(sender instanceof Player player)){
            return true;
        }

        player.sendMessage(ServerAPI.prefix + "§7Du hast §d" + Formatter.getFormatTokens(player.getUniqueId()) + " §7Tokens für §dCoins-Truhen");
        return false;
    }
}