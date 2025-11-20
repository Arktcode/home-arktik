package jooling.content.blocks;

import arc.graphics.Color;
import static jooling.content.items.JoolingItems.magnesium;
import static jooling.content.items.JoolingItems.zinc;
import static jooling.content.items.JoolingItems.zirconium;
import jooling.content.units.UnitTypesLoaderc;
import jooling.graphics.JooPalet;
import mindustry.content.Blocks;
import mindustry.content.Items;
import static mindustry.content.Items.scrap;
import mindustry.type.Category;
import static mindustry.type.ItemStack.with;
import mindustry.world.Block;
import mindustry.world.blocks.distribution.DirectionLiquidBridge;
import mindustry.world.blocks.distribution.Duct;
import mindustry.world.blocks.distribution.DuctBridge;
import mindustry.world.blocks.distribution.DuctRouter;
import mindustry.world.blocks.liquid.Conduit;
import mindustry.world.blocks.liquid.LiquidRouter;
import mindustry.world.blocks.storage.CoreBlock;
import mindustry.world.blocks.storage.StorageBlock;
import mindustry.world.blocks.storage.Unloader;
import mindustry.world.meta.BlockGroup;


public class JoolingDistr {

    public static Block zincDuct, zincRouter, zincDuctBridge, liquidTank,
    
    zincConduit, zincBridgeConduit, zincLiquidRouter,

    container, reinforcedRouterUnloader,

    coreFrame;

    public static void load() {

        zincDuct = new Duct("zinc-duct"){{
            requirements(Category.distribution, with(zinc, 2));
            health = 80;
            speed = 3.6f;
            researchCost = with(zinc, 5);
        }};

        zincRouter = new DuctRouter("zinc-duct-router"){{
            requirements(Category.distribution, with(zinc, 10, scrap, 3));
            health = 90;
            speed = 2.5f;
            regionRotated1 = 1;
            solid = false;
            researchCost = with(zinc, 25);
        }};

        zincDuctBridge = new DuctBridge("zinc-duct-bridge"){{
            requirements(Category.distribution, with(zinc, 20, Items.scrap, 2));
            health = 90;
            range = 3;
            speed = 4f;
            buildCostMultiplier = 2f;
            researchCostMultiplier = 0.3f;
            squareSprite = false;
        }};

        zincConduit = new Conduit("zinc-conduit"){{

            requirements(Category.liquid, with(zinc, 5, scrap, 5));
            botColor = JooPalet.Darkblue;
            leaks = true;
            liquidCapacity = 40f;
            liquidPressure = 1.3f;
            health = 200;
            researchCostMultiplier = 2;
            underBullets = true;
            armor = 1.5f;

        }};

        zincBridgeConduit = new DirectionLiquidBridge("zinc-bridge-conduit"){{

           requirements(Category.liquid, with(zinc, 10, scrap, 15));
            range = 3;
            hasPower = false;
            hasLiquids = true;
            liquidCapacity = 25f;
            researchCostMultiplier = 2;
            underBullets = true;
            liquidCapacity = 20f;
            liquidPressure = 1.03f;
            squareSprite = false;
            ((Conduit)zincConduit).rotBridgeReplacement = this;

        }};

        zincLiquidRouter = new LiquidRouter("zinc-liquid-router"){{
            requirements(Category.liquid, with(zinc, 10, scrap, 5));
            liquidCapacity = 25f;
            liquidPadding = 3f/4f;
            researchCostMultiplier = 2;
            underBullets = true;
            solid = false;
            squareSprite = false;
        }};

        liquidTank = new LiquidRouter("liquid-tank"){{

            requirements(Category.liquid, with(zinc, 100, scrap, 50, magnesium, 30));
            liquidCapacity = 800f;
            size = 2;
            liquidPadding = 6f/4f;
            researchCostMultiplier = 3;
            solid = true;

        }};

        reinforcedRouterUnloader = new Unloader("reinforced-router-unloader"){{

            requirements(Category.effect, with(scrap, 25, magnesium, 30, zirconium, 20));
            speed = 60f / 11f;
            group = BlockGroup.transportation;
            health = 70;
            armor = 1.1f;

        }};

        container = new StorageBlock("container"){{

            requirements(Category.effect, with(scrap, 400, zirconium, 200, zinc, 100));

            size = 2;
            itemCapacity = 800;
            health = 670;
            
        }};

        /*Cores */
        coreFrame = new CoreBlock("core-frame"){{

                requirements(Category.effect, with(zinc, 1000, scrap, 500, zirconium, 300));

                unitType = UnitTypesLoaderc.temerary;
                health = 3500;
                itemCapacity = 2000;
                size = 5;
                thrusterLength = 35/2f;
                armor = 10f;
                incinerateNonBuildable = true;
                buildCostMultiplier = 0.7f;
                squareSprite = false;
                lightColor = Color.valueOf("a8dfed");
                envRequired = Blocks.coreZone.envRequired;

                drawTeamOverlay = true;

            unitCapModifier = 10;
                researchCostMultipliers.put(zinc, 0.5f);
                researchCostMultiplier = 0.17f;
            }};


    }
}