package net.guizhanss.guizhanlib.minecraft.helper;

import lombok.experimental.UtilityClass;
import org.bukkit.Material;

import javax.annotation.Nonnull;

/**
 * @deprecated 请使用 {@link net.guizhanss.minecraft.guizhanlib.gugu.minecraft.helpers.MaterialHelper} 代替
 */
@Deprecated(since = "2.0.0", forRemoval = true)
@UtilityClass
@SuppressWarnings("unused")
public final class MaterialHelper {

    @Nonnull
    public static String getName(@Nonnull Material material) {
        return net.guizhanss.minecraft.guizhanlib.gugu.minecraft.helpers.MaterialHelper.getName(material);
    }

    @Nonnull
    public static String getKey(@Nonnull Material material) {
        return net.guizhanss.minecraft.guizhanlib.gugu.minecraft.helpers.MaterialHelper.getKey(material);
    }

    @Nonnull
    public static String getName(@Nonnull String material) {
        return getName(material, false);
    }

    @Nonnull
    public static String getName(@Nonnull String material, boolean emptyString) {
        return net.guizhanss.minecraft.guizhanlib.gugu.minecraft.helpers.MaterialHelper.getName(material, emptyString);
    }
}
