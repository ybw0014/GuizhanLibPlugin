package net.guizhanss.minecraft.guizhanlib.gugu.minecraft.helpers.entity;

import com.google.common.base.Preconditions;
import lombok.experimental.UtilityClass;
import net.guizhanss.guizhanlib.common.utils.StringUtil;
import org.bukkit.entity.Rabbit;
import org.bukkit.entity.Rabbit.Type;

import javax.annotation.Nonnull;
import java.util.Map;

/**
 * {@link Rabbit} 相关
 */
@UtilityClass
@SuppressWarnings({"ConstantConditions", "unused"})
public final class RabbitHelper {

    private static final Map<Type, String> TYPE_MAP = Map.of(
        Type.BLACK, "黑色",
        Type.BLACK_AND_WHITE, "黑白相间",
        Type.BROWN, "褐色",
        Type.GOLD, "金色",
        Type.SALT_AND_PEPPER, "胡椒盐色",
        Type.THE_KILLER_BUNNY, "杀手兔",
        Type.WHITE, "白色"
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
