package at.htl.trainingproject.ui.layout

import android.R
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import at.htl.trainingproject.model.Model
import at.htl.trainingproject.model.ModelStore
import at.htl.trainingproject.model.post.Post
import at.htl.trainingproject.model.post.PostDto
import at.htl.trainingproject.model.post.PostService

@Composable
fun PostScreen(model: Model, store: ModelStore?,postService: PostService?){
    val  posts = model.posts

    if(posts.isEmpty()){
        Text("No Posts loaded yet")
    } else {
        var title by remember { mutableStateOf("") }
        var body by remember { mutableStateOf("") }
        LazyColumn (modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)){
            item{
                TextField(
                    value = title,
                    onValueChange ={
                    newValue -> title = newValue
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    modifier = Modifier.padding(horizontal = 8.dp),
                    placeholder = { Text("Title") }
                )
                TextField(
                    value = body,
                    onValueChange = {
                        newValue -> body = newValue
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    modifier = Modifier.padding(horizontal = 8.dp),
                    placeholder = { Text(text = "Body") }
                )
                Button(onClick = {
                    postService?.create(PostDto(1, title, body),posts.size)
                }) {
                    Text(text = "Submit")
                }

            }
            items(posts.size){
                val post = posts[it]
                PostRow(post = post)
                HorizontalDivider()
            }
        }
    }
}

@Composable
fun PostRow(post: Post){
    Row (
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = post.title,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.weight(1f)
        )
    }
    Row (
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp), verticalAlignment = Alignment.CenterVertically
    )
    {
        Text(text = post.body,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.weight(1f)
        )
    }
}