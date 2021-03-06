package com.gk.logistic;

import com.gk.logistic.proxy.CommonProxy;
import com.gk.logistic.tabs.GKLogisticTab;
import com.gk.logistic.util.Constants;
import net.minecraft.creativetab.CreativeTabs;
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
@Mod(modid = Constants.MODID, name = Constants.NAME, version = Constants.VERSION)
public class GKLogistic {

    @Mod.Instance
    public static GKLogistic instance;

    public static final CreativeTabs GKLOGISTIC_TAB = new GKLogisticTab(Constants.NAME);

    @SidedProxy(clientSide = Constants.CLIENT, serverSide = Constants.SERVER)
    public static CommonProxy commonProxy;

    private static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
    }
}
