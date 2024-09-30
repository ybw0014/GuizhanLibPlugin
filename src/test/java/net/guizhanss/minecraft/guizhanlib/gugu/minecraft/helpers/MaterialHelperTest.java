package net.guizhanss.minecraft.guizhanlib.gugu.minecraft.helpers;

import be.seeseemelk.mockbukkit.MockBukkit;
import net.guizhanss.minecraft.guizhanlib.GuizhanLib;
import org.bukkit.Material;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.logging.Level;

public class MaterialHelperTest {

    @BeforeAll
    public static void beforeAll() {
        MockBukkit.mock();
        MockBukkit.load(GuizhanLib.class);
    }

    @AfterAll
    public static void afterAll() {
        MockBukkit.unmock();
    }

    @Test
    void testGetKey() {
        var key = MaterialHelper.getKey(Material.DIRT);
        GuizhanLib.log(Level.INFO, "Translation key of Material dirt: " + key);
        Assertions.assertTrue(key.contains("dirt"));
    }

    @Test
    void testGetName() {
        var name = MaterialHelper.getName(Material.DIRT);
        GuizhanLib.log(Level.INFO, "Name of Material dirt: " + name);
        Assertions.assertEquals("泥土", name);
    }
}
