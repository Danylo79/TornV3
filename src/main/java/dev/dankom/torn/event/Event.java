package dev.dankom.torn.event;

import dev.dankom.torn.Torn;
import dev.dankom.torn.util.ArrayHelper;

import java.lang.reflect.InvocationTargetException;

public abstract class Event {

    /**
     *
     * Main events you may need:
     *
     * Minecraft:
     * - EventKeyboard
     * - EventMiddleClick
     * - EventTick
     *
     * EntityPlayerSP:
     * - EventUpdate
     * - EventPreMotionUpdates
     * - EventPostMotionUpdates
     *
     * GuiIngame:
     * - EventRender2D
     *
     * EntityRenderer:
     * - EventRender3D
     *
     */

    private boolean cancelled;

    public enum State {
        PRE("PRE", 0),

        POST("POST", 1);

        private State(final String string, final int number) {

        }
    }

    public Event call() {

        this.cancelled = false;
        call(this);
        return this;
    }

    public boolean isCancelled() {

        return cancelled;
    }

    public void setCancelled(boolean cancelled) {

        this.cancelled = cancelled;
    }

    private static final void call(final Event event) {

        final ArrayHelper<Data> dataList = Torn.getEventManager().get(event.getClass());

        if (dataList != null) {
            for (final Data data : dataList) {

                try {
                    data.target.invoke(data.source, event);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
