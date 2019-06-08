package Model.SocketConnector;

import Model.IMUPositionCalculator.IMUPositionCalculator;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

public class SocketConnector {

    private Socket socket;
    private IMUPositionCalculator calculator;

    public SocketConnector() throws URISyntaxException {
        this.socket = IO.socket("http://localhost:3002");
    }

    public void initSocket() {
        this.socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

            public void call(Object... args) {
                socket.emit("set-nickname", "hi");
            }

        }).on("direction", new Emitter.Listener() {

            public void call(Object... args) {
                JSONObject obj = (JSONObject)args[0];
                try {
                    calculator.setDirection(obj.getInt("alpha"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }).on("movement", new Emitter.Listener() {

            public void call(Object... args) {
                JSONObject obj = (JSONObject)args[0];
                try {
                    if (obj.getInt("y") != 0) {
                        calculator.move(obj.getInt("y"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {


            public void call(Object... args) {}

        });
        socket.connect();
    }

    public void disconnectSocket() {
        this.socket.disconnect();
    }

    public void setCalculator(IMUPositionCalculator calculator) {
        this.calculator = calculator;
    }
}
