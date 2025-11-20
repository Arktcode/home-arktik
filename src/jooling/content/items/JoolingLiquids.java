package jooling.content.items;

import arc.graphics.Color;
import arc.struct.Seq;
import mindustry.type.Liquid;

public class JoolingLiquids {


    public static final Seq<Liquid> AesgoItems = new Seq<>();

    public static Liquid

    /*Aesgo Liquids.*/
    
    oxigen;


    public static void load() {

        oxigen = new Liquid("oxigen", Color.valueOf("8890a3")){{

            viscosity = 0.8f;
            flammability = 1;
            temperature = -1;
            gas = true;

        }};

            
    }
    
}
