package net.guizhanss.guizhanlibplugin.config;

import lombok.Getter;
import net.guizhanss.guizhanlib.slimefun.addon.AddonConfig;
import net.guizhanss.guizhanlibplugin.GuizhanLibPlugin;

import javax.annotation.Nonnull;

public final class ConfigManager {
    private final GuizhanLibPlugin plugin;

    @Getter
    private final AddonConfig config;
    @Getter
    private final AddonConfig updaterConfig;

    @Getter
    private final boolean debugEnabled;
    @Getter
    private final boolean autoUpdateEnabled;
    @Getter
    private final String updaterLocation;
    @Getter
    private final String updaterLang;

    public ConfigManager(@Nonnull GuizhanLibPlugin plugin) {
        this.plugin = plugin;

        config = new AddonConfig(plugin, "config.yml");
        updaterConfig = new AddonConfig(plugin, "updater.yml");

        debugEnabled = config.getBoolean("debug", false);
        autoUpdateEnabled = config.getBoolean("auto-update", true);
        updaterLocation = config.getString("updater.location", "global");
        updaterLang = config.getString("updater.lang", "en_US");

        if (debugEnabled) {
            plugin.getLogger().info("Debug mode is enabled.");
            plugin.getLogger().info("Auto update is " + (autoUpdateEnabled ? "enabled" : "disabled") + ".");
            plugin.getLogger().info("Updater location is " + (autoUpdateEnabled ? "enabled" : "disabled") + ".");
        }
    }
}
