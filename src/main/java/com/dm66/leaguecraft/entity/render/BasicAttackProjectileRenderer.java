package com.dm66.leaguecraft.entity.render;


import com.dm66.leaguecraft.entity.BasicAttackProjectile;
import com.dm66.leaguecraft.entity.model.BasicAttackProjectileModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import software.bernie.geckolib3.renderers.geo.GeoProjectilesRenderer;

public class BasicAttackProjectileRenderer extends GeoProjectilesRenderer<BasicAttackProjectile>
{

    public BasicAttackProjectileRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new BasicAttackProjectileModel());
    }

    @Override
    public RenderType getRenderType(BasicAttackProjectile animatable, float partialTick, PoseStack poseStack, @Nullable MultiBufferSource bufferSource, @Nullable VertexConsumer buffer, int packedLight, ResourceLocation texture)
    {
        poseStack.scale(0.8f,0.8f,0.8f);
        return super.getRenderType(animatable, partialTick, poseStack, bufferSource, buffer, packedLight, texture);
    }
}
