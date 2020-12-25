package dev.dankom.torn.mixin.mixins.block;

import dev.dankom.torn.Torn;
import dev.dankom.torn.module.modules.movement.NoSlow;
import net.minecraft.block.BlockSoulSand;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Mixin(BlockSoulSand.class)
@SideOnly(Side.CLIENT)
public class MixinBlockSoulSand {
    @Inject(method = "onEntityCollidedWithBlock", at = @At("HEAD"), cancellable = true)
    private void onEntityCollidedWithBlock(CallbackInfo callbackInfo) {
        final NoSlow noSlow = (NoSlow) Torn.getModuleManager().getModule("NoSlow");

        if (Objects.requireNonNull(noSlow).isToggled())
            callbackInfo.cancel();
    }
}
