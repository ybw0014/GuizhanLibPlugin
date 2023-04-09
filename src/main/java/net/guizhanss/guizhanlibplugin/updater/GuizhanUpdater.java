package net.guizhanss.guizhanlibplugin.updater;

import net.guizhanss.guizhanlib.updater.GuizhanBuildsCNUpdater;
import net.guizhanss.guizhanlib.updater.GuizhanBuildsUpdater;
import net.guizhanss.guizhanlib.updater.UpdaterConfig;
import org.bukkit.plugin.Plugin;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.io.File;

/**
 * This is almost same wrapper with {@link GuizhanBuildsUpdaterWrapper}, but have a shorter name and have more options.
 * <p>
 * Call {@link #start(Plugin, File, String, String, String, boolean)} to create an updater.
 *
 * @author ybw0014
 */
public final class GuizhanUpdater {
    private static GuizhanUpdater instance;

    private boolean isSetup = false;
    private UpdaterLocation updaterLocation = UpdaterLocation.GLOBAL;

    /**
     * This is a singleton class, so no public constructor.
     */
    private GuizhanUpdater() {
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
     */
    @ParametersAreNonnullByDefault
    public static void start(
        Plugin plugin,
        File file,
        String githubUser,
        String githubRepo,
        String githubBranch
    ) {
        start(plugin, file, githubUser, githubRepo, githubBranch, new UpdaterConfig());
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
     * @param config
     *     The {@link UpdaterConfig}
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
        switch (getInstance().updaterLocation) {
            case CN -> new GuizhanBuildsCNUpdater(plugin, file, githubUser, githubRepo, githubBranch, config).start();
            case GLOBAL -> new GuizhanBuildsUpdater(plugin, file, githubUser, githubRepo, githubBranch, config).start();
        }
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
     * @deprecated In favor of new config system.
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
    private static GuizhanUpdater getInstance() {
        if (instance == null) {
            instance = new GuizhanUpdater();
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
