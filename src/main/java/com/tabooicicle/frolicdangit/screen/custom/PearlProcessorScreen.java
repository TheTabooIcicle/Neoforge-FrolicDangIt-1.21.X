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
    public static final ResourceLocation GUI_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(FrolicDangIt.MOD_ID,"textures/gui/pearl_processor/pearl_processor_gui.png");
    private static final ResourceLocation COIL_PROGRESS =
            ResourceLocation.fromNamespaceAndPath(FrolicDangIt.MOD_ID, "textures/gui/coil_progress.png");
    private static final ResourceLocation BURNER_FILLED =
            ResourceLocation.fromNamespaceAndPath(FrolicDangIt.MOD_ID, "textures/gui/burner.png");
    private static final ResourceLocation WHITE_BARS =
            ResourceLocation.fromNamespaceAndPath(FrolicDangIt.MOD_ID, "textures/gui/whitebar.png");

    private static final int BURNER_X = 77;
    private static final int BURNER_Y = 97;

    private static final int RIGHT_BAR_X = 120;
    private static final int RIGHT_BAR_Y = 76; // bottom of bar
    private static final int RIGHT_BAR_WIDTH = 8;
    private static final int RIGHT_BAR_HEIGHT = 25;

    private static final int RIGHT_BAR2_X = 120;
    private static final int RIGHT_BAR2_Y = 22; // top of bar
    private static final int RIGHT_BAR2_WIDTH = 8;
    private static final int RIGHT_BAR2_HEIGHT = 25;

    private static final int LEFT_BAR_X = 46;
    private static final int LEFT_BAR_Y = 76; // bottom of bar
    private static final int LEFT_BAR_WIDTH = 8;
    private static final int LEFT_BAR_HEIGHT = 25;

    private static final int LEFT_BAR2_X = 46;
    private static final int LEFT_BAR2_Y = 22; // top of bar
    private static final int LEFT_BAR2_WIDTH = 8;
    private static final int LEFT_BAR2_HEIGHT = 25;

    public PearlProcessorScreen(PearlProcessorMenu menu, Inventory inventory, Component component) {
        super(menu, inventory, component);
        this.imageWidth = 176;
        this.imageHeight = 196;
        this.inventoryLabelY = this.imageHeight -  91;
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, GUI_TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        guiGraphics.blit(GUI_TEXTURE, x, y, 0, 0, imageWidth, imageHeight);


        int fuelTime = this.menu.getFuelTime();

        if (fuelTime > 0) {
            float fuelPercent = Math.min(1.0F, (float) fuelTime / 216F);
            int burnerWidth = (int) (20F * fuelPercent);
            RenderSystem.setShaderTexture(0, BURNER_FILLED);
            guiGraphics.blit(BURNER_FILLED,
                    x + BURNER_X, // Anchor's it to the left
                    y + BURNER_Y,
                    0, 0, // Reads from left of texture
                    burnerWidth, 6, 20, 6);
        }

        // white bars
        int progress = this.menu.getProgress();
        int maxProgress = this.menu.getMaxProgress();

        // only calculate percent if max progress is > 0
        float progressPercent = maxProgress > 0 ? Math.min(1.0F, (float) progress / maxProgress) : 0F;

        int delayedProgress = progress - 2;
        if (delayedProgress < 0) delayedProgress = 0;

        float delayedPercent = Math.min(1.0F, (float) delayedProgress / maxProgress);
        int barHeight = (int) (LEFT_BAR_HEIGHT * progressPercent);
        int bar2Height = (int) (LEFT_BAR2_HEIGHT * delayedPercent);
        int rightHeight = (int) (RIGHT_BAR_HEIGHT * progressPercent);
        int right2Height = (int) (RIGHT_BAR2_HEIGHT * delayedPercent);

        if (barHeight > 0) {
            RenderSystem.setShaderTexture(0, WHITE_BARS);

            // left bars down to top
            guiGraphics.blit(WHITE_BARS,
                    x + LEFT_BAR_X,
                    y + LEFT_BAR_Y - barHeight,
                    0, LEFT_BAR_HEIGHT - barHeight,
                    LEFT_BAR_WIDTH,
                    barHeight,
                    LEFT_BAR_WIDTH,
                    LEFT_BAR_HEIGHT);
            // right bar
            guiGraphics.blit(WHITE_BARS,
                    x + RIGHT_BAR_X,
                    y + RIGHT_BAR_Y - rightHeight,
                    0, RIGHT_BAR_HEIGHT - rightHeight,
                    RIGHT_BAR_WIDTH,
                    rightHeight,
                    RIGHT_BAR_WIDTH,
                    RIGHT_BAR_HEIGHT);
        }
        if (bar2Height > 0) {
            // left bar 2 top to down
            guiGraphics.blit(WHITE_BARS,
                    x + LEFT_BAR2_X,
                    y + LEFT_BAR2_Y,
                    0, 0,
                    LEFT_BAR2_WIDTH,
                    bar2Height,
                    LEFT_BAR2_WIDTH,
                    LEFT_BAR_HEIGHT);
            // right bar 2 top to down
            guiGraphics.blit(WHITE_BARS,
                    x + RIGHT_BAR2_X,
                    y + RIGHT_BAR2_Y, // starts at top
                    0,0,
                    RIGHT_BAR2_WIDTH,
                    right2Height,
                    RIGHT_BAR2_WIDTH,
                    RIGHT_BAR2_HEIGHT);
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTick);
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }
    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        guiGraphics.drawString(this.font, this.title, this.titleLabelX, this.titleLabelY, 4210752, false);

        guiGraphics.drawString(this.font, this.playerInventoryTitle, this.inventoryLabelX, this.inventoryLabelY, 4210752, false);
    }
}