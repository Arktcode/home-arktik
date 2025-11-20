package jooling.world.blocks.power;

import arc.Core;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.math.Angles;
import arc.math.Mathf;
import arc.math.geom.Rect;
import arc.util.Tmp;
import arc.util.io.Reads;
import arc.util.io.Writes; // Añadido para Groups.unit
import mindustry.Vars;
import mindustry.gen.Groups; // Añadido para BuildPlan, si es necesario, aunque no directamente usado en este bloque.
import mindustry.graphics.Drawf;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;
import mindustry.ui.Bar;
import mindustry.world.blocks.heat.HeatBlock;
import mindustry.world.blocks.power.ConsumeGenerator;
import mindustry.world.draw.DrawDefault;
import mindustry.world.draw.DrawHeatOutput;
import mindustry.world.draw.DrawMulti;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatUnit;

public class HeaterMagneticGenerator extends ConsumeGenerator{
    public float heatOutput = 10f;
    public float warmupRate = 0.15f;

    // --- Nuevas propiedades para el campo magnético ---
    public float magneticRadius = 30f; // Radio del campo de atracción
    public float attractionStrength = 0.5f; // Fuerza máxima de atracción (se escala con la eficiencia)
    // --------------------------------------------------

    public HeaterMagneticGenerator(String name){
        super(name);

        drawer = new DrawMulti(new DrawDefault(), new DrawHeatOutput());
        rotateDraw = false;
        rotate = true;
        canOverdrive = false;
        drawArrow = true; // Mantener para la visualización de salida de calor
    }

    @Override
    public void setStats(){
        super.setStats();

        stats.add(Stat.output, heatOutput, StatUnit.heatUnits);
        // --- Añadir estadísticas para el campo magnético ---
        stats.add(Stat.range, magneticRadius / Vars.tilesize, StatUnit.blocks); // Mostrar el radio en bloques
        stats.add(Stat.powerCapacity, attractionStrength, StatUnit.perSecond); // Representar la fuerza de atracción
        // ----------------------------------------------------
    }

    @Override
    public boolean rotatedOutput(int x, int y){
        // Esto es para la salida de calor, que sigue siendo direccional.
        return false;
    }

    @Override
    public void setBars(){
        super.setBars();

        addBar("heat", (HeaterMagneticGeneratorBuild entity) -> new Bar("bar.heat", Pal.lightOrange, () -> entity.heat / heatOutput));
        // --- Añadir barra para el magnetismo ---
        addBar("magnetism", (HeaterMagneticGeneratorBuild entity) -> 
            new Bar(() -> Core.bundle.get("bar.magnetism"), () -> Color.white, () -> entity.efficiency)); // Color.white para la barra de magnetismo
        // --------------------------------------
    }

    public class HeaterMagneticGeneratorBuild extends ConsumeGeneratorBuild implements HeatBlock{
        public float heat;

        @Override
        public void updateTile(){
            super.updateTile();

            // El calor se aproxima al objetivo a la misma velocidad independientemente de la eficiencia.
            heat = Mathf.approachDelta(heat, heatOutput * efficiency, warmupRate * delta());

            // --- Lógica del campo magnético ---
            // Solo si el generador está trabajando (eficiencia > 0)
            if (efficiency > 0) {
                Groups.unit.each(unit -> {
                    float dst = unit.dst(x, y); // Distancia de la unidad al centro del bloque
                    float deadZone = 2f; // Zona muerta para que la unidad se "pegue" al centro

                    // Si la unidad está dentro del radio magnético
                    if (dst <= magneticRadius && dst > 0) {
                        if (unit == null || !unit.isAdded() || unit.isBuilding()) return;

                        // Si la unidad está en la zona muerta, la detenemos y la "pegamos" al centro.
                        if (dst < deadZone) {
                            unit.vel.set(0, 0);
                            return; // Ya está en el centro, no aplicar más fuerza
                        }

                        // Calcula la fuerza de atracción:
                        // **Corregido:** Se realiza un cast explícito a HeaterMagneticGenerator para acceder a attractionStrength.
                        float currentAttractionStrength = ((HeaterMagneticGenerator)block).attractionStrength * efficiency;
                        float appliedForce = currentAttractionStrength * (dst / magneticRadius);
                        
                        float unitAngle = Angles.angle(x, y, unit.x, unit.y);
                        float targetAngle = unitAngle + 180; // Apunta directamente al centro del bloque

                        unit.impulse(Tmp.v1.trns(targetAngle, appliedForce));
                    }
                });
            }
            // ----------------------------------
        }

        @Override
        public void draw(){
            super.draw();

            // --- Dibujar el campo magnético ---
            // **Corregido:** Calcula las coordenadas de la caja delimitadora del bloque en el mundo
            // y luego usa Rect.contains para comprobar si el ratón está sobre ella.
            float blockWorldSize = size * Vars.tilesize;
            Rect blockWorldBounds = Tmp.r1.set(x - blockWorldSize / 2f, y - blockWorldSize / 2f, blockWorldSize, blockWorldSize);
            boolean isMouseOverBlock = blockWorldBounds.contains(Core.input.mouseWorld().x, Core.input.mouseWorld().y);
            
            if(efficiency > 0.001f && isMouseOverBlock){ 
                Draw.z(Layer.power);
                // El color del campo magnético (púrpura)
                Draw.color(Pal.accent);
                // La opacidad del campo se escala con la eficiencia
                Draw.alpha(Mathf.clamp(efficiency, 0.1f, 0.5f)); 
                Drawf.circles(x, y, magneticRadius); // Dibujar el círculo de atracción
                Draw.reset();
            }
            // ----------------------------------
        }

        @Override
        public float heatFrac(){
            return heat / heatOutput;
        }

        @Override
        public float heat(){
            return heat;
        }

        @Override
        public void write(Writes write){
            super.write(write);
            write.f(heat);
        }

        @Override
        public void read(Reads read, byte revision){
            super.read(read, revision);
            heat = read.f();
        }
    }
}