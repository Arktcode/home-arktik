package jooling.content;

import jooling.content.blocks.JoolingDef;
import jooling.content.blocks.JoolingDistr;
import jooling.content.blocks.JoolingDrills;
import jooling.content.blocks.JoolingEnvs;
import jooling.content.blocks.JoolingPower;
import jooling.content.blocks.JoolingProdc;

public class BlockLoaderc {
    public static void load(){


        JoolingProdc.load();
        JoolingDistr.load();
        JoolingDef.load();
        JoolingDrills.load();
        JoolingEnvs.load();
        JoolingPower.load();
    }
}



