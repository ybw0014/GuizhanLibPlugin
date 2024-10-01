package net.guizhanss.guizhanlib.minecraft.helper.inventory;

import lombok.experimental.UtilityClass;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

/**
 * @deprecated 请使用 {@link net.guizhanss.minecraft.guizhanlib.gugu.minecraft.helpers.inventory.ItemStackHelper} 代替
 */
@Deprecated(since = "2.0.0", forRemoval = true)
@UtilityClass
@SuppressWarnings("unused")
public final class ItemStackHelper {

    @Nonnull
    public static String getDisplayName(@Nonnull ItemStack itemStack) {
        return net.guizhanss.minecraft.guizhanlib.gugu.minecraft.helpers.inventory.ItemStackHelper.getDisplayName(itemStack);
    }

    @Nonnull
    public static String getName(@Nonnull ItemStack itemStack) {
        return net.guizhanss.minecraft.guizhanlib.gugu.minecraft.helpers.inventory.ItemStackHelper.getName(itemStack);
    }
}
