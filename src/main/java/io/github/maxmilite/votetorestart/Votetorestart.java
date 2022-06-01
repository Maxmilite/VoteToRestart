package io.github.maxmilite.votetorestart;

import io.github.maxmilite.votetorestart.commands.cancelvote;
import io.github.maxmilite.votetorestart.commands.startvotetorestart;
import io.github.maxmilite.votetorestart.commands.votetorestart;
import io.github.maxmilite.votetorestart.config.Config;
import io.github.maxmilite.votetorestart.data.Data;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Votetorestart extends JavaPlugin {

    private static Votetorestart instance;
    public Data data;

    public static Votetorestart getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        this.getServer().getConsoleSender().sendMessage("[投票重启] 插件已禁用.");
        this.setupConfig();
        this.registerCommand();
        data = new Data();
    }

    @Override
    public void onDisable() {
        this.getServer().getConsoleSender().sendMessage("[投票重启] 插件已禁用.");
    }

    private void setupConfig() {
        this.getServer().getConsoleSender().sendMessage("[投票重启] 开始加载配置.");
        Config.setup();
        Config.createDefaults();
        Config.getConfiguration().options().copyDefaults(true);
        Config.saveConfig();
        this.getServer().getConsoleSender().sendMessage("[投票重启] 配置加载完成.");
    }

    private void registerCommand() {
        Objects.requireNonNull(this.getCommand("startvotetorestart")).setExecutor(new startvotetorestart());
        Objects.requireNonNull(this.getCommand("votetorestart")).setExecutor(new votetorestart());
        Objects.requireNonNull(this.getCommand("cancelvote")).setExecutor(new cancelvote());
    }
}
