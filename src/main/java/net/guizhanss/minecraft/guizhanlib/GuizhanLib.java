package net.guizhanss.minecraft.guizhanlib;

import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import lombok.Getter;
import lombok.experimental.Accessors;
import net.guizhanss.guizhanlib.slimefun.addon.AbstractAddon;
import net.guizhanss.minecraft.guizhanlib.config.ConfigManager;
import net.guizhanss.minecraft.guizhanlib.gugu.localization.MinecraftLocalization;
import net.guizhanss.minecraft.guizhanlib.updater.GuizhanUpdater;
import net.guizhanss.minecraft.guizhanlib.updater.universal.v2.UniversalUpdater;
import org.bstats.bukkit.Metrics;
import org.bstats.charts.DrilldownPie;
import org.bstats.charts.SimplePie;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

/**
 * GuizhanLib plugin version.
 *
 * @author ybw0014
 */
@SuppressWarnings("deprecated")
public class GuizhanLib extends AbstractAddon {

    private final UniversalUpdater universalUpdater = new UniversalUpdater();
    private ConfigManager configManager;
    @Getter
    @Accessors(fluent = true)
    private final boolean isUnitTest;

    public GuizhanLib() {
        super("ybw0014", "GuizhanLibPlugin", "master", "auto-update");

        // a hacky way to check if mockbukkit is used
        isUnitTest = getClassLoader().getClass().getPackageName().startsWith("be.seeseemelk.mockbukkit");
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
    private static GuizhanLib inst() {
        return getInstance();
    }

    @Override
    public void enable() {
        configManager = new ConfigManager(this);

        setupMinecraftLanguage();

        if (!isUnitTest) {
            setupMetrics();
            universalUpdater.start();
        }
    }

    @Override
    public void disable() {
        // nothing to do
    }

    private void setupMinecraftLanguage() {
        try {
            MinecraftLocalization.load();
        } catch (Exception ex) {
            if (configManager.isDebugEnabled()) {
                getLogger().log(Level.SEVERE, ex, ex::getMessage);
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
            if (sfVersion.startsWith("Dev")) {
                branch = "Official Dev";
            } else if (sfVersion.startsWith("RC")) {
                branch = "Official RC";
            } else if (sfVersion.endsWith("-Insider")) {
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
        if (getPluginVersion().startsWith("Build")) {
            GuizhanUpdater.start(this, getFile(), "ybw0014", "GuizhanLibPlugin", "master");
        }
    }
}
