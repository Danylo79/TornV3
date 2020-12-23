package dev.dankom.torn.util;

public class GL11Color {
    private double r;
    private double g;
    private double b;
    private double a;

    public GL11Color(double r, double g, double b, double a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    public double getRed() {
        return r;
    }

    public void setRed(double r) {
        this.r = r;
    }

    public double getGreen() {
        return g;
    }

    public void setGreen(double g) {
        this.g = g;
    }

    public double getBlue() {
        return b;
    }

    public void setBlue(double b) {
        this.b = b;
    }

    public double getAlpha() {
        return a;
    }

    public void setAlpha(double a) {
        this.a = a;
    }
}
