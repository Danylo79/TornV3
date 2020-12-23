package dev.dankom.torn.gui.alt;

import dev.dankom.torn.Torn;
import dev.dankom.torn.alt.Alt;
import dev.dankom.torn.alt.AltManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Keyboard;

import java.awt.*;
import java.io.IOException;

public final class GuiAltLogin extends GuiScreen {
    private AltManager manager;
    private AltSelectionList list;
    private GuiButton btnDeleteAlt;

    @Override
    protected void actionPerformed(GuiButton button) {
        switch (button.id) {
            case 0: {
                this.mc.displayGuiScreen(null);
            }
            case 3: {
                this.mc.displayGuiScreen(new GuiAltCreate(this));
                break;
            }
            case 2: {
                Alt alt = list.getSelectedAlt();
                if (alt != null) {
                    Torn.getAltManager().removeAlt(alt.getUsername());
                    this.mc.displayGuiScreen(null);
                }
            }
        }
    }

    public void handleMouseInput() throws IOException
    {
        super.handleMouseInput();
        this.list.handleMouseInput();
    }

    @Override
    public void drawScreen(int x2, int y2, float z2) {
        this.drawDefaultBackground();
        this.list.drawScreen(x2, y2, z2);
        this.mc.fontRendererObj.drawStringWithShadow("Alt Manager", width / 2 - mc.fontRendererObj.getStringWidth("Alt Manager") + 20, 5, new Color(146, 146, 146).getRGB());
        this.mc.fontRendererObj.drawStringWithShadow("Alts: " + Torn.getAltManager().getAlts().size(), width / 2 - 25, 20, new Color(69, 69, 69).getRGB());
        super.drawScreen(x2, y2, z2);
    }

    @Override
    public void initGui() {
        this.list = new AltSelectionList(this, Minecraft.getMinecraft(), this.width, this.height, 32, this.height - 64, 36);
        this.list.setShowSelectionBox(true);
        this.list.setSelectedSlotIndex(-1);
        this.buttonList.add(new GuiButton(0, this.width / 2 + 4 + 76, this.height - 28, 75, 20, "Back"));
        this.buttonList.add(this.btnDeleteAlt = new GuiButton(2, this.width / 2 - 122, this.height - 28, 20, 20, "-"));
        this.buttonList.add(new GuiButton(3, this.width / 2 - 150, this.height - 28, 20, 20, "+"));
        Keyboard.enableRepeatEvents(true);
    }

    @Override
    protected void keyTyped(char character, int key) {
        try {
            super.keyTyped(character, key);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
    {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        this.list.mouseClicked(mouseX, mouseY, mouseButton);
    }

    protected void mouseReleased(int mouseX, int mouseY, int state)
    {
        super.mouseReleased(mouseX, mouseY, state);
        this.list.mouseReleased(mouseX, mouseY, state);
    }

    @Override
    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
    }

    @Override
    public void updateScreen() {

    }

    public void selectAlt(int index) {
        list.setSelectedSlotIndex(index);
    }
}
