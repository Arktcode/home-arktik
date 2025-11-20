package jooling.content;

import static arc.Core.atlas;
import arc.graphics.Color;
import arc.math.Interp;
import arc.math.Mathf;
import arc.math.geom.Vec3;
import arc.struct.Seq;
import jooling.content.blocks.JoolingEnvs;
import jooling.graphics.g3d.CircleMesh;
import jooling.maps.HeightPass;
import jooling.maps.HeightPass.MultiHeight.MixType;
import jooling.maps.HeightPass.MultiHeight.Operation;
import jooling.maps.colored.FlatColorPass;
import jooling.maps.colored.NoiseColorPass;
import jooling.maps.colored.SphereColorPass;
import jooling.maps.planets.BetterPlanet;
import jooling.maps.planets.arkPlanetConstants;
import mindustry.content.Blocks;
import mindustry.content.Items;
import mindustry.graphics.g3d.HexMesh;
import mindustry.graphics.g3d.HexSkyMesh;
import mindustry.graphics.g3d.MultiMesh;
import mindustry.graphics.g3d.SunMesh;
import mindustry.type.Planet;

public class PlanetLoaderc {


	
    public static Planet
            jooling,
            aesgo, genebras;


    public static void load() {
	//region Jooling
        jooling = new Planet("jooling", null, 25f) {{
            solarSystem = this;
            accessible = true;
			alwaysUnlocked = true;
            visible = true;
            bloom = false;

            meshLoader = () -> new SunMesh(
                    this, 5,
                    10, 0.5, 1.7, 1.2, 2,
                    1f,
                    Color.valueOf("cce0ff"),
                    Color.valueOf("cce0ff"),
                    Color.valueOf("f2fcff"),
                    Color.valueOf("f2fcff"),
                    Color.valueOf("ccf4ff"),
                    Color.valueOf("ccf4ff")
            );
        }};
	//endregion
    //region Aesgo
        aesgo = new BetterPlanet("aesgo", jooling, 1f,3){{
            solarSystem = jooling;
            startSector = 8;
            sectorSeed = 34;
            iconColor = Color.valueOf("96c6eb");
            atmosphereColor = Color.valueOf("87ceeb");
            atmosphereRadIn = 0f;
            atmosphereRadOut = 0.155f;
            orbitTime = 40f;
            landCloudColor = Color.valueOf("9390dd").a(1);
            hiddenItems.addAll(Items.erekirItems).addAll(Items.serpuloItems);

            defaultCore = Blocks.coreAcropolis;

            alwaysUnlocked = true;
            accessible = true;
            visible = true;

            orbitSpacing = 50;
            orbitRadius = 150;
            drawOrbit = true;
            rotateTime = 10f * 60f;

            Vec3 ringPos = new Vec3(0,1,0).rotate(Vec3.X, -25);

            generator = new arkPlanetConstants(){{

				baseHeight = 0;
				baseColor = JoolingEnvs.zircon.mapColor;

				heights.add(new HeightPass.NoiseHeight() {{
					offset.set(1000, 560, 0);
					octaves = 8;
					persistence = 0.5;
					magnitude = 0.9f;
					heightOffset = -0.55f;
				}});

				Mathf.rand.setSeed(4);
				Seq<HeightPass> mountains = new Seq<>();
				for (int i = 0; i < 16; i++) {
					mountains.add(new HeightPass.DotHeight() {{
						dir.setToRandomDirection().x = Mathf.random(0.5f, 1f);
						min = 0.99f;
						magnitude = Math.max(0.33f, dir.nor().y) * 0.3f;
                        dir.rotate(Vec3.X, 10f);
						interp = Interp.exp10Out;
					}});
				}
				
				heights.add(new HeightPass.MultiHeight(mountains, MixType.max, Operation.add));
                
				Mathf.rand.setSeed(2);
				for (int i = 0; i < 10; i++) {
					mountains.add(new HeightPass.DotHeight() {{
						dir.setToRandomDirection().y = Mathf.random(0.8f, 1.5f);
						min = 0.99f;
						magnitude = Math.max(0.1f, dir.nor().y) * 0.3f;
                        dir.rotate(Vec3.X, -25f);
						interp = Interp.bounceIn;
					}});
				}

				Mathf.rand.setSeed(5);
				for (int i = 0; i < 10; i++) {
					mountains.add(new HeightPass.DotHeight() {{
						dir.setToRandomDirection().y = Mathf.random(0.4f, 1.2f);
						min = 0.99f;
						magnitude = Math.max(0.2f, dir.nor().y) * 0.2f;
                        dir.rotate(Vec3.X, -25f);
						interp = Interp.swingOut;
					}});
				}
				heights.add(new HeightPass.MultiHeight(mountains, MixType.max, Operation.add));

				Mathf.rand.setSeed(7);
                mountains = new Seq<>();
				for (int i = 0; i < 5; i++) {
					mountains.add(new HeightPass.DotHeight() {{
						dir.setToRandomDirection().x = Mathf.random(-1f, -3f);
						min = 0.99f;
						magnitude = Math.max(0.2f, dir.nor().y) * 0.5f;
						dir.rotate(Vec3.X, 22f);
						interp = Interp.exp10In;
					}});
				}
				heights.add(new HeightPass.MultiHeight(mountains, MixType.max, Operation.add));

				Seq<HeightPass> craters = new Seq<>();
				Mathf.rand.setSeed(3);
				for(int i = 0; i < 5; i++) {
					craters.add(new HeightPass.SphereHeight() {{
						pos.set(Vec3.Y).rotate(Vec3.X, 115f).rotate(ringPos, Mathf.random(120f));
						radius = 0.3f + Mathf.random(0.05f);
						offset = 0.40f;
						set = true;
					}});
				}

				heights.add(new HeightPass.ClampHeight(0f, 0.8f));

				colors.addAll(
					new NoiseColorPass() {{
						scale = 1.5;
						persistence = 0.5;
						octaves = 3;
						magnitude = 1.2f;
						min = 0.3f;
						max = 0.6f;
						out = JoolingEnvs.lipus.mapColor;
						offset.set(1500f, 300f, -500f);
					}},
					new NoiseColorPass() {{
						seed = 5;
						scale = 1.5;
						persistence = 0.5;
						octaves = 5;
						magnitude = 1.2f;
						min = 0.1f;
						max = 0.4f;
						out = JoolingEnvs.zink.mapColor;
						offset.set(1500f, 300f, -500f);
					}},
					new NoiseColorPass() {{
						seed = 8;
						scale = 1.5;
						persistence = 0.5;
						octaves = 7;
						magnitude = 1.2f;
						min = 0.1f;
						max = 0.4f;
						out = Blocks.darksand.mapColor;
						offset.set(1500f, 300f, -500f);
					}}
				);
				for(int i = 0; i < 5; i++) {
					colors.add(new SphereColorPass(new Vec3().setToRandomDirection(), 0.06f, JoolingEnvs.tera.mapColor));
				}
				colors.add(
					new FlatColorPass() {{
						min = max = 0f;
						out = JoolingEnvs.lipus.mapColor;
					}},
					new FlatColorPass() {{
						min = 0.3f;
						max = 0.5f;
						out = JoolingEnvs.garrada.mapColor;
					}},
					new FlatColorPass() {{
						max = 1f;
						min = 0.5f;
						out = Blocks.ice.mapColor;
					}}
				);
				craters.map(height -> (HeightPass.SphereHeight) height).each(height -> colors.add(
					new SphereColorPass(height.pos, height.radius/1.75f, Blocks.deepwater.mapColor)
				));
			}};

			meshLoader = () -> new MultiMesh(
					new AtmosphereHexMesh(7),
					new HexMesh(this, 7),

					new CircleMesh(atlas.find("jooling-ring2"), this,80, 1.8f, 2f, ringPos),
					new CircleMesh(atlas.find("jooling-ring1"), this,80, 1.5f, 1.7f, ringPos),

                    new HexSkyMesh(this, 6, -0.5f, 0.14f, 6, Blocks.water.mapColor.cpy().a(0.2f), 2, 0.42f, 1f, 0.6f),
                    new HexSkyMesh(this, 1, 0.6f, 0.15f, 6, JoolingEnvs.lipus.mapColor.cpy().a(0.2f), 2, 0.42f, 1.2f, 0.5f),
                    new HexSkyMesh(this, 1, 0.6f, 0.2f, 6, Color.valueOf("fffffff").cpy().a(0.2f), 2, 0.42f, 1.4f, 0.5f)
                );
            
        };
    };
}};
