package com.gk.logistic.blocks;

import com.gk.logistic.GKLogistic;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.*;

public class GKDebugger extends BlockBase{

    public GKDebugger() {
        super("gkdebugger_block", Material.CAKE, GKLogistic.GKLOGISTIC_TAB);


    }



    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }
}
