package io.github.maxmilite.votetorestart.data;

import io.github.maxmilite.votetorestart.event.Event;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Data {
    public BossBar bossBar = Bukkit.createBossBar(
            "",
            BarColor.RED,
            BarStyle.SOLID,
            BarFlag.CREATE_FOG
    );
    public int currentPlayer = 0;
    public long previousTime = 0;
    public boolean exist = false;
    public ArrayList<Player> list;
    public Event event;
    public Data() {
        list = new ArrayList<>();
        event = new Event();
    }
    public void refreshEvent() {
        event = new Event();
    }
}
