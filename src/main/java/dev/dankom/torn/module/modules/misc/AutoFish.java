package dev.dankom.torn.module.modules.misc;

import dev.dankom.torn.event.EventTarget;
import dev.dankom.torn.event.EventType;
import dev.dankom.torn.event.events.PacketEvent;
import dev.dankom.torn.module.base.Category;
import dev.dankom.torn.module.base.Module;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S12PacketEntityVelocity;

import java.awt.*;

public class AutoFish extends Module {
    public AutoFish() {
        super("AutoFish", "Automatically ", Category.MISC, -1, new Color(0, 255, 167), true, true);
    }

    @EventTarget
    public void onPacketReceive(PacketEvent event) {
        if (event.getType() != EventType.RECEIVE) return;
        Packet packet = event.getPacket();
        if(packet instanceof S12PacketEntityVelocity){
            S12PacketEntityVelocity p = (S12PacketEntityVelocity) packet;

            Entity e = invoker.getEntityById(invoker.getPacketVelocityEntityId(p));

            if(e instanceof EntityFishHook){
                int x = invoker.getXMovePacketVel(p);
                int y = invoker.getYMovePacketVel(p);
                int z = invoker.getZMovePacketVel(p);

                if(x == 0 && y != 0 && z == 0){
                    new Thread(){
                        public void run(){
                            try{
                                Thread.sleep(40);
                                invoker.setKeybind(invoker.getWrapper().getGameSettings().keyBindUseItem.getKeyCode(), true);
                                Thread.sleep(700);
                                invoker.setKeybind(invoker.getWrapper().getGameSettings().keyBindUseItem.getKeyCode(), true);
                            }catch(InterruptedException e){
                                e.printStackTrace();
                            }
                        }
                    }.start();
                }
            }
        }
    }
}
