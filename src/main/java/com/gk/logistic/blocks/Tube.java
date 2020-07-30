package com.gk.logistic.blocks;

import com.gk.logistic.GKLogistic;
import com.gk.logistic.init.ModBlocks;
import com.gk.logistic.init.ModItems;
import com.gk.logistic.util.Registrable;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

/**
 * класс трубы.
 *
 * @author Я
 */
public class Tube extends BlockFence implements Registrable {

    public static final String NAME = "gk_tube";

    public Tube() {
        super(Material.ROCK, MapColor.BLUE);
        setCreativeTab(GKLogistic.GKLOGISTIC_TAB);
        setUnlocalizedName(NAME);
        setRegistryName(NAME);

        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(NAME));
    }

    @Override
    public void registerModels() {
        GKLogistic.commonProxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }
}
