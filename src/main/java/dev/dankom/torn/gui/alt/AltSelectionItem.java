package dev.dankom.torn.gui.alt;

import dev.dankom.torn.Torn;
import dev.dankom.torn.alt.Alt;
import dev.dankom.torn.alt.AltManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

import java.awt.*;

public class AltSelectionItem implements GuiListExtended.IGuiListEntry {

    private Minecraft mc = Minecraft.getMinecraft();
    private GuiAltLogin owner;
    private Alt alt;
    private static final ResourceLocation SERVER_SELECTION_BUTTONS = new ResourceLocation("textures/gui/server_selection.png");

    public AltSelectionItem(Alt alt, GuiAltLogin owner) {
        this.owner = owner;
        this.alt = alt;
    }

    @Override
    public void setSelected(int p_178011_1_, int p_178011_2_, int p_178011_3_) {

    }

    @Override
    public void drawEntry(int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX, int mouseY, boolean isSelected) {
        float x1 = (float) listWidth / 2 - 40;
        mc.fontRendererObj.drawString("Name: " + alt.getUsername(), (int) x1, y + 5, new Color(0x8D8D8D).getRGB());
        mc.fontRendererObj.drawString(alt.isOffline() ? "cracked" : "premium", (int) x1, y + 15, new Color(0x616161).getRGB());
        if (isSelected) {
            this.mc.getTextureManager().bindTexture(SERVER_SELECTION_BUTTONS);
            Gui.drawRect(x, y, x + 32, y + 32, -1601138544);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            int k1 = mouseX - x;
            int l1 = mouseY - y;
            if (true) {
                if (k1 < 32 && k1 > 16) {
                    Gui.drawModalRectWithCustomSizedTexture(x, y, 0.0F, 32.0F, 32, 32, 256.0F, 256.0F);
                } else {
                    Gui.drawModalRectWithCustomSizedTexture(x, y, 0.0F, 0.0F, 32, 32, 256.0F, 256.0F);
                }
            }
        }
    }

    @Override
    public boolean mousePressed(int slotIndex, int p_148278_2_, int p_148278_3_, int p_148278_4_, int p_148278_5_, int p_148278_6_) {
        this.owner.selectAlt(slotIndex);
        if (p_148278_5_ < 32 && p_148278_5_ > 16 && true) {
            Torn.getAltManager().login(alt);
            this.mc.displayGuiScreen(this.owner);
            return true;
        }
        return false;
    }

    @Override
    public void mouseReleased(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY) {

    }

    public Alt getCurrentAlt() {
        return this.alt;
    }
}

