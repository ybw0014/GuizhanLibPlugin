package net.guizhanss.minecraft.guizhanlib.gugu.minecraft.helpers.entity;

import com.google.common.base.Preconditions;
import lombok.experimental.UtilityClass;
import net.guizhanss.guizhanlib.common.utils.StringUtil;
import net.guizhanss.minecraft.guizhanlib.gugu.MinecraftLocalization;
import org.bukkit.entity.TropicalFish;
import org.bukkit.entity.TropicalFish.Pattern;

import javax.annotation.Nonnull;

/**
 * {@link TropicalFish} 相关
 */
@UtilityClass
@SuppressWarnings({"ConstantConditions", "unused"})
public final class TropicalFishHelper {

    @Nonnull
    public static String getPatternKey(@Nonnull Pattern pattern) {
        Preconditions.checkNotNull(pattern);
        return "entity.minecraft.tropical_fish.type." + pattern.name().toLowerCase();
    }

    @Nonnull
    public static String getPatternName(@Nonnull Pattern pattern) {
        Preconditions.checkNotNull(pattern);
        return MinecraftLocalization.getOrDefault(getPatternKey(pattern), StringUtil.humanize(pattern.name()));
    }
}
