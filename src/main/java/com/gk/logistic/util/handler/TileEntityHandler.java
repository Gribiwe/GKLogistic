package com.gk.logistic.util.handler;

import com.gk.logistic.blocks.connector.TileEntityConnector;
import com.gk.logistic.util.Constants;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler {
    public static void registerTileEntities()
    {
        GameRegistry.registerTileEntity(TileEntityConnector.class, new ResourceLocation(Constants.MODID + ":connector"));
    }
}
