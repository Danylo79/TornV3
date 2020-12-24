package dev.dankom.torn.util.esp;

public enum EspMode {
    CROSSED, SOLID;

    public static EspMode get(String name) {
        for (EspMode em : values()) {
            if (em.name().toLowerCase().equalsIgnoreCase(name.toLowerCase())) {
                return em;
            }
        }
        return null;
    }
}
