package com.dm66.leaguecraft.entity.render;

import com.dm66.leaguecraft.LeagueCraftMod;
import com.dm66.leaguecraft.entity.BlueCasterMinion;
import com.dm66.leaguecraft.entity.model.BlueCasterMinionModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class BlueCasterMinionRenderer extends MobRenderer<BlueCasterMinion, BlueCasterMinionModel>
{
    protected static final ResourceLocation TEXTURE = new ResourceLocation(LeagueCraftMod.MOD_ID, "textures/entity/blue_caster_minion.png");

    public BlueCasterMinionRenderer(EntityRendererManager renderManagerIn)
    {
        super(renderManagerIn, new BlueCasterMinionModel(), 0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(BlueCasterMinion entity)
    {
        return TEXTURE;
    }
}
