package dev.dankom.torn.alt;

public class Alt {
    private final String username;
    private final String password;
    private final boolean offline;

    public Alt(String username, String password) {
        this.username = username;
        this.password = password;
        this.offline = password.equals("");
    }

    public Alt(String username) {
        this.username = username;
        this.password = "";
        this.offline = true;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isOffline() {
        return offline;
    }
}
