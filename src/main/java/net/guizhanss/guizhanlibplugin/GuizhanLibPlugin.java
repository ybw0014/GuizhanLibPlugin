package net.guizhanss.guizhanlibplugin;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import net.guizhanss.guizhanlib.updater.GuizhanBuildsUpdater;
import net.guizhanss.guizhanlibplugin.setup.MinecraftLanguageSetup;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;

public final class GuizhanLibPlugin extends JavaPlugin implements SlimefunAddon {

    private static GuizhanLibPlugin instance;

    @Override
    public void onEnable() {
        instance = this;

        setupMetrics();
        autoUpdate();

        MinecraftLanguageSetup.setup(this);
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
            new GuizhanBuildsUpdater(this, getFile(), "ybw0014", "GuizhanLibPlugin", "master", false).start();
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
