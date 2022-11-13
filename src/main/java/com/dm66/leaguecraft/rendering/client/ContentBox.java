package com.dm66.leaguecraft.rendering.client;

import com.dm66.leaguecraft.LeagueCraftMod;
import com.dm66.leaguecraft.utils.AnimatedGif;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.NativeImage;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextComponent;
import net.minecraftforge.fml.DistExecutor;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.file.Paths;

public class ContentBox extends Widget
{

    LeagueClientGUI parent;
    public AnimatedGif gifImage;
    public AnimatedGif.GifPlayer gifPlayer;

    public ContentBox(int x, int y, int width, int height, LeagueClientGUI _p)
    {
        super(x, y, width, height, new StringTextComponent(""));
        parent = _p;


        new Thread(() ->
        {
            try
            {
                gifImage = AnimatedGif.fromInputStream(Minecraft.getInstance().getResourceManager().getResource(new ResourceLocation(LeagueCraftMod.MOD_ID, "textures/gui/giphy.gif")).getInputStream());

                gifPlayer = gifImage.makeGifPlayer();
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
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks)
    {
        fill(matrixStack, x, y, x + width, y + height, 0xff00ff00);

        if(gifPlayer == null || gifImage == null) return;

        int w = gifImage.getWidth(), h = gifImage.getHeight();
        gifPlayer.render(matrixStack, x+width/2-w/8, y+height/2-h/8, w/4, h/4, partialTicks);
    }
}
