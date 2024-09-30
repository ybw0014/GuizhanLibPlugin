package net.guizhanss.minecraft.guizhanlib.gugu.minecraft.helpers.block;

import com.google.common.base.Preconditions;
import lombok.experimental.UtilityClass;
import net.guizhanss.guizhanlib.common.utils.StringUtil;
import net.guizhanss.minecraft.guizhanlib.gugu.MinecraftLocalization;
import org.bukkit.block.Biome;

import javax.annotation.Nonnull;

/**
 * {@link Biome} 中文名称获取
 *
 * @author ybw0014
 */
@UtilityClass
@SuppressWarnings({"ConstantConditions", "unused"})
public final class BiomeHelper {

    @Nonnull
    public static String getKey(@Nonnull Biome instance) {
        Preconditions.checkNotNull(instance);
        return instance.translationKey();
    }

    @Nonnull
    public static String getName(@Nonnull Biome instance) {
        Preconditions.checkNotNull(instance);
        return MinecraftLocalization.getOrKey(getKey(instance));
    }

    @Nonnull
    public static String getName(@Nonnull String instance) {
        Preconditions.checkNotNull(instance);
        return getName(instance, false);
    }

    @Nonnull
    public static String getName(@Nonnull String instance, boolean emptyString) {
        Preconditions.checkNotNull(instance);
        try {
            Biome biome = Biome.valueOf(StringUtil.dehumanize(instance));
            return getName(biome);
        } catch (Exception ex) {
            return emptyString ? "" : StringUtil.humanize(instance);
        }
    }
}
