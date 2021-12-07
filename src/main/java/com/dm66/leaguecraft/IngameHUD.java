package com.dm66.leaguecraft;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.apache.logging.log4j.LogManager;

public class IngameHUD extends AbstractGui
{
    @SubscribeEvent
    public void onRenderGameOverlay(RenderGameOverlayEvent event)
    {
        if(!event.isCancelable() && event.getType() == RenderGameOverlayEvent.ElementType.ALL)
        {
            Minecraft mc = Minecraft.getInstance();
            int posX = event.getWindow().getScaledWidth() * 3 / 10 - 77;
            int posY = event.getWindow().getScaledHeight() - 38;
            mc.textureManager.bindTexture(new ResourceLocation("leaguecraft:textures/hud/hud.png"));
            blit(event.getMatrixStack(), posX, posY, 0, 0, 77, 38, 77, 38);
        }
    }
}
