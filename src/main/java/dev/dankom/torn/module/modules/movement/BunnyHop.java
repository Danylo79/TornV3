package dev.dankom.torn.module.modules.movement;

import dev.dankom.torn.event.EventTarget;
import dev.dankom.torn.event.events.UpdateEvent;
import dev.dankom.torn.module.base.Category;
import dev.dankom.torn.module.base.Module;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.awt.*;

public class BunnyHop extends Module {
    public BunnyHop() {
        super("B-Hop", "Jumps for you and slightly increases your speed", Category.MOVEMENT, -1, new Color(231, 201, 24), true, true);
    }

    public boolean isToJump() {
        if (Minecraft.getMinecraft().thePlayer != null && !Minecraft.getMinecraft().thePlayer.isInWater() && !Minecraft.getMinecraft().thePlayer.isOnLadder()) return true;
        return false;
    }

    @EventTarget
    public void onUpdate(UpdateEvent event) {
        if (isToJump() && Minecraft.getMinecraft().thePlayer.moveForward != 0 && (Minecraft.getMinecraft().thePlayer.posY % 1 == 0)) Minecraft.getMinecraft().thePlayer.jump();
    }
}
