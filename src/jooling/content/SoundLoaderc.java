package jooling.content;

import arc.Core;
import arc.assets.AssetDescriptor;
import arc.assets.loaders.SoundLoader;
import arc.audio.Sound;
import mindustry.Vars;

public class SoundLoaderc {
    public static Sound
        Tbeam = new Sound();

    public static void load(){
        Tbeam = loadSound("Tbeam");
    }

    private static Sound loadSound(String soundName){
        if(!Vars.headless) {
            String name = "sound/" + soundName;
            String path = Vars.tree.get(name + ".ogg").exists() ? name + ".ogg" : name + ".mp3";

            Sound sound = new Sound();

            AssetDescriptor<?> desc = Core.assets.load(path, Sound.class, new SoundLoader.SoundParameter(sound));
            desc.errored = Throwable::printStackTrace;

            return sound;

        } else {
            return new Sound();
        }
    }
}