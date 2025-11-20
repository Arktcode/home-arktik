package jooling.extra;

import arc.graphics.g2d.Draw;
import arc.math.Angles;
import arc.util.Time;
import jooling.graphics.JooPalet;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.gen.Bullet;
import mindustry.gen.Groups;
import mindustry.graphics.Drawf;

public class MagneticBullet extends BasicBulletType {

    public float magnetismRange = 80f;
    public float magnetismForce = 0.5f;

    public MagneticBullet(float speed, float damage, String bulletSprite) {
        super(speed, damage, bulletSprite);

        this.speed = speed;
    }

    @Override
    public void update(Bullet b) {
        super.update(b);

        Groups.unit.each(u -> {
            if (u.team != b.team && u.isAdded() && u.health > 0) {
                float dst = u.dst(b);
                if (dst < magnetismRange) {
                    float pullForce = magnetismForce * (1f - dst / magnetismRange);

                    u.impulse(Angles.angle(u.x, u.y, b.x, b.y), pullForce * Time.delta);
                }
            }
        });
    }

    @Override
    public void draw(Bullet b) {
        super.draw(b);

        // CORRECCIÓN: El radio del círculo es siempre el valor de magnetismRange.
        float radius = magnetismRange;
        
        // La opacidad aún se desvanecerá, pero el tamaño se mantendrá constante.
        Draw.alpha(b.fout() * 0.8f);
        Drawf.circles(b.x, b.y, radius);
        Draw.reset();

        Draw.color(JooPalet.anomalyWhite);

        Draw.reset();
    }
    
}

    