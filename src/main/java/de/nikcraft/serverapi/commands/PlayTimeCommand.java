package de.nikcraft.serverapi.commands;

import de.nikcraft.serverapi.ServerAPI;
import de.nikcraft.serverapi.api.Formatter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PlayTimeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)){
            return true;
        }

        player.sendMessage(ServerAPI.prefix + "§7Deine Spielzeit §a" + Formatter.getCommandFormat(player.getUniqueId()));
        player.sendMessage(ServerAPI.prefix + "§7Vielen Dank das du auf Nikcraft.de spielst §c♥");
        return true;
    }
}