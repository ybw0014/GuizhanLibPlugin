package net.guizhanss.guizhanlibplugin.setup;

import io.github.thebusybiscuit.slimefun4.libraries.paperlib.PaperLib;
import lombok.experimental.UtilityClass;
import net.guizhanss.guizhanlib.minecraft.LanguageHelper;
import net.guizhanss.guizhanlibplugin.GuizhanLibPlugin;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.io.InputStream;
import java.util.logging.Level;

/**
 * This setup the Guizhan-minecraft module, inject the language file with current minecraft version.
 *
 * @author ybw0014
 */
@UtilityClass
public final class MinecraftLanguageSetup {
    /**
     * Set up the Guizhan-minecraft module.
     *
     * @param plugin
     *     The plugin instance
     */
    @ParametersAreNonnullByDefault
    public static void setup(final GuizhanLibPlugin plugin) {
        int mcVersion = PaperLib.getMinecraftVersion();
        InputStream stream;
        while (mcVersion >= 16) {
            plugin.getLogger().log(Level.INFO, "Finding language file for Minecraft version 1." + mcVersion);
            stream = getLanguageStream(plugin, "1." + mcVersion);
            if (stream != null) {
                plugin.getLogger().log(Level.INFO, "Loading language file for Minecraft version 1." + mcVersion);
                LanguageHelper.loadFromStream(stream);
                return;
            } else {
                plugin.getLogger().log(Level.INFO, "The language file for Minecraft version 1." + mcVersion + "is " +
                    "missing, finding the language file of previous version");
                mcVersion--;
            }
        }

        plugin.getLogger().log(Level.SEVERE, "There is no available language file, you are using an unsupported " +
            "Minecraft version!");
    }

    @Nullable
    @ParametersAreNonnullByDefault
    private static InputStream getLanguageStream(final GuizhanLibPlugin plugin, String minecraftVersion) {
        final String filename = "/minecraft-lang/" + minecraftVersion + "/zh_cn.json";
        return plugin.getClass().getResourceAsStream(filename);
    }
}
