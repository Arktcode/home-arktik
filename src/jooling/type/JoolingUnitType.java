package jooling.type;

import arc.graphics.Color;
import mindustry.type.UnitType;

/** This is just a preset. Contains no new behavior. */
public class JoolingUnitType extends UnitType{

    public JoolingUnitType(String name){
        super(name);

        outlineColor = Color.valueOf("0e0e0f");


        //green flashing is unnecessary since they always regen
        healFlash = true;

        healColor = Color.valueOf("fffffff");

        //TODO
        //- liquid regen ability
        //- new explode effect
    }
}
