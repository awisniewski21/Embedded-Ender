package com.cs222ee.embeddedender.screen;

import com.cs222ee.embeddedender.EmbeddedEnder;
import com.cs222ee.embeddedender.container.EmbenderContainer;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

/***
 * Taken from KaupenJoes's tutorial
 * Source: https://www.youtube.com/watch?v=4YgGqJgz2VY&ab_channel=TutorialsbyKaupenjoe
 ***/
public class EmbenderScreen extends ContainerScreen<EmbenderContainer> {
    private final ResourceLocation GUI = new ResourceLocation(EmbeddedEnder.MOD_ID,
            "textures/gui/embender_gui.png");

    public EmbenderScreen(EmbenderContainer screenContainer, PlayerInventory inv, ITextComponent titleIN) {
        super(screenContainer, inv, titleIN);
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderHoveredTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {
        RenderSystem.color4f(1f, 1f, 1f,1f);
        this.minecraft.getTextureManager().bindTexture(GUI);
        int i = this.guiLeft;
        int j = this.guiTop;
        this.blit(matrixStack, i, j, 0, 0, this.xSize, this.ySize);
    }
}
