package com.dm66.leaguecraft.rendering.client;

import com.dm66.leaguecraft.LeagueCraftMod;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.glfw.GLFW;

import java.util.Map;

public class LeagueClientGUI extends Screen implements GuiEventListener
{
    private static final int WIDTH = 530, HEIGHT = 360;

    private final ResourceLocation GUI = new ResourceLocation(LeagueCraftMod.MOD_ID, "textures/gui/client_gui.png");
    private final ResourceLocation CURSOR = new ResourceLocation(LeagueCraftMod.MOD_ID, "textures/gui/legacy_cursor.png");

    protected LeagueClientGUI()
    {
        super(new TranslatableComponent("screen.leaguecraft.client"));
    }

    @Override
    protected void init()
    {
        int relX = (width - WIDTH/2) / 2;
        int relY = (height - HEIGHT/2) / 2;

        this.addRenderableWidget(new ContentBox(relX + 2, relY + 20, 199, 158, this));
        this.addRenderableWidget(new ClientPlayButton(relX + 2,relY + 2, this));
        this.addRenderableWidget(new PlayersListbox(relX + 265 - 60 - 2,relY + 20, 60, 158, this));

        this.addRenderableWidget(new ContextMenu(this));  // Has to be the last one in order to render above the other stuff

        GLFW.glfwSetInputMode(minecraft.getWindow().getWindow(), GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_HIDDEN);
    }

    @Override
    public boolean isPauseScreen()
    {
        return false;
    }



    @Override
    public void tick()
    {
        getContentBox().tick();
    }

    public static void contextMenuCallback(int option_id, Map<String, String> data)
    {
        switch (option_id)
        {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
        }
    }

    @Override
    public void render(@NotNull PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick)
    {
        this.renderBackground(pPoseStack);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        int relX = (width - WIDTH/2) / 2;
        int relY = (height - HEIGHT/2) / 2;

        // Draw background texture
        RenderSystem.setShaderTexture(0, GUI);
        blit(pPoseStack, relX, relY, WIDTH/2, HEIGHT/2, 0, 0, WIDTH, HEIGHT, WIDTH, HEIGHT);

        // Render widgets
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);

        // Draw legacy cursor texture
        RenderSystem.setShaderTexture(0, CURSOR);
        blit(pPoseStack, pMouseX, pMouseY, 10, 10, 0, 0, 50, 50, 50, 50);

    }

    public static void open()
    {
        Minecraft.getInstance().setScreen(new LeagueClientGUI());
    }


    // Pass GUI event to children cuz wtf?
    // TODO: [FIX] use onDrag method in Widget class
    // UPD: nvm

//    @Override
//    public boolean mouseReleased(double mouseX, double mouseY, int button)
//    {
//        boolean ret = false;
//        for(Widget el : GUIelements) ret |= el.mouseReleased(mouseX, mouseY, button);
//        return ret;
//    }


    // Widget methods

    public ContextMenu getContextMenu()
    {
        return (ContextMenu) this.children().get(this.children().size() - 1);
    }
    public ContentBox getContentBox()
    {
        return (ContentBox) this.children().get(0);
    }
    public ClientPlayButton getPlayButton()
    {
        return (ClientPlayButton) this.children().get(1);
    }
    public PlayersListbox getPlayersListbox()
    {
        return (PlayersListbox) this.children().get(2);
    }

}
