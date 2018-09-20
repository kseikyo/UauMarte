package TelaPrincipal;

import TelaLogin.ControllerTelaLogin;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import ControllerClass.ControllerStart;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerTelaPrincipal extends Application{
    private ControllerStart controllerStart = new ControllerStart();

    @FXML
    private Tab tab_CadastroProduto;

    @FXML
    private Tab tab_Sair;

    public void start(Stage stage) {
        try {
            controllerStart.initScreen("/FXMLFILES/TelaInicial.fxml","UAUMARTE", controllerStart.getStage());
        } catch(IOException e) { }
    }

    @FXML
    void f_AbrirCadastroProduto(ActionEvent event) {

    }

    @FXML
    void f_AbrirTelaLogin(ActionEvent event)throws IOException {

        ControllerTelaLogin controllerTelaLogin = new ControllerTelaLogin();
        controllerTelaLogin.start(controllerStart.getStage());
    }



}
