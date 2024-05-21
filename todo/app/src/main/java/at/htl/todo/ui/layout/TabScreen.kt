package at.htl.todo.ui.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import at.htl.todo.model.Model
import at.htl.todo.model.ModelStore
import at.htl.todo.model.TodoService

@Composable
fun TabScreen(model: Model,store: ModelStore?, toDoService: TodoService?){
    var uiState = model.uiState
    val tabIndex = uiState.selectedTab
    val tabs = listOf("Home", "ToDos")
    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(selectedTabIndex = uiState.selectedTab) {
            tabs.forEachIndexed { index, title ->
                Tab(text = { Text(title) },
                    selected = tabIndex == index,
                    onClick = { store?.selectTab(index)},
                    icon = {
                        when (index) {
                            0 ->Icon(Icons.Filled.Home, contentDescription = "Home")
                            1 -> BadgedBox(badge = {
                                Badge { Text("${model.todos.size}") } }) {
                                Icon(Icons.Filled.Favorite, contentDescription = "ToDos")
                            }
                        }
                    }
                )
            }
        }
        when (tabIndex) {
            0 -> HomeScreen(model, toDoService, store)
            1 -> Todos(model,store)
        }
    }
}
