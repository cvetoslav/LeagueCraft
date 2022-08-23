package com.dm66.leaguecraft.entity.render;

import com.dm66.leaguecraft.LeagueCraftMod;
import com.dm66.leaguecraft.entity.BlueCasterMinion;
import com.dm66.leaguecraft.entity.model.BlueCasterMinionModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import javax.annotation.Nullable;

public class BlueCasterMinionRenderer extends GeoEntityRenderer<BlueCasterMinion>
{

    public BlueCasterMinionRenderer(EntityRendererManager renderManager)
    {
        super(renderManager, new BlueCasterMinionModel());
        this.shadowSize = 0.3f;
    }

    @Override
    public ResourceLocation getEntityTexture(BlueCasterMinion entity) {
        return new ResourceLocation(LeagueCraftMod.MOD_ID, "textures/entity/blue_caster_minion.png");
    }

    @Override
    public RenderType getRenderType(BlueCasterMinion animatable, float partialTicks, MatrixStack stack,
                                    IRenderTypeBuffer renderTypeBuffer, IVertexBuilder vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation)
    {
        stack.scale(0.8f, 0.8f, 0.8f);
        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}
