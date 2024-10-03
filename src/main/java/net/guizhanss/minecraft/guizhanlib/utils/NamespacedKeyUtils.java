package net.guizhanss.minecraft.guizhanlib.utils;

import lombok.experimental.UtilityClass;
import net.guizhanss.guizhanlib.common.utils.StringUtil;
import org.bukkit.NamespacedKey;

import javax.annotation.Nonnull;
import java.util.Locale;

@UtilityClass
public class NamespacedKeyUtils {

    @Nonnull
    public static NamespacedKey getMinecraft(@Nonnull String key) {
        return NamespacedKey.minecraft(StringUtil.dehumanize(key).toLowerCase(Locale.ROOT));
    }
}
