package TelaPrincipal;

import ControllerClass.ControllerStart;
import TelaLogin.ControllerTelaLogin;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.stage.Stage;

import java.io.IOException;


public class ControllerTelaPrincipal extends Application {
    private ControllerStart controllerStart = new ControllerStart();

    public void start(Stage stage)throws IOException {
        controllerStart.initScreen("/FXMLFILES/TelaInicial.fxml", "UAUMARTE", controllerStart.getStage());
    }
    
    @FXML
    public void f_GoToLogin() {
        ControllerTelaLogin controllerTelaLogin = new ControllerTelaLogin();
        controllerTelaLogin.start(controllerStart.getStage());
    }

}
