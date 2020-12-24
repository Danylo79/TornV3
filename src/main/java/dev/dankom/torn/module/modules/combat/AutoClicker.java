package dev.dankom.torn.module.modules.combat;

import dev.dankom.torn.event.EventTarget;
import dev.dankom.torn.event.events.UpdateEvent;
import dev.dankom.torn.settings.Setting;
import dev.dankom.torn.module.base.Category;
import dev.dankom.torn.module.base.Module;
import dev.dankom.torn.util.ColorUtil;
import dev.dankom.torn.util.StringUtil;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Mouse;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class AutoClicker extends Module {

    private long lastClick;
    private long hold;

    private double speed;
    private double holdLength;
    private double min;
    private double max;

    public AutoClicker() {
        super("AutoClicker", "Clicks for you", Category.COMBAT, -1, new Color(179, 32, 0), true, true);
        ArrayList<String> args = new ArrayList<>();
        args.add("Left");
        args.add("Right");
        addSetting(new Setting("Mouse Button", this, "Left", args));
        addSetting(new Setting("Min CPS", this, 8, 8, 50, true));
        addSetting(new Setting("Max CPS", this, 12, 12 , 50, true));
    }

    @Override
    public void onEnable() {
        super.onEnable();
        updateVals();
    }

    @EventTarget
    public void onUpdate(UpdateEvent event) {
        tick();
    }

    public void tick() {
        int key;
        String mouse_button = getSetting("Mouse Button").getValString();
        if (mouse_button.equalsIgnoreCase("Left")) {
            key = mc.gameSettings.keyBindAttack.getKeyCode();
        } else {
            key = mc.gameSettings.keyBindPickBlock.getKeyCode();
        }
        if ((Mouse.isButtonDown(0) && mouse_button.equalsIgnoreCase("Left")) || (Mouse.isButtonDown(1) && mouse_button.equalsIgnoreCase("Right"))) {
            if (System.currentTimeMillis() - lastClick > (speed * 1000)) {
                lastClick = System.currentTimeMillis();
                if (hold < lastClick) {
                    hold = lastClick;
                }
                KeyBinding.setKeyBindState(key, true);
                KeyBinding.onTick(key);
                updateVals();
            } else if (System.currentTimeMillis() - hold > holdLength * 1000) {
                KeyBinding.setKeyBindState(key, false);
                updateVals();
            }
        }
    }

    private void updateVals() {
        min = getSetting("Min CPS").getValDouble();
        max = getSetting("Max CPS").getValDouble();

        if (min >= max) {
            max = min + 1;
        }
        speed = 1.0 / ThreadLocalRandom.current().nextDouble(min - 0.2, max);
        holdLength = speed / ThreadLocalRandom.current().nextDouble(min, max);
    }
}
