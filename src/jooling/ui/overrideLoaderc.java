package jooling.ui;

import arc.Core;
import arc.func.Floatp;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.math.Mathf;
import arc.scene.Group;
import arc.scene.actions.Actions;
import arc.scene.event.Touchable;
import arc.scene.ui.Label;
import arc.scene.ui.TextButton;
import arc.scene.ui.layout.Table;
import arc.util.Nullable;
import jooling.graphics.JooPalet;
import static mindustry.Vars.tree;
import static mindustry.Vars.ui;
import mindustry.gen.Tex;
import mindustry.graphics.Pal;
import mindustry.ui.Bar;
import mindustry.ui.Fonts;
import mindustry.ui.Styles;
import mindustry.ui.WarningBar;
import mindustry.ui.fragments.LoadingFragment;

public class overrideLoaderc extends LoadingFragment {
    private Table table;
    private static TextButton button;
    private Bar bar;
    private static Label nameLabel;
    private float progValue;
    private Label tooltipTitle;
    private Label tooltipInfo;
    public static boolean loadFragShow;
    private static Table tabl;

    public static void init() {
        ui.loadfrag = new overrideLoaderc();
        ui.loadfrag.build(Core.scene.root);
    }

    public void build(Group parent){
        if(tabl == null) {
            tabl = new Table(table -> {
                nameLabel = table.add("@loading").color(Color.white).pad(10f).style(Styles.techLabel).left().get();
                table.image(Tex.clear).growX();
                button = table.button("@cancel", () -> {
                }).size(250f, 50f).visible(true).right().get();
                updateLabel(false, "@loading");
            });
        }
        parent.fill(t -> {
            //rect must fill screen completely.
            t.rect((x, y, w, h) -> {
                Draw.alpha(t.color.a);
                Styles.black8.draw(0, 0, Core.graphics.getWidth(), Core.graphics.getHeight());
            });
            t.visible = false;
            t.touchable = Touchable.enabled;
            t.add().growY();
            t.row();
            t.table(a -> {
                tooltipTitle = a.add("Lorem Ipsum").color(Color.white).fontScale(1.5f).style(Styles.techLabel).pad(10).left().get();
                a.row();
                tooltipInfo = a.add("Line1\nline2\nline3\n").pad(10).left().get();
            }).left().row();
            t.add().growY();
            t.row();
            if(tabl != null) t.add(tabl).growX();
            t.row();
            t.table(a -> {
                a.add(new WarningBar()).color(Color.white).growX().height(34f).row();
                bar = a.add(new Bar()).pad(3).padTop(6).height(40f).growX().visible(false).get();
            }).bottom().growX().row();
            table = t;
        });
    }

    public void toFront(){
        table.toFront();
    }

    public void setProgress(Floatp progress){
        bar.reset(0f);
        bar.visible = true;
        bar.set(() -> ((int)(progress.get() * 100) + "%"), progress, Color.white);
    }

    public void snapProgress(){
        bar.snap();
    }

    public void setProgress(float progress){
        progValue = progress;
        if(!bar.visible){
            setProgress(() -> progValue);
        }
    }

    public void setButton(Runnable listener){
        button.visible = true;
        button.getListeners().remove(button.getListeners().size - 1);
        button.clicked(listener);
    }

    public void setText(String text){
        updateLabel(false, text);
        nameLabel.setColor(JooPalet.anomalyWhite);
    }

    public void show(){
        tree.loadSound("chatMessage").play();
        show("@loading");
    }

    public void show(String text){
        tree.loadSound("chatMessage").play();
        button.visible = false;
        nameLabel.setColor(Color.white);
        tooltipTitle.setColor(Color.white);
        bar.visible = false;
        table.clearActions();
        table.touchable = Touchable.enabled;
        updateLabel(false, text);
        updateLabel(true, null);
        table.visible = true;
        table.color.a = 1f;
        table.toFront();
        loadFragShow = true;
    }

    public void hide(){
        table.clearActions();
        table.toFront();
        table.touchable = Touchable.disabled;
        table.actions(Actions.fadeOut(0.5f), Actions.visible(false));
        tabl = null;
        loadFragShow = false;
    }

    private void updateLabel(boolean isTooltip, @Nullable String text) {
        Label label = isTooltip ? tooltipTitle : nameLabel;
        label.setText(text);
        label.setColor(Color.white);

        CharSequence realText = label.getText();
        for (int i = 0; i < realText.length(); i++) {
            if (Fonts.tech.getData().getGlyph(realText.charAt(i)) == null) {
                label.setStyle(Styles.defaultLabel);
                return;
            }
        }
        label.setStyle(Styles.techLabel);

        if (isTooltip) {
            int randomNum = Mathf.random(7);
            tooltipTitle.setText(Core.bundle.get("tooltipTitle-" + randomNum));
            tooltipInfo.setText(Core.bundle.get("tooltipInfo-" + randomNum));
        }
    }
}

