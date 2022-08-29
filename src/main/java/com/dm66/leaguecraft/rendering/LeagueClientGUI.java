package com.dm66.leaguecraft.rendering;

import com.dm66.leaguecraft.LeagueCraftMod;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TranslationTextComponent;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class LeagueClientGUI extends Screen
{
    private static final int WIDTH = 530, HEIGHT = 360;

    private List<Widget> GUIelements = new ArrayList<>();

    private int cntr = 0;

    private final ResourceLocation GUI = new ResourceLocation(LeagueCraftMod.MOD_ID, "textures/gui/client_gui.png");

    protected LeagueClientGUI()
    {
        super(new TranslationTextComponent("screen.leaguecraft.client"));
    }

    @Override
    protected void init()
    {
        int relX = (width - WIDTH/2) / 2;
        int relY = (height - HEIGHT/2) / 2;

        GUIelements.add(new ClientPlayButton(relX + 2,relY + 2,69,17));
    }

    @Override
    public boolean isPauseScreen()
    {
        return false;
    }

    @Override
    public void tick()
    {
        cntr++;
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks)
    {
        this.renderBackground(matrixStack);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        assert this.minecraft != null;
        this.minecraft.getTextureManager().bindTexture(GUI);
        int relX = (width - WIDTH/2) / 2;
        int relY = (height - HEIGHT/2) / 2;

        blit(matrixStack, relX, relY, WIDTH/2, HEIGHT/2, 0, 0, WIDTH, HEIGHT, WIDTH, HEIGHT);

        //drawString(matrixStack, this.minecraft.fontRenderer, "Hello there! " + this.width + "x" + this.height, relX+5, relY+5, 0xff44de33);

        for (Widget GUIelement : GUIelements)
        {
            GUIelement.render(matrixStack, mouseX, mouseY, partialTicks);
        }
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    public static void open()
    {
        Minecraft.getInstance().displayGuiScreen(new LeagueClientGUI());
    }
}
