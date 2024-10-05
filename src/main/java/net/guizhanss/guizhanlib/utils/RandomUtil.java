package net.guizhanss.guizhanlib.utils;

import lombok.experimental.UtilityClass;

/**
 * @deprecated 请使用 {@link net.guizhanss.guizhanlib.common.utils.RandomUtil} 代替
 */
@Deprecated(since = "2.0.0", forRemoval = true)
@UtilityClass
@SuppressWarnings("unused")
public final class RandomUtil {

    public static int randomInt(int min, int max) {
        return net.guizhanss.guizhanlib.common.utils.RandomUtil.randomInt(min, max);
    }

    public static double randomDouble(double min, double max) {
        return net.guizhanss.guizhanlib.common.utils.RandomUtil.randomDouble(min, max);
    }

    public static boolean testChance(int chance, int bound) {
        return net.guizhanss.guizhanlib.common.utils.RandomUtil.testChance(chance, bound);
    }

}
