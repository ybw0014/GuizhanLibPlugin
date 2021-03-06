package net.guizhanss.guizhanlibplugin.setup;

import io.github.thebusybiscuit.slimefun4.libraries.paperlib.PaperLib;
import lombok.experimental.UtilityClass;
import net.guizhanss.guizhanlib.minecraft.LanguageHelper;
import net.guizhanss.guizhanlibplugin.GuizhanLibPlugin;

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
     * @param plugin The plugin instance
     */
    @ParametersAreNonnullByDefault
    public static void setup(final GuizhanLibPlugin plugin) {
        final String minecraftVersion = "1." + PaperLib.getMinecraftVersion();
        final String filename = "/minecraft-lang/" + minecraftVersion + "/zh_cn.json";
        final InputStream stream = plugin.getClass().getResourceAsStream(filename);

        if (stream != null) {
            plugin.getLogger().log(Level.INFO, "Loading language file for Minecraft version " + minecraftVersion);
            LanguageHelper.loadFromStream(stream);
        } else {
            throw new UnsupportedOperationException("The language file for Minecraft version " + minecraftVersion + " is missing!");
        }
    }
}
