package dev.dankom.torn.module.modules.movement;

import dev.dankom.torn.event.EventTarget;
import dev.dankom.torn.event.events.UpdateEvent;
import dev.dankom.torn.module.base.Category;
import dev.dankom.torn.module.base.Module;

import java.awt.*;

public class Spider extends Module {
    public Spider() {
        super("Spider", "Climb up walls", Category.MOVEMENT, -1, new Color(255, 123, 0), true, true);
    }

    @EventTarget
    public void onUpdate(UpdateEvent event){
        if(invoker.isCollidedHorizontally()){
            invoker.setMotionY(0.2);

            float var6 = 0.15F;

            if (invoker.getMotionX() < (double)(-var6))
            {
                invoker.setMotionX((double)(-var6));
            }

            if (invoker.getMotionX() > (double)var6)
            {
                invoker.setMotionX((double)var6);
            }

            if (invoker.getMotionZ() < (double)(-var6))
            {
                invoker.setMotionZ((double)(-var6));
            }

            if (invoker.getMotionZ() > (double)var6)
            {
                invoker.setMotionZ((double)var6);
            }

            invoker.setFallDistance(0);

            if (invoker.getMotionY() < -0.15D)
            {
                invoker.setMotionY(-0.15D);
            }
        }
    }
}
