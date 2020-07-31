package com.gk.logistic.blocks.connector;

import com.gk.logistic.GKLogistic;
import com.gk.logistic.init.ModBlocks;
import com.gk.logistic.util.Constants;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;

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
        String name = I18n.format(ModBlocks.CONNECTOR.getUnlocalizedName() + ".name");
        this.fontRenderer.drawString(name, 8, 6, 000000);
        this.fontRenderer.drawString(this.playerInv.getDisplayName().getFormattedText(), 8, this.ySize - 92, 000000);
    }

    //This method is called when the Gui is first called!
    @Override
    public void initGui()
    {
        //You have to add this line for the Gui to function properly!
        super.initGui();

        //The parameters of GuiButton are(id, x, y, width, height, text);
        this.buttonList.add(new GuiButton( 1, this.guiLeft + 123, this.guiTop + 4, 48, 13, "Start"));
        //NOTE: the id always has to be different or else it might get called twice or never!

        //Add any other buttons here too!
    }

    @Override
    protected void actionPerformed(GuiButton b)
    {
        //If the button id is different, or you have mrs buttons, create another if block for that too!
        if(b.id == 1)
        {
            b.playPressSound(mc.getSoundHandler());
            b.mousePressed(mc, b.x, b.y);
            sendChatMessage("Hello world");

//            te.getWorld()
//                    .getClosestPlayer(te.getPos().getX(), te.getPos().getY(), te.getPos().getZ(), 10, false)
//            .sendMessage(new TextComponentString("Hello world"));

        }
    }


}
