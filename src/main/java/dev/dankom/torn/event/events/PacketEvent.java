package dev.dankom.torn.event.events;

import dev.dankom.torn.event.Cancellable;
import dev.dankom.torn.event.EventType;
import net.minecraft.network.Packet;

public class PacketEvent extends Cancellable {
    private final EventType type;
    private final Packet packetIn;

    public PacketEvent(EventType type, Packet packetIn) {
        this.type = type;
        this.packetIn = packetIn;
    }

    public EventType getType() {
        return type;
    }

    public Packet getPacket() {
        return packetIn;
    }
}
