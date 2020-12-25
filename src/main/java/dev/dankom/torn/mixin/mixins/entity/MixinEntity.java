package dev.dankom.torn.mixin.mixins.entity;

import dev.dankom.torn.Torn;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Random;

@Mixin(Entity.class)
public abstract class MixinEntity {
    @Shadow public abstract boolean isSprinting();
    @Shadow
    public double posX;
    @Shadow
    public double posY;
    @Shadow
    public double posZ;
    @Shadow
    public float rotationYaw;
    @Shadow
    public float rotationPitch;
    @Shadow
    public boolean onGround;
    @Shadow
    public boolean inPortal;
    @Shadow
    public Random rand;
    @Shadow
    public int timeUntilPortal;
    @Shadow
    public abstract boolean isRiding();
    @Shadow
    public float width;
    @Shadow
    public float height;
    @Shadow
    public abstract AxisAlignedBB getEntityBoundingBox();
    @Shadow
    public abstract void setSprinting(boolean sprinting);
    @Shadow
    public boolean isCollidedHorizontally;
    @Shadow
    public boolean isCollidedVertically;
    @Shadow
    public double motionX;
    @Shadow
    public double motionY;
    @Shadow
    public double motionZ;
    @Shadow
    public float prevRotationYaw;
    @Shadow
    public World worldObj;
    @Shadow
    public int ticksExisted;
    @Shadow
    public Entity ridingEntity;
    @Shadow
    public abstract Vec3 getPositionEyes(float partialTicks);
    @Shadow
    public abstract Vec3 getLook(float partialTicks);
    @Shadow
    public abstract void setRotation(float yaw, float pitch);
    @Shadow
    public abstract void setPosition(double x, double y, double z);
    @Shadow
    public abstract boolean isInWater();
    @Shadow
    public abstract boolean isInLava();

    @Shadow public abstract int getEntityId();
}
