package jooling;

import static arc.Core.app;
import arc.Events;
import jooling.content.ArktikMusic;
import jooling.content.BlockLoaderc;
import jooling.content.ItemsLoaderc;
import jooling.content.PlanetLoaderc;
import jooling.content.SoundLoaderc;
import jooling.content.UnitLoaderc;
import jooling.graphics.JooShaders;
import jooling.maps.planets.AsteroidsLoadercs;
import jooling.ui.overrideLoaderc;
import mindustry.game.EventType;
import mindustry.mod.Mod;
import mindustry.type.Planet;

// Importa la clase JoolingUI
import jooling.core.JoolingUI;

public class Arktik extends Mod{

    // Declara una instancia de JoolingUI
    public JoolingUI joolingUI;

    public Arktik(){
        super();

        // Inicializa JoolingUI cuando el mod se crea.
        // Su constructor registrará el evento ClientLoadEvent para mostrar la StartSplash.
        joolingUI = new JoolingUI();

        Events.on(EventType.FileTreeInitEvent.class, e ->
            app.post(JooShaders::load)
        );
        Events.on(EventType.MusicRegisterEvent.class, e ->
            ArktikMusic.load()
        );
        Events.on(EventType.DisposeEvent.class, e ->
            JooShaders.dispose()
        );
    }
    
    @Override
    public void loadContent(){
        // Recordatorio... Poner load(); al final.
        ItemsLoaderc.load();
        SoundLoaderc.load();
        UnitLoaderc.load();
        BlockLoaderc.load();
        PlanetLoaderc.load(); 
        AsteroidsLoadercs.load();
        // No hay necesidad de llamar a joolingUI.loadContent() aquí,
        // ya que la lógica de la StartSplash se maneja con ClientLoadEvent.
    }

    @Override
    public void init(){
        // Llama al método init de overrideLoaderc.
        // La inicialización de JoolingUI ya se maneja en el constructor y a través de eventos.
        overrideLoaderc.init();
    }

    public static void resetSaves(Planet planet) {
        planet.sectors.each(sector -> {
            if (sector.hasSave()) {
                sector.save.delete();
                sector.save = null;
            }
        });
    }
}