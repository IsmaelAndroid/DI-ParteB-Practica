package es.dam.parteb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import es.dam.parteb.ejercicios.CentroPokemon
import es.dam.parteb.ejercicios.ConstructorMazo
import es.dam.parteb.ejercicios.ReunionEmergencia

/*
 * Menú para ejecutar cada ejercicio.
 * 1. Busca los 8 errores leyendo el código (como en el examen).
 * 2. Ejecuta el ejercicio y comprueba qué falla.
 * 3. Corrige el código y vuelve a ejecutarlo.
 * Los enunciados están en el paquete `ejercicios`.
 */

private val ejercicios: List<Pair<String, @Composable () -> Unit>> = listOf(
    "1 · Pokémon · El Centro Pokémon" to { CentroPokemon() },
    "2 · Among Us · Reunión de emergencia" to { ReunionEmergencia() },
    "3 · Clash Royale · Constructor de mazos" to { ConstructorMazo() },
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                AppPracticas()
            }
        }
    }
}

@Composable
fun AppPracticas() {
    var actual by rememberSaveable { mutableIntStateOf(-1) }

    Surface(Modifier.fillMaxSize()) {
        Column(Modifier.safeDrawingPadding()) {
            if (actual == -1) {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Parte B · Depuración", style = MaterialTheme.typography.headlineSmall)
                    Text("Busca los 8 errores leyendo el código. Después ejecuta, corrige y comprueba.")
                    ejercicios.forEachIndexed { i, (titulo, _) ->
                        FilledTonalButton(onClick = { actual = i }, Modifier.fillMaxWidth()) { Text(titulo) }
                    }
                }
            } else {
                BackHandler { actual = -1 }
                TextButton(onClick = { actual = -1 }) { Text("← Volver") }
                ejercicios[actual].second()
            }
        }
    }
}
