package net.guizhanss.guizhanlibplugin;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import net.guizhanss.guizhanlibplugin.setup.MinecraftLanguageSetup;
import net.guizhanss.guizhanlibplugin.updater.GuizhanBuildsUpdaterWrapper;
import net.guizhanss.guizhanlibplugin.updater.UpdaterLocation;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;

public final class GuizhanLibPlugin extends JavaPlugin implements SlimefunAddon {

    private static GuizhanLibPlugin instance;

    @Override
    public void onEnable() {
        instance = this;

        MinecraftLanguageSetup.setup(this);

        final String updaterLocationCfg = getConfig().getString("updater-location", "GLOBAL");
        final UpdaterLocation updaterLocation = UpdaterLocation.getLocation(updaterLocationCfg);
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

    @Nonnull
    public static GuizhanLibPlugin getInstance() {
        if (instance == null) {
            throw new IllegalStateException("GuizhanLibPlugin is not loaded correctly.");
        }
        return instance;
    }
}
