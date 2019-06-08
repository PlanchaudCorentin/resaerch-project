package Controller;

import Contract.IController;
import Contract.IModel;
import Contract.IView;

public class Controller implements IController {

    private IModel model;

    private IView view;

    public Controller(IModel model, IView view) {
        this.model = model;
        this.view = view;
        this.model.getObservable().addPropertyChangeListener(this.view.getFrame().getPanel());
        this.model.startSocket();
    }
}
