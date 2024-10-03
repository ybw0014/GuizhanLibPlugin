package net.guizhanss.minecraft.guizhanlib.config;

import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;
import lombok.Getter;
import net.guizhanss.guizhanlib.slimefun.addon.AddonConfig;
import net.guizhanss.minecraft.guizhanlib.GuizhanLib;

import javax.annotation.Nonnull;

/**
 * The configuration manager for the plugin.
 *
 * @author ybw0014
 */
public final class ConfigManager {

    private final AddonConfig config;
    @Getter
    private final Config updaterConfig;

    @Getter
    private final boolean debugEnabled;
    @Getter
    private final boolean autoUpdateEnabled;
    @Getter
    private final String updaterLocation;
    @Getter
    private final String updaterLang;

    public ConfigManager(@Nonnull GuizhanLib plugin) {
        config = new AddonConfig(plugin, "config.yml");
        updaterConfig = new Config(plugin, "updater.yml");

        debugEnabled = config.getBoolean("debug", false);
        autoUpdateEnabled = config.getBoolean("auto-update", true);
        updaterLocation = config.getString("updater.location", "global");
        updaterLang = config.getString("updater.lang", "en_US");

        if (debugEnabled) {
            plugin.getLogger().info("Debug mode is enabled.");
            plugin.getLogger().info("Auto update is " + (autoUpdateEnabled ? "enabled" : "disabled") + ".");
            plugin.getLogger().info("Updater location is " + updaterLocation + ".");
        }
    }
}
