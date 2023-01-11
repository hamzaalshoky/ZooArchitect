package net.itshamza.za.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.itshamza.za.entity.custom.CapybaraEntity;
import net.itshamza.za.entity.custom.JaguarEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.renderers.geo.GeoLayerRenderer;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;

@OnlyIn(Dist.CLIENT)
public class CapybaraSaddleLayer<E extends CapybaraEntity> extends GeoLayerRenderer<E> {

    public CapybaraSaddleLayer(IGeoRenderer<E> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack stack, MultiBufferSource buffer, int packedLightIn, E creeper, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if(creeper.isSaddled()) {
            GeoModel normalModel = this.getEntityModel().getModel(CapybaraModel.MODEL);
            VertexConsumer glowConsumer = buffer.getBuffer(RenderType.entityCutoutNoCull(CapybaraModel.GLOW_TEXTURE));
            getRenderer().render(normalModel, creeper, partialTicks,
                    null, stack, null, glowConsumer,
                    packedLightIn, OverlayTexture.NO_OVERLAY,
                    1f, 1f, 1f, 1f);
        }

    }
}
