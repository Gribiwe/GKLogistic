package com.gb.logistic.init;

import com.gb.logistic.items.ItemBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ModItems {

    public static final List<Item> ITEMS = new ArrayList<>();

    public static final Item GB_DEBUGGER = new ItemBase("debugger", CreativeTabs.TOOLS);
}
