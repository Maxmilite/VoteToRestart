package io.github.maxmilite.votetorestart.commands;

import io.github.maxmilite.votetorestart.Votetorestart;
import io.github.maxmilite.votetorestart.message.Message;
import org.bukkit.Bukkit;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cancelvote implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!Votetorestart.getInstance().data.exist) {
            if (sender instanceof Player) {
                Message.sendError("当前没有正在进行的投票，使用 \"/startvtr\" 发起一场投票.", (Player) sender);
            } else {
                Bukkit.getConsoleSender().sendMessage("当前没有正在进行的投票，使用 \"/startvtr\" 发起一场投票.");
            }
            return true;
        }
        if (sender instanceof Player) {
            Message.sendInfo("管理员 " + sender.getName() + " 取消了本次投票重启计划.");
        } else {
            Message.sendInfo("控制台取消了本次投票重启计划.");
        }
        Votetorestart.getInstance().data.currentPlayer = 0;
        Votetorestart.getInstance().data.previousTime = 0;
        Votetorestart.getInstance().data.list.clear();
        Votetorestart.getInstance().data.exist = false;
        Votetorestart.getInstance().data.event.cancelThread();
        Votetorestart.getInstance().data.refreshEvent();
        BossBar bossBar = Votetorestart.getInstance().data.bossBar;
        bossBar.removeAll();
        return true;
    }
}
