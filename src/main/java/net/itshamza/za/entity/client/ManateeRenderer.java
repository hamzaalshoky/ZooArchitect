package net.itshamza.za.entity.client;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.itshamza.za.ZooArchitect;
import net.itshamza.za.entity.custom.ManateeEntity;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import java.util.Map;

public class ManateeRenderer extends GeoEntityRenderer<ManateeEntity> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("za:textures/entity/manatee/manatee.png");
    private static final ResourceLocation SLEEPING = new ResourceLocation("za:textures/entity/manatee/manatee_sleep.png");

    public ManateeRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ManateeModel());
        this.shadowRadius = 0.3f;
    }

    @Override
    public ResourceLocation getTextureLocation(ManateeEntity instance) {
        return instance.isSleeping() ? SLEEPING : TEXTURE;
    }


    @Override
    public RenderType getRenderType(ManateeEntity animatable, float partialTicks, PoseStack stack,
                                    MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        if(animatable.isBaby()){
            stack.scale(0.5F, 0.5F, 0.5F);
        }else{
            stack.scale(1.1F, 1.1F, 1.1F);
        }
        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}
