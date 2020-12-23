package dev.dankom.torn.event.events;

import dev.dankom.torn.event.EventBase;
import dev.dankom.torn.event.EventType;

public class UpdateEvent extends EventBase {
    public UpdateEvent(EventType eventType) {
        super(eventType);
    }
}
