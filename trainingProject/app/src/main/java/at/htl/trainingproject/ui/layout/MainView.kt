package at.htl.trainingproject.ui.layout

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.rxjava3.subscribeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView

import at.htl.trainingproject.model.Model
import at.htl.trainingproject.model.ModelStore
import at.htl.trainingproject.model.picture.PictureService
import at.htl.trainingproject.model.post.PostService
import at.htl.trainingproject.model.todo.TodoService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import javax.inject.Inject
import javax.inject.Singleton

/** Our View implemented in kotlin.
 * Application Logic is separated from this view.
 * Our State is delivered exclusively from our {@link Store}, which we subscribe here.
 */
@Singleton
class MainView @Inject constructor(){
    @Inject
    lateinit var store: ModelStore
    @Inject
    lateinit var toDoService: TodoService
    @Inject
    lateinit var pictureService: PictureService
    @Inject
    lateinit var postService: PostService

    fun buildContent(activity: ComponentActivity) {
        val view = ComposeView(activity)
        view.setContent {
            val viewModel = store.pipe.observeOn(AndroidSchedulers.mainThread()).subscribeAsState(initial = Model()).value
            Surface(
                modifier = Modifier.fillMaxSize()
            ) {
                TabScreen(viewModel, store, toDoService,pictureService,postService)
            }
        }
        activity.setContentView(view)
    }
}


