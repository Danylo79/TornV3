package dev.dankom.torn.module.modules.gui;

import dev.dankom.torn.Torn;
import dev.dankom.torn.gui.clickgui.ClickGui;
import dev.dankom.torn.settings.Setting;
import dev.dankom.torn.module.base.Category;
import dev.dankom.torn.module.base.Module;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

import java.awt.*;

public class TextGUI extends Module {
    public TextGUI() {
        super("TextGUI", "Shows your enabled modules", Category.GUI, -1, new Color(255, 0, 0), true, false);
        addSetting(new Setting("Use Module Color", this, false));
    }

    @Override
    public void onRender(RenderGameOverlayEvent e) {
        if (!isToggled()) {
            return;
        }
        if (mc.currentScreen == null && e.type.equals(RenderGameOverlayEvent.ElementType.CROSSHAIRS)) {
            ScaledResolution sr = new ScaledResolution(mc);
            int y = 2;
            for (Module m : Torn.getModuleManager().getEnabledModules()) {
                fontRenderer().drawString(m.getEnabledModName(), sr.getScaledWidth() - fontRenderer().getStringWidth(m.getEnabledModName()) - 1, y, (getSetting("Use Module Color").getValBoolean() ? m.getColor().getRGB() : ClickGui.getColor()));
                y += fontRenderer().FONT_HEIGHT + 2;
            }
        }
    }
}
