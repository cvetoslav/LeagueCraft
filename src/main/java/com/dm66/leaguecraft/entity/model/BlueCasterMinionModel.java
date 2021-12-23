package com.dm66.leaguecraft.entity.model;

import com.dm66.leaguecraft.entity.BlueCasterMinion;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class BlueCasterMinionModel extends EntityModel<BlueCasterMinion> {
    private final ModelRenderer feet;
    private final ModelRenderer feet2;
    private final ModelRenderer torso;
    private final ModelRenderer arm_base;
    private final ModelRenderer cube_r1;
    private final ModelRenderer l_arm;
    private final ModelRenderer r_arm2;
    private final ModelRenderer head;
    private final ModelRenderer sopa;
    private final ModelRenderer rune;

    public BlueCasterMinionModel()
    {
        textureWidth = 64;
        textureHeight = 64;

        feet = new ModelRenderer(this);
        feet.setRotationPoint(0.0F, 24.0F, 0.0F);
        feet.setTextureOffset(31, 0).addBox(2.0F, -1.0F, -2.0F, 3.0F, 1.0F, 4.0F, 0.0F, false);
        feet.setTextureOffset(0, 14).addBox(-2.0F, -1.0F, -5.0F, 4.0F, 1.0F, 10.0F, 0.0F, false);
        feet.setTextureOffset(28, 9).addBox(-5.0F, -1.0F, -2.0F, 3.0F, 1.0F, 4.0F, 0.0F, false);
        feet.setTextureOffset(46, 3).addBox(-4.0F, -1.0F, -4.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
        feet.setTextureOffset(40, 43).addBox(2.0F, -1.0F, -4.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
        feet.setTextureOffset(41, 0).addBox(2.0F, -1.0F, 2.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
        feet.setTextureOffset(31, 5).addBox(-4.0F, -1.0F, 2.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

        feet2 = new ModelRenderer(this);
        feet2.setRotationPoint(0.0F, 24.0F, 0.0F);
        feet2.setTextureOffset(38, 5).addBox(2.0F, -2.0F, -2.0F, 2.0F, 1.0F, 4.0F, 0.0F, false);
        feet2.setTextureOffset(15, 0).addBox(-2.0F, -2.0F, -4.0F, 4.0F, 1.0F, 8.0F, 0.0F, false);
        feet2.setTextureOffset(0, 38).addBox(-4.0F, -2.0F, -2.0F, 2.0F, 1.0F, 4.0F, 0.0F, false);
        feet2.setTextureOffset(0, 22).addBox(-3.0F, -2.0F, -3.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        feet2.setTextureOffset(6, 16).addBox(2.0F, -2.0F, -3.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        feet2.setTextureOffset(0, 16).addBox(2.0F, -2.0F, 2.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        feet2.setTextureOffset(6, 14).addBox(-3.0F, -2.0F, 2.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        torso = new ModelRenderer(this);
        torso.setRotationPoint(0.0F, 23.0F, 0.0F);
        torso.setTextureOffset(12, 33).addBox(2.0F, -5.0F, -2.0F, 1.0F, 4.0F, 4.0F, 0.0F, false);
        torso.setTextureOffset(18, 14).addBox(-2.0F, -5.0F, -3.0F, 4.0F, 4.0F, 6.0F, 0.0F, false);
        torso.setTextureOffset(0, 14).addBox(-3.0F, -5.0F, -2.0F, 1.0F, 4.0F, 4.0F, 0.0F, false);

        arm_base = new ModelRenderer(this);
        arm_base.setRotationPoint(0.0F, 18.0F, 0.0F);
        arm_base.setTextureOffset(24, 24).addBox(-2.0F, -5.0F, -2.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);
        arm_base.setTextureOffset(22, 36).addBox(2.0F, -5.0F, -2.0F, 1.0F, 3.0F, 4.0F, 0.0F, false);
        arm_base.setTextureOffset(36, 20).addBox(-3.0F, -5.0F, -2.0F, 1.0F, 3.0F, 4.0F, 0.0F, false);

        cube_r1 = new ModelRenderer(this);
        cube_r1.setRotationPoint(0.0F, 5.0F, 0.0F);
        arm_base.addChild(cube_r1);
        setRotationAngle(cube_r1, 0.0F, 1.5708F, 0.0F);
        cube_r1.setTextureOffset(40, 27).addBox(2.0F, -10.0F, -2.0F, 1.0F, 2.0F, 4.0F, 0.0F, false);
        cube_r1.setTextureOffset(0, 0).addBox(-4.0F, -9.0F, -2.0F, 2.0F, 3.0F, 4.0F, 0.0F, false);
        cube_r1.setTextureOffset(0, 0).addBox(-3.0F, -9.0F, 2.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
        cube_r1.setTextureOffset(0, 7).addBox(-3.0F, -9.0F, -3.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
        cube_r1.setTextureOffset(18, 25).addBox(-4.0F, -6.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

        l_arm = new ModelRenderer(this);
        l_arm.setRotationPoint(0.0F, 24.0F, 0.0F);
        l_arm.setTextureOffset(0, 43).addBox(2.0F, -8.0F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);
        l_arm.setTextureOffset(48, 15).addBox(2.0F, -7.0F, -1.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        l_arm.setTextureOffset(34, 42).addBox(3.0F, -9.0F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);
        l_arm.setTextureOffset(28, 36).addBox(3.0F, -8.0F, -2.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
        l_arm.setTextureOffset(18, 33).addBox(4.0F, -8.0F, -2.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
        l_arm.setTextureOffset(0, 48).addBox(4.0F, -9.0F, -1.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        l_arm.setTextureOffset(46, 47).addBox(5.0F, -8.0F, -1.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        l_arm.setTextureOffset(46, 6).addBox(4.0F, -7.0F, 0.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
        l_arm.setTextureOffset(23, 46).addBox(6.0F, -7.0F, -1.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        l_arm.setTextureOffset(8, 46).addBox(6.0F, -5.0F, -1.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        l_arm.setTextureOffset(40, 11).addBox(4.0F, -7.0F, -2.0F, 2.0F, 3.0F, 2.0F, 0.0F, false);
        l_arm.setTextureOffset(42, 17).addBox(5.0F, -5.0F, 0.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
        l_arm.setTextureOffset(15, 42).addBox(6.0F, -6.0F, -1.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);
        l_arm.setTextureOffset(0, 14).addBox(5.0F, -6.0F, 1.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        r_arm2 = new ModelRenderer(this);
        r_arm2.setRotationPoint(-11.0F, 24.0F, 0.0F);
        r_arm2.setTextureOffset(28, 41).addBox(8.0F, -8.0F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);
        r_arm2.setTextureOffset(33, 47).addBox(8.0F, -7.0F, -1.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        r_arm2.setTextureOffset(40, 38).addBox(7.0F, -9.0F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);
        r_arm2.setTextureOffset(7, 33).addBox(7.0F, -8.0F, -2.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
        r_arm2.setTextureOffset(22, 9).addBox(6.0F, -8.0F, -2.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
        r_arm2.setTextureOffset(18, 47).addBox(6.0F, -9.0F, -1.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        r_arm2.setTextureOffset(12, 47).addBox(5.0F, -8.0F, -1.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        r_arm2.setTextureOffset(46, 10).addBox(4.0F, -7.0F, -1.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        r_arm2.setTextureOffset(8, 0).addBox(4.0F, -6.0F, 1.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
        r_arm2.setTextureOffset(0, 10).addBox(3.0F, -6.0F, 1.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        r_arm2.setTextureOffset(9, 41).addBox(5.0F, -7.0F, -2.0F, 2.0F, 2.0F, 3.0F, 0.0F, false);
        r_arm2.setTextureOffset(46, 44).addBox(4.0F, -6.0F, -1.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);

        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, 24.0F, 0.0F);
        head.setTextureOffset(15, 0).addBox(-1.0F, -15.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
        head.setTextureOffset(0, 25).addBox(-2.0F, -14.0F, -2.0F, 4.0F, 3.0F, 4.0F, 0.0F, false);
        head.setTextureOffset(0, 25).addBox(-2.0F, -14.0F, -4.0F, 4.0F, 1.0F, 2.0F, 0.0F, false);
        head.setTextureOffset(0, 25).addBox(-2.0F, -13.0F, -5.0F, 4.0F, 1.0F, 2.0F, 0.0F, false);
        head.setTextureOffset(18, 14).addBox(-2.0F, -15.0F, -3.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);
        head.setTextureOffset(21, 43).addBox(-1.0F, -15.0F, 1.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        head.setTextureOffset(0, 51).addBox(1.0F, -15.0F, -3.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);
        head.setTextureOffset(18, 28).addBox(-1.0F, -15.0F, -4.0F, 2.0F, 1.0F, 3.0F, 0.0F, false);
        head.setTextureOffset(18, 28).addBox(-1.0F, -14.0F, -5.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        head.setTextureOffset(40, 33).addBox(-2.0F, -14.0F, 2.0F, 4.0F, 4.0F, 1.0F, 0.0F, false);

        sopa = new ModelRenderer(this);
        sopa.setRotationPoint(0.0F, 16.0F, 6.0F);
        setRotationAngle(sopa, 1.3542F, 0.0F, 0.0436F);
        sopa.setTextureOffset(0, 0).addBox(5.0F, -4.0F, -6.0F, 1.0F, 1.0F, 13.0F, 0.0F, false);
        sopa.setTextureOffset(32, 14).addBox(6.0F, -4.0F, 2.0F, 1.0F, 1.0F, 5.0F, 0.0F, false);
        sopa.setTextureOffset(0, 32).addBox(4.0F, -4.0F, 2.0F, 1.0F, 1.0F, 5.0F, 0.0F, false);
        sopa.setTextureOffset(42, 46).addBox(4.0F, -5.0F, 4.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        sopa.setTextureOffset(46, 38).addBox(6.0F, -5.0F, 4.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        sopa.setTextureOffset(29, 46).addBox(6.0F, -3.0F, 4.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        sopa.setTextureOffset(46, 28).addBox(4.0F, -3.0F, 4.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        sopa.setTextureOffset(11, 27).addBox(5.0F, -5.0F, 2.0F, 1.0F, 1.0F, 5.0F, 0.0F, false);
        sopa.setTextureOffset(0, 7).addBox(5.0F, -3.0F, 2.0F, 1.0F, 1.0F, 5.0F, 0.0F, false);

        rune = new ModelRenderer(this);
        rune.setRotationPoint(7.0F, 29.0F, -1.0F);
        setRotationAngle(rune, 0.2113F, 0.4584F, -0.1151F);
        rune.setTextureOffset(15, 9).addBox(-14.0F, -14.0F, 0.0F, 4.0F, 2.0F, 1.0F, 0.0F, false);
        rune.setTextureOffset(46, 22).addBox(-13.0F, -12.0F, 0.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
        rune.setTextureOffset(18, 18).addBox(-13.0F, -15.0F, 0.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(BlueCasterMinion entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
    {

    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        feet.render(matrixStack, buffer, packedLight, packedOverlay);
        feet2.render(matrixStack, buffer, packedLight, packedOverlay);
        torso.render(matrixStack, buffer, packedLight, packedOverlay);
        arm_base.render(matrixStack, buffer, packedLight, packedOverlay);
        l_arm.render(matrixStack, buffer, packedLight, packedOverlay);
        r_arm2.render(matrixStack, buffer, packedLight, packedOverlay);
        head.render(matrixStack, buffer, packedLight, packedOverlay);
        sopa.render(matrixStack, buffer, packedLight, packedOverlay);
        rune.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z)
    {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}