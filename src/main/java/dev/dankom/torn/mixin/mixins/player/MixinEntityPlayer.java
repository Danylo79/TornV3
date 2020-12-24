package dev.dankom.torn.mixin.mixins.player;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
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
    @Shadow
    public FoodStats foodStats;
    @Shadow
    public InventoryPlayer inventory;
    @Shadow
    public float prevCameraYaw;
    @Shadow
    public float cameraYaw;
    @Shadow
    public float speedInAir;
    @Shadow
    public abstract boolean isSpectator();
    @Shadow
    public abstract void collideWithPlayer(Entity p_71044_1_);
}
