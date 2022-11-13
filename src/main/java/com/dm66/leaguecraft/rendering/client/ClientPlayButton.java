package com.dm66.leaguecraft.rendering.client;

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
    public final int WIDTH = 205, HEIGHT = 110;

    private final ResourceLocation texture = new ResourceLocation(LeagueCraftMod.MOD_ID, "textures/gui/client_play_button.png");

    LeagueClientGUI parent;

    public ClientPlayButton(int x, int y, int width, int height, LeagueClientGUI _p)
    {
        super(x, y, width, height, new StringTextComponent(""));
        parent = _p;
    }

    public ClientPlayButton(int x, int y, LeagueClientGUI _p)
    {
        this(x, y, 41, 11, _p);
    }

    @Override
    public void onClick(double mouseX, double mouseY)
    {
        // TODO: c'mon, do something
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button)
    {
        if(!this.clicked(mouseX, mouseY)) return false;

        if(button == 0)
        {
            this.onClick(mouseX, mouseY);
            return true;
        }
        return false;
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
            blit(matrixStack, x, y, width, height, 0, HEIGHT / 2, WIDTH, HEIGHT / 2, WIDTH, HEIGHT);
        }
        else
        {
            blit(matrixStack, x, y, width, height, 0, 0, WIDTH, HEIGHT / 2, WIDTH, HEIGHT);
        }
    }
}
