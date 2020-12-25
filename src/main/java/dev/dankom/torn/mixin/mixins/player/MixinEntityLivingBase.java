package dev.dankom.torn.mixin.mixins.player;

import dev.dankom.torn.mixin.mixins.entity.MixinEntity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(EntityLivingBase.class)
public abstract class MixinEntityLivingBase extends MixinEntity {
    @Shadow
    public abstract boolean isPotionActive(Potion potionIn);
    @Shadow
    public abstract PotionEffect getActivePotionEffect(Potion potionIn);
    @Shadow
    public void onLivingUpdate() {}
}
