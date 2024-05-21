package at.htl.todo.ui.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import at.htl.todo.model.Model
import at.htl.todo.model.ModelStore
import at.htl.todo.model.TodoService

@Composable
fun HomeScreen(model: Model, toDoService: TodoService?, store: ModelStore?) {
    val todos = model.todos;
    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .padding(16.dp)) {
            Text(
                text = "Welcome Home!",
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Row(Modifier.align(Alignment.CenterHorizontally)) {
            Text("${todos.size} Todos have been loaded")
        }
    }
}