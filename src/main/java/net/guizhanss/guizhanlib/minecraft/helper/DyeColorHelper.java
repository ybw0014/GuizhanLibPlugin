package net.guizhanss.guizhanlib.minecraft.helper;

import lombok.experimental.UtilityClass;
import org.bukkit.DyeColor;

import javax.annotation.Nonnull;

/**
 * @deprecated 请使用 {@link net.guizhanss.minecraft.guizhanlib.gugu.minecraft.helpers.DyeColorHelper} 代替
 */
@Deprecated(since = "2.0.0", forRemoval = true)
@UtilityClass
@SuppressWarnings("unused")
public final class DyeColorHelper {

    @Nonnull
    public static String getName(@Nonnull DyeColor dyeColor) {
        return net.guizhanss.minecraft.guizhanlib.gugu.minecraft.helpers.DyeColorHelper.getName(dyeColor);
    }

    @Nonnull
    public static String getKey(@Nonnull DyeColor dyeColor) {
        return net.guizhanss.minecraft.guizhanlib.gugu.minecraft.helpers.DyeColorHelper.getKey(dyeColor);
    }

    @Nonnull
    public static String getName(@Nonnull String dyeColor) {
        return getName(dyeColor, false);
    }

    @Nonnull
    public static String getName(@Nonnull String dyeColor, boolean emptyString) {
        return net.guizhanss.minecraft.guizhanlib.gugu.minecraft.helpers.DyeColorHelper.getName(dyeColor, emptyString);
    }
}
