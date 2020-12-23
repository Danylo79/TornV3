package dev.dankom.torn.alt;

import com.mojang.authlib.Agent;
import com.mojang.authlib.AuthenticationService;
import com.mojang.authlib.UserAuthentication;
import com.mojang.authlib.UserType;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import net.minecraft.client.Minecraft;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AltManager {
    private UserAuthentication auth;
    private List<Alt> alts = new ArrayList<Alt>();
    private String session;

    public AltManager() {
        openAuthenticationService();
    }

    public void openAuthenticationService() {
        UUID uuid = UUID.randomUUID();
        AuthenticationService authService = new YggdrasilAuthenticationService(Minecraft.getMinecraft().getProxy(), uuid.toString());
        this.auth = authService.createUserAuthentication(Agent.MINECRAFT);
        authService.createMinecraftSessionService();
    }

    public void closeAuthenticationService() {
        this.auth = null;
    }

    public void addAlt(Alt alt) {
        if (!alts.contains(alt)) {
            alts.add(alt);
        }
    }

    public void removeAlt(String username) {
        for (Alt alt : alts) {
            if (alt.getUsername().equalsIgnoreCase(username)) {
                alts.remove(alt);
                return;
            }
        }
    }

    public Alt getAlt(String username) {
        for (Alt a : alts) {
            if (a.getUsername().equalsIgnoreCase(username)) {
                return a;
            }
        }
        return null;
    }

    public void login(String username, String password) {
        try {
            session = "Logging into " + username;
            auth.setUsername(username);
            auth.setPassword(password);
        } catch (Exception e) {
            session = "Failed to log into " + username;
        }
    }

    public void login(Alt alt) {
        login(alt.getUsername(), alt.getPassword());
    }

    public void login(String username) {
        login(getAlt(username));
    }

    public List<Alt> getAlts() {
        return alts;
    }

    public String getSession() {
        return session;
    }

    public Alt createAlt(String username, String password) {
        Alt alt = new Alt(username, password);
        addAlt(alt);
        return alt;
    }

    public Alt createAlt(String username) {
        Alt alt = new Alt(username);
        addAlt(alt);
        return alt;
    }
}
