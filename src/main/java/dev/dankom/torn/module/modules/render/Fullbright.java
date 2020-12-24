package dev.dankom.torn.module.modules.render;

import dev.dankom.torn.event.EventTarget;
import dev.dankom.torn.event.events.UpdateEvent;
import dev.dankom.torn.module.base.Category;
import dev.dankom.torn.module.base.Module;
import dev.dankom.torn.settings.Setting;

import java.awt.*;

public class Fullbright extends Module {

    private float oldBrightness;

    public Fullbright() {
        super("Fullbright", "Make your screen brighter", Category.RENDER, -1, new Color(0, 0, 0), true, true);
        addSetting(new Setting("Brightness", this, 10.0, 10.0, 100.0, true));
    }

    @Override
    public void onEnable() {
        super.onEnable();

        oldBrightness =  mc.gameSettings.gammaSetting;
    }

    @EventTarget
    public void onUpdate(UpdateEvent event) {
        mc.gameSettings.gammaSetting = (int) getSetting("Brightness").getValDouble();
    }

    @Override
    public void onDisable() {
        super.onDisable();

        mc.gameSettings.gammaSetting = oldBrightness;
    }
}
