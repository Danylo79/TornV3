package dev.dankom.torn.module.modules.movement;

import dev.dankom.torn.event.events.UpdateEvent;
import dev.dankom.torn.module.base.Category;
import dev.dankom.torn.module.base.Module;

import java.awt.*;

public class Sprint extends Module {
    public Sprint() {
        super("Sprint", "Automatically sprints for you", Category.MOVEMENT, -1, new Color(0, 166, 255), true, true);
    }

    @Override
    public void onUpdate(UpdateEvent e) {
        invoker.setSprint(true);
    }

    @Override
    public void onDisable() {
        super.onDisable();
        invoker.setSprint(false);
    }
}
