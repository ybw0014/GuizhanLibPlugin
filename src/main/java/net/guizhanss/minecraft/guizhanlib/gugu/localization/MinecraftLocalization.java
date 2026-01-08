package net.guizhanss.minecraft.guizhanlib.gugu.localization;

import com.google.common.base.Preconditions;
import com.google.gson.Gson;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;

/**
 * Minecraft 本地化资源管理
 */
@SuppressWarnings("ConstantConditions")
public final class MinecraftLocalization {

    private static final Gson GSON = new Gson();

    private static MinecraftLocalization instance;

    private final Map<String, String> lang;

    private MinecraftLocalization() {
        lang = new LocalizationLoader().getResult();
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
        Preconditions.checkNotNull(key);
        Preconditions.checkNotNull(instance, "MinecraftLocalization has not been initialized");

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
        Preconditions.checkNotNull(defaultVal);

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
