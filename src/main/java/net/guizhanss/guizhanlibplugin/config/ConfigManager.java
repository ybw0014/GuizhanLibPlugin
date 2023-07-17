package net.guizhanss.guizhanlibplugin.config;

import lombok.Getter;
import net.guizhanss.guizhanlibplugin.GuizhanLibPlugin;

import javax.annotation.Nonnull;

public final class ConfigManager {
    private final GuizhanLibPlugin plugin;

    @Getter
    private boolean debugEnabled;
    @Getter
    private boolean autoUpdateEnabled;

    public ConfigManager(@Nonnull GuizhanLibPlugin plugin) {
        this.plugin = plugin;

        debugEnabled = plugin.getConfig().getBoolean("debug", false);
        autoUpdateEnabled = plugin.getConfig().getBoolean("auto-update", true);
    }
}
