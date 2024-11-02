package net.guizhanss.minecraft.guizhanlib.gugu.minecraft.helpers;

import com.google.common.base.Preconditions;
import lombok.experimental.UtilityClass;
import net.guizhanss.guizhanlib.common.utils.StringUtil;
import net.guizhanss.minecraft.guizhanlib.gugu.localization.MinecraftLocalization;
import org.bukkit.Material;

import javax.annotation.Nonnull;

/**
 * {@link Material} 中文名称获取
 *
 * @author ybw0014
 */
@UtilityClass
@SuppressWarnings({"ConstantConditions", "unused"})
public final class MaterialHelper {

    @Nonnull
    public static String getKey(@Nonnull Material instance) {
        Preconditions.checkNotNull(instance);
        return instance.translationKey();
    }

    @Nonnull
    public static String getName(@Nonnull Material instance) {
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
        Material mat = Material.getMaterial(instance);
        if (mat == null) {
            return emptyString ? "" : StringUtil.humanize(instance);
        } else {
            return getName(mat);
        }
    }
}
