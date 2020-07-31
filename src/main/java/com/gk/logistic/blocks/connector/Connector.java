package com.gk.logistic.blocks.connector;

import com.gk.logistic.GKLogistic;
import com.gk.logistic.blocks.Pipe;
import com.gk.logistic.init.ModBlocks;
import com.gk.logistic.init.ModItems;
import com.gk.logistic.util.Constants;
import com.gk.logistic.util.Registrable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemLead;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

/**
 * класс трубы.
 *
 * @author Я
 */
public class Connector extends Block implements Registrable {

    public static final String NAME = "connector";

    public Connector() {
        super(Material.ROCK, MapColor.BLUE);
        setCreativeTab(GKLogistic.GKLOGISTIC_TAB);
        this.setDefaultState(
                this.blockState.getBaseState()
                        .withProperty(NORTH, Boolean.FALSE)
                        .withProperty(EAST, Boolean.valueOf(false))
                        .withProperty(SOUTH, Boolean.valueOf(false))
                        .withProperty(WEST, Boolean.valueOf(false))
                        .withProperty(UP, Boolean.valueOf(false))
                        .withProperty(DOWN, Boolean.valueOf(false)));

        setUnlocalizedName(NAME);
        setRegistryName(NAME);

        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(NAME));
    }

    public static final PropertyBool NORTH = PropertyBool.create("north");
    public static final PropertyBool EAST = PropertyBool.create("east");
    public static final PropertyBool SOUTH = PropertyBool.create("south");
    public static final PropertyBool WEST = PropertyBool.create("west");
    public static final PropertyBool UP = PropertyBool.create("up");
    public static final PropertyBool DOWN = PropertyBool.create("down");

    protected static final AxisAlignedBB[] BOUNDING_BOXES = new AxisAlignedBB[] {
            new AxisAlignedBB(0.25D, 0.1D, 0.25D, 0.75D, 0.9D, 0.75D),
            new AxisAlignedBB(0.25D, 0.1D, 0.25D, 0.75D, 0.9D, 1.0D),
            new AxisAlignedBB(0.0D, 0.1D, 0.25D, 0.75D, 0.9D, 0.75D),
            new AxisAlignedBB(0.0D, 0.1D, 0.25D, 0.75D, 0.9D, 1.0D),

            new AxisAlignedBB(0.25D, 0.1D, 0.0D, 0.75D, 0.9D, 0.75D),
            new AxisAlignedBB(0.25D, 0.1D, 0.0D, 0.75D, 0.9D, 1.0D),
            new AxisAlignedBB(0.0D, 0.1D, 0.0D, 0.75D, 0.9D, 0.75D),
            new AxisAlignedBB(0.0D, 0.1D, 0.0D, 0.75D, 0.9D, 1.0D),

            new AxisAlignedBB(0.25D, 0.1D, 0.25D, 1.0D, 0.9D, 0.75D),
            new AxisAlignedBB(0.25D, 0.1D, 0.25D, 1.0D, 0.9D, 1.0D),
            new AxisAlignedBB(0.0D, 0.1D, 0.25D, 1.0D, 0.9D, 0.75D),
            new AxisAlignedBB(0.0D, 0.1D, 0.25D, 1.0D, 0.9D, 1.0D),

            new AxisAlignedBB(0.25D, 0.1D, 0.0D, 1.0D, 0.9D, 0.75D),
            new AxisAlignedBB(0.25D, 0.1D, 0.0D, 1.0D, 0.9D, 1.0D),
            new AxisAlignedBB(0.0D, 0.1D, 0.0D, 1.0D, 0.9D, 0.75D),
            new AxisAlignedBB(0.0D, 0.1D, 0.0D, 1.0D, 0.9D, 1.0D)};

    public static final AxisAlignedBB PILLAR_AABB = new AxisAlignedBB(0.25D, 0.10D, 0.25D, 0.75D, 0.9D, 0.75D);
    public static final AxisAlignedBB SOUTH_AABB = new AxisAlignedBB(0.25D, 0.10D, 0.75D, 0.75D, 0.9D, 1.0D);
    public static final AxisAlignedBB WEST_AABB = new AxisAlignedBB(0.0D, 0.10D, 0.25D, 0.25D, 0.9D, 0.75D);
    public static final AxisAlignedBB NORTH_AABB = new AxisAlignedBB(0.25D, 0.10D, 0.0D, 0.75D, 0.9D, 0.25D);
    public static final AxisAlignedBB EAST_AABB = new AxisAlignedBB(0.75D, 0.10D, 0.25D, 1.0D, 0.9D, 0.75D);
    public static final AxisAlignedBB UP_AABB = new AxisAlignedBB(0.0D, 0.10D, 0, 0, 0.9D, 0);
    public static final AxisAlignedBB DOWN_AABB = new AxisAlignedBB(0.0D, 0.10D, 0, 0, 0.9D, 0);

    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean isActualState)
    {
        if (!isActualState)
        {
            state = state.getActualState(worldIn, pos);
        }

        addCollisionBoxToList(pos, entityBox, collidingBoxes, PILLAR_AABB);

        if (((Boolean)state.getValue(NORTH)).booleanValue())
        {
            addCollisionBoxToList(pos, entityBox, collidingBoxes, NORTH_AABB);
        }

        if (((Boolean)state.getValue(EAST)).booleanValue())
        {
            addCollisionBoxToList(pos, entityBox, collidingBoxes, EAST_AABB);
        }

        if (((Boolean)state.getValue(SOUTH)).booleanValue())
        {
            addCollisionBoxToList(pos, entityBox, collidingBoxes, SOUTH_AABB);
        }

        if (((Boolean)state.getValue(WEST)).booleanValue())
        {
            addCollisionBoxToList(pos, entityBox, collidingBoxes, WEST_AABB);
        }

        if (((Boolean)state.getValue(UP)).booleanValue())
        {
            addCollisionBoxToList(pos, entityBox, collidingBoxes, UP_AABB);
        }

        if (((Boolean)state.getValue(DOWN)).booleanValue())
        {
            addCollisionBoxToList(pos, entityBox, collidingBoxes, DOWN_AABB);
        }
    }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        state = this.getActualState(state, source, pos);
        return BOUNDING_BOXES[getBoundingBoxIdx(state)];
    }

    private static int getBoundingBoxIdx(IBlockState state)
    {
        int i = 0;

        if (((Boolean)state.getValue(NORTH)).booleanValue())
        {
            i |= 1 << EnumFacing.NORTH.getHorizontalIndex();
        }

        if (((Boolean)state.getValue(EAST)).booleanValue())
        {
            i |= 1 << EnumFacing.EAST.getHorizontalIndex();
        }

        if (((Boolean)state.getValue(SOUTH)).booleanValue())
        {
            i |= 1 << EnumFacing.SOUTH.getHorizontalIndex();
        }

        if (((Boolean)state.getValue(WEST)).booleanValue())
        {
            i |= 1 << EnumFacing.WEST.getHorizontalIndex();
        }

//        if (((Boolean)state.getValue(UP)).booleanValue())
//        {
//            i |= 1 << EnumFacing.UP.getHorizontalIndex();
//        }
//
//        if (((Boolean)state.getValue(DOWN)).booleanValue())
//        {
//            i |= 1 << EnumFacing.DOWN.getHorizontalIndex();
//        }

        return i;
    }

    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    public boolean isPassable(IBlockAccess worldIn, BlockPos pos)
    {
        return false;
    }

    public boolean canConnectTo(IBlockAccess worldIn, BlockPos pos, EnumFacing facing)
    {
        IBlockState iblockstate = worldIn.getBlockState(pos);
        BlockFaceShape blockfaceshape = iblockstate.getBlockFaceShape(worldIn, pos, facing);
        Block block = iblockstate.getBlock();

//        boolean flag = blockfaceshape == BlockFaceShape.MIDDLE_POLE && (iblockstate.getMaterial() == this.blockMaterial || block instanceof Tube);
        return !isExcepBlockForAttachWithPiston(block) && (block instanceof Connector || block instanceof Pipe || block instanceof BlockContainer);
    }

    protected static boolean isExcepBlockForAttachWithPiston(Block p_194142_0_)
    {
        return Block.isExceptBlockForAttachWithPiston(p_194142_0_) || p_194142_0_ == Blocks.BARRIER || p_194142_0_ == Blocks.MELON_BLOCK || p_194142_0_ == Blocks.PUMPKIN || p_194142_0_ == Blocks.LIT_PUMPKIN;
    }

    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        return true;
    }

    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack itemstack = playerIn.getHeldItem(hand);

        if (itemstack.getItem() == Items.LEAD) {
            if (!worldIn.isRemote)
            {
                return ItemLead.attachToFence(playerIn, worldIn, pos);
            }
            else
            {
                return  true;
            }
        } else {
            playerIn.openGui(GKLogistic.instance, Constants.GUI_CONNECTOR, worldIn, pos.getX(), pos.getY(), pos.getZ());
            return true;
        }

    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        TileEntityConnector tileEntity = (TileEntityConnector) worldIn.getTileEntity(pos);
        InventoryHelper.dropInventoryItems(worldIn, pos, tileEntity); //TODO возможно tileEntity надо заменить на state (по видосу)
        super.breakBlock(worldIn, pos, state);
    }

    @Override
    public BlockStateContainer getBlockState() {
        return super.getBlockState();
    }

    public int getMetaFromState(IBlockState state)
    {
        return 0;
    }

    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        return state.withProperty(NORTH, canFenceConnectTo(worldIn, pos, EnumFacing.NORTH))
                .withProperty(EAST,  canFenceConnectTo(worldIn, pos, EnumFacing.EAST))
                .withProperty(SOUTH, canFenceConnectTo(worldIn, pos, EnumFacing.SOUTH))
                .withProperty(UP, canFenceConnectTo(worldIn, pos, EnumFacing.UP))
                .withProperty(DOWN, canFenceConnectTo(worldIn, pos, EnumFacing.DOWN))
                .withProperty(WEST,  canFenceConnectTo(worldIn, pos, EnumFacing.WEST));
    }

    public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        switch (rot)
        {
            case CLOCKWISE_180:
                return state.withProperty(NORTH, state.getValue(SOUTH)).withProperty(EAST, state.getValue(WEST)).withProperty(SOUTH, state.getValue(NORTH)).withProperty(WEST, state.getValue(EAST));
            case COUNTERCLOCKWISE_90:
                return state.withProperty(NORTH, state.getValue(EAST)).withProperty(EAST, state.getValue(SOUTH)).withProperty(SOUTH, state.getValue(WEST)).withProperty(WEST, state.getValue(NORTH));
            case CLOCKWISE_90:
                return state.withProperty(NORTH, state.getValue(WEST)).withProperty(EAST, state.getValue(NORTH)).withProperty(SOUTH, state.getValue(EAST)).withProperty(WEST, state.getValue(SOUTH));
            default:
                return state;
        }
    }

    public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
    {
        switch (mirrorIn)
        {
            case LEFT_RIGHT:
                return state.withProperty(NORTH, state.getValue(SOUTH)).withProperty(SOUTH, state.getValue(NORTH));
            case FRONT_BACK:
                return state.withProperty(EAST, state.getValue(WEST)).withProperty(WEST, state.getValue(EAST));
            default:
                return super.withMirror(state, mirrorIn);
        }
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {NORTH, EAST, WEST, SOUTH, UP, DOWN});
    }

    /* ======================================== FORGE START ======================================== */

    @Override
    public boolean canBeConnectedTo(IBlockAccess world, BlockPos pos, EnumFacing facing)
    {
        return canConnectTo(world, pos.offset(facing), facing.getOpposite());
    }

    private boolean canFenceConnectTo(IBlockAccess world, BlockPos pos, EnumFacing facing)
    {
        BlockPos other = pos.offset(facing);
        Block block = world.getBlockState(other).getBlock();
        return block.canBeConnectedTo(world, other, facing.getOpposite()) || canConnectTo(world, other, facing.getOpposite());
    }

    /* ======================================== FORGE END ======================================== */

    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
    {
        return face != EnumFacing.UP && face != EnumFacing.DOWN ? BlockFaceShape.MIDDLE_POLE : BlockFaceShape.CENTER;
    }

    @Override
    public void registerModels() {
        GKLogistic.commonProxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }

    // Для контейнера


    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityConnector();
    }

    /*
    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityConnector();
    }

     */

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }
}
