package com.dm66.leaguecraft.rendering.client;

import com.dm66.leaguecraft.LeagueCraftMod;
import com.dm66.leaguecraft.utils.AnimatedGif;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ContentBox extends AbstractWidget
{
    LeagueClientGUI parent;
    AnimatedGif gifImage;
    AnimatedGif.GifPlayer gifPlayer;

    public ContentBox(int x, int y, int width, int height, LeagueClientGUI _p)
    {
        super(x, y, width, height, new TextComponent(""));
        parent = _p;

        new Thread(() ->
        {
            try
            {
                gifImage = AnimatedGif.fromInputStream(Minecraft.getInstance().getResourceManager().getResource(new ResourceLocation(LeagueCraftMod.MOD_ID, "textures/gui/giphy.gif")).getInputStream());

                gifPlayer = gifImage.makeGifPlayer("test_gif1");
                gifPlayer.setAutoplay(true);
                gifPlayer.setLooping(true);

                // TODO: gifPlayer.close(); when the player isn't needed, else you'll leak OpenGL Textures!!!
            }catch (Exception e){e.printStackTrace();}
        }).start();

    }

    int cntr = 0;

    public void tick()
    {
        if(gifPlayer != null) gifPlayer.tick();
    }

    @Override
    public void mouseMoved(double pMouseX, double pMouseY) {
        super.mouseMoved(pMouseX, pMouseY);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button)
    {
        if(parent.getContextMenu().isHovered()) return false;
        else parent.getContextMenu().clear();
        if(button == 0)
        {

        }
        else if(button == 1)
        {

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
    public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks)
    {
        fill(matrixStack, x, y, x + width, y + height, 0xff00ff00);

        if(gifPlayer == null || gifImage == null) return;

        int w = gifImage.getWidth(), h = gifImage.getHeight();
        gifPlayer.render(matrixStack, x+width/2-w/8, y+height/2-h/8, w/4, h/4, partialTicks);
    }

    @Override
    public void updateNarration(@NotNull NarrationElementOutput pNarrationElementOutput) {

    }
}
