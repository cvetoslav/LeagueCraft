package com.dm66.leaguecraft.entity.model;

import com.dm66.leaguecraft.LeagueCraftMod;
import com.dm66.leaguecraft.entity.BlueCasterMinion;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BlueCasterMinionModel extends AnimatedGeoModel<BlueCasterMinion> {
    
    @Override
    public ResourceLocation getModelLocation(BlueCasterMinion object)
    {
        return new ResourceLocation(LeagueCraftMod.MOD_ID, "geo/blue_caster_minion.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(BlueCasterMinion object)
    {
        return new ResourceLocation(LeagueCraftMod.MOD_ID, "textures/entity/blue_caster_minion.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(BlueCasterMinion animatable)
    {
        return new ResourceLocation(LeagueCraftMod.MOD_ID, "animations/animation.blue_caster_minion.json");
    }
}