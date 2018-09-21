package TelaPrincipal;

import CadastroProdutos.TelaCadastro;
import ControllerClass.ControllerStart;
import TelaLogin.ControllerTelaLogin;
import javafx.application.Application;
import javafx.fxml.FXML;
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

    @FXML
    public void f_GoToCadastroP() throws Exception{
        TelaCadastro telaCadastro = new TelaCadastro();
        telaCadastro.start(controllerStart.getStage());
    }

}
