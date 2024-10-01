package net.guizhanss.guizhanlib.minecraft.helper.attribute;

import lombok.experimental.UtilityClass;
import org.bukkit.attribute.Attribute;

import javax.annotation.Nonnull;

/**
 * @deprecated 请使用 {@link net.guizhanss.minecraft.guizhanlib.gugu.minecraft.helpers.attribute.AttributeHelper} 代替
 */
@Deprecated(since = "2.0.0", forRemoval = true)
@UtilityClass
@SuppressWarnings("unused")
public final class AttributeHelper {

    @Nonnull
    public static String getName(@Nonnull Attribute attribute) {
        return net.guizhanss.minecraft.guizhanlib.gugu.minecraft.helpers.attribute.AttributeHelper.getName(attribute);
    }

    @Nonnull
    public static String getKey(@Nonnull Attribute attribute) {
        return net.guizhanss.minecraft.guizhanlib.gugu.minecraft.helpers.attribute.AttributeHelper.getKey(attribute);
    }

    @Nonnull
    public static String getName(@Nonnull String attribute) {
        return getName(attribute, false);
    }

    @Nonnull
    public static String getName(@Nonnull String attribute, boolean emptyString) {
        return net.guizhanss.minecraft.guizhanlib.gugu.minecraft.helpers.attribute.AttributeHelper.getName(attribute, emptyString);
    }
}
