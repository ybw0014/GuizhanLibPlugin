package net.guizhanss.minecraft.guizhanlib.gugu.localization;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.papermc.lib.PaperLib;
import net.guizhanss.minecraft.guizhanlib.GuizhanLib;
import net.guizhanss.minecraft.guizhanlib.utils.MinecraftVersionUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class LocalizationLoader {

    private static final Gson GSON = new Gson();
    private static final HttpClient HTTP_CLIENT = HttpClient.newBuilder()
        .connectTimeout(Duration.ofSeconds(5))
        .build();

    private final Map<String, String> lang = new HashMap<>();
    private final Logger logger;
    private final String fullVersion;
    private final File localeFile;

    public LocalizationLoader() {
        logger = GuizhanLib.getInstance().getLogger();
        fullVersion = MinecraftVersionUtils.getFullVersion();

        // create minecraft-lang folder if not exists
        File langFolder = new File(GuizhanLib.getInstance().getDataFolder(), "minecraft-lang");
        if (!langFolder.exists()) {
            langFolder.mkdirs();
        }

        localeFile = new File(GuizhanLib.getInstance().getDataFolder(), "minecraft-lang/" + fullVersion + ".json");

        prepareFile();
        loadFile();
    }

    public Map<String, String> getResult() {
        return Collections.unmodifiableMap(lang);
    }

    private void prepareFile() {
        logger.log(Level.INFO, "开始加载 Minecraft 本地化文件");
        logger.log(Level.INFO, "当前版本: " + fullVersion);

        try {
            if (!localeFile.exists()) {
                logger.log(Level.INFO, "当前版本的本地化文件不存在，正在尝试下载");

                String remoteUrl = "https://cdn.jsdelivr.net/gh/InventivetalentDev/minecraft-assets@" + fullVersion + "/assets/minecraft/lang/zh_cn.json";

                HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(remoteUrl))
                    .GET()
                    .timeout(Duration.ofSeconds(10))
                    .build();

                HttpResponse<InputStream> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofInputStream());
                InputStream inputStream = response.body();
                saveToFile(inputStream);

                logger.log(Level.INFO, "已下载当前版本的本地化文件");
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "加载 Minecraft 本地化资源时发生错误，尝试备用方案", e);
            prepareBackupFile();
        }
    }

    private void prepareBackupFile() {
        logger.log(Level.INFO, "开始加载本地备用 Minecraft 本地化文件（可能不是当前版本最新）");
        try {
            int mcVersion = PaperLib.getMinecraftVersion();
            InputStream input;
            while (mcVersion >= 18) {
                logger.log(Level.INFO, "尝试寻找 1." + mcVersion);
                final String filename = "/minecraft-lang/1." + mcVersion + "/zh_cn.json";
                input = GuizhanLib.getInstance().getClass().getResourceAsStream(filename);
                if (input != null) {
                    logger.log(Level.INFO, "正在加载 1." + mcVersion);

                    saveToFile(input);

                    logger.log(Level.INFO, "已加载 1." + mcVersion);
                    break;
                } else {
                    logger.log(Level.INFO, "1." + mcVersion + " 的本地化文件缺失，正在尝试加载上一个版本");
                    mcVersion--;
                }
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "加载本地备用 Minecraft 本地化资源失败", e);
        }
    }

    private void saveToFile(InputStream inputStream) throws Exception {
        FileOutputStream output = new FileOutputStream(localeFile);
        byte[] data = new byte[1024];
        int read;

        while ((read = inputStream.read(data, 0, 1024)) != -1) {
            output.write(data, 0, read);
        }

        inputStream.close();
        output.close();
    }

    private void loadFile() {
        try {
            FileInputStream stream = new FileInputStream(localeFile);
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                stream, StandardCharsets.UTF_8
            ));
            // @formatter:off
            Type type = new TypeToken<Map<String, String>>() {}.getType();
            // @formatter:on
            lang.putAll(GSON.fromJson(reader, type));

            logger.log(Level.INFO, "加载成功");
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "加载 Minecraft 本地化文件时发生错误", ex);
        }
    }
}
