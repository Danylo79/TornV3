package dev.dankom.torn.module.modules.combat;

import dev.dankom.torn.Torn;
import dev.dankom.torn.event.EventTarget;
import dev.dankom.torn.event.events.UpdateEvent;
import dev.dankom.torn.settings.Setting;
import dev.dankom.torn.module.base.Category;
import dev.dankom.torn.module.base.Module;
import dev.dankom.torn.util.EntityUtil;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.awt.*;

public class AimAssist extends Module {

    private Entity target;
    private EntityUtil entityUtils = new EntityUtil();

    public AimAssist() {
        super("Aim Assist", "Aims at the nearest entity", Category.COMBAT, -1, new Color(119, 0, 255), true, true);
        addSetting(new Setting("Invisibles", this, false));
        addSetting(new Setting("Players", this, true));
        addSetting(new Setting("Animals", this, false));
        addSetting(new Setting("Monsters", this, false));
        addSetting(new Setting("Range", this, 10.0, 1.0, 50.0, false));
    }

    @EventTarget
    public void onUpdate(UpdateEvent event) {
        if (Torn.getWrapper().getWorld() == null || Torn.getWrapper().getWorld().loadedEntityList == null) { return; }
        target = entityUtils.getClosestEntity(Torn.getWrapper().getPlayer(), getSetting("Players").getValBoolean(), getSetting("Monsters").getValBoolean(), getSetting("Animals").getValBoolean(), getSetting("Invisibles").getValBoolean(), false);
        try {
            if (target != null && entityUtils.isWithinRange((float) getSetting("Range").getValDouble(), target)) {
                entityUtils.faceEntity(target);
            }
        } catch (Exception exception) {
        }
    }
}
