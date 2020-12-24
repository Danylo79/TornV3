package dev.dankom.torn.module.modules.misc;

import dev.dankom.torn.module.base.Category;
import dev.dankom.torn.module.base.Module;

import java.awt.*;

public class Liquids extends Module {
    public Liquids() {
        super("Liquids", "Break liquids?", Category.MISC, -1, new Color(0, 136, 255), true, true);
    }
}
