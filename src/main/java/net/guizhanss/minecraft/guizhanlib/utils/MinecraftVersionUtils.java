package net.guizhanss.minecraft.guizhanlib.utils;

import io.papermc.lib.PaperLib;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class MinecraftVersionUtils {

    public static String getFullVersion() {
        String fullVersion = "1." + PaperLib.getMinecraftVersion();
        if (PaperLib.getMinecraftPatchVersion() > 0) {
            fullVersion += "." + PaperLib.getMinecraftPatchVersion();
        }
        return fullVersion;
    }
}
