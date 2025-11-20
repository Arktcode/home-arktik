package jooling.content.blocks;

import static jooling.content.items.JoolingItems.bauxite;
import static jooling.content.items.JoolingItems.neodymium;
import static jooling.content.items.JoolingItems.zinc;
import static jooling.content.items.JoolingLiquids.oxigen;
import jooling.world.blocks.drills.ElixedDrill;
import jooling.world.meta.JooAttribute;
import mindustry.content.Fx;
import mindustry.content.Items;
import static mindustry.content.Items.scrap;
import mindustry.entities.effect.MultiEffect;
import mindustry.gen.Sounds;
import mindustry.type.Category;
import static mindustry.type.ItemStack.with;
import mindustry.world.Block;
import mindustry.world.blocks.production.BurstDrill;
import mindustry.world.blocks.production.WallCrafter;


public class JoolingDrills {

    public static Block elixDrill, explosionChamber, industrialCrusher;

    public static void load() {

        elixDrill = new ElixedDrill("elix-drill") {{
            size = 2;
            tier = 1;
            drillTime = 710f;

            requirements(Category.production, with(zinc, 35));

        }};

         explosionChamber = new BurstDrill("explosion-chamber"){{
            requirements(Category.production, with(zinc, 150, scrap, 200));

            arrows = 0;

            drillTime = 40f * 12f;
            size = 2;
            hasPower = true;
            tier = 2;
            drillEffect = new MultiEffect(Fx.drillSteam);
            shake = 2f;
            itemCapacity = 40;
            blockedItem = neodymium;
            researchCostMultiplier = 0.5f;

            drillMultipliers.put(zinc, 2f);

            fogRadius = 4;

            consumePower(60f / 60f);
            consumeLiquid(oxigen, 0.1f);
        }};

        industrialCrusher = new WallCrafter("industrial-crusher"){{
            requirements(Category.production, with(zinc, 80));
            consumePower(11 / 60f);

            drillTime = 150f;
            size = 2;
            attribute = JooAttribute.redsand;
            output = bauxite;
            fogRadius = 2;
            researchCost = with(Items.beryllium, 100, Items.graphite, 40);
            ambientSound = Sounds.drill;
            ambientSoundVolume = 0.04f;
        }};
    }
}
