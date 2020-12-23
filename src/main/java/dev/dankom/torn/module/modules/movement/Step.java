package dev.dankom.torn.module.modules.movement;

import dev.dankom.torn.Torn;
import dev.dankom.torn.module.base.Category;
import dev.dankom.torn.module.base.Module;
import dev.dankom.torn.settings.Setting;
import dev.dankom.torn.util.ColorUtil;
import dev.dankom.torn.util.StringUtil;

import java.awt.*;

public class Step extends Module {
    public Step() {
        super("Step", "Changes the amount of blocks you can step over without jumping", Category.MOVEMENT, -1, new Color(0, 43, 255), true, true);
        addSetting(new Setting("Height", this, 1.5, 0.5, 2, true));
    }

    @Override
    public void onEnable() {
        super.onEnable();

        setEnabledModName("Step " + ColorUtil.translate(StringUtil.wrapWithSquareBracket("Height: " + getSetting("Height").getValDouble())));
        invoker.setStepHeight((float) getSetting("Height").getValDouble());
    }

    @Override
    public void onDisable() {
        super.onDisable();

        invoker.setStepHeight(.5F);
    }
}
