package com.gk.logistic.items;

import com.gk.logistic.GKLogistic;
import com.gk.logistic.init.ModItems;
import com.gk.logistic.util.Registrable;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;

public class ItemFoodBase extends ItemFood implements Registrable {

    public ItemFoodBase(String name, int amount, int saturation) {
        super(amount, saturation, true);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(CreativeTabs.FOOD);
        setCreativeTab( GKLogistic.GKLOGISTIC_TAB);

        ModItems.ITEMS.add(this);
    }

    @Override
    public void registerModels() {
        GKLogistic.commonProxy.registerItemRenderer(this, 0, "inventory");
    }
}
