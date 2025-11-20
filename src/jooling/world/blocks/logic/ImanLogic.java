package jooling.world.blocks.logic;

import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import arc.math.Angles;
import arc.scene.ui.ImageButton;
import arc.scene.ui.Label;
import arc.scene.ui.layout.Table;
import arc.util.Eachable;
import arc.util.Tmp;
import arc.util.io.Reads;
import arc.util.io.Writes;
import mindustry.entities.units.BuildPlan;
import mindustry.gen.Building;
import mindustry.gen.Groups;
import mindustry.graphics.Drawf;
import mindustry.graphics.Pal;
import mindustry.ui.Bar;
import mindustry.ui.Styles;
import mindustry.world.Block;
import mindustry.world.draw.DrawBlock;
import mindustry.world.draw.DrawDefault;

/**
 * Un bloque de lógica que actúa como atractor y repulsor, de forma
 * independiente. @author Arksource.
 */
public class ImanLogic extends Block {

    public float range = 80f;
    public float atractPower = -100f;
    public float repelPower = 100f;

    public DrawBlock drawer = new DrawDefault();

    public ImanLogic(String name) {
        super(name);
        update = true;
        configurable = true;
        hasPower = true;
        consumesPower = false;
    }

    @Override
    public void setBars(){
        super.setBars();
        addBar("power", (ImanLogicBuild e) -> 
            new Bar(() -> "Estado Energía", () -> Pal.powerBar, () -> e.power.status));
    }

    @Override
    public void setStats(){
        super.setStats();

    }

    @Override
    public void load() {
        super.load();
    }
    
    @Override
    public TextureRegion[] icons() {
        return drawer.finalIcons(this);
    }
    
    @Override
    public void drawPlanRegion(BuildPlan plan, Eachable<BuildPlan> list) {
        drawer.drawPlan(this, plan, list);
    }

    public class ImanLogicBuild extends Building {
        public boolean pullEnabled = false;
        public boolean pushEnabled = false;

        @Override
        public void update() {
            super.update();

            Groups.unit.each(unit -> {
                float dst = unit.dst(x, y);

                if (dst > range || (!pullEnabled && !pushEnabled)) return;

                if (unit == null || !unit.isAdded() || unit.isBuilding()) return;

                float totalAppliedForce = 0;
                float unitAngle = Angles.angle(x, y, unit.x, unit.y);

                // Lógica de Atracción
                if (pullEnabled) {
                    if (dst < 2f) { 
                        unit.vel.set(0, 0);
                        return;
                    }
                    float pullForce = atractPower * (dst / range);
                    totalAppliedForce += pullForce;
                }
                
                // Lógica de Repulsión
                if (pushEnabled) {
                    float pushForce = repelPower * (1f - dst / range);
                    totalAppliedForce += pushForce;
                }

                if (Math.abs(totalAppliedForce) > 0.1f) {
                    Tmp.v1.trns(unitAngle, totalAppliedForce);
                    unit.impulse(Tmp.v1);
                }
            });
        }
        
        @Override
        public void draw() {
            drawer.draw(this); 
            
            // Dibuja el radio del efecto.
            Draw.color(Color.gray);
            Drawf.circles(x, y, range);
            
            if (pullEnabled || pushEnabled) {
                Draw.color(Color.purple);
                float opacity = 0.5f;
                Draw.alpha(opacity);
                float shieldRadius = range * 1.2f;
                Drawf.circles(x, y, shieldRadius);
            }
            
            Draw.color(); 
        }

        @Override
        public void buildConfiguration(Table table) {
            // Botón de Atracción
            ImageButton pullButton = new ImageButton(Styles.squareTogglei);
            pullButton.row();
            pullButton.setChecked(pullEnabled);
            table.add(pullButton).size(40f);
            table.add(new Label("Attraction"));
            pullButton.changed(() -> {
                pullEnabled = pullButton.isChecked();
                configure(this);
            });

            // Botón de Repulsión
            ImageButton pushButton = new ImageButton(Styles.squareTogglei);
            pushButton.row();
            pushButton.setChecked(pushEnabled);
            table.add(pushButton).size(40f);
            table.add(new Label("repulsion"));
            pushButton.changed(() -> {
                pushEnabled = pushButton.isChecked();
                configure(this);
            });
        }
        
        @Override
        public Object config() {
            return this;
        }

        @Override
        public void configure(Object value) {
            if (value instanceof ImanLogicBuild) {

                super.configure(value); 
            }
        }

        @Override
        public void write(Writes write) {
            super.write(write);
            write.bool(pullEnabled);
            write.bool(pushEnabled);
        }

        @Override
        public void read(Reads read, byte revision) {
            super.read(read, revision);
            pullEnabled = read.bool();
            pushEnabled = read.bool();
        }
    }
}
