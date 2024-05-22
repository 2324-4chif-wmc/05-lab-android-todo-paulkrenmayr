package at.htl.trainingproject.model.post;

import android.util.Log;

import java.util.concurrent.CompletableFuture;

import javax.inject.Inject;

import at.htl.trainingproject.model.Model;
import at.htl.trainingproject.model.ModelStore;

import at.htl.trainingproject.model.picture.PictureService;
import at.htl.trainingproject.util.resteasy.RestApiClientBuilder;

public class PostService {
    static final String TAG = PictureService.class.getSimpleName();
    public static String JSON_PLACEHOLDER_BASE_URL = "https://jsonplaceholder.typicode.com";
    public final PostClient postClient;
    public final ModelStore store;
    public final Model model;
    public static int id = 100;

    @Inject
    PostService(RestApiClientBuilder builder, ModelStore store, Model model) {
        Log.i(TAG, "Creating PictureService with base url: " + JSON_PLACEHOLDER_BASE_URL);
        postClient = builder.build(PostClient.class, JSON_PLACEHOLDER_BASE_URL);
        this.store = store;
        this.model = model;
    }

    public void getAll() {
        CompletableFuture
                .supplyAsync(() -> postClient.all())
                .thenAccept(store::setPosts)
                .exceptionally((e) -> {
                    Log.e(TAG, "Error loading posts", e);
                    return null;
                });
    }

    public void create(PostDto post,int id){
        Post postTmp = new Post();
        postTmp.id = id+1;
        postTmp.userId = post.userId();
        postTmp.title = post.title();
        postTmp.body = post.body();
        store.addPost(postTmp);


        CompletableFuture
                .supplyAsync(()-> postClient.create(post))
                //Pseudo post auf die Json placeholder website
                //.thenAccept(store::addPost)
                .exceptionally((e) -> {
                    Log.e(TAG, "Error posting new Post", e);
                    return null;
                });
    }

}
