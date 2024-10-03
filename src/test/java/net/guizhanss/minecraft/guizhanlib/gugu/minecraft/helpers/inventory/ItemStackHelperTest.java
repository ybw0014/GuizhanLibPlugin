package net.guizhanss.minecraft.guizhanlib.gugu.minecraft.helpers.inventory;

import be.seeseemelk.mockbukkit.MockBukkit;
import net.guizhanss.minecraft.guizhanlib.GuizhanLib;
import net.guizhanss.minecraft.guizhanlib.gugu.minecraft.helpers.MaterialHelper;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.logging.Level;

public class ItemStackHelperTest {

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
        var item = new ItemStack(Material.DIRT);
        var key = ItemStackHelper.getKey(item);
        GuizhanLib.log(Level.INFO, "Translation key of ItemStack dirt: " + key);
        Assertions.assertTrue(key.contains("dirt"));
    }

    @Test
    void testGetName() {
        var name = MaterialHelper.getName(Material.DIRT);
        GuizhanLib.log(Level.INFO, "Name of ItemStack dirt: " + name);
        Assertions.assertEquals("泥土", name);
    }

    @Test
    void testGetDisplayName() {
        // normal item
        var item = new ItemStack(Material.DIRT);
        var meta = item.getItemMeta();
        meta.setDisplayName("泥土X");
        item.setItemMeta(meta);

        var displayName = ItemStackHelper.getDisplayName(item);
        GuizhanLib.log(Level.INFO, "Display name of ItemStack dirt: " + displayName);
        Assertions.assertEquals("泥土X", displayName);

        // player head
    }
}
