package dev.dankom.torn.module.modules.combat;

import dev.dankom.torn.event.EventTarget;
import dev.dankom.torn.event.EventType;
import dev.dankom.torn.event.events.PacketEvent;
import dev.dankom.torn.module.base.Category;
import dev.dankom.torn.module.base.Module;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S12PacketEntityVelocity;

import java.awt.*;

public class AntiKnockback extends Module {
    public AntiKnockback() {
        super("AntiKnockback", "Prevent you from taking kb!", Category.COMBAT, -1, new Color(106, 255, 0), true, true);
    }

    @EventTarget
    public void onPacketReceive(PacketEvent e) {
        if (e.getType() != EventType.RECEIVE) return;
        Packet eventPacket = e.getPacket();
        if(eventPacket instanceof S12PacketEntityVelocity){
            e.setCancelled(true);
        }
    }
}
