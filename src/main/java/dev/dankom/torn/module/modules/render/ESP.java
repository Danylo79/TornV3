package dev.dankom.torn.module.modules.render;

import dev.dankom.torn.event.EventTarget;
import dev.dankom.torn.event.events.EntityRenderEvent;
import dev.dankom.torn.module.base.Category;
import dev.dankom.torn.module.base.Module;
import dev.dankom.torn.settings.Setting;
import dev.dankom.torn.util.RenderUtil;
import dev.dankom.torn.util.esp.EspMode;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntitySquid;

import java.awt.*;
import java.util.ArrayList;

public class ESP extends Module {

    private RenderUtil renderUtils = new RenderUtil();

    public ESP() {
        super("ESP", "Draws a box around items and entities", Category.RENDER, -1, new Color(0x491010), true, true);
        ArrayList<String> options = new ArrayList();
        options.add("Solid");
        options.add("Crossed");
        addSetting(new Setting("Type", this, "Solid", options));
        addSetting(new Setting("Items", this, false));
        addSetting(new Setting("Mobs", this, false));
    }

    @EventTarget
    public void onEntityRender(EntityRenderEvent e) {
        if (getSetting("Items").getValBoolean() && e.getEntity() instanceof EntityItem) {
            renderUtils.renderESP(e.getEntity(), e.getX(), e.getY(), e.getZ(), getEspMode());
        }
        if (getSetting("Mobs").getValBoolean() && (e.getEntity() instanceof EntityMob || e.getEntity() instanceof EntitySquid || e.getEntity() instanceof EntitySlime || e.getEntity() instanceof EntityBat || e.getEntity() instanceof EntityIronGolem || e.getEntity() instanceof EntityAnimal)) {
            renderUtils.renderESP(e.getEntity(), e.getX(), e.getY(), e.getZ(), getEspMode());
        }
    }

    public EspMode getEspMode() {
        if (getSetting("Type").getValString().equalsIgnoreCase("Solid")) {
            return EspMode.SOLID;
        } else if (getSetting("Type").getValString().equalsIgnoreCase("Crossed")) {
            return EspMode.CROSSED;
        }
        return null;
    }
}
