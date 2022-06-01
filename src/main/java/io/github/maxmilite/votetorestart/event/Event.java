package io.github.maxmilite.votetorestart.event;

import io.github.maxmilite.votetorestart.Votetorestart;
import io.github.maxmilite.votetorestart.config.Config;
import io.github.maxmilite.votetorestart.message.Message;
import org.bukkit.Bukkit;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collection;

public class Event extends BukkitRunnable {

    Thread thread;
    int cancelled = 0;

    void set() {
        int cur = Votetorestart.getInstance().data.currentPlayer;
        int tot = Bukkit.getServer().getOnlinePlayers().size();
        int time = Config.getConfiguration().getInt("vote.timer") * 20;
        double ratio = Config.getConfiguration().getDouble("vote.requirement");
        int tottime = Config.getConfiguration().getInt("vote.timer") * 20;
        BossBar bossBar = Votetorestart.getInstance().data.bossBar;
        Collection<Player> collection = (Collection<Player>) Bukkit.getOnlinePlayers();
        for (Player i : collection)
            bossBar.addPlayer(i);
        while (time > 0) {
            if (cancelled == 1) {
                return;
            }
            cur = Votetorestart.getInstance().data.currentPlayer;
            tot = Bukkit.getServer().getOnlinePlayers().size();
            if ((double) cur >= ratio * tot)
                break;
            int rev = time / 20;
            bossBar.setTitle("剩余时间: " + rev);
            bossBar.setProgress((double) time / tottime);
            --time;
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if ((double) cur >= ratio * tot) {
            Message.sendInfo("投票玩家超过了总玩家数的 " + ratio * 100 + "%，服务器即将重启.");
            bossBar.removeAll();
            for (int i = 10; i > 0; --i) {
                Message.sendInfo("服务器将在 " + i + "秒后重启.");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Bukkit.shutdown();
        } else {
            Message.sendInfo("投票玩家未超过总玩家数的 " + ratio * 100 + "%，本次投票无效.");
            Votetorestart.getInstance().data.previousTime = System.currentTimeMillis();
            Votetorestart.getInstance().data.exist = false;
            Votetorestart.getInstance().data.currentPlayer = 0;
            Votetorestart.getInstance().data.list.clear();
            Votetorestart.getInstance().data.event.cancelThread();
            Votetorestart.getInstance().data.refreshEvent();
            bossBar.removeAll();
        }
    }

    @Override
    public void run() {
        thread = new Thread(this::set);
        thread.start();
    }

    public void cancelThread() {
        cancelled = 1;
    }
}
