package at.htl.trainingproject.model;

import java.util.List;

import javax.inject.Inject;

import at.htl.trainingproject.model.picture.Picture;
import at.htl.trainingproject.model.post.Post;
import at.htl.trainingproject.model.todo.Todo;

public class Model{
    public static class UIState {
        public int selectedTab = 1;
        public int picturesShown = 0;
    }

    public List<Post> posts = List.of();
    public Todo[] todos = new Todo[0];
    public Picture[] pictures = new Picture[0];
    public UIState uiState = new UIState();

    @Inject
    public Model() {
    }
}
