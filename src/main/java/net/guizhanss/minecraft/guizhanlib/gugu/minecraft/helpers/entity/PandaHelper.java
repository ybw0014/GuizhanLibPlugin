package net.guizhanss.minecraft.guizhanlib.gugu.minecraft.helpers.entity;

import com.google.common.base.Preconditions;
import lombok.experimental.UtilityClass;
import net.guizhanss.guizhanlib.common.utils.StringUtil;
import org.bukkit.entity.Panda;
import org.bukkit.entity.Panda.Gene;

import javax.annotation.Nonnull;
import java.util.Map;

/**
 * {@link Panda} 相关
 */
@UtilityClass
@SuppressWarnings({"ConstantConditions", "unused"})
public final class PandaHelper {

    private static final Map<Gene, String> VARIANT_MAP = Map.of(
        Gene.AGGRESSIVE, "好斗",
        Gene.BROWN, "棕色",
        Gene.LAZY, "懒惰",
        Gene.NORMAL, "普通",
        Gene.PLAYFUL, "顽皮",
        Gene.WEAK, "虚弱",
        Gene.WORRIED, "发愁"
    );

    @Nonnull
    public static String getGeneName(@Nonnull Gene variant) {
        Preconditions.checkNotNull(variant);
        return VARIANT_MAP.getOrDefault(variant, "未知");
    }

    @Nonnull
    public static String getGeneName(@Nonnull String variant) {
        Preconditions.checkNotNull(variant);
        try {
            Gene inst = Gene.valueOf(StringUtil.dehumanize(variant));
            return getGeneName(inst);
        } catch (Exception ex) {
            return StringUtil.humanize(variant);
        }
    }
}
