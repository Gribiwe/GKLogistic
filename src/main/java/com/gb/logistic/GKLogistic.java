package com.gb.logistic;

import com.gb.logistic.proxy.ServerProxy;
import com.gb.logistic.util.Reference;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * The entry class to the GKLogic mod.
 *
 * @author Gribiwe
 */
@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class GKLogistic
{

    @Mod.Instance
    public static GKLogistic instance;

    @SidedProxy(clientSide = Reference.CLIENT, serverSide = Reference.SERVER, modId = Reference.MODID)
    public static ServerProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {

    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {

    }
}
