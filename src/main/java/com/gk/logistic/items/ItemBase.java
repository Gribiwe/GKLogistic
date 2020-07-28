package com.gk.logistic.items;

import com.gk.logistic.GKLogistic;
import com.gk.logistic.init.ModItems;
import com.gk.logistic.util.Registrable;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.thread.SidedThreadGroups;

public class ItemBase extends Item implements Registrable {

    public ItemBase(String name, CreativeTabs creativeTabs) {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(creativeTabs);

        ModItems.ITEMS.add(this);
    }

    @Override
    public void registerModels() {
        GKLogistic.clientProxy.registerItemRenderer(this, 0, "inventory");
    }

}
