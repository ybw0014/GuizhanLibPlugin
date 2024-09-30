package net.guizhanss.minecraft.guizhanlib.gugu.minecraft.helpers.entity;

import com.google.common.base.Preconditions;
import lombok.experimental.UtilityClass;
import net.guizhanss.guizhanlib.common.utils.StringUtil;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Horse.Color;
import org.bukkit.entity.Horse.Style;

import javax.annotation.Nonnull;
import java.util.Map;

/**
 * {@link Horse} 相关
 */
@UtilityClass
@SuppressWarnings({"ConstantConditions", "unused"})
public final class HorseHelper {

    private static final Map<Color, String> COLOR_MAP = Map.of(
        Color.BLACK, "黑色",
        Color.BROWN, "褐色",
        Color.CHESTNUT, "栗色",
        Color.CREAMY, "奶油色",
        Color.DARK_BROWN, "深褐色",
        Color.GRAY, "灰色",
        Color.WHITE, "白色"
    );

    private static final Map<Style, String> STYLE_MAP = Map.of(
        Style.BLACK_DOTS, "黑色斑点",
        Style.NONE, "无",
        Style.WHITE, "白色",
        Style.WHITE_DOTS, "白色斑点",
        Style.WHITEFIELD, "白色条纹"
    );

    @Nonnull
    public static String getColorName(@Nonnull Color color) {
        Preconditions.checkNotNull(color);
        return COLOR_MAP.getOrDefault(color, "未知");
    }

    @Nonnull
    public static String getColorName(@Nonnull String variant) {
        Preconditions.checkNotNull(variant);
        try {
            Color inst = Color.valueOf(StringUtil.dehumanize(variant));
            return getColorName(inst);
        } catch (Exception ex) {
            return StringUtil.humanize(variant);
        }
    }

    @Nonnull
    public static String getStyleName(@Nonnull Style style) {
        Preconditions.checkNotNull(style);
        return STYLE_MAP.getOrDefault(style, "未知");
    }

    @Nonnull
    public static String getStyleName(@Nonnull String variant) {
        Preconditions.checkNotNull(variant);
        try {
            Style inst = Style.valueOf(StringUtil.dehumanize(variant));
            return getStyleName(inst);
        } catch (Exception ex) {
            return StringUtil.humanize(variant);
        }
    }
}
