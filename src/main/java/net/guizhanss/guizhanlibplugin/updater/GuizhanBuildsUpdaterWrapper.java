package net.guizhanss.guizhanlibplugin.updater;

import lombok.experimental.UtilityClass;
import net.guizhanss.guizhanlib.updater.UpdaterConfig;
import org.bukkit.plugin.Plugin;

import javax.annotation.ParametersAreNonnullByDefault;
import java.io.File;

/**
 * The universal updater wrapper.
 *
 * @author ybw0014
 * @deprecated This class is deprecated, use {@link GuizhanUpdater} instead.
 */
@Deprecated(since = "1.3.0")
@UtilityClass
public final class GuizhanBuildsUpdaterWrapper {
    /**
     * Call the corresponding updater.
     *
     * @param plugin       The {@link Plugin} instance
     * @param file         The {@link File} of plugin
     * @param githubUser   GitHub user
     * @param githubRepo   GitHub repository
     * @param githubBranch GitHub branch
     * @param checkOnly    Whether to check the version only, without downloading
     * @deprecated Use {@link GuizhanUpdater} instead.
     */
    @Deprecated
    @ParametersAreNonnullByDefault
    public static void start(
        Plugin plugin,
        File file,
        String githubUser,
        String githubRepo,
        String githubBranch,
        boolean checkOnly
    ) {
        GuizhanUpdater.start(plugin, file, githubUser, githubRepo, githubBranch,
            UpdaterConfig.builder().checkOnly(checkOnly).build());
    }
}
