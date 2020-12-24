package dev.dankom.torn.event.events;

import dev.dankom.torn.event.Event;
import net.minecraft.entity.Entity;

public class EntityRenderEvent extends Event {
    private Entity entity;
    private double x;
    private double y;
    private double z;

    public EntityRenderEvent(Entity entity, double x, double y, double z) {
        this.entity = entity;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Entity getEntity() {
        return entity;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }
}
