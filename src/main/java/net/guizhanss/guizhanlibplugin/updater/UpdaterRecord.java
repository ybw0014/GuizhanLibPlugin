package net.guizhanss.guizhanlibplugin.updater;

import net.guizhanss.guizhanlib.updater.UpdaterConfig;
import org.bukkit.plugin.Plugin;

import java.io.File;

record UpdaterRecord(
    Plugin plugin,
    File file,
    String githubUser,
    String githubRepo,
    String githubBranch,
    UpdaterConfig updaterConfig
) {
}
