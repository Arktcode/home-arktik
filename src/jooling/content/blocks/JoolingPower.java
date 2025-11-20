package jooling.content.blocks;

import arc.graphics.Color;
import static jooling.content.items.JoolingItems.neodymium;
import static jooling.content.items.JoolingItems.zinc;
import static jooling.content.items.JoolingItems.zirconium;
import jooling.content.items.JoolingLiquids;
import jooling.world.blocks.logic.ImanLogic;
import jooling.world.blocks.power.HeaterMagneticGenerator;
import mindustry.content.Fx;
import static mindustry.content.Items.scrap;
import mindustry.content.Liquids;
import mindustry.gen.Sounds;
import mindustry.type.Category;
import static mindustry.type.ItemStack.with;
import mindustry.type.LiquidStack;
import mindustry.world.Block;
import mindustry.world.blocks.power.BeamNode;
import mindustry.world.blocks.power.ThermalGenerator;
import mindustry.world.draw.DrawBlurSpin;
import mindustry.world.draw.DrawDefault;
import mindustry.world.draw.DrawMulti;
import mindustry.world.draw.DrawPlasma;
import mindustry.world.draw.DrawRegion;
import mindustry.world.meta.Attribute;
import mindustry.world.meta.BlockGroup;

public class JoolingPower {

    public static Block hydrogenReactor, steamCatalyst, Iman, zincNode;

    public static void load() {


        Iman = new ImanLogic("magnet"){{

            requirements(Category.logic, with(neodymium, 50, scrap, 50));

            health = 400;
            size = 1;
            armor = 1.2f;

            range = 40f;
            atractPower = -100f;
            repelPower = 100f;

            
        }};


        hydrogenReactor = new HeaterMagneticGenerator("hidrogen-reactor"){{
            //esta es la cosa mas desbalanceada que he hecho en mi vida

            requirements(Category.power, with(zinc, 200, scrap, 100, zirconium, 50, neodymium, 30));
                size = 4;
                health = 250;
                liquidCapacity = 80f;
                outputLiquid = new LiquidStack(JoolingLiquids.oxigen, 10f / 60f);
                hasPower = true;
                explodeOnFull = true;
                explosionDamage = 500;
                conductivePower = true;
                consumePower( 30f / 60f);
                consumesPower = true;

                magneticRadius = 45f;
                attractionStrength = 40f;

                consumeLiquid(Liquids.water, 15f/60f);

                itemDuration = 20f * 3f;
                itemCapacity = 10;
                powerProduction = 300f / 60f;
                heatOutput = 5;
                drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawPlasma(){{plasmas = 1;}}, new DrawDefault());

        }};

        steamCatalyst = new ThermalGenerator("steam-catalyst"){{
            requirements(Category.power, with(zinc, 150, scrap, 80));

            size = 3;
            powerProduction = 10f / 60f;
            displayEfficiencyScale = 0.5f / 5f;


            attribute = Attribute.steam;
            group = BlockGroup.liquids;
            minEfficiency = 1f - 0.0001f;
            displayEfficiency = true;
            generateEffect = Fx.turbinegenerate;
            effectChance = 0.06f;
            ambientSound = Sounds.hum;
            ambientSoundVolume = 0.06f;

            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawBlurSpin("-rotor", 0.7f * 9f){{blurThresh = 0.02f;}}, new DrawDefault(){{
            }});

            hasLiquids = false;
            outputLiquid = new LiquidStack(JoolingLiquids.oxigen, 1.1f / 60f);
            liquidCapacity = 20f;
            fogRadius = 2;
            researchCost = with(zinc, 20);
        }};

        zincNode = new BeamNode("zinc-beam-node"){{
            requirements(Category.power, with(zinc, 15, scrap, 5));

            laserColor1 = Color.valueOf("ffffff");
            laserColor2 = Color.valueOf("c5c5c6");
            health = 100;
            size = 1;
        }};
        
    }
}