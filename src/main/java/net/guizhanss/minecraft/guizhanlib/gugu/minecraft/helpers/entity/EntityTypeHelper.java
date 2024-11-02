package net.guizhanss.minecraft.guizhanlib.gugu.minecraft.helpers.entity;

import com.google.common.base.Preconditions;
import lombok.experimental.UtilityClass;
import net.guizhanss.guizhanlib.common.utils.StringUtil;
import net.guizhanss.minecraft.guizhanlib.gugu.localization.MinecraftLocalization;
import org.bukkit.entity.EntityType;

import javax.annotation.Nonnull;

/**
 * {@link EntityType} 中文名称获取
 *
 * @author ybw0014
 */
@UtilityClass
@SuppressWarnings({"ConstantConditions", "unused"})
public final class EntityTypeHelper {

    @Nonnull
    public static String getKey(@Nonnull EntityType instance) {
        Preconditions.checkNotNull(instance);
        return instance.translationKey();
    }

    @Nonnull
    public static String getName(@Nonnull EntityType instance) {
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
            EntityType type = EntityType.valueOf(StringUtil.dehumanize(instance));
            return getName(type);
        } catch (Exception ex) {
            return emptyString ? "" : StringUtil.humanize(instance);
        }
    }
}
