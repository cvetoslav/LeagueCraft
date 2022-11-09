package com.dm66.leaguecraft.rendering.client;

import com.dm66.leaguecraft.LeagueCraftMod;
import com.dm66.leaguecraft.networking.ClientOnlineUsersPacket;
import com.dm66.leaguecraft.networking.Networking;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.List;

public class LeagueClientGUI extends Screen implements IGuiEventListener
{
    private static final int WIDTH = 530, HEIGHT = 360;

    public static ContextMenu contextMenu = new ContextMenu();

    private List<Widget> GUIelements;

    private final ResourceLocation GUI = new ResourceLocation(LeagueCraftMod.MOD_ID, "textures/gui/client_gui.png");
    private final ResourceLocation CURSOR = new ResourceLocation(LeagueCraftMod.MOD_ID, "textures/gui/legacy_cursor.png");

    protected LeagueClientGUI()
    {
        super(new TranslationTextComponent("screen.leaguecraft.client"));
    }

    @Override
    protected void init()
    {
        int relX = (width - WIDTH/2) / 2;
        int relY = (height - HEIGHT/2) / 2;

        GUIelements = new ArrayList<>();
        GUIelements.add(new ClientPlayButton(relX + 2,relY + 2));
        GUIelements.add(new PlayersListbox(relX + 265 - 55 - 2,relY + 20, 55, 180 - 22));

        for(Widget w : GUIelements) this.addListener(w);
        this.addListener(contextMenu);

        GLFW.glfwSetInputMode(minecraft.getMainWindow().getHandle(), GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_HIDDEN);
    }

    @Override
    public boolean isPauseScreen()
    {
        return false;
    }

    @Override
    public void tick()
    {

    }

    private void updateOnlinePlayers()
    {
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks)
    {
        this.renderBackground(matrixStack);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);

        int relX = (width - WIDTH/2) / 2;
        int relY = (height - HEIGHT/2) / 2;

        // Draw background texture
        this.minecraft.getTextureManager().bindTexture(GUI);
        blit(matrixStack, relX, relY, WIDTH/2, HEIGHT/2, 0, 0, WIDTH, HEIGHT, WIDTH, HEIGHT);

        for (Widget GUIelement : GUIelements)
        {
            GUIelement.render(matrixStack, mouseX, mouseY, partialTicks);
        }
        contextMenu.render(matrixStack, mouseX, mouseY, partialTicks);

        // Draw legacy cursor texture
        this.minecraft.getTextureManager().bindTexture(CURSOR);
        blit(matrixStack, mouseX, mouseY, 10, 10, 0, 0, 50, 50, 50, 50);

        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    public static void open()
    {
        Minecraft.getInstance().displayGuiScreen(new LeagueClientGUI());
    }


    // Pass GUI event to children cuz wtf?
    // TODO: [FIX] use onDrag method in Widget class

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button)
    {
        boolean ret = false;
        for(Widget el : GUIelements) ret |= el.mouseReleased(mouseX, mouseY, button);
        return ret;
    }

}
