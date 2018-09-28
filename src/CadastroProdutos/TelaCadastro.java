/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CadastroProdutos;
import ControllerClass.ControllerStart;
import TelaPrincipal.ControllerTelaPrincipal;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


/**
 *
 * @author soart
 */
public class TelaCadastro extends Application {
    private ControllerStart controllerStart = new ControllerStart();
    /*public Scene getScene() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/FXMLFILES/FXML_TelaCadastro.fxml"));
        Scene scene = new Scene(root);
        return scene;
    }*/

    public void start(Stage stage) throws IOException {
        controllerStart.initScreen("/FXMLFILES/FXML_TelaCadastro.fxml", "UauMarte", controllerStart.getStage());
    }

    
}
