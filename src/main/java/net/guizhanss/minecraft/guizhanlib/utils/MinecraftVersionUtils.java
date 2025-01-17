package net.guizhanss.minecraft.guizhanlib.utils;

import io.papermc.lib.PaperLib;
import lombok.experimental.UtilityClass;

import javax.annotation.Nonnull;

/**
 * Minecraft version utilities.
 *
 * @author ybw0014
 */
@UtilityClass
public final class MinecraftVersionUtils {

    /**
     * Get the full version of the Minecraft server. e.g. 1.20.6
     *
     * @return The full version of the Minecraft server
     */
    @Nonnull
    public static String getFullVersion() {
        String fullVersion = "1." + PaperLib.getMinecraftVersion();
        if (PaperLib.getMinecraftPatchVersion() > 0) {
            fullVersion += "." + PaperLib.getMinecraftPatchVersion();
        }
        return fullVersion;
    }
}
