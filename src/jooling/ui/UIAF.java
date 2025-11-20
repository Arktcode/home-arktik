package jooling.ui;

import arc.Core;
import static arc.Core.settings;
import arc.scene.ui.ScrollPane;
import arc.scene.ui.layout.Table;
import arc.struct.OrderedMap;
import arc.struct.Seq;
import static mindustry.Vars.iconXLarge;
import mindustry.ctype.UnlockableContent;
import mindustry.gen.Iconc;
import mindustry.graphics.Pal;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatCat;
import mindustry.world.meta.StatValue;
import mindustry.world.meta.Stats;

public class UIAF {

    public static void show(Table p, UnlockableContent content) {
        p.clear();

        Table table = new Table();
        table.margin(10);

        // Asegurarse de que las estadísticas se hayan inicializado
        content.checkStats();

        // Título del bloque/unidad
        table.table(title1 -> {
            title1.image(content.uiIcon).size(iconXLarge);
            title1.add("[accent]" + content.localizedName + (settings.getBool("console") ? "\n[gray]" + content.name : "")).padLeft(5);
        });
        table.row();

        // Descripción del contenido
        if (content.description != null) {
            boolean hasStats = content.stats.toMap().size > 0;
            if (hasStats) {
                table.add("@category.purpose").color(Pal.accent).fillX().padTop(10);
                table.row();
            }
            table.add("[lightgray]" + content.displayDescription()).wrap().fillX().padLeft(hasStats ? 10 : 0).width(500f).padTop(hasStats ? 0 : 10).left();
            table.row();

            if (!content.stats.useCategories && hasStats) {
                table.add("@category.general").fillX().color(Pal.accent);
                table.row();
            }
        }

        // Mostrar estadísticas
        Stats stats = content.stats;
        for (StatCat cat : stats.toMap().keys()) {
            OrderedMap<Stat, Seq<StatValue>> map = stats.toMap().get(cat);
            if (map.size == 0) continue;

            if (stats.useCategories) {
                table.add("@category." + cat.name).color(Pal.accent).fillX();
                table.row();
            }

            for (Stat stat : map.keys()) {
                table.table(inset -> {
                    inset.left();
                    inset.add("[lightgray]" + stat.localized() + ":[] ").left().top();
                    Seq<StatValue> arr = map.get(stat);
                    for (StatValue value : arr) {
                        value.display(inset);
                        inset.add().size(10f);
                    }
                }).fillX().padLeft(10);
                table.row();
            }
        }
        
        // Detalles adicionales
        if (content.details != null) {
            table.add("[gray]" + (content.unlocked() || !content.hideDetails ? content.details : Iconc.lock + " " + Core.bundle.get("unlock.incampaign"))).pad(6).padTop(20).width(400f).wrap().fillX();
            table.row();
        }

        content.displayExtra(table);

        // Agrega un ScrollPane si es necesario
        ScrollPane pane = new ScrollPane(table);
        p.add(pane);
    }
}