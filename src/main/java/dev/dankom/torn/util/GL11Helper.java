package dev.dankom.torn.util;

import dev.dankom.torn.Torn;
import org.lwjgl.opengl.GL11;

public class GL11Helper {
    public void setup3DLightlessModel() {
//        Torn.getWrapper().getInvoker().setEntityLight(false);
        GL11.glEnable(3042);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
    }

    public void shutdown3DLightlessModel() {
        GL11.glDisable(3042);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
//        MeteorClient.getInstance().getInvoker().setEntityLight(true);
    }

    public void setLineWidth(float width) {
        GL11.glLineWidth(width);
    }
}
