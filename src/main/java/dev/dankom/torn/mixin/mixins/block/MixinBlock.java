package dev.dankom.torn.mixin.mixins.block;

import net.minecraft.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Block.class)
public class MixinBlock {
    @Shadow
    public boolean isBlockContainer;
    @Shadow
    public double minX;
    @Shadow
    public double minY;
    @Shadow
    public double minZ;
    @Shadow
    public double maxX;
    @Shadow
    public double maxY;
    @Shadow
    public double maxZ;
}
