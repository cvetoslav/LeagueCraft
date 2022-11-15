package com.dm66.leaguecraft.rendering.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.TextComponent;

import java.util.List;
import java.util.Map;

public class ContextMenu extends AbstractWidget
{
    final static int border_w = 1;
    final static int padding = 3;
    final static int option_h = 10;
    final static float scale = 0.5f;
    List<String> options;
    List<Integer> option_ids;
    Map<String, String> data;
    int sz = 0;
    int hovered_ind = -1;

    static final Minecraft mc = Minecraft.getInstance();
    LeagueClientGUI parent;

    public ContextMenu(LeagueClientGUI _p)
    {
        super(0,0,0,0, new TextComponent(""));
        parent = _p;
    }

    public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks)
    {
        if(this.visible)
        {
            this.isHovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            renderWidget(matrixStack, mouseX, mouseY, partialTicks);
        }
    }

    public void renderWidget(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks)
    {
        fillGradient(matrixStack, this.x, this.y, this.x + width, this.y + height, 0xff463714, 0xff795B29);
        fill(matrixStack, this.x + border_w, this.y + border_w, this.x + width - border_w, this.y + height - border_w, 0xff010A13);

        int box_x = this.x + border_w, box_y = this.y + border_w, box_w = this.width - 2 * border_w;
        hovered_ind = -1;

        matrixStack.scale(scale, scale, scale);

        for(int i = 0; i < sz; i++)
        {
            int color = 0xffCDBE91;
            // Check if option is disabled
            if(option_ids.get(i) == 0) color = 0xff585A56;
            else if(mouseX >= box_x && mouseY >= box_y && mouseX < box_x + box_w && mouseY < box_y + option_h)
            {
                fill(matrixStack, (int) (box_x / scale), (int) (box_y / scale), (int) ((box_x + box_w) / scale), (int) ((box_y + option_h) / scale), 0xff1E2328);
                hovered_ind = i;
                color = 0xffF0E6D2;
            }
            drawString(matrixStack, mc.font, options.get(i), (int) ((box_x + padding) / scale), (int) ((box_y + padding) / scale), color);

            box_y += option_h;
        }

        matrixStack.scale(1/scale, 1/scale, 1/scale);
    }

    public boolean isHovered() {
        return this.isHovered && this.visible;
    }

    public void clear()
    {
        x = y = height = width = 0;
        visible = false;
    }

    public void create(int x, int y, List<String> opts, List<Integer> ids, Map<String, String> _data)
    {
        create(x, y, 0, opts, ids, _data);
    }

    public void create(int x, int y, int width, List<String> opts, List<Integer> ids, Map<String, String> _data)
    {
        this.x = x;
        this.y = y;
        sz = opts.size();
        height = option_h * sz + 2;
        options = opts;
        option_ids = ids;
        data = _data;
        visible = true;

        int min_w = 0;
        for(String s : opts)
            min_w = (int) Math.max(min_w, scale * mc.font.width(s));
        min_w += 2 * (padding + border_w);
        this.width = Math.max(width, min_w);
    }

    @Override
    public void mouseMoved(double pMouseX, double pMouseY) {
        super.mouseMoved(pMouseX, pMouseY);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button)
    {
        if(button == 0 && hovered_ind != -1)
        {
            this.clear();
            LeagueClientGUI.contextMenuCallback(option_ids.get(hovered_ind), data);
            return true;
        }
        return false;
    }

    @Override
    public boolean mouseScrolled(double pMouseX, double pMouseY, double pDelta) {
        return super.mouseScrolled(pMouseX, pMouseY, pDelta);
    }

    @Override
    public boolean keyPressed(int pKeyCode, int pScanCode, int pModifiers) {
        return super.keyPressed(pKeyCode, pScanCode, pModifiers);
    }

    @Override
    public boolean keyReleased(int pKeyCode, int pScanCode, int pModifiers) {
        return super.keyReleased(pKeyCode, pScanCode, pModifiers);
    }

    @Override
    public boolean charTyped(char pCodePoint, int pModifiers) {
        return super.charTyped(pCodePoint, pModifiers);
    }

    @Override
    public void updateNarration(NarrationElementOutput pNarrationElementOutput) {

    }
}
