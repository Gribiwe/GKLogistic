package com.gk.logistic.blocks;

import com.gk.logistic.GKLogistic;
import com.gk.logistic.init.ModItems;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;

import java.util.Random;

public class GKDebugger extends BlockBase{

    public GKDebugger() {
        super("gkdebugger_block", Material.CAKE, GKLogistic.GKLOGISTIC_TAB);
    }

    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return ModItems.GKDEBUGGER;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }
}
