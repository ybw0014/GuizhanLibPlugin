package net.guizhanss.guizhanlibplugin.updater;

import net.guizhanss.guizhanlib.updater.GuizhanBuildsCNUpdater;
import net.guizhanss.guizhanlib.updater.GuizhanBuildsUpdater;
import org.bukkit.plugin.Plugin;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.io.File;

/**
 * This is a wrapper updater. Deprecated in favor of {@link GuizhanUpdater}.
 * <p>
 * Call {@link #start(Plugin, File, String, String, String, boolean)} to create an updater.
 *
 * @author ybw0014
 */
@Deprecated(since = "1.3.0")
public final class GuizhanBuildsUpdaterWrapper {
    private static GuizhanBuildsUpdaterWrapper instance;

    private boolean isSetup = false;
    private UpdaterLocation updaterLocation = UpdaterLocation.GLOBAL;

    /**
     * This is a singleton class, so no public constructor.
     */
    private GuizhanBuildsUpdaterWrapper() {
    }

    /**
     * Initialize the wrapper.
     *
     * @param location
     *     The updater location from config
     */
    @ParametersAreNonnullByDefault
    public static void setup(String location) {
        getInstance().setupUpdater(location);
    }

    /**
     * Call the corresponding updater.
     *
     * @param plugin
     *     The {@link Plugin} instance
     * @param file
     *     The {@link File} of plugin
     * @param githubUser
     *     GitHub user
     * @param githubRepo
     *     GitHub repository
     * @param githubBranch
     *     GitHub branch
     * @param checkOnly
     *     Whether to check the version only, without downloading
     *
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
        switch (getInstance().updaterLocation) {
            case CN ->
                new GuizhanBuildsCNUpdater(plugin, file, githubUser, githubRepo, githubBranch, checkOnly).start();
            case GLOBAL ->
                new GuizhanBuildsUpdater(plugin, file, githubUser, githubRepo, githubBranch, checkOnly).start();
        }
    }

    @Nonnull
    private static GuizhanBuildsUpdaterWrapper getInstance() {
        if (instance == null) {
            instance = new GuizhanBuildsUpdaterWrapper();
        }
        return instance;
    }

    @ParametersAreNonnullByDefault
    private void setupUpdater(String location) {
        if (isSetup) {
            throw new IllegalStateException("GuizhanBuildsUpdaterWrapper has been setup already.");
        }
        this.updaterLocation = UpdaterLocation.getLocation(location);
        isSetup = true;
    }
}
