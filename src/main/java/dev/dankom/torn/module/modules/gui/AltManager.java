package dev.dankom.torn.module.modules.gui;

import dev.dankom.torn.gui.alt.GuiAltLogin;
import dev.dankom.torn.module.base.Category;
import dev.dankom.torn.module.base.Module;

import java.awt.*;

public class AltManager extends Module {
    public AltManager() {
        super("Alt Manager", "Opens the AltManager", Category.GUI, -1, new Color(0, 255, 128), true, false);
    }

    @Override
    public void onEnable() {
        super.onEnable();

        mc.displayGuiScreen(new GuiAltLogin());
        this.setToggled(false);
    }
}
