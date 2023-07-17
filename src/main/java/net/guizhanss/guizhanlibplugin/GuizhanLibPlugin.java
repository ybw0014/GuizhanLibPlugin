package net.guizhanss.guizhanlibplugin;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import net.guizhanss.guizhanlibplugin.setup.MinecraftLanguageSetup;
import net.guizhanss.guizhanlibplugin.updater.GuizhanBuildsUpdaterWrapper;
import net.guizhanss.guizhanlibplugin.updater.GuizhanUpdater;
import org.bstats.bukkit.Metrics;
import org.bstats.charts.SimplePie;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * GuizhanLib plugin version.
 *
 * @author ybw0014
 */
@SuppressWarnings("deprecated")
public final class GuizhanLibPlugin extends JavaPlugin implements SlimefunAddon {

    private static GuizhanLibPlugin instance;

    private boolean autoUpdateEnabled;

    /**
     * Get the instance of plugin.
     *
     * @return The instance of plugin
     *
     * @throws IllegalStateException
     *     When plugin is not enabled
     */
    @Nonnull
    public static GuizhanLibPlugin getInstance() {
        if (instance == null) {
            throw new IllegalStateException("GuizhanLibPlugin is disabled or not loaded correctly.");
        }
        return instance;
    }

    private static void setInstance(@Nullable GuizhanLibPlugin inst) {
        instance = inst;
    }

    @Override
    public void onEnable() {
        setInstance(this);

        saveDefaultConfig();

        MinecraftLanguageSetup.setup(this);

        final String updaterLocation = getConfig().getString("updater-location", "GLOBAL");
        GuizhanBuildsUpdaterWrapper.setup(updaterLocation);
        GuizhanUpdater.setup(updaterLocation);
        autoUpdateEnabled = getConfig().getBoolean("auto-update", true);

        setupMetrics();
        autoUpdate();
    }

    @Override
    public void onDisable() {
        setInstance(null);
    }

    private void setupMetrics() {
        final Metrics metrics = new Metrics(this, 15713);

        metrics.addCustomChart(new SimplePie("auto_update", () -> String.valueOf(autoUpdateEnabled)));
    }

    private void autoUpdate() {
        if (autoUpdateEnabled && getDescription().getVersion().startsWith("Build")) {
            GuizhanUpdater.start(this, getFile(), "ybw0014", "GuizhanLibPlugin", "master");
        }
    }

    @Override
    @Nonnull
    public JavaPlugin getJavaPlugin() {
        return this;
    }

    @Override
    public String getBugTrackerURL() {
        return "https://github.com/ybw0014/GuizhanLibPlugin/issues";
    }
}
