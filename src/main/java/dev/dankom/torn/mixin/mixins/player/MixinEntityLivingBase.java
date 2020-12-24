package dev.dankom.torn.mixin.mixins.player;

import dev.dankom.torn.mixin.mixins.entity.MixinEntity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(EntityLivingBase.class)
public abstract class MixinEntityLivingBase extends MixinEntity {
    @Shadow
    public abstract boolean isPotionActive(Potion potionIn);
    @Shadow
    public abstract PotionEffect getActivePotionEffect(Potion potionIn);
    @Shadow
    public abstract float getHealth();
    @Shadow
    public abstract float getMaxHealth();
    @Shadow
    public float jumpMovementFactor;
    @Shadow
    public abstract void heal(float healAmount);
    @Shadow
    public abstract IAttributeInstance getEntityAttribute(IAttribute attribute);
    @Shadow
    public abstract void setAIMoveSpeed(float speedIn);
    @Shadow
    public float cameraPitch;
}
