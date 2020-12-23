package dev.dankom.torn.module.modules.misc;

import dev.dankom.torn.module.base.Category;
import dev.dankom.torn.module.base.Module;
import net.minecraft.block.material.Material;

import java.awt.*;

public class Glide extends Module {

    public Glide() {
        super("Glide", "Makes you glide", Category.MISC, -1, new Color(0, 255, 173), true, true);
    }

    @Override
    public void onTick() {
        double oldY = mc.thePlayer.motionY;
        float oldJ = mc.thePlayer.jumpMovementFactor;
        if (isToggled()) {
            if ((mc.thePlayer.motionY < 0.0d) && (mc.thePlayer.isAirBorne) && (!mc.thePlayer.isInWater()) && (!mc.thePlayer.isOnLadder())) {
                if (!mc.thePlayer.isInsideOfMaterial(Material.lava)) {
                    mc.thePlayer.motionY = -.125d;
                    mc.thePlayer.jumpMovementFactor *= 1.12337f;
                }
            }
        } else {
            mc.thePlayer.motionY = oldY;
            mc.thePlayer.jumpMovementFactor = oldJ;
        }
    }
}
