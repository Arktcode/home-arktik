package jooling.ui;

import arc.Core;
import arc.math.Interp;
import arc.scene.Group;
import arc.scene.actions.Actions;
import arc.scene.event.ClickListener;
import arc.scene.event.InputEvent;
import arc.scene.event.Touchable;
import arc.scene.ui.Label;
import arc.scene.ui.ScrollPane;
import arc.scene.ui.TextButton;
import arc.scene.ui.layout.Table;
import arc.util.Align;
import mindustry.ui.Styles;

public class StartAUI {
    private static Table joolingLogo, joolingBanner, joolingText, cont;
    private static Table skipButtonTable;
    public static void build(Group group) {
        group.fill(t -> {
            cont = t;
            t.touchable(() -> Touchable.enabled);

            t.setBackground(Styles.black);

            t.fill(w -> {
                joolingLogo = w;
                w.image(Core.atlas.find("jooling-arktik")).center().expand();
                w.setTransform(true);
            });

            t.fill(w -> {
                joolingBanner = w;
                w.image(Core.atlas.find("jooling-logo")).center().expand();
                w.setTransform(true);
            });


            t.fill(w -> {
                joolingText = w;
                // Se define el contenido del changelog directamente en el código, ciertamente me da flojera agregar un archivo .txt.
                String changelogContent = "[white]Jooling Ark [accent]v0.60.5.Ar\n" +
                        "[#a0b1f3]• Se agregó una pantalla de inicio.\n" +
                        "[#a0b1f3]• Balance a estructuras.\n" +
                        "[#a0b1f3]• Cambios de utilidad.\n"+
                        "[royal]• Creditos; [#f37272]s_m_i_t_e[white] |[#a2e798] poufy0707.\n"+
                        "[royal]por aportar ideas utiles al desarrollo y testeo.";


                Label updatesLabel = new Label(changelogContent);
                updatesLabel.setAlignment(Align.topLeft);
                updatesLabel.setWrap(true);


                ScrollPane scroll = new ScrollPane(updatesLabel, Styles.defaultPane);
                scroll.setFadeScrollBars(false);
                scroll.setScrollingDisabled(true, false);


                w.add(scroll).grow().pad(40f);
            });
        });


        addSkipButton(group);
    }

   public static void show() {
    cont.visible(() -> true);

    float targetY = Core.graphics.getHeight() * 0.9f; 
    
    joolingLogo.actions(
        Actions.alpha(0f),
        Actions.delay(1f),
        Actions.fadeIn(1f, Interp.pow3Out),
        Actions.delay(0.9f),
        Actions.fadeOut(1f, Interp.pow3Out)
    );

    joolingText.actions(
        Actions.alpha(0f),
        Actions.delay(1f),
        Actions.fadeIn(3f, Interp.pow3Out),
        Actions.delay(2f),
        Actions.fadeOut(1f, Interp.pow3Out)
    );

    // Esta es la parte clave para el movimiento *grita*.
    joolingBanner.actions(
        Actions.sequence(
            Actions.alpha(0f),
            Actions.delay(3.6f),
            Actions.fadeIn(1f, Interp.pow3Out),
            Actions.delay(0.5f),
            Actions.moveToAligned(Core.graphics.getWidth() / 2f, targetY, Align.center, 1.5f, Interp.smooth),
            // Asegura que el banner se quede en su posición.
            Actions.forever(Actions.run(() -> {}))
        )
    );


    cont.actions(
        Actions.delay(9f),
        Actions.fadeOut(0.5f),
        Actions.run(() -> {
            cont.visible(() -> false);
            cont.touchable(() -> Touchable.disabled);
            onComplete();
        })
    );
}

    private static void skipAnimation() {

        cont.clearActions();
        joolingLogo.clearActions();
        joolingBanner.clearActions();
        joolingText.clearActions();


        if (skipButtonTable != null) {
            skipButtonTable.clearActions();
            skipButtonTable.actions(Actions.fadeOut(0f, Interp.fade), Actions.run(() -> skipButtonTable.remove()));
        }


        cont.actions(
                Actions.fadeOut(1.48f),
                Actions.run(() -> {
                    cont.visible(() -> false);
                    cont.touchable(() -> Touchable.disabled);
                    onComplete();
                })
        );
    }

    private static void onComplete() {

        if (skipButtonTable != null) {
            skipButtonTable.remove();
        }
    }

    /**
     * @param group El grupo principal de la escena.
     */
    private static void addSkipButton(Group group) {
        group.fill(t -> {
            skipButtonTable = t;
            t.bottom().right().add(new TextButton("Skip", Styles.defaultt) {{
                addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        skipAnimation();
                    }
                });
            }}).size(150, 60).pad(10f);
        });
    }
}