package com.gk.logistic.blocks;

import com.gk.logistic.GKLogistic;
import com.gk.logistic.init.ModBlocks;
import com.gk.logistic.init.ModItems;
import com.gk.logistic.util.Registrable;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockBase extends Block implements Registrable {

    public BlockBase(String name, Material material, CreativeTabs creativeTabs) {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(creativeTabs);



        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(name));
    }


    @Override
    public void registerModels() {
        GKLogistic.commonProxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }


}
