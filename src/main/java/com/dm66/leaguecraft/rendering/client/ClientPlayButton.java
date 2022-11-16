package com.dm66.leaguecraft.rendering.client;

import com.dm66.leaguecraft.LeagueCraftMod;
import com.dm66.leaguecraft.utils.AnimatedGif;
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
    public final int WIDTH = 210, HEIGHT = 55;

    AnimatedGif play_button_gif, play_button_hovered_gif;
    AnimatedGif.GifPlayer play_button, play_button_hovered;

    LeagueClientGUI parent;

    public ClientPlayButton(int x, int y, int width, int height, LeagueClientGUI _p)
    {
        super(x, y, width, height, new TextComponent(""));
        parent = _p;

        new Thread(() ->
        {
            try
            {
                play_button_gif = AnimatedGif.fromInputStream(Minecraft.getInstance().getResourceManager().getResource(new ResourceLocation(LeagueCraftMod.MOD_ID, "textures/gui/play_button.gif")).getInputStream());
                play_button_hovered_gif = AnimatedGif.fromInputStream(Minecraft.getInstance().getResourceManager().getResource(new ResourceLocation(LeagueCraftMod.MOD_ID, "textures/gui/play_button_hover.gif")).getInputStream());

                play_button = play_button_gif.makeGifPlayer("gui_play_button");
                play_button_hovered = play_button_hovered_gif.makeGifPlayer("gui_play_button_hovered");

                play_button.setAutoplay(true); play_button.setLooping(true);
                play_button_hovered.setAutoplay(true); play_button_hovered.setLooping(true);

            }catch (Exception e){e.printStackTrace();}
        }).start();
    }

    public ClientPlayButton(int x, int y, LeagueClientGUI _p)
    {
        this(x, y, 42, 11, _p);
    }

    public void tick()
    {
        if(play_button != null && play_button_hovered != null)
        {
            play_button.tick();
            play_button_hovered.tick();
        }
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
        if(play_button == null || play_button_hovered == null) return;

        if(!play_button.isPlaying())   // Sync the animations of both the gifs
        {
            play_button.start(partialTicks);
            play_button_hovered.start(partialTicks);
        }

        if(this.isHovered) play_button_hovered.render(matrixStack, x, y, width, height, partialTicks);
        else play_button.render(matrixStack, x, y, width, height, partialTicks);
    }

    @Override
    public void updateNarration(@NotNull NarrationElementOutput pNarrationElementOutput) {

    }
}
