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
 * @deprecated For backward compatibility only. Use the {@link net.guizhanss.minecraft.guizhanlib.updater.GuizhanUpdater} in the new package instead.
 */
@Deprecated(since = "2.0.0", forRemoval = true)
@UtilityClass
public final class GuizhanUpdater {

    @ParametersAreNonnullByDefault
    public static void start(
        Plugin plugin,
        File file,
        String githubUser,
        String githubRepo,
        String githubBranch
    ) {
        net.guizhanss.minecraft.guizhanlib.updater.GuizhanUpdater.start(plugin, file, githubUser, githubRepo, githubBranch);
    }

    @ParametersAreNonnullByDefault
    public static void start(
        Plugin plugin,
        File file,
        String githubUser,
        String githubRepo,
        String githubBranch,
        UpdaterConfig config
    ) {
        net.guizhanss.minecraft.guizhanlib.updater.GuizhanUpdater.start(plugin, file, githubUser, githubRepo, githubBranch, config);
    }

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
        net.guizhanss.minecraft.guizhanlib.updater.GuizhanUpdater.start(plugin, file, githubUser, githubRepo, githubBranch, UpdaterConfig.builder().checkOnly(checkOnly).build());
    }
}
