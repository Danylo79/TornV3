package dev.dankom.torn.mixin.mixins.block;

import dev.dankom.torn.Torn;
import dev.dankom.torn.module.modules.render.XRay;
import net.minecraft.block.Block;
import net.minecraft.block.state.BlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Block.class)
public abstract class MixinBlock {
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

    @Shadow public abstract BlockState getBlockState();

    @Shadow @Final protected BlockState blockState;

    /**
     * @author Dankom
     */
//    @SideOnly(Side.CLIENT)
//    @Overwrite
//    public boolean shouldSideBeRendered(IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
//        try {
//            if (Torn.getModuleManager().getModule("Xray").isToggled()) {
//                XRay xRay = (XRay) Torn.getModuleManager().getModule("Xray");
//                if (getBlockState() == Blocks.chest.getBlockState() && xRay.chests()) {
//                    return false;
//                } else if ((getBlockState() == Blocks.coal_ore.getBlockState() ||
//                        getBlockState() == Blocks.iron_ore.getBlockState() ||
//                        getBlockState() == Blocks.lapis_ore.getBlockState() ||
//                        getBlockState() == Blocks.redstone_ore.getBlockState() ||
//                        getBlockState() == Blocks.emerald_ore.getBlockState() ||
//                        getBlockState() == Blocks.gold_ore.getBlockState() ||
//                        getBlockState() == Blocks.quartz_ore.getBlockState() ||
//                        getBlockState() == Blocks.diamond_ore.getBlockState()) &&
//                        xRay.ores()) {
//                    return false;
//                } else if ((getBlockState() == Blocks.water.getBlockState() || getBlockState() == Blocks.lava.getBlockState()) &&
//                        xRay.liquids()) {
//                    return false;
//                } else {
//                    return false;
//                }
//            }
//            return side == EnumFacing.DOWN && this.minY > 0.0D ? true : (side == EnumFacing.UP && this.maxY < 1.0D ? true : (side == EnumFacing.NORTH && this.minZ > 0.0D ? true : (side == EnumFacing.SOUTH && this.maxZ < 1.0D ? true : (side == EnumFacing.WEST && this.minX > 0.0D ? true : (side == EnumFacing.EAST && this.maxX < 1.0D ? true : !worldIn.getBlockState(pos).getBlock().isOpaqueCube())))));
//        } catch (NullPointerException e) {
//            return true;
//        }
//    }
}
