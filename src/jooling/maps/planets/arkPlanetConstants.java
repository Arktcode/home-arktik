package jooling.maps.planets;

import arc.graphics.Color;
import arc.math.geom.Vec3;
import arc.struct.Seq;
import jooling.maps.HeightPass;
import jooling.maps.colored;
import mindustry.maps.generators.PlanetGenerator;
import mindustry.type.Sector;

/**Simple Planet Color set... @author Arkt.code */
public class arkPlanetConstants extends PlanetGenerator {
    
	public Seq<HeightPass> heights = new Seq<>();
	public Seq<colored> colors = new Seq<>();
	public float baseHeight = 1;
	public Color baseColor = Color.valueOf("6377cd");

	public float rawHeight(Vec3 position) {
		float height = baseHeight;
		for (HeightPass h : heights) {
			height = h.height(position, height);
		}
		return height;
	}

	@Override
	public void generateSector(Sector sector) {

	}

	@Override
	public float getHeight(Vec3 position) {
		return rawHeight(position);
	}

	@Override
	public Color getColor(Vec3 position) {
		Color color = baseColor;
		for (colored c : colors) {
			if (c.color(position, rawHeight(position)) != null) color = c.color(position, rawHeight(position));
		}
		return color;
	}
}
