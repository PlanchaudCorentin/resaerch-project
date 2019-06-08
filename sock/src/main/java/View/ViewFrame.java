package View;

import Contract.IView;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class ViewFrame extends JFrame {

    private IView view;
    private ViewPanel panel;

    public ViewFrame(IView view, String title) {
        super(title);

        this.view = view;

        this.setResizable(false);
        this.setLocationRelativeTo(null);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(1000, 800);
        this.panel = new ViewPanel();
        this.setContentPane(this.panel);
    }

    public ViewPanel getPanel() {
        return panel;
    }
}
