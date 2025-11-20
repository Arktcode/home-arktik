package jooling.content.items;

import arc.graphics.Color;
import arc.struct.Seq;
import jooling.world.utils.stats.JooItem;
import mindustry.type.Item;

public class JoolingItems {

    public static final Seq<Item> AesgoItems = new Seq<>();

    public static JooItem

    /*Aesgo Items.*/
    
    zinc,ice, magnesium, monazite, neodymium, zirconium, bauxite;


    public static void load() {

        zinc = new JooItem("zinc", Color.valueOf("e1e9f0")) {{

            explosiveness = 0.2f;
            hardness = 1;
            cost = 1.0f;
        }};

        ice = new JooItem("ice", Color.valueOf("89a8f0")){{

            description = ("Elemento basado en Hidrogeno y Oxigeno");
            details = ("No suele ser comun... Generalmente por la poca humedad... [#fed17b]Se puede cultivar.");

            charge = 0.9f;
            hardness = 1;
            cost = 0.5f;
            flammability = -0.01f;
        }};

        magnesium = new JooItem("magnesium",Color.valueOf("6676a3")){{

            description = ("remanentes de un suelo infertil...");

            charge = 0.2f;
            magnetic = true;
            magnetism = 0.2f;
            hardness = 2;
            cost = 2f;
        }};

        monazite = new JooItem("monazite",Color.valueOf("846059")){{

            description = ("[#846059]Monacita[white], Mineral residual de roca ignea...");

            charge = 0.4f;
            magnetic = true;
            magnetism = 0.4f;
            hardness = 3;
            cost = 2.2f;
        }};

        zirconium = new JooItem("zirconium", Color.valueOf("67697a")){{

            description = ("[#67697a]Zirconium[white] Elemento poco comun, presenta una dureza excelente y absorbe bien varios tipos de calibre");
            details = ("Confundido por antiguos mineros, este material parecido al oro no es lo que parece...");

            charge = 0.1f;
            magnetic = true;
            magnetism = 0.4f;
            hardness = 2;
            cost = 3f;
        }};

        neodymium = new JooItem("neodymium",Color.valueOf("67697a")){{

            description = ("Altamente magnetico, usado en imanes y generadores electricos.");
            description = ("No es el elemento mas abundante de este lugar");

            charge = 0.9f;
            magnetic = true;
            magnetism = 1;
            hardness = 3;
            cost = 1.5f;
        }};


        bauxite = new JooItem("bauxite", Color.valueOf("af4753")) {{

            description = ("[#af4753]Bauxita[white] Una roca poroza con grandes cantidades de oxido de Alumina.");
            details = ("Bastante abundate, No solo tiñe de un color rojizo las estructuras... sino que tambien absorbe el calor.");

            flammability = -0.1f;
            hardness = 1;
            cost = 0.8f;
        }};
    }
    
}
