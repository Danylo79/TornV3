package dev.dankom.torn.module.base;

public enum Category {
    COMBAT(false), RENDER(false), MISC(false), MOVEMENT(false), PLAYER(false), WORLD(false), GUI(false);

    private boolean hide;

    Category(boolean hide) {
        this.hide = hide;
    }

    public boolean isHide() {
        return hide;
    }

    public void setHide(boolean hide) {
        this.hide = hide;
    }
}
