package net.itshamza.za.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTabs {
    public static final CreativeModeTab AFRICA_TAB = new CreativeModeTab("africa_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.JAGUAR_SPAWN_EGG.get());
        }
    };
}
