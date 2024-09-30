package net.guizhanss.minecraft.guizhanlib.gugu.minecraft.helpers.entity;

import com.google.common.base.Preconditions;
import lombok.experimental.UtilityClass;
import net.guizhanss.guizhanlib.common.utils.StringUtil;
import org.bukkit.entity.Cat;
import org.bukkit.entity.Cat.Type;

import javax.annotation.Nonnull;
import java.util.Map;

/**
 * {@link Cat} 相关
 */
@UtilityClass
@SuppressWarnings({"ConstantConditions", "unused"})
public final class CatHelper {

    private static final Map<Type, String> TYPE_MAP = Map.ofEntries(
        Map.entry(Type.ALL_BLACK, "黑猫"),
        Map.entry(Type.BLACK, "西服猫"),
        Map.entry(Type.BRITISH_SHORTHAIR, "英国短毛猫"),
        Map.entry(Type.CALICO, "花猫"),
        Map.entry(Type.JELLIE, "Jellie"),
        Map.entry(Type.PERSIAN, "波斯猫"),
        Map.entry(Type.RAGDOLL, "布偶猫"),
        Map.entry(Type.RED, "红虎斑猫"),
        Map.entry(Type.SIAMESE, "暹罗猫"),
        Map.entry(Type.TABBY, "虎斑猫"),
        Map.entry(Type.WHITE, "白猫")
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
