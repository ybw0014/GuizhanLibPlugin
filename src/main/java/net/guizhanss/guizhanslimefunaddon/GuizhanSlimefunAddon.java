package net.guizhanss.guizhanslimefunaddon;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import net.guizhanss.guizhanlib.updater.GuizhanBuildsUpdater;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

public final class GuizhanSlimefunAddon extends JavaPlugin implements SlimefunAddon {

    private static GuizhanSlimefunAddon instance;

    @Override
    public void onEnable() {
        instance = this;

        setupMetrics();
        autoUpdate();
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    private void setupMetrics() {
        new Metrics(this, 114514);
    }

    private void autoUpdate() {
        if (getConfig().getBoolean("auto-update") &&
            getDescription().getVersion().startsWith("Build")) {
            new GuizhanBuildsUpdater(this, getFile(), "ybw0014", "GuizhanSlimefunAddon", "master", false).start();
        }
    }

    @Override
    public JavaPlugin getJavaPlugin() {
        return this;
    }

    @Override
    public String getBugTrackerURL() {
        return "https://github.com/ybw0014/GuizhanSlimefunAddon/issues";
    }

    public static GuizhanSlimefunAddon getInstance() {
        return instance;
    }
}
