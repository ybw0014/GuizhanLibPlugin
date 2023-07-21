package net.guizhanss.guizhanlibplugin;

import net.guizhanss.guizhanlib.slimefun.addon.AbstractAddon;
import net.guizhanss.guizhanlibplugin.config.ConfigManager;
import net.guizhanss.guizhanlibplugin.setup.MinecraftLanguageSetup;
import net.guizhanss.guizhanlibplugin.updater.GuizhanBuildsUpdaterWrapper;
import net.guizhanss.guizhanlibplugin.updater.GuizhanUpdater;
import org.bstats.bukkit.Metrics;
import org.bstats.charts.SimplePie;

import javax.annotation.Nonnull;
import java.lang.reflect.Field;
import java.util.logging.Level;

/**
 * GuizhanLib plugin version.
 *
 * @author ybw0014
 */
@SuppressWarnings("deprecated")
public final class GuizhanLibPlugin extends AbstractAddon {

    public GuizhanLibPlugin() {
        super("ybw0014", "GuizhanLibPlugin", "master", "auto-update");
    }

    private ConfigManager configManager;

    @Nonnull
    public static ConfigManager getConfigManager() {
        return inst().configManager;
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
    }

    @Override
    public void disable() {
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
        final String updaterLocation = getConfig().getString("updater-location", "GLOBAL");
        GuizhanBuildsUpdaterWrapper.setup(updaterLocation);
        GuizhanUpdater.setup(updaterLocation);

        if (configManager.isDebugEnabled()) {
            // enable debug messages in UpdaterTask
            try {
                Class<?> clazz = Class.forName("net.guizhanss.guizhanlib.updater.UpdaterTask");
                Field field = clazz.getDeclaredField("DEBUG");
                field.setAccessible(true);
                field.set(null, true);
            } catch (ClassNotFoundException | NoSuchFieldException | IllegalAccessException ex) {
                getLogger().log(Level.SEVERE, ex, ex::getMessage);
            }
        }
    }

    private void setupMetrics() {
        final Metrics metrics = new Metrics(this, 15713);

        metrics.addCustomChart(new SimplePie("auto_update", () -> String.valueOf(configManager.isAutoUpdateEnabled())));
    }

    @Override
    protected void autoUpdate() {
        if (getDescription().getVersion().startsWith("Build")) {
            GuizhanUpdater.start(this, getFile(), "ybw0014", "GuizhanLibPlugin", "master");
        }
    }
}
