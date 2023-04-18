package net.guizhanss.guizhanlibplugin.updater;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * This class holds all available locations of Guizhan Builds.
 *
 * @author ybw0014
 */
enum UpdaterLocation {
    /**
     * Global deployment by Cloudflare.
     * <a href="https://builds.guizhanss.net/">https://builds.guizhanss.net/</a>
     */
    GLOBAL,
    /**
     * Mainland China mirror by Tencent Cloud.
     * <a href="https://builds.guizhanss.cn/">https://builds.guizhanss.cn/</a>
     */
    CN;

    @ParametersAreNonnullByDefault
    @Nonnull
    public static UpdaterLocation getLocation(String location) {
        try {
            return UpdaterLocation.valueOf(location.toUpperCase());
        } catch (IllegalArgumentException ex) {
            return UpdaterLocation.GLOBAL;
        }
    }
}
