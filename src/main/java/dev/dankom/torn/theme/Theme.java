package dev.dankom.torn.theme;

import dev.dankom.torn.Torn;
import dev.dankom.torn.util.ColorUtil;
import dev.dankom.torn.util.MathUtil;

import java.awt.*;

public class Theme {

    public static int getWidth() {
        return 65;
    }

    public static int getColorInt() {
        if (!Torn.getSettingsManager().getSetting(Torn.getModuleManager().getModule("ClickGUI"), "Chroma").getValBoolean()) {
            float r = (float) MathUtil.clamp(0.0, 254.0, Torn.getSettingsManager().getSetting(Torn.getModuleManager().getModule("ClickGUI"), "R").getValDouble());
            float g = (float) MathUtil.clamp(0.0, 254.0, Torn.getSettingsManager().getSetting(Torn.getModuleManager().getModule("ClickGUI"), "G").getValDouble());
            float b = (float) MathUtil.clamp(0.0, 254.0, Torn.getSettingsManager().getSetting(Torn.getModuleManager().getModule("ClickGUI"), "B").getValDouble());
            float a = (float) MathUtil.clamp(0.0, 254.0, Torn.getSettingsManager().getSetting(Torn.getModuleManager().getModule("ClickGUI"), "A").getValDouble());
            return new Color(
                    r / 100,
                    g / 100,
                    b / 100,
                    a / 100
            ).getRGB();
        } else {
            return ColorUtil.rainbow((int) Torn.getSettingsManager().getSetting(Torn.getModuleManager().getModule("ClickGUI"), "Chroma Speed").getValDouble());
        }
    }

    public static Color getColor() {
        return new Color(getColorInt());
    }
}
