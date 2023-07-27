package net.guizhanss.guizhanlibplugin.updater;

import lombok.Getter;
import net.guizhanss.guizhanlib.updater.UpdaterConfig;
import net.guizhanss.guizhanlibplugin.GuizhanLibPlugin;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * This is a universal updater. It will put all the updater tasks in a queue and execute them one by one.
 *
 * @author ybw0014
 */
public final class UniversalUpdater {
    @Getter
    private final List<UpdaterRecord> records = new ArrayList<>();

    public void add(Plugin plugin, File file, String githubUser, String githubRepo, String githubBranch,
                    UpdaterConfig updaterConfig) {
        records.add(new UpdaterRecord(plugin, file, githubUser, githubRepo, githubBranch, updaterConfig));
    }

    public void start() {
        GuizhanLibPlugin.getScheduler().runAsync(1, new UniversalUpdaterTask(this));
    }
}
