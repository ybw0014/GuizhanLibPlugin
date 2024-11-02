package net.guizhanss.minecraft.guizhanlib.gugu.minecraft.helpers;

import com.google.common.base.Preconditions;
import lombok.experimental.UtilityClass;
import net.guizhanss.guizhanlib.common.utils.StringUtil;
import net.guizhanss.minecraft.guizhanlib.gugu.localization.MinecraftLocalization;
import org.bukkit.DyeColor;

import javax.annotation.Nonnull;
import java.util.Locale;

/**
 * {@link DyeColor} 中文名称获取
 *
 * @author ybw0014
 */
@UtilityClass
@SuppressWarnings({"ConstantConditions", "unused"})
public final class DyeColorHelper {

    @Nonnull
    public static String getKey(@Nonnull DyeColor instance) {
        Preconditions.checkNotNull(instance);
        return "color.minecraft." + instance.name().toLowerCase(Locale.ROOT);
    }

    @Nonnull
    public static String getName(@Nonnull DyeColor instance) {
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
            DyeColor color = DyeColor.valueOf(StringUtil.dehumanize(instance));
            return getName(color);
        } catch (Exception ex) {
            return emptyString ? "" : StringUtil.humanize(instance);
        }
    }
}

