package com.dm66.leaguecraft.rendering;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class WorldProjectionRenderer
{
//    @SubscribeEvent
//    @OnlyIn(Dist.CLIENT)
//    public static void onWorldRender(RenderWorldLastEvent event)
//    {
//        GL11.glLineWidth(5);
//        BufferBuilder b = Tessellator.getInstance().getBuffer();
//        b.begin(GL11.GL_LINES, DefaultVertexFormats.POSITION_COLOR);
//        renderOverlay(event.getPartialTicks(), event.getMatrixStack(), b);
//        Tessellator.getInstance().draw();
//    }
//
//    private static void renderOverlay(float partialTicks, MatrixStack stack, IVertexBuilder consumer)
//    {
//        Entity player = Minecraft.getInstance().getRenderViewEntity();
//        double cameraX = player.lastTickPosX + (player.getPosX() - player.lastTickPosX) * (double)partialTicks;
//        double cameraY = player.lastTickPosY + (player.getPosY() - player.lastTickPosY) * (double)partialTicks + player.getEyeHeight(player.getPose());
//        double cameraZ = player.lastTickPosZ + (player.getPosZ() - player.lastTickPosZ) * (double)partialTicks;
//        stack.push();
//        stack.translate(-cameraX, -cameraY, -cameraZ);
//        double posX = cameraX;
//        double posY = player.lastTickPosY + (player.getPosY() - player.lastTickPosY) * (double)partialTicks + 0.2;
//        double posZ = cameraZ;
//        Vector3d lookVec = player.getLookVec();
//        lookVec = new Vector3d(lookVec.x, 0 ,lookVec.z).normalize();
//        Vector3d right = lookVec.crossProduct(new Vector3d(0,1,0));
//        Vector3d left = right.scale(-1);
//        double range = 4.2;
//        drawLine(consumer, stack, new Vector3d(posX, posY, posZ).add(right.scale(0.5)), new Vector3d(posX, posY, posZ).add(right.scale(0.5)).add(lookVec.scale(range)), 85, 247, 240);
//        drawLine(consumer, stack, new Vector3d(posX, posY, posZ).add(left.scale(0.5)), new Vector3d(posX, posY, posZ).add(left.scale(0.5)).add(lookVec.scale(range)), 85, 247, 240);
//        stack.pop();
//    }
//
//    private static void drawLine(IVertexBuilder consumer, MatrixStack stack, float x1, float y1, float z1, float x2, float y2, float z2, int red, int green, int blue)
//    {
//        drawLine(consumer, stack, x1, y1, z1, x2, y2, z2, red, green, blue, 1);
//    }
//
//    private static void drawLine(IVertexBuilder consumer, MatrixStack stack, Vector3d a, Vector3d b, int red, int green, int blue)
//    {
//        drawLine(consumer, stack, a, b, red, green, blue, 1);
//    }
//
//    private static void drawLine(IVertexBuilder consumer, MatrixStack stack, Vector3d a, Vector3d b, int red, int green, int blue, int alpha)
//    {
//        drawLine(consumer, stack, (float) a.x, (float) a.y, (float) a.z, (float) b.x, (float) b.y, (float) b.z, red, green, blue, alpha);
//    }
//
//    private static void drawLine(IVertexBuilder consumer, MatrixStack stack, float x1, float y1, float z1, float x2, float y2, float z2, int red, int green, int blue, int alpha)
//    {
//        Matrix4f model = stack.getLast().getMatrix();
//        consumer.pos(model, x1, y1, z1).color(col2flo(red), col2flo(green), col2flo(blue), col2flo(alpha)).endVertex();
//        consumer.pos(model, x2, y2, z2).color(col2flo(red), col2flo(green), col2flo(blue), col2flo(alpha)).endVertex();
//    }
//
//    private static float col2flo(int col)
//    {
//        return (float)col/255;
//    }

}
