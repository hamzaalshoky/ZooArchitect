package net.itshamza.za.entity.client;

import net.itshamza.za.ZooArchitect;
import net.itshamza.za.entity.custom.CapybaraEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class CapybaraModel extends AnimatedGeoModel<CapybaraEntity> {
    public static final ResourceLocation MODEL = new ResourceLocation("za:geo/capybara.geo.json");
    public static final ResourceLocation GLOW_TEXTURE = new ResourceLocation("za:textures/entity/capybara/capybara_saddle_layer.png");
    
    @Override
    public ResourceLocation getModelLocation(CapybaraEntity object) {
        return new ResourceLocation(ZooArchitect.MOD_ID, "geo/capybara.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(CapybaraEntity object) {
        return new ResourceLocation(ZooArchitect.MOD_ID, "textures/entity/capybara/capybara.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(CapybaraEntity animatable) {
        return new ResourceLocation(ZooArchitect.MOD_ID, "animations/capybara.animation.json");
    }
}
