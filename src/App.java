import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
//import javafx.scene.image.Image;
import javafx.stage.Stage;
import ControllerClass.ControllerStart;

/**
 * @author Lucas
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        ControllerStart controllerStart = new ControllerStart();

        controllerStart.initScreen("/FXMLFILES/TelaLogin.fxml", "Uau!Marte", stage);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
