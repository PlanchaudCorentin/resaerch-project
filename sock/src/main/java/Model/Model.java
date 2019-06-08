package Model;

import Contract.IModel;
import Contract.MyObservable;
import Model.IMUPositionCalculator.IMUPositionCalculator;
import Model.SocketConnector.SocketConnector;

import java.net.URISyntaxException;

public class Model implements IModel {

    private SocketConnector connector;
    private IMUPositionCalculator calculator;

    public Model() {
        this.calculator = new IMUPositionCalculator();

        try {
            this.connector = new SocketConnector();
            this.connector.setCalculator(calculator);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }

    public MyObservable getObservable() {
        return calculator;
    }

    public void startSocket() {
        this.connector.initSocket();
    }
}
