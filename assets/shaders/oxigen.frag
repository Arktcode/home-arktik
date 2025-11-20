#define HIGHP

// Colores personalizados para el agua, adaptados al nuevo color base
#define O1 vec3(0.533, 0.565, 0.639)  // Color base adaptado (#8890a3)
#define O2 vec3(0.75, 0.75, 0.75)     // Olas claras adaptadas
#define O3 vec3(0.55, 0.62, 0.65)     // Color intermedio adaptado

#define NSCALE 100.0 / 2.0

uniform sampler2D u_texture;  // Textura del suelo
uniform sampler2D u_noise;    // Textura de ruido para animaciones

uniform vec2 u_campos;  // Posición de la cámara
uniform vec2 u_resolution;  // Resolución de la pantalla
uniform float u_time;  // Tiempo para animación

varying vec2 v_texCoords;

void main(){
    vec2 c = v_texCoords.xy;
    vec2 coords = vec2(c.x * u_resolution.x + u_campos.x, c.y * u_resolution.y + u_campos.y);

    float btime = u_time / 5000.0;

    // Ondas suaves con la animación y el movimiento de las coordenadas + variación aleatoria
    float randWave = sin(coords.x * 1.1 + coords.y + sin(u_time * 0.1)) * 0.05;  // Introduce aleatoriedad
    float wave = abs(sin(coords.x * 1.1 + coords.y) + 
                     0.12 * sin(2.5 * coords.x) + 
                     0.18 * sin(3.0 * coords.y) + randWave) / 30.0;

    // Añadir el ruido para la animación de la superficie líquida (nuevo patrón)
    float noise = wave + (texture2D(u_noise, (coords) / NSCALE + vec2(btime) * vec2(0.3, 0.6)).r + 
                          texture2D(u_noise, (coords) / NSCALE + vec2(btime * 1.3) * vec2(1.2, -1.3)).r) / 2.0;

    vec4 color = texture2D(u_texture, c);

    // Balancear los tres colores según el valor de noise
    if (noise > 0.60 && noise < 0.75){
        color.rgb = O2;  // Olas blancas adaptadas
    } else if (noise > 0.50 && noise <= 0.60) {
        color.rgb = O3;  // Color intermedio adaptado
    } else if (noise > 0.45 && noise <= 0.50){
        color.rgb = O1;  // Color base adaptado (#8890a3)
    }

    gl_FragColor = color;
}
