package at.htl.trainingproject.model;

import at.htl.trainingproject.model.pictures.Picture;
import at.htl.trainingproject.model.todo.Todo;

public class Model {
    public static class UIState {
        public int selectedTab = 1;
        public int picturesShown = 0;
    }

    public Todo[] todos = new Todo[0];
    public Picture[] pictures = new Picture[0];
    public UIState uiState = new UIState();
}
