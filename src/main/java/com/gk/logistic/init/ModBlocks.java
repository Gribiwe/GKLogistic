package com.gk.logistic.init;

import com.gk.logistic.blocks.BlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

import java.util.ArrayList;
import java.util.List;

public class ModBlocks {
    public static final List<Block> BLOCKS = new ArrayList<>();

    public static final Block GKDEBUGGER_BLOCK = new BlockBase("gkdebugger_block", Material.CAKE, CreativeTabs.BUILDING_BLOCKS);

}
