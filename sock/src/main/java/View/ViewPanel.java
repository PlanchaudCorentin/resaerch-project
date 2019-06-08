package View;

import Contract.ILocation;

import javax.swing.JPanel;
import java.awt.Graphics2D;
import java.awt.Graphics;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class ViewPanel extends JPanel implements PropertyChangeListener {

    ArrayList<Point> path;

    public ViewPanel(){
        this.setSize(1000, 800);
        this.path = new ArrayList<Point>();
        this.path.add(new Point(this.getSize().width / 2, Math.round(this.getSize().height / 2)));

    }


    public void propertyChange(PropertyChangeEvent e) {
        ILocation l = (ILocation) e.getNewValue();
        this.path.add(new Point(l.getX(), l.getY()));
        this.repaint();
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        for(Point p: path) {
            g2d.fillOval(p.getX(), p.getY(), 10, 10);
        }

    }

}
