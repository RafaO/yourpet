import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun PetsListError(message: String, onRetry: () -> Unit) {
    Column {
        Text(message)
        Button(onClick = onRetry) {
            Text("Retry")
        }
    }
}
