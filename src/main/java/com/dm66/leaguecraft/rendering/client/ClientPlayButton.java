package com.dm66.leaguecraft.rendering.client;

import com.dm66.leaguecraft.LeagueCraftMod;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ClientPlayButton extends AbstractWidget
{
    public final int WIDTH = 205, HEIGHT = 110;

    private final ResourceLocation texture = new ResourceLocation(LeagueCraftMod.MOD_ID, "textures/gui/client_play_button.png");

    LeagueClientGUI parent;

    public ClientPlayButton(int x, int y, int width, int height, LeagueClientGUI _p)
    {
        super(x, y, width, height, new TextComponent(""));
        parent = _p;
    }

    public ClientPlayButton(int x, int y, LeagueClientGUI _p)
    {
        this(x, y, 41, 11, _p);
    }

    @Override
    public void onClick(double mouseX, double mouseY)
    {
        // TODO: c'mon, do something (open game mode select)
    }

    @Override
    public void mouseMoved(double pMouseX, double pMouseY) {
        super.mouseMoved(pMouseX, pMouseY);
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
    public boolean mouseScrolled(double pMouseX, double pMouseY, double pDelta) {
        return super.mouseScrolled(pMouseX, pMouseY, pDelta);
    }

    @Override
    public boolean keyPressed(int pKeyCode, int pScanCode, int pModifiers) {
        return super.keyPressed(pKeyCode, pScanCode, pModifiers);
    }

    @Override
    public boolean keyReleased(int pKeyCode, int pScanCode, int pModifiers) {
        return super.keyReleased(pKeyCode, pScanCode, pModifiers);
    }

    @Override
    public boolean charTyped(char pCodePoint, int pModifiers) {
        return super.charTyped(pCodePoint, pModifiers);
    }

    @Override
    public void render(@NotNull PoseStack matrixStack, int mouseX, int mouseY, float partialTicks)
    {
        if (this.visible)
        {
            this.isHovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            renderWidget(matrixStack, mouseX, mouseY, partialTicks);
        }
    }

    public void renderWidget(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks)
    {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, texture);
        if(this.isHovered)
        {
            blit(matrixStack, x, y, width, height, 0, HEIGHT / 2, WIDTH, HEIGHT / 2, WIDTH, HEIGHT);
        }
        else
        {
            blit(matrixStack, x, y, width, height, 0, 0, WIDTH, HEIGHT / 2, WIDTH, HEIGHT);
        }
    }

    @Override
    public void updateNarration(@NotNull NarrationElementOutput pNarrationElementOutput) {

    }
}
