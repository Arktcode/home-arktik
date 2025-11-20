package jooling.content.units;

import arc.graphics.Color;
import jooling.content.SoundLoaderc;
import jooling.type.JoolingUnitType; // Asumo que es tu clase base para unidades voladoras o genéricas.
import mindustry.ai.types.BuilderAI;
import mindustry.content.Fx;
import mindustry.content.StatusEffects;
import mindustry.entities.abilities.ForceFieldAbility; // Importado para la habilidad de Artrei
import mindustry.entities.bullet.LaserBulletType;
import mindustry.entities.bullet.MissileBulletType; // Importado para el arma de Artrei
import mindustry.entities.part.RegionPart;
import mindustry.gen.LegsUnit; // Importado para el sonido del arma de Artrei
import mindustry.gen.Sounds;
import mindustry.gen.UnitEntity;
import mindustry.type.UnitType;
import mindustry.type.Weapon;

public class UnitTypesLoaderc {

    public static UnitType
//flyingc
    temerary,
//Spiders
    Artrei;

    public static void load() {

        temerary = new JoolingUnitType("temerary"){{
            constructor = UnitEntity::create;
            aiController = BuilderAI::new;
            isEnemy = false;
            flying = true;

            parts.add(
            new RegionPart("-slap"){{
                moveRot = -10f;
                moveX = -1f;
                moves.add(new PartMove(PartProgress.reload, 1f, 1f, 10f));
                progress = PartProgress.warmup;
                mirror = true;
            }});


            hovering = true;
            shadowElevation = 0.35f;

            health = 450f;
            hitSize = 10f;
            armor = 1f;

            accel = 0.08f;
            engineOffset = 8f;
            engineSize = 3f;
            useEngineElevation = false;
            researchCostMultiplier = 0f;

            drag = 0.09f;
            speed = 3f;
            rotateSpeed = 5.8f;

            itemCapacity = 30;
            buildSpeed = 1f;
            mineTier = 1;
            mineSpeed = 35 / 60f;
            alwaysUnlocked = true;

           weapons.add(new Weapon("temerary-nucleos"){{
                top = false;
                mirror = false;
                shootY = 5f;
                x = 0;
                y = 0.3f;
                reload = 35f;
                recoil = 0.5f;
                shootCone = 30f;

                cooldownTime = 300f;

                shootStatusDuration = 60f * 2f;
                shootStatus = StatusEffects.disarmed;
                shoot.firstShotDelay = Fx.lancerLaserCharge.lifetime;
                parentizeEffects = true;

                bullet = new LaserBulletType(){{
                    length = 9f;
                    damage = 10f;
                    width = 15f;

                    shootSound = SoundLoaderc.Tbeam;

                    shootStatusDuration = 60f * 1.1f;
                    shootStatus = StatusEffects.slow;

                    lifetime = 30f;

                    lightningSpacing = 20f;
                    lightningLength = 2;
                    lightningDelay = 1.1f;
                    lightningLengthRand = 1;
                    lightningDamage = 5;
                    lightningAngleRand = 40f;
                    largeHit = true;
                    lightColor = lightningColor = Color.valueOf("ffffff");

                    chargeEffect = Fx.lancerLaserCharge;
                    lightRadius = 2f;

                    //healPercent = 25f;
                    collidesTeam = true;

                    sideAngle = 15f;
                    sideWidth = 0f;
                    sideLength = 0f;
                    colors = new Color[]{Color.white.cpy().a(0.4f), Color.white, Color.white};
                }};
            }});

        }};


        Artrei = new JoolingUnitType("artrei"){{
            constructor = LegsUnit::create;

            outlines = true;
            hitSize = 12f;
            outlineColor = Color.valueOf("232835");
            hovering = true;
            rotateToBuilding = true;
            drawShields = true;
            shadowElevation = 0.3f;


            targetGround = true;
            targetAir = true;


            health = 365;
            mineFloor = true;
            armor = 2f;
            speed = 0.9f;
            rotateSpeed = 3f;
            

            legCount = 4;
            legLength = 6f;
            legBaseOffset = 3f;
            legExtension = -2f;
            legSpeed = 0.1f;
            allowLegStep = true;
            legMoveSpace = 3f;
            legSplashDamage = 10f;
            legSplashRange = 2f;
            legContinuousMove = true;
            legStraightness = 0.3f;
            lockLegBase = true;
            legMaxLength = 1.1f;
            legMinLength = 0.2f;
            legLengthScl = 0.97f;
            legForwardScl = 1.1f;
            legGroupSize = 4;
            rippleScale = 0.2f;
            legPhysicsLayer = false;

            lightRadius = 100f;
            lightColor = Color.valueOf("84f591");


            researchCostMultiplier = 1f;


            weapons.add(new Weapon("artrei-slat") {{
                top = false;
                mirror = true;
                x = 0f;
                y = 0f;
                rotate = false;
                rotateSpeed = 2.1f;
                reload = 40f;
                recoil = 2f;
                shootY = 6f;
                shootSound = Sounds.shoot;
                shake = 1f;

                bullet = new MissileBulletType(3f, 10) {{
                    range = 30f;
                    recoil = 1f;
                    speed = 3f;
                    laserAbsorb = true;
                    damage = 10f;
                    lifetime = 35f;
                    collides = true;
                    collidesTiles = true;
                    collidesTeam = true;
                    width = 5f;
                    height = 5f;
                    hitShake = 1f;
                    frontColor = Color.valueOf("bbfab4");
                    backColor = Color.valueOf("c8fa98");
                    lightColor = Color.valueOf("81f777");
                    trailColor = Color.valueOf("bbfab4");
                    smokeEffect = Fx.none;
                    hitEffect = Fx.none;
                    despawnEffect = Fx.smoke;
                }};
            }});

            abilities.add(new ForceFieldAbility(20f, 0.2f, 565f, 700f));
        }};
    }
}
