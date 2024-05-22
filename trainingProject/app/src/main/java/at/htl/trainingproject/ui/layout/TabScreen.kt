package at.htl.trainingproject.ui.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import at.htl.trainingproject.model.Model
import at.htl.trainingproject.model.ModelStore
import at.htl.trainingproject.model.picture.PictureService
import at.htl.trainingproject.model.post.PostService
import at.htl.trainingproject.model.todo.TodoService

@Composable
fun TabScreen(model: Model, store: ModelStore?, toDoService: TodoService?, pictureService: PictureService?, postService: PostService?){
    var uiState = model.uiState
    val tabIndex = uiState.selectedTab
    val tabs = listOf("Pictures", "Home", "ToDos", "Posts")
    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(selectedTabIndex = uiState.selectedTab) {
            tabs.forEachIndexed { index, title ->
                Tab(text = { Text(title) },
                    selected = tabIndex == index,
                    onClick = { store?.selectTab(index)},
                    icon = {
                        when (index) {
                            0 -> BadgedBox(badge = {
                                Badge { Text("${model.pictures.size}") } }) {
                                Icon(Icons.Filled.Place, contentDescription = "Pictures")
                            }
                            1 ->Icon(Icons.Filled.Home, contentDescription = "Home")
                            2 -> BadgedBox(badge = {
                                Badge { Text("${model.todos.size}") } }) {
                                Icon(Icons.Filled.Favorite, contentDescription = "ToDos")
                            }
                            3 -> BadgedBox(badge = {
                                Badge { Text("${model.posts.size}") } }) {
                                Icon(Icons.Filled.MailOutline, contentDescription = "Posts")
                            }
                        }
                    }
                )
            }
        }
        when (tabIndex) {
            1 -> HomeScreen(model, toDoService, pictureService, postService, store)
            2 -> TodoScreen(model,store)
            0 -> PictureScreen(model,store)
            3 -> PostScreen(model,store,postService)
        }
    }
}
