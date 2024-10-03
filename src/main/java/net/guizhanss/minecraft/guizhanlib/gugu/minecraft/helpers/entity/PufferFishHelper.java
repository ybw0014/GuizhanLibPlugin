package net.guizhanss.minecraft.guizhanlib.gugu.minecraft.helpers.entity;

import lombok.experimental.UtilityClass;
import org.bukkit.entity.Panda;

import javax.annotation.Nonnull;

/**
 * {@link Panda} 相关
 */
@UtilityClass
@SuppressWarnings({"unused"})
public final class PufferFishHelper {

    @Nonnull
    public static String getPuffState(int level) {
        return switch (level) {
            case 0 -> "未膨胀";
            case 1 -> "半膨胀";
            case 2 -> "完全膨胀";
            default -> "未知";
        };
    }
}
