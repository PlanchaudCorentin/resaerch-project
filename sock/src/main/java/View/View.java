package View;

import Contract.IController;
import Contract.IView;

import javax.swing.SwingUtilities;

public class View implements IView, Runnable {

    private IController controller;
    private ViewFrame frame;

    public View(){
        this.frame = new ViewFrame(this, "Socket Client");
        SwingUtilities.invokeLater(this);
    }

    public void setController(IController controller) {
        this.controller = controller;
    }

    public void run() {
        this.frame.setVisible(true);
    }

    public ViewFrame getFrame(){
        return this.frame;
    }
}
