package com.dm66.leaguecraft.rendering.client;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import org.lwjgl.opengl.GL11;
import software.bernie.shadowed.eliotlash.mclib.math.functions.limit.Min;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PlayersListbox extends Widget
{

    int scroll_x, scroll_y, scroll_w, scroll_h;
    double scroll_pos, curr_scroll_pos, sp;
    double scroll_speed = 18;  // pixel per tick
    int contentHeight;
    double sc_y;
    boolean isScrollHovered;
    boolean isScrollClicked;
    int hovered_ind = -1;
    final int item_h = 20;

    List<PlayerInfo> players;

    static final Minecraft mc = Minecraft.getInstance();

    public PlayersListbox(int x, int y, int width, int height)
    {
        super(x, y, width, height, new StringTextComponent(""));

        players = new ArrayList<>();
        players.add(new PlayerInfo("W. Heisenberg", 69, 2));
        players.add(new PlayerInfo("Jesse", 1, 1));
        players.add(new PlayerInfo("Joe Mama", 2, 1));
        players.add(new PlayerInfo("Farmer", 100, 1));
        players.add(new PlayerInfo("vankata", 110, 0));

        contentHeight = players.size() * item_h;
        scroll_h = MathHelper.clamp((height - 2)*(height - 2)/contentHeight, 10, height-2);
        scroll_w = 1;
        scroll_pos = curr_scroll_pos = 0;
    }

    @Override
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
        fill(matrixStack, x, y, x+width, y+height, 0xff010B13);

        recalcScrollBar();

        // smooth scroll
        if(curr_scroll_pos != scroll_pos)
        {
            double delta = scroll_speed * partialTicks / (contentHeight - height + 2);
            if(delta >= Math.abs(scroll_pos - curr_scroll_pos)) curr_scroll_pos = scroll_pos;
            else if(scroll_pos > curr_scroll_pos) curr_scroll_pos += delta;
            else curr_scroll_pos -= delta;
        }

        drawScrollBar(matrixStack, mouseX, mouseY, partialTicks);
        drawContent(matrixStack, mouseX, mouseY, partialTicks);
    }

    private void drawContent(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks)
    {
        double scale = mc.getMainWindow().getGuiScaleFactor();
        int bottom = y + height - 1;
        GL11.glEnable(GL11.GL_SCISSOR_TEST);     // Some omega cringe and autistic interaction with content clipping
                                                 // in general we make sure that the coordinates are correctly
                                                 // converted to literally WINDOW pixels, can't tell what this exactly means
        GL11.glScissor((int)((this.x+1)  * scale), (int)(mc.getMainWindow().getFramebufferHeight() - (bottom * scale)),
                (int)((width - scroll_w - 3) * scale), (int)((height - 2) * scale));

        int scrolled = (int) MathHelper.clamp(curr_scroll_pos * (contentHeight - height + 2), 0, contentHeight);
        int i = scrolled / item_h;
        int rest = scrolled % item_h;
        int _y = y+1 - rest;
        hovered_ind = -1;
        while (_y < y+height && i < players.size())
        {
            drawPlayerBox(matrixStack, mouseX, mouseY, x+1, _y, i);
            _y += item_h;
            i++;
        }

        GL11.glDisable(GL11.GL_SCISSOR_TEST);
    }

    private void drawPlayerBox(MatrixStack matrixStack, int mouseX, int mouseY, int _x, int _y, int ind)
    {
        int color = 0xff0B1B25;
        if(mouseX >= _x && mouseY >= _y && mouseX < _x + width - 3 - scroll_w && mouseY < _y + item_h)
        {
            color = 0xff212D35;
            hovered_ind = ind;
        }
        fill(matrixStack, _x, _y, _x + width - 3 - scroll_w, _y + item_h, color);
        drawString(matrixStack, mc.fontRenderer, players.get(ind).name, _x + 5, _y + item_h / 2, 0xff999588);
    }

    private void drawScrollBar(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks)
    {
        this.isScrollHovered = mouseX >= scroll_x && mouseY >= scroll_y && mouseX < scroll_x + scroll_w && mouseY < scroll_y + scroll_h;

        int color = 0xff1E2328;
        if(this.isHovered) color = 0xff785A28;
        if(this.isScrollHovered) color = 0xffCDBE91;
        if(this.isScrollClicked) color = 0xff463714;
        fill(matrixStack, scroll_x, scroll_y, scroll_x + scroll_w, scroll_y + scroll_h, color);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button)
    {
        if(button == 0)
        {
            if(isScrollHovered)
            {
                isScrollClicked = true;
                sc_y = mouseY;
                sp = scroll_pos;
            }
            else if(hovered_ind != -1)
            {
                // TODO: open chat with player
            }
            return true;
        }
        else if(button == 1)
        {
            if(hovered_ind != -1)
            {
                // TODO: open player context menu
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button)
    {
        if(button == 0)
        {
            if(isScrollClicked) isScrollClicked = false;
            return true;
        }
        return false;
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY)
    {
        if(isScrollClicked)
        {
            // We don't need smooth scroll for scroll bar dragging (curr_scroll_pos)
            if((height - 2 - scroll_h) > 0) scroll_pos = curr_scroll_pos = sp + (mouseY - sc_y)/(height - 2 - scroll_h);
            return true;
        }
        return false;
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double delta)
    {
        if(delta != 0)
        {
            if((height - 2 - scroll_h) > 0) scroll_pos -= 80 * delta / contentHeight;
            return true;
        }
        return false;
    }

    private void recalcScrollBar()
    {
        scroll_pos = MathHelper.clamp(scroll_pos, 0, 1);
        curr_scroll_pos = MathHelper.clamp(curr_scroll_pos, 0, 1);

        scroll_x = x+width - 1 - scroll_w;
        scroll_y = (int) (y + 1 + curr_scroll_pos*(height - 2 - scroll_h));
    }

    private class PlayerInfo
    {
        String name;
        int id;
        int status; // 1-online, 2-champselect, 3-ingame

        public PlayerInfo(String name, int id, int status)
        {
            this.id = id;
            this.name = name;
            this.status = status;
        }
    }
}
