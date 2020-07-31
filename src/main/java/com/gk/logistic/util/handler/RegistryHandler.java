package com.gk.logistic.util.handler;

import com.gk.logistic.GKLogistic;
import com.gk.logistic.blocks.connector.TileEntityConnector;
import com.gk.logistic.init.ModBlocks;
import com.gk.logistic.init.ModItems;
import com.gk.logistic.util.Registrable;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@Mod.EventBusSubscriber
public class RegistryHandler {

    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(ModItems.ITEMS.toArray(new Item[0]));
    }

    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(ModBlocks.BLOCKS.toArray(new Block[0]));
        TileEntityHandler.registerTileEntities();
    }

    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event) {
        for (Item item : ModItems.ITEMS) {
            if (item instanceof Registrable) {
                ((Registrable) item).registerModels();
            }
        }

        for (Block block : ModBlocks.BLOCKS) {
            if (block instanceof Registrable) {
                ((Registrable) block).registerModels();
            }
        }
    }

    public static void initRegistries() {
        NetworkRegistry.INSTANCE.registerGuiHandler(GKLogistic.instance, new GuiHandler());
        //ClientRegistry.bindTileEntitySpecialRenderer(TileEntityConnector.class, new );
    }
}
