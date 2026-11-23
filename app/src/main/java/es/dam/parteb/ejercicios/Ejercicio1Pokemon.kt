package es.dam.parteb.ejercicios

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/*
 * ════════════════════════════════════════════════════════════════════
 *  PARTE B · DEPURACIÓN
 *  EJERCICIO 1 · Pokémon · El Centro Pokémon        (1,5 + 0,1 extra)
 * ════════════════════════════════════════════════════════════════════
 *
 *  Esta es la app del equipo de un entrenador Pokémon. Así tiene que
 *  funcionar:
 *
 *  - Arriba hay un campo "Entrenador". Debajo se ve "Equipo de X" con lo
 *    que se ha escrito. El nombre NO se pierde al girar el móvil.
 *  - Cada Pokémon se ve en una tarjeta con "Nombre · Nv. N", una barra
 *    con la vida que le queda (vida actual / vida máxima) y un botón
 *    "Atacar". La tarjeta acepta un `modifier` desde fuera.
 *  - "Atacar" le quita 15 de vida a ESE Pokémon. La vida nunca baja de 0.
 *  - Con 0 de vida aparece "Debilitado" en el color de error del tema y
 *    su botón "Atacar" no se puede pulsar.
 *  - "Curar todo" devuelve a todos los Pokémon su vida máxima.
 *  - Abajo se ve "Pokémon en pie: N de M".
 *
 *  El código tiene 8 ERRORES. Todos compilan: la app arranca, pero no
 *  hace lo que dice el enunciado. Por cada error: INDICA LA LÍNEA,
 *  EXPLICA QUÉ FALLA y ESCRIBE LA LÍNEA CORREGIDA.
 *  Cada error vale 0,2: si encuentras los 8 sumas 1,6.
 *
 *  Primero respóndelo leyendo, como en el examen. Después ejecuta la
 *  app, corrige el código y comprueba que funciona.
 * ════════════════════════════════════════════════════════════════════
 */

data class Pokemon(val nombre: String, val nivel: Int, val vida: Int, val vidaMax: Int)

val equipoInicial = listOf(
    Pokemon("Pikachu", 25, 60, 60),
    Pokemon("Charmander", 18, 50, 50),
    Pokemon("Snorlax", 30, 120, 120),
)

@Composable
fun TarjetaPokemon(pokemon: Pokemon, onAtacar: () -> Unit, modifier: Modifier = Modifier) {
    val debilitado = pokemon.vida == 0

    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(12.dp)) {
            Text("$pokemon.nombre · Nv. ${pokemon.nivel}")
            LinearProgressIndicator(
                progress = { pokemon.vida.toFloat() },
                modifier = Modifier.fillMaxWidth(),
            )
            if (debilitado) {
                Text("Debilitado", color = Color.Red)
            }
            Button(onClick = { onAtacar }, enabled = !debilitado) { Text("Atacar") }
        }
    }
}

@Composable
fun CentroPokemon() {
    var entrenador = ""
    val equipo = remember { mutableStateListOf<Pokemon>().apply { addAll(equipoInicial) } }
    val enPie = equipo.count { it.vida > 0 }

    Column(Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = entrenador,
            onValueChange = { entrenador = it },
            label = { Text("Entrenador") },
        )
        Text("Equipo de $entrenador")
        Spacer(Modifier.height(8.dp))

        equipo.forEachIndexed { i, p ->
            TarjetaPokemon(p, onAtacar = { equipo[i] = p.copy(vida = p.vida - 15) })
            Spacer(Modifier.height(8.dp))
        }

        Button(onClick = { equipo.forEach { it.copy(vida = it.vidaMax) } }) { Text("Curar todo") }
        Text("Pokémon en pie: $enPie de ${equipo.size}")
    }
}

/*
 * ────────────────────────────────────────────────────────────────────
 *  ✍  TUS RESPUESTAS
 * ────────────────────────────────────────────────────────────────────
 *  Nº | Línea | Qué falla                     | Línea corregida
 *  ───┼───────┼───────────────────────────────┼─────────────────────────
 *  1  |       |                               |
 *  2  |       |                               |
 *  3  |       |                               |
 *  4  |       |                               |
 *  5  |       |                               |
 *  6  |       |                               |
 *  7  |       |                               |
 *  8  |       |                               |
 * ────────────────────────────────────────────────────────────────────
 */
