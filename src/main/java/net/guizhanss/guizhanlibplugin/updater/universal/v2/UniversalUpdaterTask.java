package net.guizhanss.guizhanlibplugin.updater.universal.v2;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import net.guizhanss.guizhanlib.updater.GuizhanBuildsCNUpdater;
import net.guizhanss.guizhanlib.updater.GuizhanBuildsUpdater;
import net.guizhanss.guizhanlib.updater.LocaleKey;
import net.guizhanss.guizhanlibplugin.GuizhanLibPlugin;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.logging.Level;

@RequiredArgsConstructor
public class UniversalUpdaterTask implements Runnable {

    private static final String BUILD_PATH = "api/build/%s/%s/%s/latest?status=success";
    private static final String DOWNLOAD_PATH = "api/download/%s/%s/%s/%d";

    private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();
    private static final Gson GSON = new Gson();
    private static final UpdaterLocation location = UpdaterLocation.getLocation(GuizhanLibPlugin.getConfigManager().getUpdaterLocation());

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
            final UpdaterRecord r = records.get(i);
            log(Level.INFO, "task.running", i + 1, r.plugin().getName());
            runTask(r);
        }
    }

    private boolean loadLanguage() {
        final GuizhanLibPlugin plugin = GuizhanLibPlugin.getInstance();
        InputStream stream;
        try {
            stream =
                plugin.getClass().getResourceAsStream("/updater/" + GuizhanLibPlugin.getConfigManager().getUpdaterLang() + ".json");
        } catch (Exception ex) {
            GuizhanLibPlugin.log(Level.WARNING, "Failed to load updater language file, using default en_US.");
            try {
                stream = plugin.getClass().getResourceAsStream("/updater/en_US.json");
            } catch (Exception e) {
                GuizhanLibPlugin.log(Level.SEVERE, "Failed to load default updater language file, please report this!");
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
            log(Level.INFO, "init");
        } catch (Exception ex) {
            GuizhanLibPlugin.log(Level.SEVERE, ex, "Failed to load updater language file, please report this!");
            return false;
        }

        return true;
    }

    private void runTask(@Nonnull UpdaterRecord record) {
        // check if the plugin is disabled
        if (!record.plugin().isEnabled()) {
            log(Level.INFO, "task.disabled", record.plugin().getName());
            return;
        }

        try {
            var buildResp = fetchJson(URI.create(
                location.getBaseUrl() + String.format(BUILD_PATH, record.githubUser(), record.githubRepo(), record.githubBranch())
            ));
            if (buildResp == null) {
                throw new IllegalArgumentException(lang.get("tasks.no-info"));
            }
            var buildResponse = buildResp.getAsJsonObject();
            if (buildResponse.get("code").getAsInt() != 0) {
                throw new IllegalArgumentException(lang.get("tasks.no-info"));
            }

            JsonObject build = buildResponse.get("data").getAsJsonObject();

            if (isLatest(record.plugin(), build)) {
                log(Level.INFO, "task.no-update", record.plugin().getName());
                return;
            }

            // check if the requirement is check only
            if (record.updaterConfig().checkOnly()) {
                log(Level.INFO, "task.has-update", record.plugin().getName());
                log(Level.INFO, "task.check-only");
                return;
            }

            // download the latest build
            var buildId = build.get("id").getAsInt();
            var buildVersion = build.get("target").getAsString().replace(".jar", "").replace(record.plugin().getName() + "-", "");

            log(Level.INFO, "task.downloading", record.plugin().getName(), buildVersion);
            downloadBuild(record, buildId);
            log(Level.INFO, "task.downloaded", record.plugin().getName(), buildVersion);
        } catch (Exception ex) {
            log(Level.SEVERE, ex, "task.failed", record.plugin().getName());
        }
    }

    @Nullable
    private JsonElement fetchJson(URI uri) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();
            HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            return GSON.fromJson(response.body(), JsonElement.class);
        } catch (Exception ex) {
            log(Level.SEVERE, ex, "task.failed");
            return null;
        }
    }

    private boolean isLatest(Plugin plugin, JsonObject build) {
        var latestBuildVer = build.get("target").getAsString();
        var currentBuildVer = MessageFormat.format("{0}-{1}.jar", plugin.getName(), plugin.getDescription().getVersion());
        return latestBuildVer.equals(currentBuildVer);
    }

    private void downloadBuild(UpdaterRecord record, int buildId) throws Exception {
        BufferedInputStream input = new BufferedInputStream(URI.create(
            location.getBaseUrl() + String.format(DOWNLOAD_PATH, record.githubUser(), record.githubRepo(), record.githubBranch(), buildId)
        ).toURL().openStream());
        FileOutputStream output = new FileOutputStream(new File("plugins/" + record.plugin().getServer().getUpdateFolder(), record.file().getName()));
        byte[] data = new byte[1024];
        int read;

        while ((read = input.read(data, 0, 1024)) != -1) {
            output.write(data, 0, read);
        }

        input.close();
        output.close();
    }

    private void log(Level level, String key, Object... args) {
        String msg = lang.get(key) != null ? lang.get(key) : key;
        GuizhanLibPlugin.log(level, msg, args);
    }

    private void log(Level level, Throwable throwable, String key, Object... args) {
        String msg = lang.get(key) != null ? lang.get(key) : key;
        if (GuizhanLibPlugin.getConfigManager().isDebugEnabled()) {
            GuizhanLibPlugin.log(level, throwable, msg, args);
        } else {
            GuizhanLibPlugin.log(level, msg, args);
        }
    }
}
