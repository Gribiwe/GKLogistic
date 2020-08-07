package com.gk.logistic.blocks.connector;

import com.gk.logistic.util.Constants;
import com.gk.logistic.util.network.Network;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

public class TileEntityConnector extends TileEntityLockableLoot implements ITickable {
    private NonNullList<ItemStack> contents = NonNullList.withSize(3, ItemStack.EMPTY);
    private Network network;

    public int numPlayersUsing, ticksSinceLastSync;

    public boolean isConnector;

    public TileEntityConnector() {
        super();

    }

    @Override
    public void onLoad() {
        super.onLoad();
        List<TileEntityConnector> neighbors = getNeighbors();
        if (neighbors.isEmpty()) {
                this.setNetwork(new Network());
            } else {
                this.setNetwork(neighbors.get(0).getNetwork());
                for (TileEntityConnector neighbor:neighbors
                ) {
                    if (!neighbor.getNetwork().equals(this.network)) {
                        neighbor.setNetwork(this.network);
                    }
                }
            }
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.contents;
    }

    @Override
    public int getSizeInventory() {
        return this.contents.size();
    }

    @Override
    public boolean isEmpty() {
        for(ItemStack stack : this.contents)
        {
            if(!stack.isEmpty())
            {
                return false;
            }
        }
        return true;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.contents = NonNullList.<ItemStack>withSize(getSizeInventory(), ItemStack.EMPTY);
        if(!this.checkLootAndRead(compound))
        {
            ItemStackHelper.loadAllItems(compound, contents);
        }
        if(compound.hasKey("CustomName", 8))
        {
            this.customName = compound.getString("CustomName");
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        if(!this.checkLootAndWrite(compound))
        {
            ItemStackHelper.saveAllItems(compound, contents);
        }
        if(compound.hasKey("CustomName", 8))
        {
            this.customName = compound.getString("CustomName");
        }
        return compound;
    }

    @Override
    public void update() {

    }

    @Override
    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
        return new ContainerConnector(playerInventory, this, playerIn);
    }

    @Override
    public String getGuiID() {
        return Constants.MODID + ":" + "connector";
    }

    @Override
    public String getName() {
        return this.hasCustomName() ? this.customName : "gk_connector";
    }

    @Override
    public void openInventory(EntityPlayer player) {
        ++this.numPlayersUsing;
        this.world.addBlockEvent(pos, this.getBlockType(), 1, this.numPlayersUsing);
        this.world.notifyNeighborsOfStateChange(pos, this.getBlockType(), false);
    }

    @Override
    public void closeInventory(EntityPlayer player) {
        --this.numPlayersUsing;
        this.world.addBlockEvent(pos, this.getBlockType(), 1, this.numPlayersUsing);
        this.world.notifyNeighborsOfStateChange(pos, this.getBlockType(), false);
    }

    public List<TileEntityConnector> getNeighbors() {
        List<TileEntityConnector> neighbors = new ArrayList<>();
        BlockPos pos = this.getPos();
        World world = this.getWorld();

        TileEntity tec;
        if (world.getTileEntity(pos.up()) instanceof TileEntityConnector){
            tec = world.getTileEntity(pos.up());
            neighbors.add((TileEntityConnector) tec);
        }
        if (world.getTileEntity(pos.down()) instanceof TileEntityConnector){
            tec = world.getTileEntity(pos.down());
            neighbors.add((TileEntityConnector) tec);
        }
        if (world.getTileEntity(pos.west()) instanceof TileEntityConnector){
            tec = world.getTileEntity(pos.west());
            neighbors.add((TileEntityConnector) tec);
        }
        if (world.getTileEntity(pos.east()) instanceof TileEntityConnector){
            tec = world.getTileEntity(pos.east());
            neighbors.add((TileEntityConnector) tec);
        }
        if (world.getTileEntity(pos.south()) instanceof TileEntityConnector){
            tec = world.getTileEntity(pos.south());
            neighbors.add((TileEntityConnector) tec);
        }
        if (world.getTileEntity(pos.north()) instanceof TileEntityConnector){
            tec = world.getTileEntity(pos.north());
            neighbors.add((TileEntityConnector) tec);
        }

        return neighbors;
    }



    public Network getNetwork() {
        return network;
    }

    public void setNetwork(Network network) {
        if (this.network != null) {
            this.network.removeConnector(this);
        }
        this.network = network;
        network.addConnector(this);
    }
}
