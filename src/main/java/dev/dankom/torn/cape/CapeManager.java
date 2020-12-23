package dev.dankom.torn.cape;

import com.mojang.authlib.GameProfile;

import java.util.UUID;

public class CapeManager {
    private final PlayerHook hook;

    public CapeManager(PlayerHook hook) {
        this.hook = hook;
    }

    public CapeManager(String name, UUID uuid) {
        this(new PlayerHook(new GameProfile(uuid, name)));
    }

    public PlayerHook getHook() {
        return hook;
    }

    public static CapeManager get(String name, UUID uuid) {
        return new CapeManager(name, uuid);
    }
}
