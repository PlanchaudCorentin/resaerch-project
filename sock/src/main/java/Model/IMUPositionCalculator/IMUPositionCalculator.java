package Model.IMUPositionCalculator;

import Contract.MyObservable;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


public class IMUPositionCalculator implements MyObservable {

    private int direction;
    private int highestValue;
    private Location pLocation;
    private Location location;

    private PropertyChangeSupport changes = new PropertyChangeSupport(this);

    public IMUPositionCalculator() {
        this.highestValue = 0;
        this.location = new Location(500, 400);
        this.pLocation = new Location(500, 400);
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        this.changes.addPropertyChangeListener(l);
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void move(int accelerationy) {
        if (Math.abs(accelerationy) >= this.highestValue){
            this.highestValue = Math.abs(accelerationy);
        }
        if (accelerationy <= -2) {

            if(direction > 337.5 || direction <= 22.5){
                this.location.move(0, -1);
                changes.firePropertyChange("location", this.pLocation, this.location);

            } else if (direction <= 67.5) {
                this.location.move(1, -1);
                changes.firePropertyChange("location", this.pLocation, this.location);

            } else if (direction <= 112.5) {
                this.location.move(1, 0);
                changes.firePropertyChange("location", this.pLocation, this.location);

            } else if (direction <= 157.5) {
                this.location.move(1, 1);
                changes.firePropertyChange("location", this.pLocation, this.location);

            } else if (direction <= 202.5) {
                this.location.move(0, 1);
                changes.firePropertyChange("location", this.pLocation, this.location);

            } else if (direction <= 247.5) {
                this.location.move(-1, 1);
                changes.firePropertyChange("location", this.pLocation, this.location);

            } else if (direction <= 292.5) {
                this.location.move(-1, 0);
                changes.firePropertyChange("location", this.pLocation, this.location);

            } else {
                this.location.move(-1, -1);
                changes.firePropertyChange("location", this.pLocation, this.location);
            }
            this.pLocation.setY(this.location.getY());
            this.pLocation.setX(this.location.getX());

        }
    }
}
