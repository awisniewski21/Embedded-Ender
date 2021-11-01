package com.cs222ee.embeddedender.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

/***
 * Template taken from Kaupenjoe's tutorial
 * Source: https://tutorialsbykaupenjoe.net/minecraft-forge-modding-116/adding-a-new-creative-tab-in-minecraft-forge-1-16-5/
 ***/
public class ModItemGroup {
    public static final ItemGroup EMBEDDED_ENDER_GROUP = new ItemGroup("embeddedEnderTab") {

        // Sets icon for the custom creative tab
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItems.ENDER_EGG.get()); // TODO: Update with Embender item model
        }
    };
}
