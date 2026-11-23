package es.dam.parteb.ejercicios

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/*
 * ════════════════════════════════════════════════════════════════════
 *  PARTE B · DEPURACIÓN
 *  EJERCICIO 2 · Among Us · Reunión de emergencia   (1,5 + 0,1 extra)
 * ════════════════════════════════════════════════════════════════════
 *
 *  Esta es la pantalla de votación de una reunión de emergencia. Así
 *  tiene que funcionar:
 *
 *  - Dentro de un Scaffold, con la barra superior "¿Quién es el
 *    impostor?". El contenido empieza DEBAJO de la barra.
 *  - Salen TODOS los tripulantes, cada uno con su nombre en su color.
 *    Se elige UNO SOLO para expulsar. Los muertos aparecen en la lista,
 *    pero no se pueden elegir.
 *  - El botón "Saltar voto" también cuenta como elección.
 *  - Debajo se ve "Tu voto: X", o "Tu voto: ninguno" si no hay elección.
 *    La elección NO se pierde al girar el móvil.
 *  - Un campo "Acusación" de hasta 40 caracteres, con el contador "n/40".
 *    Lo escrito se guarda tal cual, con sus espacios.
 *  - "Votar" solo se puede pulsar si se ha elegido algo. Al pulsarlo se
 *    ve el resultado ("X ha sido expulsado." o "Nadie ha sido
 *    expulsado.") y se vacían la elección y la acusación.
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

data class Tripulante(val nombre: String, val color: Color, val vivo: Boolean = true)

val tripulacion = listOf(
    Tripulante("Rojo", Color(0xFFC51111)),
    Tripulante("Azul", Color(0xFF132ED1)),
    Tripulante("Verde", Color(0xFF117F2D), vivo = false),
    Tripulante("Rosa", Color(0xFFED54BA)),
)

const val SALTAR = "Saltar voto"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReunionEmergencia() {
    var voto by remember { mutableStateOf<String?>(null) }
    var acusacion by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }

    Scaffold(topBar = { TopAppBar(title = { Text("¿Quién es el impostor?") }) }) { padding ->
        Column(Modifier.padding(16.dp).padding(bottom = padding.calculateBottomPadding())) {
            tripulacion.filter { it.vivo }.forEach { t ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = voto == t.nombre,
                        onCheckedChange = { voto = t.nombre },
                    )
                    Text(t.nombre, color = t.color)
                }
            }
            TextButton(onClick = { voto = SALTAR }) { Text(SALTAR) }
            Text("Tu voto: $voto")

            OutlinedTextField(
                value = acusacion,
                onValueChange = { if (it.length <= 40) acusacion = it.trim() },
                label = { Text("Acusación") },
                supportingText = { Text("${acusacion.length}/40") },
                modifier = Modifier.fillMaxWidth(),
            )

            Button(
                onClick = {
                    resultado = if (voto == SALTAR) "Nadie ha sido expulsado." else "$voto ha sido expulsado."
                    voto = null
                },
                enabled = voto == null,
            ) { Text("Votar") }
            Text(resultado)
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
