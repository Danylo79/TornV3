package dev.dankom.torn.mixin.mixins.player;

import dev.dankom.torn.Torn;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.world.WorldSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(PlayerControllerMP.class)
public class MixinPlayerControllerMP {
    @Shadow
    public WorldSettings.GameType currentGameType;
    /**
     * @author Dankom
     */
    @Overwrite
    public float getBlockReachDistance()
    {
        return (Torn.getModuleManager().getModule("Reach").isToggled() ? (float) (Torn.getSettingsManager().getSetting("Reach", "Distance").getValDouble() + 1.0) : (this.currentGameType.isCreative() ? 5.0F : 4.5F));
    }
}
