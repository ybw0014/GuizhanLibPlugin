package net.guizhanss.minecraft.guizhanlib.updater;

import lombok.experimental.UtilityClass;
import net.guizhanss.guizhanlib.updater.UpdaterConfig;
import net.guizhanss.minecraft.guizhanlib.GuizhanLib;
import org.bukkit.plugin.Plugin;

import javax.annotation.ParametersAreNonnullByDefault;
import java.io.File;

/**
 * The universal updater wrapper.
 *
 * @author ybw0014
 */
@UtilityClass
public final class GuizhanUpdater {
    /**
     * Call the corresponding updater.
     *
     * @param plugin       The {@link Plugin} instance
     * @param file         The {@link File} of plugin
     * @param githubUser   GitHub user
     * @param githubRepo   GitHub repository
     * @param githubBranch GitHub branch
     */
    @ParametersAreNonnullByDefault
    public static void start(
        Plugin plugin,
        File file,
        String githubUser,
        String githubRepo,
        String githubBranch
    ) {
        start(plugin, file, githubUser, githubRepo, githubBranch, UpdaterConfig.DEFAULT);
    }

    /**
     * Call the corresponding updater.
     *
     * @param plugin       The {@link Plugin} instance
     * @param file         The {@link File} of plugin
     * @param githubUser   GitHub user
     * @param githubRepo   GitHub repository
     * @param githubBranch GitHub branch
     * @param config       The {@link UpdaterConfig}
     */
    @ParametersAreNonnullByDefault
    public static void start(
        Plugin plugin,
        File file,
        String githubUser,
        String githubRepo,
        String githubBranch,
        UpdaterConfig config
    ) {
        GuizhanLib.getUniversalUpdater().add(plugin, file, githubUser, githubRepo, githubBranch, config);
    }
}
