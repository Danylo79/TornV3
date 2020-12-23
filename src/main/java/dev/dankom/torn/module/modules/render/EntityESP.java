package dev.dankom.torn.module.modules.render;

import dev.dankom.torn.module.base.Category;
import dev.dankom.torn.module.base.Module;

import java.awt.*;

public class EntityESP extends Module {
    public EntityESP() {
        super("EntityESP", "Draws a box around entities", Category.RENDER, -1, new Color(-1), true, true);
    }
}
