package net.guizhanss.minecraft.guizhanlib.gugu;

import com.google.common.base.Preconditions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.papermc.lib.PaperLib;
import net.guizhanss.minecraft.guizhanlib.GuizhanLib;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

/**
 * Minecraft 本地化资源管理
 */
@SuppressWarnings("ConstantConditions")
public class MinecraftLocalization {

    private static final Gson GSON = new Gson();

    private static MinecraftLocalization instance;

    private Map<String, String> lang = new HashMap<>();

    private MinecraftLocalization() {
        var logger = GuizhanLib.getInstance().getLogger();
        try {
            logger.log(Level.INFO, "开始加载 Minecraft 本地化文件");
            int mcVersion = PaperLib.getMinecraftVersion();
            InputStream stream;
            while (mcVersion >= 16) {
                logger.log(Level.INFO, "尝试寻找 1." + mcVersion);
                final String filename = "/minecraft-lang/1." + mcVersion + "/zh_cn.json";
                stream = GuizhanLib.getInstance().getClass().getResourceAsStream(filename);
                if (stream != null) {
                    logger.log(Level.INFO, "正在加载 1." + mcVersion);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(
                        stream, StandardCharsets.UTF_8
                    ));
                    // @formatter:off
                    Type type = new TypeToken<Map<String, String>>() {}.getType();
                    // @formatter:on
                    lang = GSON.fromJson(reader, type);
                    logger.log(Level.INFO, "已加载 1." + mcVersion);
                    return;
                } else {
                    logger.log(Level.INFO, "1." + mcVersion + " 的本地化文件缺失，正在尝试加载上一个版本");
                    mcVersion--;
                }
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "加载 Minecraft 本地化资源失败", e);
        }
    }

    /**
     * 加载 Minecraft 本地化资源。应仅在 GuizhanLibPlugin 启用时调用。
     */
    public static void load() {
        if (instance != null) {
            throw new IllegalStateException("MinecraftLocalization has already been initialized");
        }

        instance = new MinecraftLocalization();
    }

    /**
     * 获取本地化文件中指定键名的内容
     *
     * @param key {@link String} 键名
     * @return 内容，如果不存在则返回 null
     */
    @Nullable
    public static String getOrNull(@Nonnull String key) {
        Preconditions.checkArgument(key != null, "键名不能为空");

        return instance.lang.get(key);
    }

    /**
     * 获取本地化文件中指定键名的内容
     *
     * @param key        {@link String} 键名
     * @param defaultVal 默认值
     * @return 内容，如果不存在则返回默认值
     */
    @Nonnull
    public static String getOrDefault(@Nonnull String key, @Nonnull String defaultVal) {
        String lang = getOrNull(key);
        return lang != null ? lang : defaultVal;
    }

    /**
     * 获取本地化文件中指定键名的内容
     *
     * @param key {@link String} 键名
     * @return 内容，如果不存在则返回键名
     */
    @Nonnull
    public static String getOrKey(@Nonnull String key) {
        return getOrDefault(key, key);
    }

}
