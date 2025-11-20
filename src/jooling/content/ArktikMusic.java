package jooling.content;

import arc.Events;
import arc.audio.Music;
import jooling.content.blocks.JoolingDistr;
import mindustry.Vars;
import mindustry.game.EventType;
import mindustry.game.EventType.WorldLoadEvent;
import mindustry.gen.Musics;

public class ArktikMusic {

    public static Music
    // Vanilla and Mod
    launch, land, menu, retorn, desolated, lobby;

    public static void load() {
        initializeMusics();
        setupEventHandlers();
    }

    private static void initializeMusics() {
        // Vanilla
        launch = Musics.launch;
        land = Musics.land;
        menu = Musics.menu;

        // Jooling
        retorn = loadMusic("return");
        desolated = loadMusic("desolated");
        lobby = loadMusic("lobby");
    }

    private static void setupEventHandlers(){
        Events.run(EventType.Trigger.update, ArktikMusic::updateLaunchMusic);
        Events.run(EventType.Trigger.update, ArktikMusic::updateMenu);
        Events.on(WorldLoadEvent.class, e -> {
            updateLandMusic();
            updateMenu(); // Keep this for initial check on world load too, though the Trigger.update will handle ongoing.
        });
    }

    private static void updateLaunchMusic(){
        Musics.launch = (Vars.ui.planet.state.planet == PlanetLoaderc.jooling || Vars.ui.planet.state.planet == PlanetLoaderc.aesgo)
            ? retorn
            : launch;
    }

    private static void updateMenu(){
        Musics.menu = (Vars.state.isMenu())
            ? lobby
            : menu;
    }

    private static void updateLandMusic(){
        Vars.state.rules.defaultTeam.cores().each(core ->
            Musics.land = (core.block == JoolingDistr.coreFrame)
                ? desolated
                : land);
    }

    private static Music loadMusic(String name) {
        return Vars.tree.loadMusic(name);
    }
}
