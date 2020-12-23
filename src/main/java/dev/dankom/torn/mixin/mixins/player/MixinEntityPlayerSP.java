package dev.dankom.torn.mixin.mixins.player;

import dev.dankom.torn.event.EventType;
import dev.dankom.torn.event.events.MotionUpdateEvent;
import dev.dankom.torn.event.events.UpdateEvent;
import dev.dankom.torn.mixin.mixins.entity.MixinEntity;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityPlayerSP.class)
public class MixinEntityPlayerSP extends MixinEntity {
    private double cachedX;
    private double cachedY;
    private double cachedZ;

    @Inject(method = "onUpdateWalkingPlayer", at = @At("HEAD"))
    private void onUpdateWalkingPlayerPre(CallbackInfo ci) {
        cachedX = posX;
        cachedY = posY;
        cachedZ = posZ;

        MotionUpdateEvent event = new MotionUpdateEvent(EventType.PRE, posX, posY, posZ, rotationYaw, rotationPitch, onGround);

        event.call();

        posX = event.getX();
        posY = event.getY();
        posZ = event.getZ();

        rotationYaw = event.getYaw();
        rotationPitch = event.getPitch();
    }

    @Inject(method = "onUpdateWalkingPlayer", at = @At("RETURN"))
    private void onUpdateWalkingPlayerPost(CallbackInfo ci) {
        posX = cachedX;
        posY = cachedY;
        posZ = cachedZ;

        new MotionUpdateEvent(EventType.POST, posX, posY, posZ, rotationYaw, rotationPitch, onGround).call();
    }

    @Inject(method = "onUpdate", at = @At("HEAD"))
    private void onUpdate(CallbackInfo ci) {
        posX = cachedX;
        posY = cachedY;
        posZ = cachedZ;

        new MotionUpdateEvent(EventType.POST, posX, posY, posZ, rotationYaw, rotationPitch, onGround).call();
    }
}
