package TelaPrincipal;

import CadastroProdutos.TelaCadastro;
import ControllerClass.ControllerStart;
import TelaCatalogo.ControllerTelaCatalogo;
import TelaLogin.ControllerTelaLogin;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;


public class ControllerTelaPrincipal extends Application {
    @FXML
    private MenuItem menu_Comprar;

    public static BorderPane root = new BorderPane();

    @FXML
    private AnchorPane ap_Principal;



    public void start(Stage stage) throws IOException{
        /*URL menuBarUrl = null;
        try {
            menuBarUrl = getClass().getResource("/FXMLFILES/menus.fxml");
        } catch(NullPointerException e) {
            e.getMessage();
            e.printStackTrace();
        }
        MenuBar bar = FXMLLoader.load( menuBarUrl );

        URL paneOneUrl = getClass().getResource("/FXMLFILES/TelaCatalogo.fxml");
        ap_Principal = FXMLLoader.load( paneOneUrl );


        // constructing our scene using the static root

        getRoot().setTop(bar);
        getRoot().setCenter(ap_Principal);
        if(root.getScene() == null) {
            Scene scene = new Scene(getRoot());
            stage.setScene(scene);
            stage.show();
        }else {
            stage.setScene(root.getScene());
            stage.show();
        }
        */

        ControllerStart c = new ControllerStart();
        c.initMenu("/FXMLFILES/TelaCatalogo.fxml");
    }
    
    @FXML
    public void f_GoToLogin() {
        ControllerTelaLogin controllerTelaLogin = new ControllerTelaLogin();
        ControllerTelaLogin.idusuario = -1;
        controllerTelaLogin.start(ControllerStart.stage);
    }
    /*
    @FXML
    public void f_GoToCadastroP() throws Exception{
        TelaCadastro telaCadastro = new TelaCadastro();
        telaCadastro.start(controllerStart.getStage());
    }
    */

    @FXML
    public void f_GoToCatalogo() throws IOException{
        System.out.println("entrou");

        URL paneOneUrl = getClass().getResource("/FXMLFILES/TelaCatalogo.fxml");
        ap_Principal = FXMLLoader.load( paneOneUrl );


        // constructing our scene using the static root

        if(root.getScene() == null) {
            Scene scene = new Scene(getRoot());
            ControllerStart.stage.setScene(scene);
            ControllerStart.stage.show();
        }else {
            ControllerStart.stage.setScene(root.getScene());
            ControllerStart.stage.show();
        }



    }

    public static BorderPane getRoot() {
        return root;
    }

}
