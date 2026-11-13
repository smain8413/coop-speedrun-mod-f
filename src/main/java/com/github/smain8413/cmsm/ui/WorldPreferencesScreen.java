package com.github.smain8413.cmsm.ui;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

public class WorldPreferencesScreen extends Screen {

    private final Screen parent;
    private String currentMode = "Easy";
    private double modeClicks = 1;
    private LiteralText modename = new LiteralText(currentMode);

    public WorldPreferencesScreen(Screen parent) {
        super(new LiteralText("Speedrun Settings"));
        this.parent = parent;
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta){
        this.renderBackground(matrices);
        this.drawCenteredText(matrices, this.textRenderer, this.title, this.width/2, 20, 0xFFFFFFFF);
        super.render(matrices, mouseX, mouseY, delta);
    }

    protected void init(){

        ButtonWidget structures = this.addButton(new ButtonWidget(this.width/4,
                this.height / 2 - 25,
                200,
                20,
                new LiteralText("Structures"),
                button -> {
                    System.out.println("Structures button getting pressed");
                    assert this.client != null;
                    //this.client.openScreen(new structuresScreen(this));
                    MinecraftClient.getInstance().openScreen(new StructurePreferenceScreen(this));
                }));

        ButtonWidget mode = this.addButton(new ButtonWidget(this.width/4, this.height/2 +25, 200, 20, new TranslatableText("selectWorld.gameMode"),
                                                   button -> {
                                                       modeClicks++;
                                                       System.out.println("mode has been clicked");
                                                       if (modeClicks%3 == 1%3){
                                                           currentMode = "Easy";
                                                       }
                                                       else if (modeClicks%3 == 2%3){
                                                           currentMode = "Medium";
                                                       }
                                                       else {
                                                           currentMode = "Hard";
                                                       }
                                                   }) {
                                               @Override
                                               public Text getMessage(){
                                                   return super.getMessage().shallowCopy().append(": ").append(new LiteralText(currentMode));
                                               }
                                           }
        );

    }

    @Override
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
