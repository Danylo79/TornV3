package dev.dankom.torn.util;

import java.awt.*;

public class ColorUtil {
    public static int rainbow(int delay) {
        double rainbowState = Math.ceil((System.currentTimeMillis() + delay) / 20.0);
        rainbowState %= 360;
        return Color.getHSBColor((float) (rainbowState / 360.0f), 0.8f, 0.7f).getRGB();
    }

    public static String translate(String s) {
        String out = s;
        for (ColorCode cc : ColorCode.values()) {
            out = cc.translate(out);
        }
        return out;
    }

    enum ColorCode {

        DARK_RED('4', "\u00A74"),
        RED('c', "\u00A7c"),
        GOLD('6', "\u00A76"),
        YELLOW('e', "\u00A7e"),
        DARK_GREEN('2', "\u00A72"),
        GREEN('a', "\u00A7a"),
        AQUA('b', "\u00A7b"),
        DARK_AQUA('3', "\u00A73"),
        DARK_BLUE('1', "\u00A71"),
        BLUE('9', "\u00A79"),
        LIGHT_PURPLE('d', "\u00A7d"),
        DARK_PURPLE('5', "\u00A75"),
        WHITE('f', "\u00A7f"),
        GRAY('7', "\u00A77"),
        DARK_GRAY('8', "\u00A78"),
        BLACK('0', "\u00A70"),
        RESET('r', "\u00A7r"),
        BOLD('l', "\u00A7l"),
        ITALIC('o', "\u00A7o"),
        OBFUSCATED('k', "\u00A7k"),
        ;

        private final char code;
        private final String color;

        ColorCode(char code, String color) {
            this.code = code;
            this.color = color;
        }

        public String translate(String s) {
            return s.replaceAll("&" + code, color);
        }
    }
}
