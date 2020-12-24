package dev.dankom.torn.module.modules.gui;

import dev.dankom.torn.event.EventTarget;
import dev.dankom.torn.event.events.Render2DEvent;
import dev.dankom.torn.event.events.RenderEvent;
import dev.dankom.torn.gui.notification.NotificationManager;
import dev.dankom.torn.settings.Setting;
import dev.dankom.torn.module.base.Category;
import dev.dankom.torn.module.base.Module;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

import java.awt.*;

public class Notifications extends Module {
    public Notifications() {
        super("Notification", "Toggles whether you see notifications", Category.GUI, -1, new Color(0, 0, 0), true, false);
        addSetting(new Setting("Use ClickGUI Color", this, false));
        addSetting(new Setting("Show Override", this, false));
    }

    @EventTarget
    public void onRender(RenderEvent event) {
        NotificationManager.render();
    }
}
