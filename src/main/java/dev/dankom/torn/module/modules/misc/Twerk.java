package dev.dankom.torn.module.modules.misc;

import dev.dankom.torn.event.EventTarget;
import dev.dankom.torn.event.events.UpdateEvent;
import dev.dankom.torn.module.base.Category;
import dev.dankom.torn.module.base.Module;

import java.awt.*;

public class Twerk extends Module {

    private boolean skipTick;

    public Twerk() {
        super("Twerk", "Makes you twerk", Category.MISC, -1, new Color(0, 144, 255), true, true);
    }

    @EventTarget
    public void onUpdate(UpdateEvent event) {
        if(!skipTick){
            skipTick = true;
            invoker.setShift(true);
        }else{
            skipTick = false;
            invoker.setShift(false);
        }
    }
}
