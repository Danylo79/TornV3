package dev.dankom.torn.gui.alt;

import dev.dankom.torn.Torn;
import dev.dankom.torn.alt.Alt;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiListExtended;

import java.util.ArrayList;
import java.util.List;

public class AltSelectionList extends GuiListExtended {

    private List<AltSelectionItem> items = new ArrayList<AltSelectionItem>();
    private GuiAltLogin owner;
    private int selectedSlotIndex = -1;

    public AltSelectionList(GuiAltLogin owner, Minecraft mcIn, int widthIn, int heightIn, int topIn, int bottomIn, int slotHeightIn) {
        super(mcIn, widthIn, heightIn, topIn, bottomIn, slotHeightIn);
        this.owner = owner;

        items.clear();
        createAlt("MeteorForTheWin", "");
        createAlt("Yeet1234", "");
        createAlt("ToEZ", "");
        createAlt("NoHacksJustSkill", "");
        for (int i = 0; i < Torn.getAltManager().getAlts().size(); i++) {
            items.add(new AltSelectionItem(Torn.getAltManager().getAlts().get(i), owner));
        }
    }

    @Override
    public IGuiListEntry getListEntry(int index) {
        return items.get(index);
    }

    @Override
    protected int getSize() {
        return items.size();
    }

    protected int getScrollBarX()
    {
        return super.getScrollBarX() + 30;
    }

    public int getListWidth()
    {
        return super.getListWidth() + 85;
    }

    @Override
    public void setShowSelectionBox(boolean showSelectionBoxIn) {
        super.setShowSelectionBox(showSelectionBox);
    }

    protected boolean isSelected(int slotIndex)
    {
        return slotIndex == this.selectedSlotIndex;
    }

    public void setSelectedSlotIndex(int selectedSlotIndex) {
        this.selectedSlotIndex = selectedSlotIndex;
    }

    public Alt getSelectedAlt() {
        if (selectedSlotIndex != -1) {
            return items.get(selectedSlotIndex).getCurrentAlt();
        }
        return null;
    }

    public void createAlt(String username, String password) {
        Alt alt = new Alt(username, password);
        Torn.getAltManager().addAlt(alt);
    }
}
