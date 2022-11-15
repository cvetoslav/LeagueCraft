package com.dm66.leaguecraft.rendering.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.util.Mth;
import org.lwjgl.opengl.GL11;

import java.util.*;

public class PlayersListbox extends AbstractWidget
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
    LeagueClientGUI parent;

    public PlayersListbox(int x, int y, int width, int height, LeagueClientGUI _p)
    {
        super(x, y, width, height, new TextComponent(""));
        parent = _p;

        players = new ArrayList<>();
        players.add(new PlayerInfo("W. Heisenberg", 69, 3));
        players.add(new PlayerInfo("Jesse", 1, 2));
        players.add(new PlayerInfo("Joe Mama", 2, 1));
        players.add(new PlayerInfo("Farmer", 100, 1));
        players.add(new PlayerInfo("vankata", 110, 0));
        players.add(new PlayerInfo("W. Heisenberg", 69, 3));
        players.add(new PlayerInfo("Jesse", 1, 2));
        players.add(new PlayerInfo("Joe Mama", 2, 1));
        players.add(new PlayerInfo("Farmer", 100, 1));
        players.add(new PlayerInfo("vankata", 110, 0));
        players.add(new PlayerInfo("W. Heisenberg", 69, 3));
        players.add(new PlayerInfo("Jesse", 1, 2));
        players.add(new PlayerInfo("Joe Mama", 2, 1));
        players.add(new PlayerInfo("Farmer", 100, 1));
        players.add(new PlayerInfo("vankata", 110, 0));
        players.add(new PlayerInfo("W. Heisenberg", 69, 3));
        players.add(new PlayerInfo("Jesse", 1, 2));
        players.add(new PlayerInfo("Joe Mama", 2, 1));
        players.add(new PlayerInfo("Farmer", 100, 1));
        players.add(new PlayerInfo("vankata", 110, 0));

        contentHeight = players.size() * item_h;
        scroll_h = Mth.clamp((height - 2)*(height - 2)/contentHeight, 10, height-2);
        scroll_w = 1;
        scroll_pos = curr_scroll_pos = 0;
    }

    @Override
    public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks)
    {
        if(this.visible)
        {
            this.isHovered = (!parent.getContextMenu().isHovered()) && mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            renderWidget(matrixStack, mouseX, mouseY, partialTicks);
        }
    }

    public void renderWidget(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks)
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

    private void drawContent(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks)
    {
        double scale = mc.getWindow().getGuiScale();
        int bottom = y + height - 1;
        GL11.glEnable(GL11.GL_SCISSOR_TEST);     // Some omega cringe and autistic interaction with content clipping
                                                 // in general we make sure that the coordinates are correctly
                                                 // converted to literally WINDOW pixels, can't tell what this exactly means
        GL11.glScissor((int)((this.x+1)  * scale), (int)(mc.getWindow().getHeight() - (bottom * scale)),
                (int)((width - scroll_w - 3) * scale), (int)((height - 2) * scale));

        int scrolled = (int) Mth.clamp(curr_scroll_pos * (contentHeight - height + 2), 0, contentHeight);
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

    private void drawPlayerBox(PoseStack matrixStack, int mouseX, int mouseY, int _x, int _y, int ind)
    {
        int color = 0xff0B1B25;
        if((!parent.getContextMenu().isHovered()) && mouseX >= _x && mouseY >= _y && mouseX < _x + width - 3 - scroll_w && mouseY < _y + item_h)
        {
            color = 0xff212D35;
            hovered_ind = ind;
        }
        fill(matrixStack, _x, _y, _x + width - 3 - scroll_w, _y + item_h, color);

        // Player name
        float scale = 0.5f;
        matrixStack.scale(scale, scale, scale);
        scale = 1/scale;
        drawString(matrixStack, mc.font, players.get(ind).name, (int) (scale*(_x + 5)), (int) (scale*(_y + 4)), 0xff999588);

        // Player status
        String txt = "Offline";
        color = 0xff5B5A56;
        switch (players.get(ind).status)
        {
            case 1 ->
            {
                txt = "Online";
                color = 0xff09A646;
            }
            case 2 ->
            {
                txt = "In Champion select";
                color = 0xff0ACBE6;
            }
            case 3 ->
            {
                txt = "In Game";
                color = 0xff0ACBE6;
            }
        }

        drawString(matrixStack, mc.font, txt, (int) (scale*(_x + 5)), (int) (scale*(_y + 12)), color);
        matrixStack.scale(scale, scale, scale);
    }

    private void drawScrollBar(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks)
    {
        this.isScrollHovered = (!parent.getContextMenu().isHovered()) && mouseX >= scroll_x && mouseY >= scroll_y && mouseX < scroll_x + scroll_w && mouseY < scroll_y + scroll_h;

        int color = 0xff1E2328;
        if(this.isHovered) color = 0xff785A28;
        if(this.isScrollHovered) color = 0xffCDBE91;
        if(this.isScrollClicked) color = 0xff463714;
        fill(matrixStack, scroll_x, scroll_y, scroll_x + scroll_w, scroll_y + scroll_h, color);
    }

    @Override
    public void mouseMoved(double pMouseX, double pMouseY) {
        super.mouseMoved(pMouseX, pMouseY);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button)
    {
        if(parent.getContextMenu().isHovered()) return false;
        else parent.getContextMenu().clear();
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
                LeagueClientGUI.contextMenuCallback(2, (Map<String, String>) new HashMap<>().put("player_id", String.valueOf(players.get(hovered_ind).id)));
            }
            return true;
        }
        else if(button == 1)
        {
            if(hovered_ind != -1)
            {
                // Opens Player context menu
                List<String> opts = new ArrayList<>();
                List<Integer> ids = new ArrayList<>();
                Map<String, String> data = new HashMap<>();
                data.put("player_id", String.valueOf(players.get(hovered_ind).id));

                opts.add("Invite to Game");     ids.add(0);
                opts.add("Send Message");       ids.add(2);
                opts.add("Spectate Game");      ids.add(0);
                opts.add("View Profile");       ids.add(4);
                opts.add("Unfriend");           ids.add(5);
                opts.add("Block");              ids.add(6);

                parent.getContextMenu().create((int)mouseX, (int)mouseY, opts, ids, data);
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

    private void recalcScrollBar()
    {
        scroll_pos = Mth.clamp(scroll_pos, 0, 1);
        curr_scroll_pos = Mth.clamp(curr_scroll_pos, 0, 1);

        scroll_x = x+width - 1 - scroll_w;
        scroll_y = (int) (y + 1 + curr_scroll_pos*(height - 2 - scroll_h));
    }

    @Override
    public void updateNarration(NarrationElementOutput pNarrationElementOutput) {

    }

    private static class PlayerInfo
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
