package com.dm66.leaguecraft.rendering;

import com.dm66.leaguecraft.LeagueCraftMod;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class ClientPlayButton extends Widget
{
    public final int WIDTH = 206, HEIGHT = 102;

    private final ResourceLocation texture = new ResourceLocation(LeagueCraftMod.MOD_ID, "textures/gui/client_play_button.png");

    public ClientPlayButton(int x, int y, int width, int height)
    {
        super(x, y, width, height, new StringTextComponent(""));
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks)
    {
        if (this.visible)
        {
            this.isHovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            renderWidget(matrixStack, mouseX, mouseY, partialTicks);
        }
    }

    @Override
    public void renderWidget(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks)
    {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        Minecraft minecraft = Minecraft.getInstance();
        minecraft.getTextureManager().bindTexture(texture);
        if(this.isHovered())
        {
            blit(matrixStack, x, y, width, height, 0, 51, 206, 51, WIDTH, HEIGHT);
        }
        else
        {
            blit(matrixStack, x, y, width, height, 0, 0, 206, 51, WIDTH, HEIGHT);
        }
    }
}
