package net.guizhanss.guizhanlibplugin;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import net.guizhanss.guizhanlibplugin.setup.MinecraftLanguageSetup;
import net.guizhanss.guizhanlibplugin.updater.GuizhanBuildsUpdaterWrapper;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;

/**
 * GuizhanLib plugin version.
 *
 * @author ybw0014
 */
public final class GuizhanLibPlugin extends JavaPlugin implements SlimefunAddon {

    private static GuizhanLibPlugin instance;

    /**
     * Get the instance of plugin.
     *
     * @return The instance of plugin
     *
     * @throws IllegalStateException When plugin is not enabled
     */
    @Nonnull
    public static GuizhanLibPlugin getInstance() {
        if (instance == null) {
            throw new IllegalStateException("GuizhanLibPlugin is not loaded correctly.");
        }
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();

        MinecraftLanguageSetup.setup(this);

        final String updaterLocation = getConfig().getString("updater-location", "GLOBAL");
        GuizhanBuildsUpdaterWrapper.setup(updaterLocation);

        setupMetrics();
        autoUpdate();
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    private void setupMetrics() {
        final Metrics metrics = new Metrics(this, 15713);
    }

    private void autoUpdate() {
        if (getConfig().getBoolean("auto-update") &&
            getDescription().getVersion().startsWith("Build")) {
            GuizhanBuildsUpdaterWrapper.start(this, getFile(), "ybw0014", "GuizhanLibPlugin", "master", false);
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
