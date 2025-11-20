package jooling.maps.planets;

import arc.func.Cons;
import arc.graphics.Color;
import arc.math.Rand;
import arc.math.geom.Mat3D;
import arc.math.geom.Vec3;
import arc.struct.Seq;
import arc.util.Tmp;
import jooling.content.PlanetLoaderc;
import jooling.content.blocks.JoolingEnvs;
import jooling.maps.colored.FlatColorPass;
import jooling.maps.colored.NoiseColorPass;
import jooling.maps.colored.SphereColorPass;
import mindustry.content.Blocks;
import mindustry.graphics.g3d.GenericMesh;
import mindustry.graphics.g3d.HexMesh;
import mindustry.graphics.g3d.MatMesh;
import mindustry.graphics.g3d.MultiMesh;
import mindustry.graphics.g3d.NoiseMesh;
import mindustry.graphics.g3d.PlanetGrid.Ptile;
import mindustry.maps.planet.AsteroidGenerator;
import mindustry.type.Planet;
import mindustry.type.Sector;
import mindustry.world.Block;
import mindustry.world.meta.Env;

public class AsteroidsLoadercs extends PlanetLoaderc{


	
    public static Planet
        burus, depna,
        cenegale;


    public static void load() {
        //region Extras
        cenegale = new BetterPlanet("cenegale", jooling, 0.7f){{

            solarSystem = jooling;

            alwaysUnlocked = false;
            accessible = false;
            visible = false;

            iconColor = Color.valueOf("96c6eb");
            atmosphereColor = Color.valueOf("87ceeb");
            atmosphereRadIn = 0f;
            atmosphereRadOut = 0.1f;
            
            landCloudColor = Color.valueOf("9390dd").a(1);

            startSector = 4;
            orbitTime = 60;
			orbitRadius = 55f;
            orbitSpacing = 45;
			drawOrbit = true;
			defaultEnv = Env.terrestrial;
			generator = new arkPlanetConstants(){{

			    colors.addAll(
					new NoiseColorPass() {{
						scale = 1.5;
						persistence = 0.5;
						octaves = 3;
						magnitude = 1.2f;
						min = 0.3f;
						max = 0.6f;
						out = JoolingEnvs.redSand.mapColor;
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
						out = JoolingEnvs.redSandStone.mapColor;
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
						out = Blocks.salt.mapColor;
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
						out = Blocks.sand.mapColor;
						offset.set(1500f, 300f, -500f);
					}});


					for(int i = 0; i < 5; i++) {
						colors.add(new SphereColorPass(new Vec3().setToRandomDirection(), 0.06f, JoolingEnvs.garrada.mapColor));
					}
					colors.add(
						new FlatColorPass() {{
							min = max = 0f;
							out = JoolingEnvs.deathGrass.mapColor;
						}},
						new FlatColorPass() {{
							min = 0.3f;
							max = 0.5f;
							out = JoolingEnvs.deathGrass.mapColor;
						}},
						new FlatColorPass() {{
							max = 1f;
							min = 0.5f;
							out = Blocks.redStone.mapColor;
						}}
					);

			}};
            meshLoader = () -> new MultiMesh(
					new AtmosphereHexMesh(7), 
					new HexMesh(this, 5)
					);		
        }}; 
        //rendregion
        //region More Asteroids!!!        
  
        burus = Asteroid("burus", aesgo, Blocks.ferricStoneWall, Blocks.carbonWall, 0.4f, 7, 1f, gen -> {
        gen.min = 25;
        gen.max = 35;
        gen.carbonChance = 0.6f;
        gen.iceChance = 0f;
        gen.berylChance = 0.1f;
                    
        });

        depna = Asteroid2("depna", jooling, Blocks.ferricStoneWall, Blocks.carbonWall, 1f, 50, 3f, gen -> {
        gen.min = 25;
        gen.max = 35;
        gen.carbonChance = 0.6f;
        gen.iceChance = 0f;
        gen.berylChance = 0.1f;
        });
                
        
    }
    //region Asteroide1
    private static Planet Asteroid(String name, Planet parent, Block base, Block tint, float tintThresh, int pieces, float scale, Cons<AsteroidGenerator> cgen){
        return new Planet(name, parent, 0.2f){{
            hasAtmosphere = false;
            updateLighting = false;
            sectors.add(new Sector(this, Ptile.empty));
            camRadius = 0.68f * scale;
            minZoom = 0.6f;
            drawOrbit = false;
            orbitSpacing = 20f;
            orbitTime = -10;
            orbitRadius = 20;
            accessible = true;
			alwaysUnlocked = true;
            clipRadius = 40f;
            defaultEnv = Env.space;
            icon = "commandRally";
            generator = new AsteroidGenerator();
            cgen.get((AsteroidGenerator)generator);

            meshLoader = () -> {
                iconColor = tint.mapColor;
                Color tinted = tint.mapColor.cpy().a(1f - tint.mapColor.a);
                Seq<GenericMesh> meshes = new Seq<>();
                Color color = base.mapColor;
                Rand rand = new Rand(id + 2);

                meshes.add(new NoiseMesh(
                    this, 0, 2, radius, 2, 0.55f, 0.6f, 14f,
                    color, tinted, 3, 0.6f, 0.38f, tintThresh
                ));

                for(int j = 0; j < pieces; j++){
                    meshes.add(new MatMesh(
                        new NoiseMesh(this, j + 1, 3, 0.05f + rand.random(0.039f) * scale, 2, 0.6f, 0.38f, 1f,
                        color, tinted, 3, 0.6f, 0.38f, tintThresh),
                        new Mat3D().setToTranslation(Tmp.v31.setToRandomDirection(rand).setLength(rand.random(0.44f, 1.4f) * scale)))
                    );
                }

                return new MultiMesh(meshes.toArray(GenericMesh.class));
            };
        }};
    }
    //endregion
    //region Asteroide2

    private static Planet Asteroid2(String name, Planet parent, Block base, Block tint, float tintThresh, int pieces, float scale, Cons<AsteroidGenerator> cgen){
        return new Planet(name, parent, 0.2f){{
            hasAtmosphere = false;
            updateLighting = false;
            sectors.add(new Sector(this, Ptile.empty));
            camRadius = 0.68f * scale;
            minZoom = 0.6f;
            drawOrbit = false;
            orbitSpacing = 45f;
            orbitTime = -10;
            orbitRadius = 50;
            accessible = true;
			alwaysUnlocked = true;
            clipRadius = 40f;
            defaultEnv = Env.space;
            icon = "commandRally";
            generator = new AsteroidGenerator();
            cgen.get((AsteroidGenerator)generator);

            meshLoader = () -> {
                iconColor = tint.mapColor;
                Color tinted = tint.mapColor.cpy().a(1f - tint.mapColor.a);
                Seq<GenericMesh> meshes = new Seq<>();
                Color color = base.mapColor;
                Rand rand = new Rand(id + 2);

                meshes.add(new NoiseMesh(
                    this, 0, 2, radius, 2, 0.55f, 0.6f, 14f,
                    color, tinted, 3, 0.6f, 0.38f, tintThresh
                ));

                for(int j = 0; j < pieces; j++){
                    meshes.add(new MatMesh(
                        new NoiseMesh(this, j + 1, 3, 0.05f + rand.random(0.039f) * scale, 2, 0.6f, 0.38f, 1f,
                        color, tinted, 3, 0.6f, 0.38f, tintThresh),
                        new Mat3D().setToTranslation(Tmp.v31.setToRandomDirection(rand).setLength(rand.random(0.44f, 1.4f) * scale)))
                    );
                }

                return new MultiMesh(meshes.toArray(GenericMesh.class));
            };
        }};
    }
    
}
