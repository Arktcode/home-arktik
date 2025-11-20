package jooling.world.utils;

import arc.struct.Seq;
import mindustry.world.meta.Stat;

public class ArktikStats {

    public static final Seq<Stat> all = new Seq<>();

    public static final Stat
        magnetism = new Stat("jooling-magnetism"),
        magnetic = new Stat("jooling-magnetic"),
        magneticAmmo = new Stat("magnetism");

    // Static block to add all stats to the sequence
    static {
        all.add(magnetism);
        all.add(magnetic);
        all.add(magneticAmmo);
    }
}