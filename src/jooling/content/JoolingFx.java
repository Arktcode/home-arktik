package jooling.content;

import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.math.Angles;
import arc.math.Rand;
import arc.math.geom.Vec2;
import mindustry.entities.Effect;

public class JoolingFx{

    public static final Rand rand = new Rand();
    public static final Vec2 vec = new Vec2(), vec2 = new Vec2();
    public static Effect


    PartIn = new Effect(60f, e -> {
            Draw.color(e.color);
            Draw.alpha(e.fin() / 5);
            vec.trns(e.rotation, 4f).add(e.x, e.y);
            Angles.randLenVectors(e.id, 3, 16 * e.fout(), e.rotation, 10, (x, y) -> {
                Fill.circle(vec.x + x, vec.y + y, 3 * e.fout());
            });
            Draw.alpha(e.fin() / 7);
            Angles.randLenVectors(e.id + 3, 3, 16 * e.fout(), e.rotation, 20, (x, y) -> {
                Fill.rect(vec.x + x, vec.y + y, 5 * e.fout(), e.fout(), vec.angleTo(vec.x + x, vec.y + y));
            });
        });
    
}
