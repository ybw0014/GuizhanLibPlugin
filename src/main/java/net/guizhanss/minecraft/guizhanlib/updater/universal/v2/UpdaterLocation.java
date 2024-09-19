package net.guizhanss.minecraft.guizhanlib.updater.universal.v2;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * This class holds all available locations of Guizhan Builds.
 *
 * @author ybw0014
 */
@Getter
@AllArgsConstructor
enum UpdaterLocation {
    /**
     * Cloudflare Pages.
     */
    GLOBAL("https://builds.guizhanss.com/", "https://builds-r2.gzassets.com/"),

    /**
     * China mainland mirror by Tencent Cloud.
     *
     * @deprecated This location is not available right now.
     */
    @Deprecated
    CN("https://builds.guizhanss.cn/", "https://builds-r2.gzassets.cn/");

    private final String baseUrl;
    private final String r2BaseUrl;

    @ParametersAreNonnullByDefault
    @Nonnull
    public static UpdaterLocation getLocation(String location) {
        return UpdaterLocation.GLOBAL;
//        try {
//            return UpdaterLocation.valueOf(location.toUpperCase());
//        } catch (IllegalArgumentException ex) {
//            return UpdaterLocation.GLOBAL;
//        }
    }
}
