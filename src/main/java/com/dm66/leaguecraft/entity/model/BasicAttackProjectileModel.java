package com.dm66.leaguecraft.entity.model;


import com.dm66.leaguecraft.LeagueCraftMod;
import com.dm66.leaguecraft.entity.BasicAttackProjectile;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BasicAttackProjectileModel extends AnimatedGeoModel<BasicAttackProjectile>
{

    @Override
    public ResourceLocation getModelLocation(BasicAttackProjectile object) {
        return new ResourceLocation(LeagueCraftMod.MOD_ID, "geo/basic_attack_projectile.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(BasicAttackProjectile object) {
        return new ResourceLocation(LeagueCraftMod.MOD_ID, "textures/entity/basic_attack_projectile.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(BasicAttackProjectile animatable) {
        return null;
    }
}