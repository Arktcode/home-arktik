package jooling.content.blocks;

import static jooling.content.items.JoolingItems.magnesium;
import static jooling.content.items.JoolingItems.zinc;
import static jooling.content.items.JoolingItems.zirconium;
import jooling.graphics.JooPalet;
import jooling.graphics.JooShaders;
import jooling.world.meta.JooAttribute;
import mindustry.content.Fx;
import mindustry.content.StatusEffects;
import mindustry.entities.Effect;
import mindustry.world.Block;
import mindustry.world.blocks.environment.Floor;
import mindustry.world.blocks.environment.OreBlock;
import mindustry.world.blocks.environment.Prop;
import mindustry.world.blocks.environment.StaticWall;
import mindustry.world.blocks.environment.SteamVent;
import mindustry.world.blocks.environment.TreeBlock;
import mindustry.world.meta.Attribute;


public class JoolingEnvs {

    public static Block 

     //region Env
     zincFloor, zincPlate, lipus, lipusWall, lipusBioWall, lipusVent, lipusBoulder, test, garrada, garradaStone, garradaCrater, teraglific, tera, teraLinesN, teraLinesOld, teraWall, redSand, redSandStoneFloor, redSandStone, redSandStoneOld, redSandDune, redSandDuneColapse, redSandWall, deathGrass,
     //endregion
     //region Props
     scrapDefense, Rotaterus, RotarusL, RotarusD, RoturusL, RoturusD,
     //endregion
     //region Liquids
     MetallicXigene,
     //endregion
     //region Ores
     zink, magnesiumk, zircon, zinkWall;

    public static void load() {

        tera = new Floor("tera-floor") {{

            variants = 6;

            status = StatusEffects.slow;
            statusDuration = 30f;
        }};

        teraLinesN = new Floor("tera-lines-n") {{
            variants = 0;
        }};

        teraLinesOld = new Floor("tera-lines-old") {{
            variants = 3;
            blendGroup = teraLinesN;
        }};

        teraWall = new StaticWall("tera-wall") {{
            variants = 3;
            
        }};

        teraglific = new Floor("teraglific") {{

            variants = 10;
            blendGroup = tera;
        }};

        lipus = new Floor("lipus", 6) {{
            decoration = JoolingEnvs.lipusBoulder;
        }};

        lipusWall = new StaticWall("lipus-wall") {{
            variants = 3;
            lipus.asFloor();
            
        }};

        lipusVent = new SteamVent("lipus-vent"){{

            variants = 3;
            parent = blendGroup = lipus;
            attributes.set(Attribute.steam, 1);
            effectColor = JooPalet.anomalyWhite;
            effect = Fx.vapor;
            effectSpacing = 10f;
        }};

        lipusBoulder = new Prop("lipus-boulder") {{

            variants = 3;
            lipus.asFloor();
        }};

        garrada = new Floor("garrada", 4) {{
            albedo = 0.6f;
        }};

        garradaStone = new StaticWall("garrada-stone") {{
            variants = 5;
            garrada.asFloor();
        }};

        garradaCrater = new SteamVent("garrada-crater-vent") {{
            variants = 1;
            parent = blendGroup = garrada;
            attributes.set(Attribute.water, 0.5f);
            effectColor = JooPalet.anomalyWhite;
            effect = Fx.none;
            effectSpacing = 10f;
            
        }};

        redSand = new Floor("red-sand") {{
            variants = 7;
            attributes.set(JooAttribute.redsand, 1);
            
        }};

        redSandStoneFloor = new Floor("red-sand-stone-floor") {{
            variants = 6;
            attributes.set(JooAttribute.redsand, 1);
        }};

        redSandStone = new Floor("red-sand-stone") {{
            variants = 6;
            attributes.set(JooAttribute.redsand, 1);
        }};

        redSandStoneOld = new Floor("red-sand-stone-old") {{
            variants = 6;
            blendGroup = redSandStone;
            attributes.set(JooAttribute.redsand, 0.95f);
        }};

        redSandDune = new Floor("red-sand-dunes") {{
            attributes.set(JooAttribute.redsand, 1);
            variants = 2;
        }};

        redSandDuneColapse = new Floor("red-sand-dunes-colapse") {{
            attributes.set(JooAttribute.redsand, 1);
            variants = 5;
        }};

        redSandWall = new  StaticWall("red-sand-wall") {{
            attributes.set(JooAttribute.redsand, 1);
            variants = 4;
        }};

        deathGrass = new Floor("death-grass", 6){{
        attributes.set(JooAttribute.redsand, 0.2f);
        }};

        scrapDefense = new Prop("scrap") {{
            rotate = true;
            variants = 3;
        }};

        Rotaterus = new TreeBlock("rotaterus") {{
            clipSize = 70;
            variants = 3;
        }};

        RotarusL = new TreeBlock("rotarus-live") {{
            sizeOffset = 1;
            clipSize = 80;
        }};

        RotarusD = new TreeBlock("rotarus-death") {{
            sizeOffset = 1;
            clipSize = 80;
        }};

        RoturusD = new TreeBlock("roturus-death") {{
            sizeOffset = 2;
            shadowOffset = -4;
            clipSize = 169;
        }};
        RoturusL = new TreeBlock("roturus-live") {{
            sizeOffset = 2;
            shadowOffset = -4;
            clipSize = 170;
        }};

        zircon = new OreBlock("zircon") {{
            variants = 2;
            itemDrop = zirconium;
        }};

        zincFloor = new Floor("zinc-floor"){{
            variants = 6;

        }};

        zincPlate = new Floor("zinc-plate"){{
            variants = 9;

        }};

        zink = new OreBlock("zink") {{
            variants = 4;
            itemDrop = zinc;
        }};

        zinkWall = new OreBlock("zink-wall") {{

            itemDrop = zinc;
            wallOre  = true;
            variants = 3;
            walkEffect = Effect.all.random();

        }};

        magnesiumk  = new OreBlock("scoria"){{

            itemDrop = magnesium;
            variants = 3;

        }};

        MetallicXigene = new Floor("metalxigene") {{
            variants = 0;
            liquidMultiplier = 0.5f;
            isLiquid = true;
            status = StatusEffects.freezing;  // Opcional: añadir algún efecto de estado (como veneno)
            statusDuration = 20f;
            cacheLayer = JooShaders.oxigenLayer;
        }};

    }
}
