package net.guizhanss.minecraft.guizhanlib.utils;

import lombok.experimental.UtilityClass;
import net.guizhanss.guizhanlib.common.utils.StringUtil;
import org.bukkit.NamespacedKey;

import javax.annotation.Nonnull;
import java.util.Locale;

/**
 * {@link NamespacedKey} utilities.
 *
 * @author ybw0014
 */
@UtilityClass
public class NamespacedKeyUtils {

    /**
     * Get the minecraft {@link NamespacedKey} from the key.
     *
     * @param key The key
     * @return The minecraft {@link NamespacedKey}
     */
    @Nonnull
    public static NamespacedKey getMinecraft(@Nonnull String key) {
        return NamespacedKey.minecraft(StringUtil.dehumanize(key).toLowerCase(Locale.ROOT));
    }
}
