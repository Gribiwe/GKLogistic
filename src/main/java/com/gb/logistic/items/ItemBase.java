package com.gb.logistic.items;

import com.gb.logistic.GKLogistic;
import com.gb.logistic.init.ModItems;
import com.gb.logistic.util.Registrable;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBase extends Item implements Registrable {

    public ItemBase(String name, CreativeTabs creativeTabs) {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(creativeTabs);

        ModItems.ITEMS.add(this);
    }

    @Override
    public void registerModels() {
        GKLogistic.proxy.registerItemRenderer(this, 0, "inventory");
    }

}
