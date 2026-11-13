package com.github.smain8413.cmsm.mixin.client.ui.mixins;


import com.github.smain8413.cmsm.ui.WorldPreferencesScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.world.CreateWorldScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

@Mixin(CreateWorldScreen.class)
public class WorldScreenMixin extends Screen {

    private ButtonWidget myInjectedValue;


    protected WorldScreenMixin(Text title) {
        super(title);
    }

    @Inject(method = "init", at = @At("HEAD"))
    private void onInit(CallbackInfo info){
        ButtonWidget button = new ButtonWidget(width/2 + 5, 151, 150, 20, new LiteralText("Set Choices"), button1 -> {
            System.out.println("hi");
            MinecraftClient.getInstance().openScreen(new WorldPreferencesScreen(this));
        });
        this.myInjectedValue = button;
        button.visible = false;
        this.addButton(button);

    }

    @Shadow
    private boolean moreOptionsOpen;

    @Inject(method = "setMoreOptionsOpen(Z)V", at = @At("TAIL"))
    private void onInit(boolean open, CallbackInfo info) {
        // Your custom screen initialization logic here

        ButtonWidget button = myInjectedValue;

        button.visible = moreOptionsOpen;
    }

    @Override //don't know where this came from
    public Optional<Element> hoveredElement(double mouseX, double mouseY) {
        return super.hoveredElement(mouseX, mouseY);
    }
}
