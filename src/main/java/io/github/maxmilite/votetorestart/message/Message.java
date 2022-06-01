package io.github.maxmilite.votetorestart.message;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Message {
    public static void sendInfo(String info) {
        String cur = ChatColor.YELLOW + "[" + ChatColor.AQUA + "投票重启" + ChatColor.YELLOW + "] "
                + ChatColor.GREEN + info;
        Bukkit.broadcastMessage(cur);
    }
    public static void sendError(String error, Player player) {
        String cur = ChatColor.DARK_RED + "错误: "
                + ChatColor.RED + error;
        player.sendMessage(cur);
    }
}
