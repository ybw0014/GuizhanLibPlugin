package net.guizhanss.guizhanlib.utils;

import lombok.experimental.UtilityClass;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.jar.JarEntry;

/**
 * @deprecated 请使用 {@link net.guizhanss.guizhanlib.common.utils.FileUtil} 代替
 */
@Deprecated(since = "2.0.0", forRemoval = true)
@UtilityClass
@SuppressWarnings("unused")
public final class FileUtil {

    @Nonnull
    @ParametersAreNonnullByDefault
    public List<String> listJarEntries(File jarFile, BiPredicate<String, JarEntry> predicate, BiFunction<String, JarEntry, String> mapper) throws IOException {
        return net.guizhanss.guizhanlib.common.utils.FileUtil.listJarEntries(jarFile, predicate, mapper);
    }

}
