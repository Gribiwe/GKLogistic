package com.gk.logistic.blocks.connector;

import com.gk.logistic.init.ModBlocks;
import com.gk.logistic.util.Constants;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiConnector extends GuiContainer {

    private static final ResourceLocation GUI_CONNECTOR = new ResourceLocation(Constants.MODID + ":textures/gui/connector.png");
    private final InventoryPlayer playerInv;
    private final TileEntityConnector te;

    public GuiConnector(InventoryPlayer playerInv, TileEntityConnector chestInv, EntityPlayer player) {
        super(new ContainerConnector(playerInv, chestInv, player));
        this.playerInv = playerInv;
        this.te = chestInv;

        this.xSize = 175;
        this.ySize = 131;

    }



    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0f, 1.0f,  1.0f);
        this.mc.getTextureManager().bindTexture(GUI_CONNECTOR);
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
    }


    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        String name = I18n.format(ModBlocks.TUBE.getUnlocalizedName() + ".name");
        this.fontRenderer.drawString(name, 8, 6, 000000);
        this.fontRenderer.drawString(this.playerInv.getDisplayName().getFormattedText(), 8, this.ySize - 92, 000000);
    }


}
