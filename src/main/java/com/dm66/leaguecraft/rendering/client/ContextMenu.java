package com.dm66.leaguecraft.rendering.client;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import org.lwjgl.opengl.GL11;

import java.util.List;
import java.util.function.Function;

public class ContextMenu extends Widget
{
    final static int border_w = 1;
    final static int padding = 3;
    final static int option_h = 10;
    final static double scale = 0.5;
    List<String> options;
    List<Function> funcs;
    int sz = 0;
    int hovered_ind = -1;

    static final Minecraft mc = Minecraft.getInstance();

    public ContextMenu()
    {
        super(0,0,0,0, new StringTextComponent(""));
    }

    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks)
    {
        if(this.visible)
        {
            this.isHovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            renderWidget(matrixStack, mouseX, mouseY, partialTicks);
        }
    }

    @Override
    public void renderWidget(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks)
    {
        fillGradient(matrixStack, this.x, this.y, this.x + width, this.y + height, 0xff463714, 0xff795B29);
        fill(matrixStack, this.x + border_w, this.y + border_w, this.x + width - border_w, this.y + height - border_w, 0xff010A13);

        int box_x = this.x + border_w, box_y = this.y + border_w, box_w = this.width - 2 * border_w;
        hovered_ind = -1;

        GL11.glScaled(scale, scale, scale);

        for(int i = 0; i < sz; i++)
        {
            int color = 0xffCDBE91;
            // TODO: add check for   funcs.get(i) == null   and disable option, color = 0xff585A56
            //if(funcs.size() <= i || funcs.get(i) == null) color = 0xff585A56; else
            if(mouseX >= box_x && mouseY >= box_y && mouseX < box_x + box_w && mouseY < box_y + option_h)
            {
                fill(matrixStack, (int) (box_x / scale), (int) (box_y / scale), (int) ((box_x + box_w) / scale), (int) ((box_y + option_h) / scale), 0xff1E2328);
                hovered_ind = i;
                color = 0xffF0E6D2;
            }
            drawString(matrixStack, mc.fontRenderer, options.get(i), (int) ((box_x + padding) / scale), (int) ((box_y + padding) / scale), color);

            box_y += option_h;
        }

        GL11.glScaled(1/scale, 1/scale, 1/scale);
    }

    @Override
    public boolean isHovered() {
        return this.isHovered && this.visible;
    }

    public void clear()
    {
        x = y = height = width = 0;
        visible = false;
    }

    public void create(int x, int y, List<String> opts, List<Function> f)
    {
        create(x, y, 0, opts, f);
    }

    public void create(int x, int y, int width, List<String> opts, List<Function> f)
    {
        this.x = x;
        this.y = y;
        sz = opts.size();
        height = option_h * sz + 2;
        options = opts;
        funcs = f;
        visible = true;

        int min_w = 0;
        for(String s : opts)
            min_w = (int) Math.max(min_w, scale * mc.fontRenderer.getStringWidth(s));
        min_w += 2 * (padding + border_w);
        this.width = Math.max(width, min_w);
    }
}
