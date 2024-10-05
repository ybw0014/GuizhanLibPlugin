package net.guizhanss.guizhanlib.java;

import lombok.experimental.UtilityClass;

import javax.annotation.Nonnull;

/**
 * @deprecated 请使用 {@link net.guizhanss.minecraft.guizhanlib.gugu.java.BooleanHelper} 代替
 */
@Deprecated(since = "2.0.0", forRemoval = true)
@UtilityClass
@SuppressWarnings("unused")
public final class BooleanHelper {

    @Nonnull
    public static String yesOrNo(boolean value) {
        return net.guizhanss.minecraft.guizhanlib.gugu.java.BooleanHelper.yesOrNo(value);
    }

    @Nonnull
    public static String enabledOrDisabled(boolean value) {
        return net.guizhanss.minecraft.guizhanlib.gugu.java.BooleanHelper.enabledOrDisabled(value);
    }
}
