package net.guizhanss.minecraft.guizhanlib.updater.universal.v2;

import net.guizhanss.guizhanlib.updater.UpdaterConfig;
import org.bukkit.plugin.Plugin;

import javax.annotation.Nonnull;
import java.io.File;

record UpdaterRecord(
    Plugin plugin,
    File file,
    String githubUser,
    String githubRepo,
    String githubBranch,
    UpdaterConfig updaterConfig
) {

    @Nonnull
    public String getConfigPath() {
        return githubUser + "-" + githubRepo + "-" + githubBranch;
    }
}
