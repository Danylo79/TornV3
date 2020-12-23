package dev.dankom.torn.util;

import dev.dankom.torn.theme.Theme;
import dev.dankom.torn.util.esp.EntityESP;
import dev.dankom.torn.util.esp.Esp;
import dev.dankom.torn.util.esp.EspMode;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;

public class RenderUtil {

    private Entity entity;
    private AxisAlignedBB axisAlignedBB;
    private double x;
    private double y;
    private double z;
    private GL11Color color;

    private EspMode mode;

    public void renderESP(Entity entity, double x, double y, double z, EspMode mode) {
        this.color = new GL11Color(Theme.getColor().getRed(), Theme.getColor().getGreen(), Theme.getColor().getBlue(), Theme.getColor().getAlpha());
        this.entity = entity;
        EntityESP esp = new EntityESP(entity, color, x, y, z, mode);
        try {
            esp.setup3DLightlessModel();
            esp.setLineWidth(1.0F);
            esp.draw();
        } finally {
            esp.shutdown3DLightlessModel();
        }
    }

    public void renderESP(AxisAlignedBB axisAlignedBB, double x, double y, double z, EspMode mode) {
        this.color = new GL11Color(Theme.getColor().getRed(), Theme.getColor().getGreen(), Theme.getColor().getBlue(), Theme.getColor().getAlpha());
        this.axisAlignedBB = axisAlignedBB;
        Esp esp = new Esp(axisAlignedBB, color, x, y, z, mode);
        try {
            esp.setup3DLightlessModel();
            esp.setLineWidth(1.0F);
            esp.draw();
        } finally {
            esp.shutdown3DLightlessModel();
        }
    }
}
