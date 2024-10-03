package net.guizhanss.guizhanlib.minecraft.helper.block;

import lombok.experimental.UtilityClass;
import org.bukkit.block.Biome;

import javax.annotation.Nonnull;

/**
 * @deprecated 请使用 {@link net.guizhanss.minecraft.guizhanlib.gugu.minecraft.helpers.block.BiomeHelper} 代替
 */
@Deprecated(since = "2.0.0", forRemoval = true)
@UtilityClass
@SuppressWarnings("unused")
public final class BiomeHelper {

    @Nonnull
    public static String getName(@Nonnull Biome biome) {
        return net.guizhanss.minecraft.guizhanlib.gugu.minecraft.helpers.block.BiomeHelper.getName(biome);
    }

    @Nonnull
    public static String getKey(@Nonnull Biome biome) {
        return net.guizhanss.minecraft.guizhanlib.gugu.minecraft.helpers.block.BiomeHelper.getKey(biome);
    }

    @Nonnull
    public static String getName(@Nonnull String biome) {
        return getName(biome, false);
    }

    @Nonnull
    public static String getName(@Nonnull String biome, boolean emptyString) {
        return net.guizhanss.minecraft.guizhanlib.gugu.minecraft.helpers.block.BiomeHelper.getName(biome, emptyString);
    }
}
