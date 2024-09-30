package net.guizhanss.minecraft.guizhanlib;

import be.seeseemelk.mockbukkit.MockBukkit;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PluginLoadTest {

    @BeforeAll
    public static void beforeAll() {
        MockBukkit.mock();
        MockBukkit.load(Slimefun.class);
        MockBukkit.load(GuizhanLib.class);
    }

    @AfterAll
    public static void afterAll() {
        MockBukkit.unmock();
    }

    @Test
    void pluginLoad() {
        assertNotNull(GuizhanLib.getInstance());
    }
}
