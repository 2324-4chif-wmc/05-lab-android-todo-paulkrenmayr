package at.htl.trainingproject.model;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import at.htl.trainingproject.model.picture.Picture;
import at.htl.trainingproject.model.post.Post;
import at.htl.trainingproject.model.todo.Todo;
import at.htl.trainingproject.util.store.Store;

@Singleton
public class ModelStore extends Store<Model> {

    @Inject
    ModelStore() {
        super(Model.class, new Model());
    }

    public void setTodos(Todo[] todos) {
        apply(model -> model.todos = todos);
    }
    public void setPictures(Picture[] pictures) {
        apply(model -> model.pictures = pictures);
    }
    public void setPicturesShown(int picturesShown) {
        apply(model -> model.uiState.picturesShown = picturesShown);
    }
    public void setPosts(List<Post> posts) {
        apply(model -> model.posts = posts);
    }
    public void addPost(Post post) {
        apply(model -> {
            model.posts.add(post);
        });
    }
    public void selectTab(int tabIndex) {
        apply(model -> model.uiState.selectedTab = tabIndex);
    }
    public void setTodoDone(Todo todo, boolean done){
        apply(model -> model.todos[todo.id.intValue()-1].completed = done);
    }
}
