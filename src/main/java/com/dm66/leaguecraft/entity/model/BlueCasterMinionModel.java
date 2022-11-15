package com.dm66.leaguecraft.entity.model;

import com.dm66.leaguecraft.LeagueCraftMod;
import com.dm66.leaguecraft.entity.BlueCasterMinion;
import net.minecraft.resources.ResourceLocation;
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