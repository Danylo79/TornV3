package dev.dankom.torn.cape;

import com.mojang.authlib.GameProfile;

public class PlayerHook {
    private final GameProfile profile;

    public PlayerHook(GameProfile profile) {
        this.profile = profile;
    }

    public GameProfile getProfile() {
        return profile;
    }
}
