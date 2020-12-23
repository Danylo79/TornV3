package dev.dankom.torn.listeners;

import dev.dankom.torn.Torn;
import dev.dankom.torn.event.EventTarget;
import dev.dankom.torn.event.events.KeyEvent;
import dev.dankom.torn.module.base.Module;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

public class KeyListener {
    @EventTarget
    public void onKey(KeyEvent e) {
        if (Minecraft.getMinecraft().theWorld == null || Minecraft.getMinecraft().thePlayer == null)
            return;
        try {
            if (Keyboard.isCreated()) {
                if (Keyboard.getEventKeyState()) {
                    int keyCode = Keyboard.getEventKey();
                    if (keyCode <= 0)
                        return;
                    for (Module m : Torn.getModuleManager().getModules()) {
                        if (m.getKey() == keyCode && keyCode > 0) {
                            m.toggle();
                        }
                    }
                }
            }
        } catch (Exception q) { q.printStackTrace(); }
    }
}
