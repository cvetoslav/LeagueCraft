package com.dm66.leaguecraft;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.apache.logging.log4j.LogManager;

public class IngameHUD extends AbstractGui
{
    @SubscribeEvent
    public void onPreRenderGameOverlay(RenderGameOverlayEvent.Pre event)
    {
        if(event.isCancelable())
        {
            if(event.getType() == RenderGameOverlayEvent.ElementType.HEALTH) event.setCanceled(true);
            else if(event.getType() == RenderGameOverlayEvent.ElementType.FOOD) event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onRenderGameOverlay(RenderGameOverlayEvent event)
    {
        if(!event.isCancelable() && event.getType() == RenderGameOverlayEvent.ElementType.ALL)
        {
            Minecraft mc = Minecraft.getInstance();
            int posX = event.getWindow().getScaledWidth() / 2 - 91 - 80;
            int posY = event.getWindow().getScaledHeight() - 38;
            mc.textureManager.bindTexture(new ResourceLocation("leaguecraft:textures/hud/hud.png"));
            mc.fontRenderer.drawString(event.getMatrixStack(), "123", posX + 12, posY + 6, 0x123456);
            blit(event.getMatrixStack(), posX, posY, 0, 0, 77, 38, 77, 38);
        }
    }
}
