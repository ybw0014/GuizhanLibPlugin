package net.guizhanss.minecraft.guizhanlib.gugu.minecraft.helpers.entity;

import com.google.common.base.Preconditions;
import lombok.experimental.UtilityClass;
import net.guizhanss.guizhanlib.common.utils.StringUtil;
import org.bukkit.entity.Axolotl;
import org.bukkit.entity.Axolotl.Variant;

import javax.annotation.Nonnull;
import java.util.Map;

/**
 * {@link Axolotl} 相关
 */
@UtilityClass
@SuppressWarnings({"ConstantConditions", "unused"})
public final class AxolotlHelper {

    private static final Map<Variant, String> VARIANT_MAP = Map.of(
        Variant.LUCY, "粉红色",
        Variant.WILD, "棕色",
        Variant.GOLD, "金色",
        Variant.CYAN, "青色",
        Variant.BLUE, "蓝色"
    );

    @Nonnull
    public static String getVariantName(@Nonnull Variant variant) {
        Preconditions.checkNotNull(variant);
        return VARIANT_MAP.getOrDefault(variant, "未知");
    }


    @Nonnull
    public static String getVariantName(@Nonnull String variant) {
        Preconditions.checkNotNull(variant);
        try {
            Variant inst = Variant.valueOf(StringUtil.dehumanize(variant));
            return getVariantName(inst);
        } catch (Exception ex) {
            return StringUtil.humanize(variant);
        }
    }
}
