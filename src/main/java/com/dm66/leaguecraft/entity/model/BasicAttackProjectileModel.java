package com.dm66.leaguecraft.entity.model;

import com.dm66.leaguecraft.LeagueCraftMod;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.ResourceLocation;

public class BasicAttackProjectileModel extends Model
{

    private final ModelRenderer bone;
    private final ModelRenderer cube_r1;
    private final ModelRenderer cube_r2;

    public BasicAttackProjectileModel()
    {
        super(RenderType::getEntitySolid);

        textureWidth = 32;
        textureHeight = 32;

        bone = new ModelRenderer(this);
        bone.setRotationPoint(0.0F, 0.0F, 0.0F);
        //bone.setRotationPoint(0.0F, 24.0F, 0.0F);
        bone.setTextureOffset(0, 0).addBox(-1.0F, -1.0F, -6.0F, 1.0F, 1.0F, 13.0F, 0.0F, false);
        bone.setTextureOffset(0, 0).addBox(-1.0F, -2.0F, 5.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        bone.setTextureOffset(0, 0).addBox(-1.0F, -2.0F, 5.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        bone.setTextureOffset(0, 0).addBox(-1.0F, -2.0F, -7.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        bone.setTextureOffset(0, 0).addBox(-1.0F, -1.0F, 7.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        bone.setTextureOffset(0, 0).addBox(-2.0F, -1.0F, 5.0F, 1.0F, 1.0F, 2.0F, 0.0F, true);

        cube_r1 = new ModelRenderer(this);
        cube_r1.setRotationPoint(-1.0F, 1.0F, 0.0F);
        bone.addChild(cube_r1);
        setRotationAngle(cube_r1, 0.0F, 0.0F, 1.5708F);
        cube_r1.setTextureOffset(0, 0).addBox(-1.0F, -1.0F, 5.0F, 1.0F, 1.0F, 2.0F, 0.0F, true);
        cube_r1.setTextureOffset(0, 0).addBox(-1.0F, -1.0F, -7.0F, 1.0F, 1.0F, 2.0F, 0.0F, true);

        cube_r2 = new ModelRenderer(this);
        cube_r2.setRotationPoint(0.0F, 0.0F, 0.0F);
        bone.addChild(cube_r2);
        setRotationAngle(cube_r2, 0.0F, 0.0F, 1.5708F);
        cube_r2.setTextureOffset(0, 0).addBox(-1.0F, -1.0F, 5.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
    }

    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha)
    {
        bone.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}