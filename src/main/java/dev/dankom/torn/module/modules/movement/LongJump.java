package dev.dankom.torn.module.modules.movement;

import dev.dankom.torn.event.EventTarget;
import dev.dankom.torn.event.events.MotionUpdateEvent;
import dev.dankom.torn.module.base.Category;
import dev.dankom.torn.module.base.Module;
import dev.dankom.torn.settings.Setting;
import net.minecraft.potion.Potion;

import java.awt.*;

public class LongJump extends Module {
    public LongJump() {
        super("LongJump", "Jump farther", Category.MOVEMENT, -1, new Color(255, 0, 188), true, true);
        addSetting(new Setting("Length", this, 1.261, 1.0, 10.0, false));
    }

    @EventTarget
    public void onPost(MotionUpdateEvent event) {
        double length = getSetting("Length").getValDouble();
        if((mc.gameSettings.keyBindForward.isKeyDown() || mc.gameSettings.keyBindLeft.isKeyDown() || mc.gameSettings.keyBindRight.isKeyDown() || mc.gameSettings.keyBindBack.isKeyDown()) && mc.gameSettings.keyBindJump.isKeyDown()) {
            float dir = mc.thePlayer.rotationYaw + ((mc.thePlayer.moveForward < 0) ? 180 : 0) + ((mc.thePlayer.moveStrafing > 0) ? (-90F * ((mc.thePlayer.moveForward < 0) ? -.5F : ((mc.thePlayer.moveForward > 0) ? .4F : 1F))) : 0);
            float xDir = (float)Math.cos((dir + 90F) * Math.PI / 180);
            float zDir = (float)Math.sin((dir + 90F) * Math.PI / 180);
            if(mc.thePlayer.isCollidedVertically && (mc.gameSettings.keyBindForward.isKeyDown() || mc.gameSettings.keyBindLeft.isKeyDown() || mc.gameSettings.keyBindRight.isKeyDown() || mc.gameSettings.keyBindBack.isKeyDown()) && mc.gameSettings.keyBindJump.isKeyDown()) {
                mc.thePlayer.motionX = xDir * .29F;
                mc.thePlayer.motionZ = zDir * .29F;
            }
            if(mc.thePlayer.motionY == .33319999363422365 && (mc.gameSettings.keyBindForward.isKeyDown() || mc.gameSettings.keyBindLeft.isKeyDown() || mc.gameSettings.keyBindRight.isKeyDown() || mc.gameSettings.keyBindBack.isKeyDown())) {
                if (mc.thePlayer.isPotionActive(Potion.moveSpeed)) {
                    mc.thePlayer.motionX = xDir * (length + 0.079);
                    mc.thePlayer.motionZ = zDir * (length + 0.079);
                } else {
                    mc.thePlayer.motionX = xDir * length;
                    mc.thePlayer.motionZ = zDir * length;
                }
            }
        }
    }
}
