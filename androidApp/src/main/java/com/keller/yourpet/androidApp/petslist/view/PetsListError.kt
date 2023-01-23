import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
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
