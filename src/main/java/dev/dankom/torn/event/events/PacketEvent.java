package dev.dankom.torn.event.events;

import dev.dankom.torn.event.EventBase;
import dev.dankom.torn.event.EventType;
import net.minecraft.network.Packet;

public class PacketEvent extends EventBase {
    private Packet packet;

    public PacketEvent(EventType type, Packet packet) {
        super(type);
        this.packet = packet;
    }

    public Packet getPacket() {
        return packet;
    }

    public void setPacket(Packet packet) {
        this.packet = packet;
    }
}
