package net.guizhanss.minecraft.guizhanlib.gugu.minecraft.helpers.attribute;

import com.google.common.base.Preconditions;
import lombok.experimental.UtilityClass;
import net.guizhanss.guizhanlib.common.utils.StringUtil;
import net.guizhanss.minecraft.guizhanlib.gugu.localization.MinecraftLocalization;
import org.bukkit.attribute.Attribute;

import javax.annotation.Nonnull;

/**
 * {@link Attribute} 中文名称获取
 *
 * @author ybw0014
 */
@UtilityClass
@SuppressWarnings({"ConstantConditions", "unused"})
public final class AttributeHelper {

    @Nonnull
    public static String getKey(@Nonnull Attribute instance) {
        Preconditions.checkNotNull(instance);
        return instance.translationKey();
    }

    @Nonnull
    public static String getName(@Nonnull Attribute instance) {
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
            Attribute attribute = Attribute.valueOf(StringUtil.dehumanize(instance));
            return getName(attribute);
        } catch (Exception ex) {
            return emptyString ? "" : StringUtil.humanize(instance);
        }
    }
}
