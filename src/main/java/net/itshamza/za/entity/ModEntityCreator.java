package net.itshamza.za.entity;

import net.itshamza.za.ZooArchitect;
import net.itshamza.za.entity.client.JaguarRenderer;
import net.itshamza.za.entity.client.ManateeRenderer;
import net.itshamza.za.entity.custom.JaguarEntity;
import net.itshamza.za.entity.custom.ManateeEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = ZooArchitect.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntityCreator {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITIES, ZooArchitect.MOD_ID);

    // REGESTRIES

    public static final RegistryObject<EntityType<JaguarEntity>> JAGUAR = ENTITY_TYPES.register("jaguar", () -> EntityType.Builder.of(JaguarEntity::new, MobCategory.CREATURE).sized(1.4F, 1.4F).build(new ResourceLocation(ZooArchitect.MOD_ID, "jaguar").toString()));
    public static final RegistryObject<EntityType<ManateeEntity>> MANATEE = ENTITY_TYPES.register("manatee", () -> EntityType.Builder.of(ManateeEntity::new, MobCategory.WATER_CREATURE).sized(2.2F, 1.4F).build(new ResourceLocation(ZooArchitect.MOD_ID, "manatee").toString()));

    // ATTRIBUTES

    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
        event.put(ModEntityCreator.JAGUAR.get(), JaguarEntity.setAttributes());
        event.put(ModEntityCreator.MANATEE.get(), ManateeEntity.setAttributes());
    }

    // RENDERERS

    @SubscribeEvent
    public static void registerEntityRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntityCreator.JAGUAR.get(), JaguarRenderer::new);
        event.registerEntityRenderer(ModEntityCreator.MANATEE.get(), ManateeRenderer::new);
    }

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
