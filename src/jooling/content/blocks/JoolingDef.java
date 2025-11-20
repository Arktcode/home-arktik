package jooling.content.blocks;

import arc.graphics.Color;
import jooling.content.items.JoolingItems;
import static jooling.content.items.JoolingItems.bauxite;
import static jooling.content.items.JoolingItems.ice;
import static jooling.content.items.JoolingItems.magnesium;
import static jooling.content.items.JoolingItems.neodymium;
import static jooling.content.items.JoolingItems.zinc;
import static jooling.content.items.JoolingItems.zirconium;
import jooling.extra.MagneticBullet;
import jooling.extra.MagneticTurret;
import jooling.graphics.JooPalet;
import mindustry.content.Fx;
import mindustry.content.Items;
import static mindustry.content.Items.scrap;
import mindustry.content.Liquids;
import mindustry.content.StatusEffects;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.entities.effect.ExplosionEffect;
import mindustry.entities.part.RegionPart;
import mindustry.entities.part.ShapePart;
import mindustry.entities.pattern.ShootAlternate;
import mindustry.entities.pattern.ShootHelix;
import mindustry.gen.Sounds;
import mindustry.graphics.Pal;
import mindustry.type.Category;
import static mindustry.type.ItemStack.with;
import mindustry.world.Block;
import mindustry.world.blocks.defense.Door;
import mindustry.world.blocks.defense.Wall;
import mindustry.world.blocks.defense.turrets.ItemTurret;
import mindustry.world.consumers.ConsumeLiquid;
import mindustry.world.draw.DrawTurret;
import mindustry.world.meta.Env;



public class JoolingDef {

    public static Block securityDoor, zincWall, zincWallLarge, magnesiumWallLarge, magnesiumWall, bauxiteWall, bauxiteWallLarge,

    //turrets
    Intersection, Flicker; // Añadir Flicker aquí

    public static void load() {

        int MultiplicadorHealth = 4;

        zincWall = new Wall("zinc-wall"){{
            
            requirements(Category.defense, with(zinc, 5));

            size = 1;
            health = 60;

            absorbLasers = false;
        }};

        zincWallLarge = new Wall("zinc-wall-large"){{

            requirements(Category.defense, with(zinc, 20));

            size = 2;
            health = 60 * 3 * MultiplicadorHealth;

            absorbLasers = false;
        }};

        magnesiumWall = new Wall("magnesium-wall"){{

            requirements(Category.defense, with(magnesium, 25, zinc, 10));

            size = 1;
            health = 95;
            buildCost = 20;

            absorbLasers = true;
        }};

        magnesiumWallLarge = new Wall("magnesium-wall-large"){{

            requirements(Category.defense, with(magnesium, 60, zinc, 20));

            size = 2;
            health = 95 * 3 * MultiplicadorHealth;
            buildCost = 30;

            absorbLasers = true;
        }};

        bauxiteWall = new Wall("bauxite-wall"){{

            requirements(Category.defense, with(JoolingItems.bauxite, 5));

            consumePower(40 / 60f);

            size = 1;
            health = 40;

            absorbLasers = true;
        }};

        bauxiteWallLarge = new Wall("bauxite-wall-large"){{

            requirements(Category.defense, with(bauxite, 20));

            size = 2;
            health = 40 * 3 * MultiplicadorHealth;

            absorbLasers = true;
        }};

        securityDoor = new Door("security-door"){{

            requirements(Category.defense, with(zinc, 30));
            size = 2;
            health = 40 * 4 * MultiplicadorHealth;
            
            openfx = Fx.dooropenlarge;
            closefx = Fx.doorcloselarge;
            envDisabled |= Env.scorching;
        }};


        /*Turrets */

        Intersection = new ItemTurret("intersection"){{

             requirements(Category.turret, with(zinc, 150, Items.scrap, 50));

            ammo(
            zinc, new BasicBulletType(6f, 18f){{
                hitSize = 3f;
                ammoMultiplier = 1;
                pierceCap = 2;
                
                pierce = true;
                pierceBuilding = true;
                trailWidth = 1f;
                trailLength = 15;
                buildingDamageMultiplier = 0.5f;

                    shoot = new ShootHelix(){{
                        mag = 1f;
                        scl = 5f;
                    }};

                    smokeEffect = Fx.trailFade;
                    hitColor = Color.valueOf("e2e1e8");
                    despawnSound = Sounds.flame;

                    sprite = "bullet";
                    trailInterval = 0f;
                    trailParam = 1f;
                    speed = 2f;
                    lifetime = 60f;
                    width = height = 5f;
                    backColor = JooPalet.anomalyWhite;
                    frontColor = Color.valueOf("e2e1e8");
                    shrinkX = shrinkY = 0f;
                    trailColor = JooPalet.anomalyWhite;
                    despawnEffect = hitEffect = new ExplosionEffect(){{
                        waveColor = JooPalet.anomalyWhite;
                        smokeColor = Color.white;
                        sparkColor = Color.valueOf("b4b2bf");
                        waveStroke = 4f;
                        waveRad = 10f;
                    }};
                }},

                ice, new BasicBulletType(6f, 13f){{
                status = StatusEffects.freezing;
                statusDuration = 10f;
                hitSize = 3f;
                ammoMultiplier = 1;
                pierceCap = 1;
                
                pierce = true;
                pierceBuilding = true;
                trailWidth = 1f;
                trailLength = 15;
                buildingDamageMultiplier = 0.5f;

                    shoot = new ShootHelix(){{
                        mag = 1f;
                        scl = 5f;
                    }};

                    smokeEffect = Fx.trailFade;
                    hitColor = Color.valueOf("907ff2");
                    despawnSound = Sounds.flame;

                    sprite = "raper-bullet";
                    trailInterval = 0f;
                    trailParam = 1f;
                    speed = 5f;
                    lifetime = 40f;
                    width = height = 5f;
                    backColor = Color.valueOf("b1a6f2");
                    frontColor = Color.valueOf("907ff2");
                    shrinkX = shrinkY = 0f;
                    trailColor = Color.valueOf("b1a6f2");
                    despawnEffect = hitEffect = new ExplosionEffect(){{
                        waveColor = Color.valueOf("b1a6f2");
                        smokeColor = Color.white;
                        sparkColor = Color.valueOf("b4b2bf");
                        waveStroke = 4f;
                        waveRad = 10f;
                    }};
                }},

                zirconium, new BasicBulletType(8f, 16f){{
                hitSize = 2.7f;
                ammoMultiplier = 1;
                pierceCap = 5;
                
                pierce = true;
                pierceBuilding = true;
                trailWidth = 1f;
                trailLength = 20;
                buildingDamageMultiplier = 0.9f;

                    shoot = new ShootHelix(){{
                        mag = 1f;
                        scl = 5f;
                    }};

                    smokeEffect = Fx.trailFade;
                    hitColor = Color.valueOf("ffffff");
                    despawnSound = Sounds.flame;

                    sprite = "bullet";
                    trailInterval = 2f;
                    trailParam = 1f;
                    speed = 5f;
                    lifetime = 40f;
                    width = height = 5f;
                    backColor = Color.valueOf("ffc156");
                    frontColor = Color.valueOf("ffffff");
                    shrinkX = shrinkY = 0f;
                    trailColor = Color.valueOf("ffc156");
                    despawnEffect = hitEffect = new ExplosionEffect(){{
                        waveColor = Color.valueOf("ffc156");
                        smokeColor = Color.orange;
                        sparkColor = Color.valueOf("ffc156");
                        waveStroke = 7f;
                        waveRad = 2f;
                    }};
                }}

            );

            shootSound = Sounds.shootAlt;

            targetUnderBlocks = false;
            shake = 0.5f;
            ammoPerShot = 2;
            drawer = new DrawTurret("reinforced-");
            shootY = 4;
            outlineColor = Pal.darkOutline;
            size = 2;
            envEnabled |= Env.space;
            reload = 50f;
            recoil = 2f;
            range = 145;
            shootCone = 3f;
            scaledHealth = 150;
            rotateSpeed = 1.4f;
            researchCostMultiplier = 0.05f;

            coolantMultiplier = 3f;

            coolant = consume(new ConsumeLiquid(Liquids.water, 8f / 60f));
            limitRange();

        }};

        // --- Flicker Turret ---
        Flicker = new MagneticTurret("flicker"){{
            requirements(Category.turret, with(magnesium, 60, zinc, 200, scrap, 100));
            size = 3;
            health = 400;
            reload = 5f * 60f;
            shootY = 6;

            shoot = new ShootAlternate(){{ 
                shots = 10;
                shotDelay = 14f;
            }};
            
            range = 164f;
            recoil = 1f;
            shake = 0.5f;
            rotateSpeed = 2.5f;
            ammoPerShot = 10;

            targetAir = true;
            targetGround = false;
            shootSound = Sounds.shoot;


            ammo(
                scrap, new BasicBulletType(3f, 23f){{
                    width = 7f;
                    height = 9f;
                    lifetime = range / speed;
                    lightColor = JooPalet.anomalyWhite;
                    frontColor = Color.valueOf("b9b8a3");
                    backColor = Pal.gray;
                    trailColor = Color.valueOf("fffffff").cpy().a(0.5f);
                    trailWidth = 1.5f;
                    trailLength = 10;
                    hitEffect = Fx.hitBulletColor;
                    despawnEffect = Fx.smoke;
                    shootEffect = Fx.shootSmall;
                }},

                magnesium, new BasicBulletType(3f, 26f){{
                    width = 7f;
                    height = 9f;
                    lifetime = range / speed;
                    lightColor = JooPalet.anomalyWhite;
                    frontColor = Color.valueOf("7b5a60");
                    backColor = Color.valueOf("38252c");
                    trailColor = Color.valueOf("8a4550").cpy().a(0.5f);
                    trailWidth = 1.5f;
                    trailLength = 10;
                    hitEffect = Fx.hitBulletColor;
                    despawnEffect = Fx.smoke;
                    shootEffect = Fx.shootSmall;


                }},

                neodymium, new MagneticBullet(3f, 24f,"bullet"){{
                    width = 7f;
                    height = 9f;
                    lifetime = range / speed;
                    lightColor = JooPalet.anomalyWhite;
                    frontColor = Color.valueOf("bdb9a8");
                    backColor = Color.valueOf("a3aab5");
                    trailColor = Color.valueOf("a3aab5").cpy().a(0.5f);
                    trailWidth = 1.5f;
                    trailLength = 10;
                    hitEffect = Fx.hitBulletColor;
                    despawnEffect = Fx.drillSteam;
                    hitSize = 5f;
                    shootEffect = Fx.shootSmall;
                    magnetismRange = 10f;
                    magnetismForce = 70f;
                    magnetism = 30f;

                }}
            );

            drawer = new DrawTurret("reinforced-"){{
                
                parts.addAll(


                 new RegionPart("-blade"){{
                    progress = PartProgress.reload;
                    heatProgress = PartProgress.reload;
                    mirror = true;
                    moveX = 2f;
                    moves.add(new PartMove(PartProgress.recoil, 0f, -2.2f, 4.5f));
                }},

                 new RegionPart("-back"){{
                    progress = PartProgress.recoil;
                    heatProgress = PartProgress.recoil;
                    mirror = true;
                    moveX = 1f;
                    moves.add(new PartMove(PartProgress.recoil, 0f, -3f, 1f));
                }},

                new RegionPart("-barrel"){{
                    progress = PartProgress.warmup;
                    heatProgress = PartProgress.warmup;
                    mirror = true;
                    moveX = 0f;
                    moves.add(new PartMove(PartProgress.recoil, 0f, -2f, 0f));
                }},
                    
                    // c2

                     new ShapePart(){{
                        progress = PartProgress.recoil.delay(0.2f);
                        color = JooPalet.anomalyWhite;
                        circle = false;
                        sides = 4;
                        hollow = true;
                        mirror = true;
                        stroke = 0f;
                        strokeTo = 0.6f;
                        radius = 5f;
                        layer = mindustry.graphics.Layer.effect;
                        x = 0f;
                        y = -14f;
                        rotateSpeed = -1.2f; // Rotación para animación
                    }},
                    // t1
                    new ShapePart(){{
                        progress = PartProgress.recoil.delay(0.7f);
                        color = JooPalet.anomalyWhite;
                        circle = false;
                        sides = 3;
                        hollow = true;
                        stroke = 0f;
                        strokeTo = 1.5f;
                        radius = 2f;
                        layer = mindustry.graphics.Layer.effect;
                        x = 0f;
                        y = -14f;
                        rotateSpeed = -1.2f; // Rotación espejada
                    }},

                     new ShapePart(){{
                        progress = PartProgress.recoil.delay(0.7f);
                        color = Color.white;
                        circle = false;
                        sides = 3;
                        hollow = true;
                        stroke = -2f;
                        strokeTo = 0.6f;
                        radius = 5f;
                        mirror = true;
                        layer = mindustry.graphics.Layer.effect;
                        x =  10f;
                        y = -15f;
                        moveRot = -15;
                        rotateSpeed = -0f; // Rotación espejada
                    }}
                    
                );


            }};

            limitRange();

        }};

    }
}
