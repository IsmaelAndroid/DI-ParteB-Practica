package es.dam.parteb.ejercicios

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/*
 * ════════════════════════════════════════════════════════════════════
 *  PARTE B · DEPURACIÓN
 *  EJERCICIO 3 · Clash Royale · Constructor de mazos (1,5 + 0,1 extra)
 * ════════════════════════════════════════════════════════════════════
 *
 *  Esta es la pantalla para montar un mazo de Clash Royale. Así tiene
 *  que funcionar:
 *
 *  - Arriba se ve "Mazo: n/8 · Coste medio: m". El coste medio es la
 *    media del elixir de las cartas del mazo, con un decimal (3,5 o 3.5).
 *  - Debajo, en una fila, las cartas que ya están en el mazo. La fila se
 *    puede desplazar de lado. Al pulsar una carta, ESA carta sale del mazo.
 *  - Un campo "Buscar carta" filtra el catálogo por nombre, sin
 *    distinguir mayúsculas de minúsculas ("bruja" encuentra "Bruja").
 *  - El catálogo es una lista. Las cartas legendarias llevan "★" delante.
 *  - "Añadir" mete la carta en el mazo. No se puede pulsar si la carta
 *    ya está en el mazo. Un mazo tiene como MÁXIMO 8 cartas.
 *  - El mazo y la búsqueda NO se pierden al girar el móvil.
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

enum class Rareza { COMUN, ESPECIAL, EPICA, LEGENDARIA }

data class Carta(val nombre: String, val elixir: Int, val rareza: Rareza)

val catalogo = listOf(
    Carta("Caballero", 3, Rareza.COMUN),
    Carta("Esqueletos", 1, Rareza.COMUN),
    Carta("Mosquetera", 4, Rareza.ESPECIAL),
    Carta("Montapuercos", 4, Rareza.ESPECIAL),
    Carta("Bruja", 5, Rareza.EPICA),
    Carta("P.E.K.K.A", 7, Rareza.EPICA),
    Carta("Princesa", 3, Rareza.LEGENDARIA),
    Carta("Mago de Hielo", 3, Rareza.LEGENDARIA),
    Carta("Leñador", 4, Rareza.LEGENDARIA),
    Carta("Gigante", 5, Rareza.ESPECIAL),
)

@Composable
fun FilaCarta(carta: Carta, enMazo: Boolean, onAnadir: () -> Unit) {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Text(
            if (carta.rareza.name == "Legendaria") "★ ${carta.nombre}" else carta.nombre,
            Modifier.weight(1f),
        )
        Text("${carta.elixir} de elixir", Modifier.padding(end = 8.dp))
        Button(onClick = onAnadir, enabled = enMazo) { Text("Añadir") }
    }
}

@Composable
fun ConstructorMazo() {
    var busqueda by rememberSaveable { mutableStateOf("") }
    var mazo by remember { mutableStateOf(listOf<Carta>()) }
    val visibles = catalogo.filter { it.nombre.contains(busqueda) }
    val media = if (mazo.isEmpty()) 0.0 else (mazo.sumOf { it.elixir } / mazo.size).toDouble()

    Column(Modifier.padding(16.dp)) {
        Text("Mazo: ${mazo.size}/8 · Coste medio: ${"%.1f".format(media)}")

        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            mazo.forEach { c ->
                AssistChip(onClick = { mazo = mazo.drop(1) }, label = { Text(c.nombre) })
            }
        }

        OutlinedTextField(
            value = busqueda,
            onValueChange = { busqueda = it },
            label = { Text("Buscar carta") },
            modifier = Modifier.fillMaxWidth(),
        )

        LazyColumn {
            items(visibles) { carta ->
                FilaCarta(
                    carta = carta,
                    enMazo = carta in mazo,
                    onAnadir = { if (mazo.size <= 8) mazo = mazo + carta },
                )
            }
        }
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
