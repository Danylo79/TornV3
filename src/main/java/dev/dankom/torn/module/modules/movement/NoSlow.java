package dev.dankom.torn.module.modules.movement;

import dev.dankom.torn.module.base.Category;
import dev.dankom.torn.module.base.Module;

import java.awt.*;

public class NoSlow extends Module {
    public NoSlow() {
        super("NoSlow", "Prevent slowing down while eating or walking on soul sand", Category.MOVEMENT, -1, new Color(255, 0, 133), true, true);
    }
}
