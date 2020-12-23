package dev.dankom.torn.module.modules.movement;

import dev.dankom.torn.event.events.UpdateEvent;
import dev.dankom.torn.module.base.Category;
import dev.dankom.torn.module.base.Module;
import dev.dankom.torn.settings.Setting;
import dev.dankom.torn.util.ColorUtil;
import dev.dankom.torn.util.StringUtil;
import net.minecraft.network.play.client.C03PacketPlayer;
import org.lwjgl.input.Keyboard;

import java.awt.*;
import java.util.ArrayList;

public class Fly extends Module {
    public Fly() {
        super("Fly", "Fly like a bird!", Category.MOVEMENT, Keyboard.KEY_K, new Color(193, 255, 0), true, true);
        ArrayList<String> options = new ArrayList<>();
        options.add("Vanilla");
        options.add("Hypixel");
        addSetting(new Setting("Fly Mode", this, "Hypixel", options));
    }

    @Override
    public void onUpdate(UpdateEvent e) {
        setEnabledModName(ColorUtil.translate("Fly " + StringUtil.wrapWithSquareBracket(getSetting("Fly Mode").getValString())));
        String mode = getSetting("Fly Mode").getValString();

        if(mode.equalsIgnoreCase("Hypixel")) {
            double y;
            double y1;
            mc.thePlayer.motionY = 0;
            if(mc.thePlayer.ticksExisted % 3 == 0) {
                y = mc.thePlayer.posY + 1.0E-10D;
                mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, y, mc.thePlayer.posZ, true));
            }
            y1 = mc.thePlayer.posY + 1.0E-10D;
            mc.thePlayer.setPosition(mc.thePlayer.posX, y1, mc.thePlayer.posZ);
        }

        if(mode.equalsIgnoreCase("Vanilla"))
            mc.thePlayer.capabilities.isFlying = true;
    }

    @Override
    public void onEnable() {
        String mode = getSetting("Fly Mode").getValString();
        super.onEnable();

        System.out.println(mode);
    }

    @Override
    public void onDisable() {
        String mode = getSetting("Fly Mode").getValString();
        super.onDisable();

        if(mode.equalsIgnoreCase("Vanilla"))
            mc.thePlayer.capabilities.isFlying = false;
    }


}
