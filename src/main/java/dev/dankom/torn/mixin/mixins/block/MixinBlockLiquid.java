package dev.dankom.torn.mixin.mixins.block;

import dev.dankom.torn.Torn;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@SideOnly(Side.CLIENT)
@Mixin(BlockLiquid.class)
public class MixinBlockLiquid extends MixinBlock {
    @Inject(method = "canCollideCheck", at = @At("HEAD"), cancellable = true)
    private void onCollideCheck(CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        if (Torn.getModuleManager().getModule("Liquids").isToggled())
            callbackInfoReturnable.setReturnValue(true);
    }
    /**
     * @author Dankom
     */
    @Overwrite
    public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state)
    {
        if (Torn.getModuleManager().getModule("Jesus").isToggled()) {
            return AxisAlignedBB.fromBounds(pos.getX()+this.minX, pos.getY()+this.minY, pos.getZ()+minZ, pos.getX()+this.maxX, pos.getY()+this.maxY, pos.getZ()+maxZ);
        }
        return null;
    }
}
