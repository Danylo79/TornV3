package dev.dankom.torn.module.modules.misc;

import dev.dankom.torn.event.EventTarget;
import dev.dankom.torn.event.events.UpdateEvent;
import dev.dankom.torn.module.base.Category;
import dev.dankom.torn.module.base.Module;
import dev.dankom.torn.util.InventoryUtil;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;

import java.awt.*;

public class AutoEat extends Module {

    private InventoryUtil utils = new InventoryUtil();
    private boolean goOnce = false;
    private int prevSlot = -1;
    private boolean finished = false;

    public AutoEat() {
        super("AutoEat", "Automatically eats food when you are hungry", Category.MISC, -1, new Color(255, 100, 0), true, true);
    }

    @EventTarget
    public void onUpdate(UpdateEvent event) {
        if(prevSlot != -1 && finished && goOnce){
            invoker.setInvSlot(prevSlot);
            invoker.setUseItemKeyPressed(false);
            goOnce = false;
        }
        if(invoker.getFoodLevel() < 18){
            for(int i=0; i<9; i++){
                ItemStack item = invoker.getItemAtSlotHotbar(i);
                if(item != null && item.getItem() instanceof ItemFood) {
                    prevSlot = invoker.getCurInvSlot();
                    invoker.setInvSlot(i);
                    invoker.setUseItemKeyPressed(true);
                    if(invoker.getFoodLevel() > 16){
                        goOnce = true;
                        finished = true;
                    }
                }
            }
        }
    }
}
