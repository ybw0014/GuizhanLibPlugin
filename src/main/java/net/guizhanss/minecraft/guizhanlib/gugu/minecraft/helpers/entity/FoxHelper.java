package net.guizhanss.minecraft.guizhanlib.gugu.minecraft.helpers.entity;

import com.google.common.base.Preconditions;
import lombok.experimental.UtilityClass;
import net.guizhanss.guizhanlib.common.utils.StringUtil;
import org.bukkit.entity.Fox;
import org.bukkit.entity.Fox.Type;

import javax.annotation.Nonnull;
import java.util.Map;

/**
 * {@link Fox} 相关
 */
@UtilityClass
@SuppressWarnings({"ConstantConditions", "unused"})
public final class FoxHelper {

    private static final Map<Type, String> TYPE_MAP = Map.of(
        Type.RED, "红狐",
        Type.SNOW, "雪狐"
    );

    @Nonnull
    public static String getTypeName(@Nonnull Type type) {
        Preconditions.checkNotNull(type);
        return TYPE_MAP.getOrDefault(type, "未知");
    }


    @Nonnull
    public static String getTypeName(@Nonnull String type) {
        Preconditions.checkNotNull(type);
        try {
            Type inst = Type.valueOf(StringUtil.dehumanize(type));
            return getTypeName(inst);
        } catch (Exception ex) {
            return StringUtil.humanize(type);
        }
    }
}
