package de.nikcraft.serverapi.util;

import de.nikcraft.serverapi.api.Formatter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LobbyScoreBoard {

    private static final Map<UUID, Scoreboard> boards = new HashMap<>();

    public static void createBoard(Player p) {
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();

        Objective obj = board.registerNewObjective("nikcraft", "dummy",
                ChatColor.GOLD.toString() + ChatColor.BOLD + "Nikcraft.de");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        String[] entries = new String[]{
                ChatColor.BLACK.toString(),
                ChatColor.DARK_BLUE.toString(),
                ChatColor.DARK_GREEN.toString(),
                ChatColor.DARK_AQUA.toString(),
                ChatColor.DARK_RED.toString(),
                ChatColor.DARK_PURPLE.toString(),
                ChatColor.GOLD.toString(),
                ChatColor.GRAY.toString(),
                ChatColor.DARK_GRAY.toString(),
                ChatColor.RED.toString(),
                ChatColor.AQUA.toString(),
                ChatColor.WHITE.toString(),
                ChatColor.BLUE.toString()
        };

        for (int i = 0; i < entries.length; i++) {
            Team team = board.registerNewTeam("line" + i);
            team.addEntry(entries[i]);
            obj.getScore(entries[i]).setScore(entries.length - i);
        }

        boards.put(p.getUniqueId(), board);
        p.setScoreboard(board);
        updateBoard(p);
    }

    public static void updateBoard(Player p) {
        Scoreboard board = boards.get(p.getUniqueId());
        if (board == null) {
            createBoard(p);
            return;
        }

        String rang = "§aSpieler";
        if (p.hasPermission("Lobbysystem.Admin")) rang = "§4Administrator";
        else if (p.hasPermission("Lobbysystem.Developer")) rang = "§cDeveloper";
        else if (p.hasPermission("Lobbysystem.Mod")) rang = "§cModerator";
        else if (p.hasPermission("Lobbysystem.Content")) rang = "§cContent";
        else if (p.hasPermission("Lobbysystem.Vip+")) rang = "§5Vip+";
        else if (p.hasPermission("Lobbysystem.Vip")) rang = "§eVip";
        else if (p.hasPermission("Lobbysystem.Premium")) rang = "§bPremium";

        String coins = Formatter.getFormatCoins(p.getUniqueId());
        String pl = Formatter.getSbFormat(p.getUniqueId());

        board.getTeam("line0").setPrefix("       " + ChatColor.DARK_GRAY + ChatColor.BOLD + "§8Lobby");
        board.getTeam("line1").setPrefix("");
        board.getTeam("line2").setPrefix(ChatColor.WHITE + "Rang:");
        board.getTeam("line3").setPrefix(ChatColor.RED + rang);
        board.getTeam("line4").setPrefix("");
        board.getTeam("line5").setPrefix(ChatColor.WHITE + "Coins:");
        board.getTeam("line6").setPrefix(ChatColor.YELLOW + coins);
        board.getTeam("line7").setPrefix("");
        board.getTeam("line8").setPrefix(ChatColor.WHITE + "Spielzeit:");
        board.getTeam("line9").setPrefix(ChatColor.GOLD + pl);
        board.getTeam("line10").setPrefix("");
        board.getTeam("line11").setPrefix(ChatColor.WHITE + "Hosted by");
        board.getTeam("line12").setSuffix(ChatColor.LIGHT_PURPLE + "Galaxyhosting.ch");
    }
}