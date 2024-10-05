package net.guizhanss.guizhanlib.utils;

import lombok.experimental.UtilityClass;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @deprecated 请使用 {@link net.guizhanss.guizhanlib.common.utils.StringUtil} 代替
 */
@Deprecated(since = "2.0.0", forRemoval = true)
@UtilityClass
@SuppressWarnings("unused")
public final class StringUtil {

    @Nonnull
    public static String humanize(@Nonnull String str) {
        return net.guizhanss.guizhanlib.common.utils.StringUtil.humanize(str);
    }

    @Nonnull
    public static String dehumanize(@Nonnull String str) {
        return net.guizhanss.guizhanlib.common.utils.StringUtil.dehumanize(str);
    }

    @Nonnull
    public static String capitalize(@Nonnull String str) {
        return net.guizhanss.guizhanlib.common.utils.StringUtil.capitalize(str);
    }

    public static boolean isBlank(@Nullable String str) {
        return net.guizhanss.guizhanlib.common.utils.StringUtil.isBlank(str);
    }
}
