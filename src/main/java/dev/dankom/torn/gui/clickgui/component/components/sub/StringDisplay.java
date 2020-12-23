package dev.dankom.torn.gui.clickgui.component.components.sub;

import dev.dankom.torn.gui.clickgui.component.Component;
import dev.dankom.torn.gui.clickgui.component.components.Button;
import dev.dankom.torn.module.base.Module;
import dev.dankom.torn.settings.Setting;

public class StringDisplay extends Component {
    private boolean hovered;
    private Button parent;
    private Setting set;
    private int offset;
    private int x;
    private int y;
    private Module mod;

    private int modeIndex;

    public StringDisplay(Setting set, Button button, Module mod, int offset) {
        this.set = set;
        this.parent = button;
        this.mod = mod;
        this.x = button.parent.getX() + button.parent.getWidth();
        this.y = button.parent.getY() + button.offset;
        this.offset = offset;
        this.modeIndex = 0;
    }
}
