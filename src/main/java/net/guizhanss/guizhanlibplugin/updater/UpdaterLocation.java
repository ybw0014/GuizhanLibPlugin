package net.guizhanss.guizhanlibplugin.updater;

import net.guizhanss.guizhanlib.updater.GuizhanBuildsCNUpdater;
import net.guizhanss.guizhanlib.updater.GuizhanBuildsUpdater;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * This class holds all available locations of Guizhan Builds.
 *
 * @author ybw0014
 */
public enum UpdaterLocation {
    /**
     * Global deployment by Cloudflare.
     * https://builds.guizhanss.net/
     */
    GLOBAL,
    /**
     * Mainland China mirror by Tencent Cloud.
     * https://builds.guizhanss.cn/
     */
    CN;

    @ParametersAreNonnullByDefault
    public static UpdaterLocation getLocation(String location) {
        try {
            return UpdaterLocation.valueOf(location.toUpperCase());
        } catch (IllegalArgumentException ex) {
            return UpdaterLocation.GLOBAL;
        }
    }
}
