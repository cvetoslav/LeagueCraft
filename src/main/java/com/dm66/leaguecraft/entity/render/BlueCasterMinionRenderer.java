package com.dm66.leaguecraft.entity.render;

import com.dm66.leaguecraft.LeagueCraftMod;
import com.dm66.leaguecraft.entity.BlueCasterMinion;
import com.dm66.leaguecraft.entity.model.BlueCasterMinionModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class BlueCasterMinionRenderer extends GeoEntityRenderer<BlueCasterMinion>
{

    public BlueCasterMinionRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new BlueCasterMinionModel());
        this.shadowRadius = 0.3f;
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull BlueCasterMinion entity) {
        return new ResourceLocation(LeagueCraftMod.MOD_ID, "textures/entity/blue_caster_minion.png");
    }

    @Override
    public RenderType getRenderType(BlueCasterMinion animatable, float partialTicks, PoseStack stack,
                                    MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation)
    {
        stack.scale(0.8f, 0.8f, 0.8f);
        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}
