package dev.dankom.torn.mixin.interfaces;

import net.minecraft.util.Session;

public interface IMixinMinecraft {

    Session getSession();

    void setSession(Session session);

}
