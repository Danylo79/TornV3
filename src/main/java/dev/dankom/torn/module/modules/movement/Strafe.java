package dev.dankom.torn.module.modules.movement;

import dev.dankom.torn.Torn;
import dev.dankom.torn.module.base.Category;
import dev.dankom.torn.module.base.Module;
import dev.dankom.torn.settings.Setting;
import dev.dankom.torn.util.wrapper.Wrapper;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class Strafe extends Module {

    Wrapper wrapper = Torn.getWrapper();

    public Strafe() {
        super("Strafe", "Automatically strafes for you", Category.MOVEMENT, -1, new Color(215, 129, 0), true, true);
        addSetting(new Setting("Min Movement", this, 0.1, 0.1, 1, false));
        addSetting(new Setting("Max Movement", this, 0.1, 0.1, 1, false));
    }

    @Override
    public void onTick() {
        double min = getSetting("Min Movement").getValDouble();
        double max = getSetting("Max Movement").getValDouble();

        if (min == max) {
            max += 1;
        }

        double moveRight = ThreadLocalRandom.current().nextDouble(min, max);
        double moveLeft = ThreadLocalRandom.current().nextDouble(min, max);

        move(moveRight, moveLeft);
    }

    public void move(double right, double left) {

    }
}
