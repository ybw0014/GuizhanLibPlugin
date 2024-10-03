package net.guizhanss.minecraft.guizhanlib.gugu.minecraft.helpers.entity;

import com.google.common.base.Preconditions;
import lombok.experimental.UtilityClass;
import net.guizhanss.guizhanlib.common.utils.StringUtil;
import net.guizhanss.minecraft.guizhanlib.gugu.MinecraftLocalization;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;
import org.bukkit.entity.Villager.Type;

import javax.annotation.Nonnull;
import java.util.Map;

/**
 * {@link Villager} 相关
 */
@UtilityClass
@SuppressWarnings({"ConstantConditions", "unused"})
public final class VillagerHelper {

    private static final Map<Type, String> TYPE_MAP = Map.of(
        Type.PLAINS, "平原",
        Type.DESERT, "沙漠",
        Type.JUNGLE, "丛林",
        Type.SAVANNA, "热带草原",
        Type.SNOW, "雪地",
        Type.SWAMP, "沼泽",
        Type.TAIGA, "针叶林"
    );

    @Nonnull
    public static String getProfessionKey(@Nonnull Profession profession) {
        Preconditions.checkNotNull(profession);
        return profession.translationKey();
    }

    @Nonnull
    public static String getProfessionName(@Nonnull Profession profession) {
        Preconditions.checkNotNull(profession);
        return MinecraftLocalization.getOrDefault(getProfessionKey(profession), StringUtil.humanize(profession.name()));
    }

    @Nonnull
    public static String getTypeName(@Nonnull Type type) {
        Preconditions.checkNotNull(type);
        return TYPE_MAP.getOrDefault(type, "未知");
    }
}
