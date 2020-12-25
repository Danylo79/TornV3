package dev.dankom.torn.mixin.mixins.player;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.util.FoodStats;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(EntityPlayer.class)
public abstract class MixinEntityPlayer extends MixinEntityLivingBase {
    @Shadow
    public abstract boolean isUsingItem();
    @Shadow
    public abstract FoodStats getFoodStats();
    @Shadow
    public PlayerCapabilities capabilities;
    @Shadow
    public int flyToggleTimer;
}
