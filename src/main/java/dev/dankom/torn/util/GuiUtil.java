package dev.dankom.torn.util;

import net.minecraft.client.gui.Gui;

public class GuiUtil {
    /**
     *
     * @param x x coordinate of the rect
     * @param y y coordinate of the rect
     * @param w width of the rect
     * @param h height of the rect
     * @param color - color of the rect
     * Draws a hollow rect with the passed in parameters.
     */
    public static void drawHollowRect(int x, int y, int w, int h, int color) {
        drawHorizontalLine(x, x + w, y, color);
        drawHorizontalLine(x, x + w, y + h, color);

        drawVerticalLine(x, y + h, y, color);
        drawVerticalLine(x + w, y + h, y, color);
    }

    /**
     * Draw a 1 pixel wide horizontal line. Args: x1, x2, y, color
     */
    private static  void drawHorizontalLine(int startX, int endX, int y, int color)
    {
        if (endX < startX)
        {
            int i = startX;
            startX = endX;
            endX = i;
        }

        Gui.drawRect(startX, y, endX + 1, y + 1, color);
    }

    /**
     * Draw a 1 pixel wide vertical line. Args : x, y1, y2, color
     */
    private static  void drawVerticalLine(int x, int startY, int endY, int color)
    {
        if (endY < startY)
        {
            int i = startY;
            startY = endY;
            endY = i;
        }

        Gui.drawRect(x, startY + 1, x + 1, endY, color);
    }
}
