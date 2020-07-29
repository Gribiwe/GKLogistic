package com.gk.logistic.init;

import com.gk.logistic.GKLogistic;
import com.gk.logistic.items.ItemBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ModItems {

    public static final List<Item> ITEMS = new ArrayList<>();

    public static final Item GKDEBUGGER = new ItemBase("gkdebugger", GKLogistic.GKLogTab);
}
