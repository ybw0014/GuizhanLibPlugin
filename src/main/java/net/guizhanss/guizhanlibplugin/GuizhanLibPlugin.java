package net.guizhanss.guizhanlibplugin;

import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import net.guizhanss.guizhanlib.slimefun.addon.AbstractAddon;
import net.guizhanss.guizhanlibplugin.config.ConfigManager;
import net.guizhanss.guizhanlibplugin.setup.MinecraftLanguageSetup;
import net.guizhanss.guizhanlibplugin.updater.GuizhanUpdater;
import net.guizhanss.guizhanlibplugin.updater.universal.v2.UniversalUpdater;
import org.bstats.bukkit.Metrics;
import org.bstats.charts.DrilldownPie;
import org.bstats.charts.SimplePie;

import javax.annotation.Nonnull;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

/**
 * GuizhanLib plugin version.
 *
 * @author ybw0014
 */
@SuppressWarnings("deprecated")
public final class GuizhanLibPlugin extends AbstractAddon {

    private final UniversalUpdater universalUpdater = new UniversalUpdater();
    private ConfigManager configManager;

    public GuizhanLibPlugin() {
        super("ybw0014", "GuizhanLibPlugin", "master", "auto-update");
    }

    @Nonnull
    public static ConfigManager getConfigManager() {
        return inst().configManager;
    }

    @Nonnull
    public static UniversalUpdater getUniversalUpdater() {
        return inst().universalUpdater;
    }

    @Nonnull
    private static GuizhanLibPlugin inst() {
        return getInstance();
    }

    @Override
    public void enable() {
        configManager = new ConfigManager(this);

        setupMinecraftLanguage();
        setupUpdater();
        setupMetrics();
        universalUpdater.start();
    }

    @Override
    public void disable() {
        // nothing to do
    }

    private void setupMinecraftLanguage() {
        try {
            MinecraftLanguageSetup.setup(this);
        } catch (Exception ex) {
            if (configManager.isDebugEnabled()) {
                getLogger().log(Level.SEVERE, ex, ex::getMessage);
            }
        }
    }

    private void setupUpdater() {
        if (configManager.isDebugEnabled()) {
            // enable debug messages in UpdaterTask
            try {
                Class<?> clazz = Class.forName("net.guizhanss.guizhanlib.updater.UpdaterTask");
                Field field = clazz.getDeclaredField("debug");
                field.setAccessible(true);
                field.set(null, true);
            } catch (ClassNotFoundException | NoSuchFieldException | IllegalAccessException ex) {
                getLogger().log(Level.SEVERE, ex, () -> "An error occurred while enabling debug messages in auto " +
                    "updater");
            }
        }
    }

    private void setupMetrics() {
        final Metrics metrics = new Metrics(this, 15713);

        metrics.addCustomChart(new SimplePie("auto_update", () -> String.valueOf(configManager.isAutoUpdateEnabled())));
        metrics.addCustomChart(new SimplePie("updater_location", () -> configManager.getUpdaterLocation()));
        metrics.addCustomChart(new SimplePie("updater_lang", () -> configManager.getUpdaterLang()));
        metrics.addCustomChart(new DrilldownPie("slimefun_version", () -> {
            Map<String, Map<String, Integer>> outerMap = new HashMap<>();
            Map<String, Integer> innerMap = new HashMap<>();
            String sfVersion = Slimefun.getVersion();
            String branch = "Other";

            innerMap.put(sfVersion, 1);
            if (sfVersion.endsWith("-Insider")) {
                branch = "Insider";
            } else if (sfVersion.endsWith("-canary") || sfVersion.endsWith("-Beta")) {
                branch = "Beta";
            } else if (sfVersion.endsWith("-release")) {
                branch = "Release";
            }
            outerMap.put(branch, innerMap);

            return outerMap;
        }));
    }

    @Override
    protected void autoUpdate() {
        if (getDescription().getVersion().startsWith("Build")) {
            GuizhanUpdater.start(this, getFile(), "ybw0014", "GuizhanLibPlugin", "master");
        }
    }
}
