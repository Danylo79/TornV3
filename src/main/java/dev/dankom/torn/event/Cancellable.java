package dev.dankom.torn.event;

public class Cancellable extends Event {
    private boolean isCancelled = false;

    public boolean isCancelled(){
        return isCancelled;
    }

    public void setCancelled(boolean flag){
        isCancelled = flag;
    }
}
