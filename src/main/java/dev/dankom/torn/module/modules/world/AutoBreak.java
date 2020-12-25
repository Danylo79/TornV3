package dev.dankom.torn.module.modules.world;

import dev.dankom.torn.Torn;
import dev.dankom.torn.event.EventTarget;
import dev.dankom.torn.event.events.UpdateEvent;
import dev.dankom.torn.module.base.Category;
import dev.dankom.torn.module.base.Module;

import java.awt.*;

public class AutoBreak extends Module {
    public AutoBreak() {
        super("AutoBreak", "Automatically breaks the block you are looking at", Category.WORLD, -1, new Color(27, 102, 102), true, true);
    }

    @EventTarget
    public void onUpdate(UpdateEvent e) {
        if (mc.objectMouseOver != null || mc.objectMouseOver.getBlockPos() != null || mc.theWorld != null && !isToggled()) {
            invoker.setKeybind(Torn.getWrapper().getGameSettings().keyBindAttack.getKeyCode(), true);
        }
    }

    @Override
    public void onDisable() {
        invoker.setKeybind(Torn.getWrapper().getGameSettings().keyBindAttack.getKeyCode(), false);
    }
}
