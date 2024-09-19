package net.guizhanss.minecraft.guizhanlib.gugu.minecraft.helpers;

import net.guizhanss.minecraft.guizhanlib.gugu.MinecraftLocalization;

import javax.annotation.Nonnull;

public interface TranslatableClassHelper<T> {
    @Nonnull
    String getTranslationKey(T instance);

    @Nonnull
    default String getTranslatedName(T instance) {
        String transKey = getTranslationKey(instance);
        if (transKey == null) {
            return null;
        }
        return MinecraftLocalization.
    }
}
