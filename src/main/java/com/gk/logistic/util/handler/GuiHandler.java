package com.gk.logistic.util.handler;

import com.gk.logistic.blocks.connector.ContainerConnector;
import com.gk.logistic.blocks.connector.GuiConnector;
import com.gk.logistic.blocks.connector.TileEntityConnector;
import com.gk.logistic.util.Constants;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import javax.annotation.Nullable;

public class GuiHandler implements IGuiHandler {
    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == Constants.GUI_CONNECTOR) {
            return new ContainerConnector(player.inventory, (TileEntityConnector) world.getTileEntity(new BlockPos(x, y, z)), player);
        }
        return null;
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == Constants.GUI_CONNECTOR) {
            return new GuiConnector(player.inventory, (TileEntityConnector) world.getTileEntity(new BlockPos(x, y, z)), player);
        }
        return null;
    }

    public static void registerGuis() {

    }
}
