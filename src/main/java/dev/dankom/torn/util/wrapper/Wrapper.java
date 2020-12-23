package dev.dankom.torn.util.wrapper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.settings.GameSettings;

public class Wrapper {

    private Invoker invoker;

    public Wrapper() {
        this.invoker = new Invoker(this);
    }

    public Minecraft getMinecraft() {
        return Minecraft.getMinecraft();
    }

    public EntityPlayerSP getPlayer() {
        return getMinecraft().thePlayer;
    }

    public WorldClient getWorld() {
        return getMinecraft().theWorld;
    }

    public PlayerControllerMP getPlayerController() {
        return getMinecraft().playerController;
    }

    public GameSettings getGameSettings() {
        return getMinecraft().gameSettings;
    }

    public Invoker getInvoker() {
        if (invoker == null) {
            invoker = new Invoker(this);
        }
        return invoker;
    }
}
