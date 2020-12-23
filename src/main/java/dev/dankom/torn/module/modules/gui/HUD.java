package dev.dankom.torn.module.modules.gui;

import dev.dankom.torn.Torn;
import dev.dankom.torn.gui.clickgui.ClickGui;
import dev.dankom.torn.module.base.Category;
import dev.dankom.torn.module.base.Module;
import dev.dankom.torn.settings.Setting;
import dev.dankom.torn.theme.Theme;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HUD extends Module {

    private List<Integer> fps = new ArrayList<>();

    public HUD() {
        super("HUD", "Shows the Heads Up Display", Category.GUI, -1, new Color(0, 255, 15), true, false);
        addSetting(new Setting("FPS", this, false));
        addSetting(new Setting("BPS", this, false));
        addSetting(new Setting("Time", this, false));
    }

    @Override
    public void onRender(RenderGameOverlayEvent e) {
        if (!isToggled() || e.type.equals(RenderGameOverlayEvent.ElementType.CROSSHAIRS)) return;

        FontRenderer fontRenderer = mc.fontRendererObj;
        ScaledResolution res = new ScaledResolution(Minecraft.getMinecraft());

        boolean fps = getSetting("FPS").getValBoolean();
        boolean bps = getSetting("BPS").getValBoolean();
        boolean time = getSetting("Time").getValBoolean();
        if (fps) {
            fontRenderer.drawString("FPS: " + Minecraft.getDebugFPS(), 4, res.getScaledHeight() - fontRenderer.FONT_HEIGHT - 2, ClickGui.getColor(), true);
        }
        if (bps) {
            double currSpeed = Math.sqrt(mc.thePlayer.motionX * mc.thePlayer.motionX + mc.thePlayer.motionZ * mc.thePlayer.motionZ);
            fontRenderer.drawString(String.format("BPS: %.2f", currSpeed), (fps ? (Minecraft.getDebugFPS() > 100 ? 50 : 45) : 4), res.getScaledHeight() - fontRenderer.FONT_HEIGHT - 2, ClickGui.getColor(), true);
        }
        if (time) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            String am_pm = (cal.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM");
            String currTime = cal.getTime().getHours() + ":" + cal.getTime().getMinutes() + " " + am_pm;
            fontRenderer.drawString(currTime, res.getScaledWidth() - fontRenderer.getStringWidth(currTime) - 4, res.getScaledHeight() - fontRenderer.FONT_HEIGHT - 2, ClickGui.getColor(), true);
        }
    }
}
