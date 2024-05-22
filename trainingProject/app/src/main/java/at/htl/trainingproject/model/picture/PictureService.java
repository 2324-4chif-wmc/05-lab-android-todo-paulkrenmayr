package at.htl.trainingproject.model.picture;

import android.util.Log;

import java.util.concurrent.CompletableFuture;

import javax.inject.Inject;

import at.htl.trainingproject.model.ModelStore;
import at.htl.trainingproject.model.todo.TodoService;
import at.htl.trainingproject.util.resteasy.RestApiClientBuilder;

public class PictureService {

    static final String TAG = PictureService.class.getSimpleName();
    public static String JSON_PLACEHOLDER_BASE_URL = "https://jsonplaceholder.typicode.com";
    public final PictureClient pictureClient;
    public final ModelStore store;

    @Inject
    PictureService(RestApiClientBuilder builder, ModelStore store) {
        Log.i(TAG, "Creating PictureService with base url: " + JSON_PLACEHOLDER_BASE_URL);
        pictureClient = builder.build(PictureClient.class, JSON_PLACEHOLDER_BASE_URL);
        this.store = store;
    }

    public void getAll(){
        CompletableFuture
                .supplyAsync(() -> pictureClient.all())
                .thenAccept(store::setPictures)
                .exceptionally((e) -> {
                    Log.e(TAG, "Error loading todos", e);
                    return null;
                });
    }

}
