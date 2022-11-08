package com.dm66.leaguecraft.rendering.client;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.List;
import java.util.function.Function;

public class ContextMenu extends Widget
{
    final static int border_w = 1;
    final static int padding = 5;
    final static int option_h = 15;
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
        for(int i = 0; i < sz; i++)
        {
            int color = 0xffCDBE91;
            // TODO: add check for   funcs.get(i) == null   and disable option, color = 0xff585A56
            if(mouseX >= box_x && mouseY >= box_y && mouseX < box_x + box_w && mouseY < box_y + option_h)
            {
                fill(matrixStack, box_x, box_y, box_x + box_w, box_y + option_h, 0xff1E2328);
                hovered_ind = i;
                color = 0xffF0E6D2;
            }
            drawString(matrixStack, mc.fontRenderer, options.get(i), box_x + padding, box_y + padding, color);

            box_y += option_h;
        }
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
            min_w = Math.max(min_w, mc.fontRenderer.getStringWidth(s));
        min_w += 2 * (padding + border_w);
        this.width = Math.max(width, min_w);
    }
}
