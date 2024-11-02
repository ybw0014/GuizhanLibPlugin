package net.guizhanss.minecraft.guizhanlib.gugu.minecraft.helpers.enchantments;

import com.google.common.base.Preconditions;
import lombok.experimental.UtilityClass;
import net.guizhanss.guizhanlib.common.utils.StringUtil;
import net.guizhanss.minecraft.guizhanlib.gugu.localization.MinecraftLocalization;
import net.guizhanss.minecraft.guizhanlib.utils.NamespacedKeyUtils;
import org.bukkit.Registry;
import org.bukkit.enchantments.Enchantment;

import javax.annotation.Nonnull;

/**
 * {@link Enchantment} 中文名称获取
 *
 * @author ybw0014
 */
@UtilityClass
@SuppressWarnings({"ConstantConditions", "unused"})
public final class EnchantmentHelper {

    @Nonnull
    public static String getKey(@Nonnull Enchantment instance) {
        Preconditions.checkNotNull(instance);
        return instance.translationKey();
    }

    @Nonnull
    public static String getName(@Nonnull Enchantment instance) {
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
        Enchantment enchantment = Registry.ENCHANTMENT.get(NamespacedKeyUtils.getMinecraft(instance));
        if (enchantment == null) {
            return emptyString ? "" : StringUtil.humanize(instance);
        } else {
            return getName(enchantment);
        }
    }
}
