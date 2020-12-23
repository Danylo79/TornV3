package dev.dankom.torn.event.events;

import dev.dankom.torn.event.Event;

public class KeyEvent extends Event {
    private int key;

    public KeyEvent(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }
}
