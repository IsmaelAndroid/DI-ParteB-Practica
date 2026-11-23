# DI · 2.º DAM · Práctica de la Parte B (depuración)

Tres ejercicios con el **mismo formato que la Parte B del examen práctico**: tienes el enunciado de una app y su código, que tiene **8 errores**. Por cada error:

1. **Indica la línea.**
2. **Explica qué falla.**
3. **Escribe la línea corregida.**

Cada error vale 0,2: si encuentras los 8 sumas 1,6. La parte vale 1,5 y el 0,1 que sobra es un punto extra.

## Ejercicios

Cada ejercicio es un fichero `.kt` en `app/src/main/java/es/dam/parteb/ejercicios/`:

| Fichero | Temática | La app |
|---|---|---|
| `Ejercicio1Pokemon.kt` | Pokémon | El Centro Pokémon: equipo, ataques y curación |
| `Ejercicio2AmongUs.kt` | Among Us | Reunión de emergencia: votación para expulsar a un tripulante |
| `Ejercicio3ClashRoyale.kt` | Clash Royale | Constructor de mazos: búsqueda, coste medio y límite de 8 cartas |

En cada fichero tienes:

1. **Arriba**, el enunciado: cómo **tiene que** funcionar la app.
2. **En medio**, el código con los 8 errores.
3. **Abajo**, la tabla `✍ TUS RESPUESTAS` (número de error, línea, qué falla y línea corregida).

Las líneas son las que numera Android Studio a la izquierda del editor.

## Cómo trabajar

1. Clona el repositorio y ábrelo en Android Studio (**File → Open**).
2. **Primero busca los errores sin ejecutar nada**, como en el examen, que es en papel. No te fíes de los avisos amarillos del editor: en el examen no los tendrás.
3. Después ejecuta la app (hay un menú con los tres ejercicios) y comprueba qué falla de verdad.
4. Corrige el código y vuelve a ejecutarlo hasta que la app haga lo que dice el enunciado.

> Aquí **todos los errores compilan** para que la app se pueda ejecutar. En el examen también puede haber errores que **no compilan**.

## Requisitos

- Android Studio con el SDK 37
- Gradle 9.6 (va incluido en el wrapper)
- `minSdk` 24
