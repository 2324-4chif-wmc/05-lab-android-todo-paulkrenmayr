package at.htl.trainingproject.ui.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import at.htl.trainingproject.model.Model
import at.htl.trainingproject.model.ModelStore
import at.htl.trainingproject.model.picture.PictureService
import at.htl.trainingproject.model.post.PostService
import at.htl.trainingproject.model.todo.TodoService

@Composable
fun HomeScreen(model: Model, toDoService: TodoService?, pictureService: PictureService?,postService: PostService?, store: ModelStore?) {
    val todos = model.todos;
    val pictures = model.pictures;
    val posts = model.posts;
    Column (modifier = Modifier.fillMaxSize()){
        Text(
            text = "Welcome Home!",
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Row(modifier = Modifier.weight(1f)) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
            ) {
                Text(
                    "${pictures.size} Pictures have been loaded",
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Button(onClick = {
                    pictureService?.getAll()
                }, modifier = Modifier.align(Alignment.CenterHorizontally)) {
                    Text("Load Pictures")
                }
                Button(onClick = {
                    store?.setPictures(arrayOf())
                }, modifier = Modifier.align(Alignment.CenterHorizontally)) {
                    Text("Clear Pictures")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    "${posts.size} Posts have been loaded",
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Button(onClick = {
                    postService?.getAll()
                }, modifier = Modifier.align(Alignment.CenterHorizontally)) {
                    Text("Load Posts")
                }
                Button(onClick = {
                    store?.setPosts(listOf())
                }, modifier = Modifier.align(Alignment.CenterHorizontally)) {
                    Text("Clear Posts")
                }
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
            ) {
                Text(
                    "${todos.size} Todos have been loaded",
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Button(onClick = {
                    toDoService?.getAll()
                }, modifier = Modifier.align(Alignment.CenterHorizontally)) {
                    Text("Load Todos")
                }
                Button(onClick = {
                    store?.setTodos(arrayOf())
                }, modifier = Modifier.align(Alignment.CenterHorizontally)) {
                    Text("Clear Todos")
                }
            }
        }
    }
}