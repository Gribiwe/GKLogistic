package com.gk.logistic.blocks.connector;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerConnector extends Container {

    private final int numRows;
    private final TileEntityConnector connectorInventory;

    public ContainerConnector(InventoryPlayer playerInv, final TileEntityConnector tileEntityConnector, EntityPlayer player){
        this.connectorInventory = tileEntityConnector;
        this.numRows = tileEntityConnector.getSizeInventory() / 3;

        connectorInventory.openInventory(player);

        for (int y = 0; y < numRows; y++)
        {
            for (int x = 0; x < 3; x++)
            {
                this.addSlotToContainer(new Slot(connectorInventory, x + y * 3, 56 + x * 24, 18 + y * 18));
            }
        }

        for(int y = 0; y < 3; y++)
        {
            for(int x = 0; x < 9; x++)
            {
                this.addSlotToContainer(new Slot(playerInv, x + y * 9 + 9, 8 + x * 18, 50 + y * 18));
            }
        }

        for(int x = 0; x < 9; x++)
        {
            this.addSlotToContainer(new Slot(playerInv, x, 8 + x * 18, 108));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return connectorInventory.isUsableByPlayer(playerIn);
    }

    @Override
    public void onContainerClosed(EntityPlayer playerIn) {
        super.onContainerClosed(playerIn);
        connectorInventory.closeInventory(playerIn);
    }

    public TileEntityConnector getConnectorInventory() {
        return connectorInventory;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            int containerSlots = inventorySlots.size() - playerIn.inventory.mainInventory.size();

            if (index < containerSlots) {
                if (!this.mergeItemStack(itemstack1, containerSlots, inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, 0, containerSlots, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.getCount() == 0) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, itemstack1);
        }

        return itemstack;
    }
}
