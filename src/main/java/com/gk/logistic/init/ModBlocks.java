package com.gk.logistic.init;

import com.gk.logistic.blocks.GKDebugger;
import com.gk.logistic.blocks.Connector;
import net.minecraft.block.Block;

import java.util.ArrayList;
import java.util.List;

public class ModBlocks {
    public static final List<Block> BLOCKS = new ArrayList<>();

    public static final Block GKDEBUGGER_BLOCK = new GKDebugger();
    public static final Block TUBE = new Connector();

}
