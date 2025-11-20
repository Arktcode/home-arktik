package jooling.world.utils.stats;

import arc.graphics.Color;
import arc.util.Strings;
import jooling.world.utils.ArktikStats;
import mindustry.type.Item;

public class JooItem  extends  Item {
/* 
 * @author IArkt
*/
    public float magnetism = 0;
    public boolean magnetic = false;

    public Strings Details;


public JooItem(String name, Color color)  {super(name, color);}

@Override
public void setStats() {
    super.setStats();
    
        stats.addPercent(ArktikStats.magnetism, magnetism);
        stats.add(ArktikStats.magnetic, magnetic);
        
    }
};


