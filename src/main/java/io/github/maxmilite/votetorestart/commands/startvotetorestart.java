package io.github.maxmilite.votetorestart.commands;

import io.github.maxmilite.votetorestart.Votetorestart;
import io.github.maxmilite.votetorestart.config.Config;
import io.github.maxmilite.votetorestart.message.Message;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class startvotetorestart implements CommandExecutor {

    public boolean checkCD() {
        long cooldown = Config.getConfiguration().getInt("vote.cooldown") * 1000L;
        long time = System.currentTimeMillis();
        long prev = Votetorestart.getInstance().data.previousTime;
        if (prev == 0)
            return false;
        return time - prev < cooldown;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (checkCD()) {
            if (sender instanceof Player)
                Message.sendError("投票重启冷却中.", (Player) sender);
            else
                Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED + "错误: 投票重启冷却中");
            return true;
        }
        if (Votetorestart.getInstance().data.exist) {
            if (sender instanceof Player)
                Message.sendError("当前已经启动了一个重启投票.", (Player) sender);
            else
                Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED + "错误: 当前已经启动了一个重启投票");
            return true;
        }
        Votetorestart.getInstance().data.exist = true;
        Votetorestart.getInstance().data.previousTime = System.currentTimeMillis();
        Votetorestart.getInstance().data.currentPlayer = 0;
        Votetorestart.getInstance().data.list.clear();
        if (sender instanceof Player) {
            Message.sendInfo("玩家 " + sender.getName() + " 开启了重启投票.");
        } else {
            Message.sendInfo("控制台开启了重启投票.");
        }
        Message.sendInfo("在接下来的 " + Config.getConfiguration().getInt("vote.timer") + " 秒内，如果您同意投票重启，请输入 \"/vtr\" .");
        Votetorestart.getInstance().data.event.run();
        return true;
    }
}
