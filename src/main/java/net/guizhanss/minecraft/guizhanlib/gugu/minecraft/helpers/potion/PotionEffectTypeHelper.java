package net.guizhanss.minecraft.guizhanlib.gugu.minecraft.helpers.potion;

import com.google.common.base.Preconditions;
import lombok.experimental.UtilityClass;
import net.guizhanss.guizhanlib.common.utils.StringUtil;
import net.guizhanss.minecraft.guizhanlib.gugu.localization.MinecraftLocalization;
import net.guizhanss.minecraft.guizhanlib.utils.NamespacedKeyUtils;
import org.bukkit.Registry;
import org.bukkit.potion.PotionEffectType;

import javax.annotation.Nonnull;

/**
 * {@link PotionEffectType} 中文名称获取
 *
 * @author ybw0014
 */
@UtilityClass
@SuppressWarnings({"ConstantConditions", "unused"})
public final class PotionEffectTypeHelper {

    @Nonnull
    public static String getKey(@Nonnull PotionEffectType instance) {
        Preconditions.checkNotNull(instance);
        return instance.translationKey();
    }

    @Nonnull
    public static String getName(@Nonnull PotionEffectType instance) {
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
        PotionEffectType type = Registry.EFFECT.get(NamespacedKeyUtils.getMinecraft(instance));
        if (type == null) {
            return emptyString ? "" : StringUtil.humanize(instance);
        } else {
            return getName(type);
        }
    }
}
