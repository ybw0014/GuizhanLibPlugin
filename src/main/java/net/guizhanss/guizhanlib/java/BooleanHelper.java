package net.guizhanss.guizhanlib.java;

import lombok.experimental.UtilityClass;

import javax.annotation.Nonnull;

/**
 * @deprecated Only for backward compatibility. Use the corresponding class in the new package instead.
 */
@Deprecated(since = "2.0.0", forRemoval = true)
@UtilityClass
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
