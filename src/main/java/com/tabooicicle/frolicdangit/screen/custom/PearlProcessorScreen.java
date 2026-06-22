package com.tabooicicle.frolicdangit.screen.custom;

import com.mojang.blaze3d.systems.RenderSystem;
import com.tabooicicle.frolicdangit.FrolicDangIt;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class PearlProcessorScreen extends AbstractContainerScreen<PearlProcessorMenu> {

    private static final ResourceLocation GUI_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(FrolicDangIt.MOD_ID,"frolicgangitptone/gui/pearl_processor_gui");
private static final ResourceLocation ARROW_TEXTURE =
        ResourceLocation.fromNamespaceAndPath(FrolicDangIt.MOD_ID, "textures/gui/pp_arrow_progress");

    public PearlProcessorScreen(PearlProcessorMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F,1.0F,1.0F,1.0F);
        RenderSystem.setShaderTexture(0,GUI_TEXTURE);
        int x = (width - imageWidth) /2;
        int y = (height - imageHeight) /2;

        guiGraphics.blit(GUI_TEXTURE, x ,y, 0, 0, imageWidth, imageHeight, 176,182);

        renderProgressArrow(guiGraphics, x, y);
    }

    private void renderProgressArrow(GuiGraphics guiGraphics, int x, int y) {
        if(menu.isCrafting()) {
            guiGraphics.blit(ARROW_TEXTURE, x + 73, y + 35, 0, 0, menu.getScaledArrowProgress(), 18, 7, 18);
        }
    }


    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        this.renderTooltip(pGuiGraphics, pMouseX, pMouseY);
    }
}
