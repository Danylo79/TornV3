package dev.dankom.torn.module.modules.combat;

import dev.dankom.torn.Torn;
import dev.dankom.torn.module.base.Category;
import dev.dankom.torn.module.base.Module;
import dev.dankom.torn.settings.Setting;
import dev.dankom.torn.util.ColorUtil;
import dev.dankom.torn.util.InventoryUtil;
import dev.dankom.torn.util.StringUtil;
import dev.dankom.torn.util.wrapper.Invoker;

import java.awt.*;

public class AutoSoup extends Module {

    private boolean shouldSoup = false;
    private Invoker invo = Torn.getWrapper().getInvoker();
    private InventoryUtil invUtils = new InventoryUtil();

    //Bowl = 281, soup = 282

    private int prevSlot = -1;
    private int soupId = 282, bowlId = 281;

    public AutoSoup() {
        super("AutoSoup", "Automatically soups when your health gets low", Category.COMBAT, -1, new Color(99, 147, 2), true, true);
        addSetting(new Setting("Min Health", this, 19.0, 1.0, 20.0, false));
    }

    @Override
    public void onTick() {

        setEnabledModName(ColorUtil.translate("AutoSoup " + StringUtil.wrapWithSquareBracket("Min HP: " + getSetting("Min Health").getValDouble())));
        if(Torn.getWrapper() != null && Torn.getWrapper().getPlayer().getHealth() <= getSetting("Min Health").getValDouble()) {
            shouldSoup = true;
        }else{
            shouldSoup = false;
        }

        if (shouldSoup) {

            if (prevSlot == -1) {
                prevSlot = invo.getCurInvSlot();
            }

            int slotHotbar = invUtils.getSlotOfHotbarItem(soupId);

            if (slotHotbar != -1) {
                invo.setInvSlot(slotHotbar);
                invUtils.sendItemUse(invo.getItemAtSlot(slotHotbar));
            } else {
                int invSlot = invUtils.getSlotOfInvItem(soupId);

                if (invSlot != -1) {

                    int freeSlot = invUtils.getFreeSlotInInv(bowlId);
                    int freeHotbarSlot = invUtils.getFreeSlotInHotbar(0);

                    if (freeHotbarSlot != -1) {
                        invUtils.click(freeSlot, 1);
                        invUtils.click(invSlot, 1);
                    } else {
                        int hotBarSlotBad = invUtils.getSlotOfHotbarItem(bowlId);

                        if (hotBarSlotBad != -1) {
                            invo.dropItemStack(hotBarSlotBad);
                            invUtils.click(invSlot, 1);
                            invUtils.sendItemUse(invo.getItemAtSlot(invSlot));
                        }

                        invUtils.click(invSlot, 1);
                        invUtils.click(freeSlot, 1);
                    }
                }
            }

        } else {
            if (prevSlot != -1 && invo.getCurInvSlot() != prevSlot) {
                invo.setInvSlot(prevSlot);
                prevSlot = -1;
            }
        }
    }
}
