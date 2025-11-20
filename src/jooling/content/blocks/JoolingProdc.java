package jooling.content.blocks;

import arc.graphics.Color;
import jooling.content.JoolingFx;
import static jooling.content.items.JoolingItems.ice;
import static jooling.content.items.JoolingItems.magnesium;
import static jooling.content.items.JoolingItems.monazite;
import static jooling.content.items.JoolingItems.zinc;
import static jooling.content.items.JoolingItems.zirconium;
import mindustry.content.Fx;
import static mindustry.content.Items.scrap;
import mindustry.content.Liquids;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import static mindustry.type.ItemStack.with;
import mindustry.type.LiquidStack;
import mindustry.world.Block;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.draw.DrawBlurSpin;
import mindustry.world.draw.DrawCrucibleFlame;
import mindustry.world.draw.DrawDefault;
import mindustry.world.draw.DrawGlowRegion;
import mindustry.world.draw.DrawLiquidTile;
import mindustry.world.draw.DrawMulti;
import mindustry.world.draw.DrawRegion;
import mindustry.world.draw.DrawSoftParticles;
import mindustry.world.meta.Env;


public class JoolingProdc {

    public static Block DryingChamber, atmosphericExtractor, castingChamber, Filter;

    public static void load() {

        DryingChamber = new GenericCrafter("drying-chamber"){{
            requirements(Category.crafting, with(zinc, 60, scrap, 40));

            outputLiquid = new LiquidStack(Liquids.water, 10f / 60f);

            consumePower(30 / 60f);
            consumeItem(ice, 2);

            craftEffect = Fx.vapor;
            
            size = 2;
            hasPower = true;
            hasItems = true;
            hasLiquids = true;
            rotate = false;
            solid = true;
            outputsLiquid = true;
            envEnabled = Env.any;
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(Liquids.water), new DrawLiquidTile(Liquids.water){{drawLiquidLight = true;}}, new DrawGlowRegion("-heat"), new DrawDefault());
            liquidCapacity = 24f;
            craftTime = 55;
            lightLiquid = Liquids.water;
        }};

        atmosphericExtractor = new GenericCrafter("atmospheric-extractor"){{
            requirements(Category.crafting, with(zinc, 200,scrap, 60));

            outputItem = new ItemStack(ice, 6);

            consumePower(40f / 60f);

            craftEffect = JoolingFx.PartIn;
            
            size = 3;
            hasPower = true;
            hasItems = true;
            rotate = false;
            solid = true;
            envEnabled = Env.any;
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawGlowRegion("-glow"){{color = Color.valueOf("a9c6fd");}}, new DrawBlurSpin("-spin", 100f /60f), new DrawDefault());
            itemCapacity = 35;
            craftTime = 80;
            lightLiquid = Liquids.water;
        }};

        castingChamber = new GenericCrafter("casting-chamber"){{
            requirements(Category.crafting, with(zinc, 300, magnesium, 120, scrap, 20));

            outputLiquid = new LiquidStack(Liquids.slag, 15f / 60f);

            consumePower(25 / 60f);
            consumeItem(magnesium, 4);

            craftEffect = Fx.fire;
            
            size = 2;
            hasPower = true;
            hasItems = true;
            hasLiquids = true;
            rotate = false;
            solid = true;
            outputsLiquid = true;
            envEnabled = Env.any;
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawCrucibleFlame(), new DrawSoftParticles(), new DrawGlowRegion("-glow"), new DrawDefault());
            liquidCapacity = 30f;
            craftTime = 40;
        }};

        Filter = new GenericCrafter("filter"){{
            requirements(Category.crafting, with(magnesium, 50, zirconium, 20, scrap, 10));

            consumePower(15 / 60f);
            consumeLiquid(Liquids.slag, 10f / 60f);

            outputItem = new ItemStack(monazite, 3);

            craftEffect = Fx.fire;
            
            size = 1;
            hasPower = true;
            hasItems = true;
            hasLiquids = true;
            rotate = false;
            solid = true;
            outputsLiquid = true;
            envEnabled = Env.any;
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(){{padding = 2f;}},new DrawDefault());
            liquidCapacity = 30f;
            craftTime = 160;
        }};





            

    }
}