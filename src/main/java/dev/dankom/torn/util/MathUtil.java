package dev.dankom.torn.util;

public class MathUtil {
    public static double clamp(double min, double max, double value) {
        double out = value;
        if (value < min) {
            out = (out + (min - out));
        }
        if (value > max) {
            out = (out - (max - out));
        }
        return out;
    }
}
