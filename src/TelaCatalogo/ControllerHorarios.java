package TelaCatalogo;

import ControllerClass.ControllerStart;
import TelaPrincipal.ControllerTelaPrincipal;
import com.jfoenix.controls.JFXButton;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;

public class ControllerHorarios extends Application {

    @FXML
    private AnchorPane ap_Principal;

    public ControllerHorarios() {

    }

    public void start(Stage stage) throws IOException {
        /*URL menuBarUrl = null;
        try {
            menuBarUrl = getClass().getResource("/FXMLFILES/menus.fxml");
        } catch(NullPointerException e) {
            e.getMessage();
            e.printStackTrace();
        }
        MenuBar bar = FXMLLoader.load( menuBarUrl );

        URL paneOneUrl = getClass().getResource("/FXMLFILES/TelaHorario.fxml");
        ap_Principal = FXMLLoader.load( paneOneUrl );


        // constructing our scene using the static root

        ControllerTelaPrincipal.getRoot().setTop(bar);
        ControllerTelaPrincipal.getRoot().setCenter(ap_Principal);
        if(ControllerTelaPrincipal.root.getScene() == null) {
            Scene scene = new Scene(ControllerTelaPrincipal.getRoot());
            stage.setScene(scene);
            stage.show();
        }else {
            stage.setScene(ControllerTelaPrincipal.root.getScene());
            stage.show();
        }*/

        ControllerStart c = new ControllerStart();
        //c.initMenu("/FXMLFILES/TelaHorario.fxml");
        c.initScreen("/FXMLFILES/TelaHorario.fxml", "Finalizando", ControllerStart.stage);
    }

    @FXML
    private void f_cancelar(ActionEvent event) throws IOException{
        ControllerTelaCatalogo c = new ControllerTelaCatalogo();
        c.start(ControllerStart.stage);
    }

    @FXML
    private void f_finalizar(ActionEvent event) throws IOException{
        ControllerFinalizar c = new ControllerFinalizar();
        c.start(ControllerStart.stage);
    }
}
