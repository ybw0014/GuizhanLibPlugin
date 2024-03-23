package net.guizhanss.guizhanlibplugin.updater;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import net.guizhanss.guizhanlib.updater.GuizhanBuildsCNUpdater;
import net.guizhanss.guizhanlib.updater.GuizhanBuildsUpdater;
import net.guizhanss.guizhanlibplugin.GuizhanLibPlugin;

import javax.annotation.Nonnull;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

@RequiredArgsConstructor
public class UniversalUpdaterTask implements Runnable {

    private static final Gson GSON = new Gson();

    private final UniversalUpdater updater;
    private Map<String, String> lang;

    @Override
    public void run() {
        // lang
        if (!loadLanguage()) {
            return;
        }

        // updater main
        final List<UpdaterRecord> records = updater.getRecords();
        log(Level.INFO, lang.get("loading"));
        log(Level.INFO, lang.get("loaded"), records.size());

        // run tasks
        for (int i = 0; i < records.size(); i++) {
            final UpdaterRecord record = records.get(i);
            log(Level.INFO, lang.get("task.running"), i + 1, record.plugin().getName());
            runTask(record);
        }
    }

    private boolean loadLanguage() {
        final GuizhanLibPlugin plugin = GuizhanLibPlugin.getInstance();
        InputStream stream;
        try {
            stream =
                plugin.getClass().getResourceAsStream("/updater/" + GuizhanLibPlugin.getConfigManager().getUpdaterLang() + ".json");
        } catch (Exception ex) {
            log(Level.WARNING, "Failed to load updater language file, using default en_US.");
            try {
                stream = plugin.getClass().getResourceAsStream("/updater/en_US.json");
            } catch (Exception e) {
                log(Level.SEVERE, "Failed to load default updater language file, please report this!");
                return false;
            }
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                stream, StandardCharsets.UTF_8
            ));
            // @formatter:off
            lang = GSON.fromJson(reader, new TypeToken<Map<String, String>>() {}.getType());
            // @formatter:on
            log(Level.INFO, lang.get("init"));
        } catch (Exception ex) {
            log(Level.SEVERE, ex, "Failed to load updater language file, please report this!");
            return false;
        }

        return true;
    }

    private void runTask(@Nonnull UpdaterRecord record) {
        // check if the plugin is disabled
        if (!record.plugin().isEnabled()) {
            log(Level.INFO, lang.get("task.disabled"), record.plugin().getName());
            return;
        }
        try {
            switch (UpdaterLocation.getLocation(GuizhanLibPlugin.getConfigManager().getUpdaterLocation())) {
                case CN -> new GuizhanBuildsCNUpdater(record.plugin(), record.file(), record.githubUser(),
                    record.githubRepo(), record.githubBranch(), record.updaterConfig()).start();
                case GLOBAL -> new GuizhanBuildsUpdater(record.plugin(), record.file(), record.githubUser(),
                    record.githubRepo(), record.githubBranch(), record.updaterConfig()).start();
            }
        } catch (Exception ex) {
            log(Level.SEVERE, ex, lang.get("task.failed"), record.plugin().getName());
        }
    }

    private void log(Level level, String message, Object... args) {
        GuizhanLibPlugin.log(level, message, args);
    }

    private void log(Level level, Throwable throwable, String message, Object... args) {
        GuizhanLibPlugin.log(level, throwable, message, args);
    }
}
