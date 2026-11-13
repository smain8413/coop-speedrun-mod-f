package com.github.smain8413.cmsm.ui;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;

public class StructurePreferenceScreen extends Screen {

    Screen parent;

    public StructurePreferenceScreen(Screen parent){
        super(new LiteralText("Structure Settings"));
        this.parent = parent;
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta){
        this.renderBackground(matrices);
        this.drawCenteredText(matrices, this.textRenderer, this.title, this.width/2, 20, 0xFFFFFFFF);
        super.render(matrices, mouseX, mouseY, delta);
    }

    public void onClose(){
        assert this.client != null;
        if (this.parent != null){
            this.client.openScreen(this.parent);
        }
        else {
            this.client.openScreen(null);

        }
    }
}
