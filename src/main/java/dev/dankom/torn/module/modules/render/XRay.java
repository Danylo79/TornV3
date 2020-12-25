package dev.dankom.torn.module.modules.render;

import dev.dankom.torn.module.base.Category;
import dev.dankom.torn.module.base.Module;
import dev.dankom.torn.settings.Setting;

import java.awt.*;

public class XRay extends Module {
    public XRay() {
        super("X-Ray", "See through blocks", Category.RENDER, -1, new Color(255, 252, 0), true, true);
        addSetting(new Setting("Ores", this, false));
        addSetting(new Setting("Chests", this, false));
        addSetting(new Setting("Liquids", this, false));
    }

    public boolean ores() {
        return getSetting("Ores").getValBoolean();
    }

    public boolean chests() {
        return getSetting("Ores").getValBoolean();
    }

    public boolean liquids() {
        return getSetting("Liquids").getValBoolean();
    }
}
