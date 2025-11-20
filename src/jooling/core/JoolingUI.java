package jooling.core; // Paquete actualizado de 'omaloon.core' a 'jooling.core'

import arc.*; // Necesario para 'ApplicationListener', 'Events', 'Core'
import mindustry.Vars; // Necesario para 'Vars.ui.menuGroup'
import mindustry.game.EventType; // Necesario para 'EventType.ClientLoadEvent'
import jooling.ui.StartAUI; // Importa la clase StartSplash desde su paquete actualizado

/**
 * Clase principal de la interfaz de usuario para el mod Jooling,
 * ahora simplificada para solo gestionar la StartSplash.
 */
public class JoolingUI implements ApplicationListener{ // Clase renombrada de 'OlUI' a 'JoolingUI'
    // Se han eliminado todas las declaraciones de fragments y dialogs
    // que no son necesarios para StartSplash.

    public JoolingUI() {
        // Se registra un manejador de eventos para el evento ClientLoadEvent.
        // Cuando el cliente se carga, se llamará al método onClient().
        Events.on(EventType.ClientLoadEvent.class, it -> onClient());
    }

    @Override
    public void init(){
        // El contenido original de este método (relacionado con los 'OlControl' y settings)
        // no es directamente necesario para que la StartSplash se construya y se muestre.
        // Se deja como un método vacío ya que 'ApplicationListener' requiere su implementación.
    }

    @Override
    public void update() {
        // Implementación vacía, ya que esta clase no requiere lógica de actualización constante.
    }

    @Override
    public void pause() {
        // Implementación vacía.
    }

    @Override
    public void resume() {
        // Implementación vacía.
    }

    @Override
    public void dispose() {
        // Implementación vacía.
    }


    /**
     * Este método se llama cuando el cliente de Mindustry ha terminado de cargar.
     * Aquí es donde se construye y se muestra la pantalla de inicio.
     */
    protected void onClient(){
        // Construye la pantalla de inicio y la añade al grupo de menú de la UI de Mindustry.
        StartAUI.build(Vars.ui.menuGroup);
        // Muestra la pantalla de inicio, lo que inicia sus animaciones y lógica post-animación.
        StartAUI.show();

        // Se ha eliminado la condición 'if (Vars.mobile) return;'
        // para asegurar que la StartSplash se muestre en todas las plataformas.

        // Se han eliminado todas las instanciaciones y llamadas a '.build()'
        // de otros fragments y dialogs que no son StartSplash.
    }
}
