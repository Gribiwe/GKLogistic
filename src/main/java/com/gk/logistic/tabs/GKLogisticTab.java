package com.gk.logistic.tabs;

import com.gk.logistic.init.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class GKLogisticTab extends CreativeTabs {

    public GKLogisticTab(String label) {
        super(label);
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(ModItems.GKDEBUGGER);
    }


}
