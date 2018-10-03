import TelaCatalogo.ControllerTelaCatalogo;
import javafx.application.Application;
import javafx.stage.Stage;
import ControllerClass.ControllerStart;

/**
 * @author Lucas
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        ControllerStart controllerStart = new ControllerStart();

        controllerStart.initScreen("/FXMLFILES/TelaInicial.fxml", "Uau!Marte", stage);

    }

    public static void main(String[] args) {
        launch(args);
    }

}
