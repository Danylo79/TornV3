package dev.dankom.torn.mixin.mixins.entity;

import dev.dankom.torn.event.events.RenderEvent;
import net.minecraft.client.renderer.EntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderer.class)
public class MixinEntityRenderer {
    @Inject(method = "updateCameraAndRender", at = @At(value = "RETURN"))
    private void renderWorld(float p_181560_1_, long p_181560_2_, CallbackInfo ci) {
        new RenderEvent().call();
    }
}
