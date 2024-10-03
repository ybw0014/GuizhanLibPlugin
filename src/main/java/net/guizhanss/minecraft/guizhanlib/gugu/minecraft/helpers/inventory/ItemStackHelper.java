package net.guizhanss.minecraft.guizhanlib.gugu.minecraft.helpers.inventory;

import com.google.common.base.Preconditions;
import lombok.experimental.UtilityClass;
import net.guizhanss.minecraft.guizhanlib.gugu.MinecraftLocalization;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

/**
 * {@link ItemStack} 中文名称获取
 *
 * @author ybw0014
 */
@UtilityClass
@SuppressWarnings({"ConstantConditions", "unused", "deprecation"})
public final class ItemStackHelper {

    @Nonnull
    public static String getKey(@Nonnull ItemStack instance) {
        Preconditions.checkNotNull(instance);
        return instance.translationKey();
    }

    @Nonnull
    public static String getName(@Nonnull ItemStack instance) {
        Preconditions.checkNotNull(instance);
        return MinecraftLocalization.getOrKey(getKey(instance));
    }

    @Nonnull
    public static String getDisplayName(@Nonnull ItemStack item) {
        Preconditions.checkNotNull(item);
        if (item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
            return item.getItemMeta().getDisplayName();
        } else {
            return getName(item);
        }
    }
}
