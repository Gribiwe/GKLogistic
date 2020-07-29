package com.gk.logistic;

import com.gk.logistic.proxy.ClientProxy;
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

    public static ServerProxy serverProxy = new ServerProxy();
    public static ClientProxy clientProxy = new ClientProxy();

    private static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {

    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
    }
}
