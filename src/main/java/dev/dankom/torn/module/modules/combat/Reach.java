package dev.dankom.torn.module.modules.combat;

import dev.dankom.torn.module.base.Category;
import dev.dankom.torn.module.base.Module;
import dev.dankom.torn.settings.Setting;

import java.awt.*;

public class Reach extends Module {
    public Reach() {
        super("Reach", "Extends your reach distance", Category.COMBAT, -1, new Color(127, 92, 4), true, true);
        addSetting(new Setting("Distance", this, 3.0, 3.0, 10.0, false));
    }
}
