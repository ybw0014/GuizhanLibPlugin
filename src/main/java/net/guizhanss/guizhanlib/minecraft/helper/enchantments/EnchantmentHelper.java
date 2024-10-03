package net.guizhanss.guizhanlib.minecraft.helper.enchantments;

import lombok.experimental.UtilityClass;
import org.bukkit.enchantments.Enchantment;

import javax.annotation.Nonnull;

/**
 * @deprecated 请使用 {@link net.guizhanss.minecraft.guizhanlib.gugu.minecraft.helpers.enchantments.EnchantmentHelper} 代替
 */
@Deprecated(since = "2.0.0", forRemoval = true)
@UtilityClass
@SuppressWarnings("unused")
public final class EnchantmentHelper {

    @Nonnull
    public static String getName(@Nonnull Enchantment enchantment) {
        return net.guizhanss.minecraft.guizhanlib.gugu.minecraft.helpers.enchantments.EnchantmentHelper.getName(enchantment);
    }

    @Nonnull
    public static String getKey(@Nonnull Enchantment enchantment) {
        return net.guizhanss.minecraft.guizhanlib.gugu.minecraft.helpers.enchantments.EnchantmentHelper.getKey(enchantment);
    }

    @Nonnull
    public static String getName(@Nonnull String enchantment) {
        return getName(enchantment, false);
    }

    @Nonnull
    public static String getName(@Nonnull String enchantment, boolean emptyString) {
        return net.guizhanss.minecraft.guizhanlib.gugu.minecraft.helpers.enchantments.EnchantmentHelper.getName(enchantment, emptyString);
    }
}
