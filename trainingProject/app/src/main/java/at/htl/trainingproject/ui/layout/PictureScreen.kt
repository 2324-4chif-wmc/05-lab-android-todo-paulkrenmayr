package at.htl.trainingproject.ui.layout

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import at.htl.trainingproject.R
import at.htl.trainingproject.model.Model
import at.htl.trainingproject.model.ModelStore
import at.htl.trainingproject.model.pictures.Picture
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun PictureScreen(model: Model, store: ModelStore?){
    val pictures = model.pictures

    if(pictures.isEmpty()){
        LazyColumn (modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)){
            item {
                Text("No Pictures loaded yet")
            }
        }
    } else if(model.uiState.picturesShown == 0) {
        var textFieldValue by remember { mutableStateOf(model.uiState.picturesShown.toString()) }
        LazyColumn (modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)){
            item {
                Row {
                    TextField(
                        value = textFieldValue,
                        onValueChange = { newValue ->
                            textFieldValue = newValue
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    Button(onClick = {
                        store?.setPicturesShown(textFieldValue.toIntOrNull() ?: 0)
                    }) {
                        Text("Submit")
                    }
                }
            }
        }
    } else {
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
        ) {
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(onClick = {
                        store?.setPicturesShown(0)
                    }) {
                        Text("Reenter number of pictures")
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
            items(model.uiState.picturesShown) { index ->
                PictureRow(picture = pictures[index])
                HorizontalDivider()
            }
        }
    }
}

@Composable
fun PictureRow(picture: Picture) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = picture.title,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = picture.id.toString(),
            style = MaterialTheme.typography.bodySmall
        )
        Spacer(modifier = Modifier.width(8.dp))
    }
    Row(
        modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ImageFromUrl(picture.thumbnailUrl)
    }
}
@Composable
fun ImageFromUrl(url: String) {
    AsyncImage(
        model = ImageRequest.Builder(context = LocalContext.current)
            .data(url)
            .build(),
        contentDescription = stringResource(R.string.app_name),
        modifier = Modifier.fillMaxWidth()
    )
}

