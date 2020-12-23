package dev.dankom.torn.module.modules.combat;

import dev.dankom.torn.Torn;
import dev.dankom.torn.settings.Setting;
import dev.dankom.torn.module.base.Category;
import dev.dankom.torn.module.base.Module;
import dev.dankom.torn.util.ColorUtil;
import dev.dankom.torn.util.StringUtil;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.awt.*;

public class Velocity extends Module {
    public Velocity() {
        super("Velocity", "Changes the amount of knockback you take", Category.COMBAT, -1, new Color(255, 119, 0), true, true);
        addSetting(new Setting("Horizontal", this, 90, 1, 100, true));
        addSetting(new Setting("Vertical", this, 90, 1, 100, true));
    }

    @SubscribeEvent
    public void onLivingUpdate(LivingEvent.LivingUpdateEvent e) {
        if (Torn.getWrapper().getPlayer() == null) { return; }
        float horizontal = (float) getSetting("Horizontal").getValDouble();
        float vertical = (float) getSetting("Vertical").getValDouble();

        if (Torn.getWrapper().getPlayer().hurtTime == Torn.getWrapper().getPlayer().maxHurtTime && Torn.getWrapper().getPlayer().maxHurtTime > 0) {
            mc.thePlayer.motionX = horizontal / 100;
            mc.thePlayer.motionY = vertical / 100;
            mc.thePlayer.motionZ = horizontal / 100;
        }
        setEnabledModName(ColorUtil.translate("Velocity " + StringUtil.wrapWithSquareBracket("X:" + horizontal + ", Y:" + vertical)));
    }
}
