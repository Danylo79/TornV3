package dev.dankom.torn.event;

import net.minecraftforge.fml.common.eventhandler.Event;

public class EventBase extends Event {
    private final EventType eventType;

    public EventBase(EventType eventType) {
        this.eventType = eventType;
    }

    public EventType getEventType() {
        return eventType;
    }
}
