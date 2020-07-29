package com.gk.logistic.init;

import com.gk.logistic.GKLogistic;
import com.gk.logistic.items.ItemBase;
import com.gk.logistic.items.ItemFoodBase;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ModItems {

    public static final List<Item> ITEMS = new ArrayList<>();

    public static final Item GKDEBUGGER = new ItemFoodBase("gkdebugger", 5, 5);
}
