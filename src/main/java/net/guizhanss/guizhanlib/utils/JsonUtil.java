package net.guizhanss.guizhanlib.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import lombok.experimental.UtilityClass;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.BufferedReader;

/**
 * @deprecated 请使用 {@link net.guizhanss.guizhanlib.common.utils.JsonUtil} 代替
 */
@Deprecated(since = "2.0.0", forRemoval = true)
@UtilityClass
@SuppressWarnings("unused")
public final class JsonUtil {

    @Nonnull
    public static JsonElement parse(@Nonnull String json) {
        return JsonParser.parseString(json);
    }

    @Nonnull
    public static JsonElement parse(@Nonnull BufferedReader reader) {
        return JsonParser.parseReader(reader);
    }

    @Nonnull
    public static JsonElement parse(@Nonnull JsonReader reader) {
        return JsonParser.parseReader(reader);
    }

    @Nullable
    public static JsonElement getFromPath(@Nonnull JsonObject root, @Nonnull String path) {
        return net.guizhanss.guizhanlib.common.utils.JsonUtil.getFromPath(root, path);
    }

}
