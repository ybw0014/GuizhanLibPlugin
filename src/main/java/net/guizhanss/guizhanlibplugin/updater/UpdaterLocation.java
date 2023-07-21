package net.guizhanss.guizhanlibplugin.updater;

import lombok.Getter;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * This class holds all available locations of Guizhan Builds.
 *
 * @author ybw0014
 */
enum UpdaterLocation {
    /**
     * Cloudflare Pages.
     * <a href="https://builds.guizhanss.com/">https://builds.guizhanss.com/</a>
     */
    GLOBAL("https://builds.guizhanss.com/"),

    /**
     * China mainland mirror by Tencent Cloud.
     * <a href="https://builds.guizhanss.cn/">https://builds.guizhanss.cn/</a>
     */
    CN("https://builds.guizhanss.cn/");

    @Getter
    private final String baseUrl;

    UpdaterLocation(String baseUrl) {
        this.baseUrl = baseUrl;
    }

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
