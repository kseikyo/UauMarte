package TelaCatalogo;

import ControllerClass.ControllerStart;
import DAO.EmpresaDAO;
import TelaPrincipal.ControllerTelaPrincipal;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerHorarios extends Application implements Initializable {

    @FXML
    private AnchorPane ap_Principal;

    @FXML
    private JFXComboBox<String> comboBox;

    private ComboBox<String> cb;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboBox.setItems(FXCollections.observableArrayList(EmpresaDAO.getHorarios("1")));
    }

    @FXML
    private void f_cancelar(ActionEvent event) throws IOException{
        ControllerTelaCatalogo c = new ControllerTelaCatalogo();
        c.start(ControllerStart.stage);
    }

    @FXML
    private void f_finalizar(ActionEvent event) throws IOException{
        ControllerFinalizar c = new ControllerFinalizar();
        if(comboBox.getSelectionModel().isEmpty()) {
            System.out.println("nothing selected");
            return;
        }
        c.start(ControllerStart.stage);
    }
}
