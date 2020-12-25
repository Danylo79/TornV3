package dev.dankom.torn.module.modules.player;

import dev.dankom.torn.Torn;
import dev.dankom.torn.event.EventTarget;
import dev.dankom.torn.event.events.UpdateEvent;
import dev.dankom.torn.gui.notification.Notification;
import dev.dankom.torn.gui.notification.NotificationManager;
import dev.dankom.torn.gui.notification.NotificationType;
import dev.dankom.torn.module.base.Category;
import dev.dankom.torn.module.base.Module;
import dev.dankom.torn.util.Timer;

import java.awt.*;

public class AntiAFK extends Module {

    private Timer timer = new Timer();

    public AntiAFK() {
        super("AntiAFK", "Moves you when you start being afk", Category.PLAYER, -1, new Color(255, 0, 186), true, true);
    }

    @EventTarget
    public void onUpdate(UpdateEvent event){
        if(timer.hasTimePassed((long) 10*1000) && Torn.getWrapper().getPlayer() != null && invoker.isOnGround()){
            timer.reset();
            invoker.jump();
            NotificationManager.show(new Notification(NotificationType.INFO, "AFK!", "You are now afk", 5));
        }
    }
}
