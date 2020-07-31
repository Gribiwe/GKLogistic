package com.gk.logistic.blocks.connector;

import com.gk.logistic.util.Constants;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;

public class TileEntityConnector extends TileEntityLockableLoot implements ITickable {
    private NonNullList<ItemStack> contents = NonNullList.withSize(3, ItemStack.EMPTY);
    public int numPlayersUsing, ticksSinceLastSync;

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
}
