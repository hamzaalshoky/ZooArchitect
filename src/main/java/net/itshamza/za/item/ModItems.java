package net.itshamza.za.item;

import net.itshamza.za.ZooArchitect;
import net.itshamza.za.entity.ModEntityCreator;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ZooArchitect.MOD_ID);

    public static final RegistryObject<Item> JAGUAR_SPAWN_EGG = ITEMS.register("jaguar_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityCreator.JAGUAR,14265190, 1973794,
                    new Item.Properties().tab(ModCreativeModeTabs.AFRICA_TAB)));

    public static final RegistryObject<Item> MANATEE_SPAWN_EGG = ITEMS.register("manatee_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityCreator.MANATEE,6843239, 7895924,
                    new Item.Properties().tab(ModCreativeModeTabs.AFRICA_TAB)));

    public static final RegistryObject<Item> JAGUAR_TOOTH = ITEMS.register("jaguar_tooth",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTabs.AFRICA_TAB)));

    public static final RegistryObject<Item> JUNGLE_DAGGER = ITEMS.register("jungle_dagger",
            () -> new SwordItem(Tiers.STONE, 2, 3f,
                    new Item.Properties().tab(ModCreativeModeTabs.AFRICA_TAB)));

    //526941190

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
