package net.guizhanss.guizhanlibplugin.updater;

import net.guizhanss.guizhanlib.updater.GuizhanBuildsCNUpdater;
import net.guizhanss.guizhanlib.updater.GuizhanBuildsUpdater;
import org.bukkit.plugin.Plugin;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.io.File;

/**
 * This is a wrapper updater.
 * <p>
 * Call {@link .start} to create an updater.
 *
 * @author ybw0014
 */
public class GuizhanBuildsUpdaterWrapper {
    private static GuizhanBuildsUpdaterWrapper instance;

    private boolean isSetup = false;
    private UpdaterLocation updaterLocation = UpdaterLocation.GLOBAL;

    /**
     * This is a singleton class, so no public constructor.
     */
    private GuizhanBuildsUpdaterWrapper() {
    }

    @ParametersAreNonnullByDefault
    public static void setup(String location) {
        getInstance().setupUpdater(location);
    }

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
            case CN -> new GuizhanBuildsCNUpdater(plugin, file, githubUser, githubRepo, githubBranch, checkOnly).start();
            case GLOBAL -> new GuizhanBuildsUpdater(plugin, file, githubUser, githubRepo, githubBranch, checkOnly).start();
        }
    }

    @ParametersAreNonnullByDefault
    private void setupUpdater(String location) {
        if (isSetup) {
            throw new IllegalStateException("GuizhanBuildsUpdaterWrapper has been setup already.");
        }
        this.updaterLocation = UpdaterLocation.getLocation(location);
        isSetup = true;
    }

    @Nonnull
    private static GuizhanBuildsUpdaterWrapper getInstance() {
        if (instance == null) {
            instance = new GuizhanBuildsUpdaterWrapper();
        }
        return instance;
    }
}
