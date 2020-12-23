package dev.dankom.torn.cape;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureManager;

public enum Cape {

    ;

    private final String name;
    private final String path;
    private static TextureManager textureManager = Minecraft.getMinecraft().getTextureManager();

    Cape(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public static TextureManager getTextureManager() {
        return textureManager;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }
}
