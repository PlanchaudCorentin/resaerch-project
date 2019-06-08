import Controller.Controller;
import Model.Model;
import View.View;

public abstract class Main {
    public static void main(String[] args) {
        final Model model = new Model();
        final View view = new View();
        final Controller controller = new Controller(model, view);
        view.setController(controller);
    }
}
