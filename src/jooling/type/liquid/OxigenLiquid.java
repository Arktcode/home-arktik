package jooling.type.liquid;

import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.util.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.Liquid;

import static mindustry.entities.Puddles.*;

public class OxigenLiquid extends Liquid {
    public Color
            colorFrom = Color.cyan.cpy(), // Definir el color del oxígeno
            colorTo = Color.blue.cpy();  // Definir el color a donde se difunde

    public int cells = 18; // Número de celdas para dispersión visual

    public OxigenLiquid(String name, Color color){
        super(name, color);
    }

    @Override
    public void drawPuddle(Puddle puddle) {
        super.drawPuddle(puddle);
        Draw.z(Layer.debris - 0.5f); // Cambiar la capa para que se dibuje correctamente

        int id = puddle.id;
        float amount = puddle.amount;
        float x = puddle.x, y = puddle.y;
        float f = Mathf.clamp(amount / (maxLiquid / 1.5f)); // Calcular el tamaño del líquido
        float smag = puddle.tile.floor().isLiquid ? 0.8f : 0f, sscl = 25f;
        float length = Math.max(f, 0.3f) * 8f; // Longitud de la onda de dispersión del líquido

        // Dibujar el "puddle" con un poco de animación sinusoidal para darle vida
        Draw.color(Tmp.c1.set(color).shiftValue(-0.05f));
        Fill.poly(
                x + Mathf.sin(Time.time + id * 5, sscl, smag),
                y + Mathf.sin(Time.time + id * 3, sscl, smag),
                6, f * 8.6f // Crear un polígono que representa el líquido
        );

        rand.setSeed(id);
        for(int i = 0; i < cells; i++) {
            Draw.z(Layer.debris - 0.5f + i / 1000f + (id % 100) / 10000f); // Ajustar la capa de z para cada célula
            Tmp.v1.trns(rand.random(360f), rand.random(length)); // Desplazar aleatoriamente las posiciones
            float vx = x + Tmp.v1.x, vy = y + Tmp.v1.y;

            // Usar los colores definidos y aplicar algo de variabilidad en el color
            Draw.color(colorFrom, colorTo, rand.random(1f));

            // Crear pequeños "puddles" dispersos alrededor
            Fill.poly(
                    vx + Mathf.sin(Time.time + i * 53, sscl, smag),
                    vy + Mathf.sin(Time.time + i * 3, sscl, smag),
                    6,
                    f * 3.8f * rand.random(0.2f, 1f) * Mathf.absin(Time.time + ((i + id) % 60) * 54, 75f * rand.random(1f, 2f), 1f)
            );
        }

        Draw.color(); // Resetear el color después de dibujar el líquido
    }
}
