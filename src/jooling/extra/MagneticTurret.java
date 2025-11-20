package jooling.extra;

import arc.util.Strings;
import mindustry.entities.bullet.BulletType;
import mindustry.world.blocks.defense.turrets.ItemTurret;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatUnit;

public class MagneticTurret extends ItemTurret {
    
    public float magnetism = 0.5f;

    public MagneticTurret(String name) {
        super(name);
    }
    
    public MagneticTurret(String name, float magnetism) {
        this(name);
        this.magnetism = magnetism;
    }

    @Override
    public void setStats() {
        super.setStats();
        
        // Se itera sobre todos los tipos de munición de la torreta.
        for (BulletType bullet : ammoTypes.values()) {
            if (bullet instanceof MagneticBullet) {
                float bulletMagnetism = ((MagneticBullet) bullet).magnetismForce;
                
                if (bulletMagnetism > 0) {

                    stats.add(new Stat("bullet.magnetic.stats"), Strings.autoFixed(bulletMagnetism, 2), StatUnit.none);
                    break;
                }
            }
        }
    }
}