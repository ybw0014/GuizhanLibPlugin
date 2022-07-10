package net.guizhanss.guizhanlibplugin.setup;

import io.github.thebusybiscuit.slimefun4.libraries.paperlib.PaperLib;
import lombok.experimental.UtilityClass;
import net.guizhanss.guizhanlib.minecraft.LanguageHelper;
import net.guizhanss.guizhanlibplugin.GuizhanLibPlugin;

import java.io.InputStream;

@UtilityClass
public final class MinecraftLanguageSetup {
    public static void setup(final GuizhanLibPlugin plugin) {
        final String minecraftVersion = "1." + PaperLib.getMinecraftVersion();
        final String filename = "/minecraft-langs/" + minecraftVersion + "/zh_cn.json";
        final InputStream stream = plugin.getResource(filename);

        if (stream != null) {
            LanguageHelper.loadFromStream(stream);
        } else {
            throw new UnsupportedOperationException("The language file for Minecraft version " + minecraftVersion + "is missing!");
        }
    }
}
