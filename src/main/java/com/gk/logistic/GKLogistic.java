package com.gk.logistic;

import com.gk.logistic.proxy.ServerProxy;
import com.gk.logistic.util.Reference;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

/**
 * The entry class to the GKLogic mod.
 *
 * @author Gribiwe
 */
@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class GKLogistic {

    @Mod.Instance
    public static GKLogistic instance;

    @SidedProxy(clientSide = Reference.CLIENT, serverSide = Reference.SERVER, modId = Reference.MODID)
    public static ServerProxy proxy;

    private static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        logger.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }
}
