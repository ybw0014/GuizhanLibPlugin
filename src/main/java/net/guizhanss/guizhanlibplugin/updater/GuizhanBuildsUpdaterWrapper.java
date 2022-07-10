package net.guizhanss.guizhanlibplugin.updater;

import lombok.experimental.UtilityClass;
import net.guizhanss.guizhanlib.updater.GuizhanBuildsCNUpdater;
import net.guizhanss.guizhanlib.updater.GuizhanBuildsUpdater;
import org.bukkit.plugin.Plugin;

import javax.annotation.ParametersAreNonnullByDefault;
import java.io.File;

@UtilityClass
public class GuizhanBuildsUpdaterWrapper {
    private static boolean isSetup = false;
    private static UpdaterLocation updaterLocation = UpdaterLocation.GLOBAL;

    @ParametersAreNonnullByDefault
    public static void setup(UpdaterLocation location) {
        if (isSetup) {
            throw new IllegalStateException("GuizhanBuildsUpdaterWrapper has been setup already.");
        }
        updaterLocation = location;
        isSetup = true;
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
        switch (updaterLocation) {
            case CN -> new GuizhanBuildsCNUpdater(plugin, file, githubUser, githubRepo, githubBranch, checkOnly).start();
            case GLOBAL -> new GuizhanBuildsUpdater(plugin, file, githubUser, githubRepo, githubBranch, checkOnly).start();
        }
    }
}
