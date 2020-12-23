package dev.dankom.torn.module.modules.movement;

import dev.dankom.torn.event.EventType;
import dev.dankom.torn.event.events.MotionUpdateEvent;
import dev.dankom.torn.event.events.PacketEvent;
import dev.dankom.torn.gui.notification.Notification;
import dev.dankom.torn.gui.notification.NotificationManager;
import dev.dankom.torn.gui.notification.NotificationType;
import dev.dankom.torn.module.base.Category;
import dev.dankom.torn.module.base.Module;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.util.Vec3;

import java.awt.*;
import java.util.ArrayList;

public class SetbackDetector extends Module {
    private ArrayList<Vec3> lastLocations = new ArrayList<Vec3>();
    private ArrayList<Long> lastSetBacks = new ArrayList<Long>();

    public SetbackDetector() {
        super("Setback Detector", "Tells you when you lag back", Category.MOVEMENT, -1, new Color(74, 208, 204), true, true);
    }

    @Override
    public void onMove(MotionUpdateEvent e) {
        if (e.getEventType() != EventType.POST) return;

        ArrayList<Long> remove = new ArrayList<Long>();

        for (Long lastSetBack : lastSetBacks) {
            if (System.currentTimeMillis() - lastSetBack > 5000) {
                remove.add(lastSetBack);
            }
        }
        for (Long aLong : remove) {
            lastSetBacks.remove(aLong);
        }

//        System.out.println(lastSetBacks);

        lastLocations.add(new Vec3(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ));

        while (lastLocations.size() > 30) {
            lastLocations.remove(0);
        }
    }

    @Override
    public void onPacket(PacketEvent e) {
        if (e.getPacket() instanceof S08PacketPlayerPosLook) {
            S08PacketPlayerPosLook p = (S08PacketPlayerPosLook) e.getPacket();
            boolean setback = lastLocations.stream().anyMatch(loc -> p.getX() == loc.xCoord && p.getY() == loc.yCoord && p.getZ() == loc.zCoord);

            if (setback) {
                lastSetBacks.add(System.currentTimeMillis());
                if (lastSetBacks.size() < 3)
                    NotificationManager.show(new Notification(NotificationType.WARNING, getName(), "Flag detected", 1));
            }
        }
    }
}
