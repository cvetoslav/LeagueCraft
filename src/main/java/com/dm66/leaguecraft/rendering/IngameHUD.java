package com.dm66.leaguecraft.rendering;

import com.dm66.leaguecraft.Summoner;
import com.mojang.blaze3d.platform.GlStateManager;
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
        if(event.isCancelable()) return ;
        if(event.getType() == RenderGameOverlayEvent.ElementType.ALL)
        {
            Minecraft mc = Minecraft.getInstance();
            Summoner summoner = Summoner.getSummoner(mc.player);
            int posX = event.getWindow().getScaledWidth() / 2 - 91 - 80;
            int posY = event.getWindow().getScaledHeight() - 38;
            mc.textureManager.bindTexture(new ResourceLocation("leaguecraft:textures/hud/hud.png"));
            blit(event.getMatrixStack(), posX, posY, 0, 0, 77, 38, 77, 38);

            int attackDamage  = (int) Math.round(summoner.attackDamage);
            int armor         = (int) Math.round(summoner.armor);
            double attackSpeed   = summoner.attackSpeed;
            int abilityPower  = (int) Math.round(summoner.abilityPower);
            int magicResist   = (int) Math.round(summoner.magicResist);
            int movementSpeed = (int) Math.round(summoner.movementSpeed);

            // AD
            mc.fontRenderer.drawString(event.getMatrixStack(), Integer.toString(attackDamage), posX + 12, posY + 3, 0x6AE639);
            // ARM
            mc.fontRenderer.drawString(event.getMatrixStack(), Integer.toString(armor), posX + 12, posY + 15.5f, 0x6AE639);
            // AS
            mc.fontRenderer.drawString(event.getMatrixStack(), String.format("%.2f", attackSpeed), posX + 12, posY + 28.5f, 0x6AE639);
            // AP
            mc.fontRenderer.drawString(event.getMatrixStack(), Integer.toString(abilityPower), posX + 48.5f, posY + 3, 0x6AE639);
            // MR
            mc.fontRenderer.drawString(event.getMatrixStack(), Integer.toString(magicResist), posX + 48.5f, posY + 15.5f, 0x6AE639);
            // MS
            mc.fontRenderer.drawString(event.getMatrixStack(), Integer.toString(movementSpeed), posX + 48.5f, posY + 28.5f, 0x6AE639);

            // Health Bar
            int maxHealth      = (int) Math.round(summoner.maxHealth);
            int health         = (int) Math.round(summoner.health);
            int shield         = (int) Math.round(summoner.shield);
            int magicShield    = (int) Math.round(summoner.magicShield);
            int physicalShield = (int) Math.round(summoner.physicalShield);

            posX = event.getWindow().getScaledWidth() / 2 - 91;
            posY = event.getWindow().getScaledHeight() - 40;
            int barWidth = 90;
            int barHeight = 10;

            int maxFill = Math.max(maxHealth, health + shield + magicShield + physicalShield);

            mc.textureManager.bindTexture(new ResourceLocation("leaguecraft:textures/hud/health_bar.png"));
            blit(event.getMatrixStack(), posX, posY, 0, 0, barWidth, barHeight, 90, 10);

            int x = 90 * health / maxFill;
            int t = 90000 / maxFill;
            posX++; posY++; barHeight-=2; barWidth-=2;
            mc.textureManager.bindTexture(new ResourceLocation("leaguecraft:textures/hud/health.png"));
            blit(event.getMatrixStack(), posX, posY, 0, 0, t*(health/1000), barHeight, t, 9);
            posX += t*(health/1000);
            if(health % 1000 != 0)
            {
                blit(event.getMatrixStack(), posX, posY, 0, 0, t*(health%1000)/1000, barHeight, t, 9);
                posX += t*(health%1000)/1000;
            }

            mc.textureManager.bindTexture(new ResourceLocation("leaguecraft:textures/hud/shield.png"));
            blit(event.getMatrixStack(), posX, posY, 0, 0, t*(shield/1000), barHeight, t, 9);
            posX += t*(shield/1000);
            if(shield % 1000 != 0)
            {
                blit(event.getMatrixStack(), posX, posY, 0, 0, t*(shield%1000)/1000, barHeight, t, 9);
                posX += t*(shield%1000)/1000;
            }

            mc.textureManager.bindTexture(new ResourceLocation("leaguecraft:textures/hud/magic_shield.png"));
            blit(event.getMatrixStack(), posX, posY, 0, 0, t*(magicShield/1000), barHeight, t, 9);
            posX += t*(magicShield/1000);
            if(magicShield % 1000 != 0)
            {
                blit(event.getMatrixStack(), posX, posY, 0, 0, t*(magicShield%1000)/1000, barHeight, t, 9);
                posX += t*(magicShield%1000)/1000;
            }

            mc.textureManager.bindTexture(new ResourceLocation("leaguecraft:textures/hud/physical_shield.png"));
            blit(event.getMatrixStack(), posX, posY, 0, 0, t*(physicalShield/1000), barHeight, t, 9);
            posX += t*(physicalShield/1000);
            if(physicalShield % 1000 != 0)
            {
                blit(event.getMatrixStack(), posX, posY, 0, 0, t*(physicalShield%1000)/1000, barHeight, t, 9);
                posX += t*(physicalShield%1000)/1000;
            }

            GlStateManager.scalef(0.5f, 0.5f, 0.5f);
            String str = Integer.toString(health) + " / " + Integer.toString(maxHealth);
            posX = event.getWindow().getScaledWidth() / 2 - 29 - mc.fontRenderer.getStringWidth(str) / 2;
            mc.fontRenderer.drawString(event.getMatrixStack(), str, 2*posX, 2*(posY + 3), 0xb36b00);
            GlStateManager.scalef(2f, 2f, 2f);

            // Mana Bar
            int mana = (int) Math.round(summoner.mana);
            int maxMana = (int) Math.round(summoner.maxMana);

            posX = event.getWindow().getScaledWidth() / 2 + 1;
            posY = event.getWindow().getScaledHeight() - 40;
            barHeight = 10;
            barWidth = 90;

            mc.textureManager.bindTexture(new ResourceLocation("leaguecraft:textures/hud/mana_bar.png"));
            blit(event.getMatrixStack(), posX, posY, 0, 0, barWidth, barHeight, 90, 10);
            mc.textureManager.bindTexture(new ResourceLocation("leaguecraft:textures/hud/mana.png"));
            blit(event.getMatrixStack(), posX+1, posY+1, 0, 0, (barWidth-2)*mana/maxMana, barHeight-2, 70, 9);

            GlStateManager.scalef(0.5f, 0.5f, 0.5f);
            String str_ = Integer.toString(mana) + " / " + Integer.toString(maxMana);
            posX = event.getWindow().getScaledWidth() / 2 + 63 - mc.fontRenderer.getStringWidth(str) / 2;
            mc.fontRenderer.drawString(event.getMatrixStack(), str_, 2*posX, 2*(posY + 4), 0xb36b00);
            GlStateManager.scalef(2f, 2f, 2f);

        }
    }
}
