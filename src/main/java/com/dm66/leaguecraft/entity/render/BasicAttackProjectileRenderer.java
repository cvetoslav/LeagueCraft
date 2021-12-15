package com.dm66.leaguecraft.entity.render;

import com.dm66.leaguecraft.LeagueCraftMod;
import com.dm66.leaguecraft.entity.BasicAttackProjectile;
import com.dm66.leaguecraft.entity.model.BasicAttackProjectileModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;

public class BasicAttackProjectileRenderer extends EntityRenderer<BasicAttackProjectile>
{
    public static final ResourceLocation TEXTURE = new ResourceLocation(LeagueCraftMod.MOD_ID, "textures/entity/basic_attack_projectile.png");
    private final BasicAttackProjectileModel model = new BasicAttackProjectileModel();

    public BasicAttackProjectileRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn);
    }

    public void render(BasicAttackProjectile entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn)
    {
        matrixStackIn.push();
        matrixStackIn.rotate(Vector3f.YP.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.prevRotationYaw, entityIn.rotationYaw) /*-90.0F*/));
        matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.prevRotationPitch, entityIn.rotationPitch) + 90.0F));
        IVertexBuilder ivertexbuilder = net.minecraft.client.renderer.ItemRenderer.getEntityGlintVertexBuilder(bufferIn, this.model.getRenderType(this.getEntityTexture(entityIn)), false, false);
        this.model.render(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        matrixStackIn.pop();
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    /**
     * Returns the location of an entity's texture.
     */
    public ResourceLocation getEntityTexture(BasicAttackProjectile entity) {
        return TEXTURE;
    }
}
