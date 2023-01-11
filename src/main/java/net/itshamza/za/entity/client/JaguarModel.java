package net.itshamza.za.entity.client;

import net.itshamza.za.ZooArchitect;
import net.itshamza.za.entity.custom.JaguarEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class JaguarModel extends AnimatedGeoModel<JaguarEntity> {
    public static final ResourceLocation MODEL = new ResourceLocation("za:geo/jaguar.geo.json");
    public static final ResourceLocation GLOW_TEXTURE = new ResourceLocation("za:textures/entity/jaguar/jaguar_glow.png");
    
    @Override
    public ResourceLocation getModelLocation(JaguarEntity object) {
        return new ResourceLocation(ZooArchitect.MOD_ID, "geo/jaguar.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(JaguarEntity object) {
        return JaguarRenderer.LOCATION_BY_VARIANT.get(object.getVariant());
    }

    @Override
    public ResourceLocation getAnimationFileLocation(JaguarEntity animatable) {
        return new ResourceLocation(ZooArchitect.MOD_ID, "animations/jaguar.animation.json");
    }
}
