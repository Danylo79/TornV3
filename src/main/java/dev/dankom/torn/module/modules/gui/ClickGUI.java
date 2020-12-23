package dev.dankom.torn.module.modules.gui;

import dev.dankom.torn.Torn;
import dev.dankom.torn.settings.Setting;
import dev.dankom.torn.module.base.Category;
import dev.dankom.torn.module.base.Module;
import dev.dankom.torn.theme.Theme;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ClickGUI extends Module {
    public ClickGUI() {
        super("ClickGUI", "Opens the ClickGUI", Category.GUI, Keyboard.KEY_RSHIFT, new Color(249, 0, 255), false, false);
        addSetting(new Setting("R", this, 100.0, 0.0, 100.0, true));
        addSetting(new Setting("G", this, 50.0, 0.0, 100.0, true));
        addSetting(new Setting("B", this, 50.0, 0.0, 100.0, true));
        addSetting(new Setting("A", this, 100.0, 0.0, 100.0, true));
        addSetting(new Setting("Chroma", this, false));
        addSetting(new Setting("Chroma Speed", this, 10.0, 10.0, 1000.0, true));
        ArrayList<String> options = new ArrayList<>();
        options.add("Large");
        options.add("Small");
        addSetting(new Setting("Watermark Type", this, "Large", options));
        addSetting(new Setting("Show Tooltips", this, true));
    }

    @Override
    public void onEnable() {
        super.onEnable();
        mc.displayGuiScreen(Torn.getClickGui());
        this.setToggled(false);
    }
}
