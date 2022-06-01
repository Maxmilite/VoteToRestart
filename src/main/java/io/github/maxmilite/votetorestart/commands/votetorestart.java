package io.github.maxmilite.votetorestart.commands;

import io.github.maxmilite.votetorestart.Votetorestart;
import io.github.maxmilite.votetorestart.message.Message;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class votetorestart implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        if (!Votetorestart.getInstance().data.exist) {
            Message.sendError("当前没有正在进行的投票，使用 \"/startvtr\" 发起一场投票.", (Player) sender);
            return true;
        }
        if (Votetorestart.getInstance().data.list.contains((Player) sender)) {
            Message.sendError("您在本次投票中已经投过票了.", (Player) sender);
            return true;
        }
        Votetorestart.getInstance().data.currentPlayer++;
        Votetorestart.getInstance().data.list.add((Player) sender);
        Message.sendInfo("玩家 " + sender.getName() + " 进行了投票, 当前已同意人数: " + ChatColor.GOLD + Votetorestart.getInstance().data.currentPlayer);
        return true;
    }
}
