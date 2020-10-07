package com.refinedmods.refinedpipes.screen.widget;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.refinedmods.refinedpipes.RefinedPipes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class IconButton extends Button {
    private static final ResourceLocation RESOURCE = new ResourceLocation(RefinedPipes.ID, "textures/gui/extractor_attachment.png");

    private final IconButtonPreset preset;
    private int overlayTexX;
    private int overlayTexY;

    public IconButton(int x, int y, IconButtonPreset preset, int overlayTexX, int overlayTexY, ITextComponent text, IPressable onPress) {
        super(x, y, preset.getWidth(), preset.getHeight(), text, onPress);

        this.preset = preset;
        this.overlayTexX = overlayTexX;
        this.overlayTexY = overlayTexY;
    }

    public void setOverlayTexX(int overlayTexX) {
        this.overlayTexX = overlayTexX;
    }

    @Override
    public void renderButton(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        Minecraft minecraft = Minecraft.getInstance();
        minecraft.getTextureManager().bindTexture(RESOURCE);

        RenderSystem.disableDepthTest();

        int y = preset.getYTexNormal();
        if (!active) {
            y = preset.getYTexDisabled();
        } else if (isHovered) {
            y = preset.getYTexHover();
        }

        blit(matrixStack, this.x, this.y, preset.getXTex(), y, this.width, this.height, 256, 256);

        // Fiddling with -1 to remove the blue border
        blit(matrixStack, this.x + 1, this.y + 1, overlayTexX + 1, overlayTexY + 1, this.width - 2, this.height - 2, 256, 256);

        RenderSystem.enableDepthTest();
    }
}
