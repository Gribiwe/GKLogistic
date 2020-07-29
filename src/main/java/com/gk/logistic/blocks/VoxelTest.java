package com.gk.logistic.blocks;

import com.gk.logistic.GKLogistic;
import com.gk.logistic.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class VoxelTest extends BlockBase {

    /**
     * x1 = н_пикселей_от_восточной_грани_модели_до_самой_модели / 16
     * y1 = н_пикселей_от_нижней_грани_модели_до_самой_модели / 16
     * z1 = н_пикселей_от_северной_грани_модели_до_самой_модели / 16
     * x2 = 1 - x1
     * y2 = высота_модели / 16
     * z2 = 1 - z1
     */
    public static final AxisAlignedBB voxelTestAxis = new AxisAlignedBB(0.1875D, 0, 0.1875D, 1 - 0.1875D, 1, 1 - 0.1875D);

    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

    {
        this.setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.EAST));
    }

    public VoxelTest() {
        super("voxel_test", Material.PISTON, GKLogistic.GKLOGISTIC_TAB);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        EnumFacing facing = EnumFacing.getFront(meta);

        if (facing.getAxis() == EnumFacing.Axis.Y) {
            facing = EnumFacing.EAST;
        }

        return getDefaultState().withProperty(FACING, facing);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(FACING).getIndex();
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[] {FACING});
    }


    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        playerIn.getFoodStats().setFoodLevel(5);
        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
    }

    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public boolean isFullBlock(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return voxelTestAxis;
    }
}
