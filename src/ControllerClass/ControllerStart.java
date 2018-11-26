package ControllerClass;

/*
 *This is a generic class so I just call the method to initialize a new screen
 *So I don't need to keep copying and pasting on every class
 *I set this stage to be the root stage, on the need to change a screen
 *Just call initScreen passing the path to the new FXML file, the title of the screen and the ControllerStart.getStage()
*/


import TelaPrincipal.ControllerTelaPrincipal;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;


public class ControllerStart {
    public static Stage stage;

    public AnchorPane ap_Principal;

    public ControllerStart() {

    }


    /*public void initScreen(String path, String title, ActionEvent event) throws IOException{
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(path));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.setResizable(false);
        stage.setTitle(title);
        stage.show();
    }*/

    public void initScreen(String path, String title, Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(path));
        Parent root = loader.load();

        stage.getIcons().add(new Image("/FXMLFILES/39257806_1032626896940813_945450888875474944_n (1).jpg"));
        ControllerStart.stage = stage;

        Scene scene = new Scene(root);
        ControllerStart.stage.setTitle(title);
        //ControllerStart.stage.setMaximized(true);
        //ControllerStart.stage.setHeight(stage.getMaxHeight());
        //ControllerStart.stage.setWidth(stage.getMaxWidth());
        ControllerStart.stage.setResizable(false);

        stage.setScene(null);
        ControllerStart.stage.setScene(scene);
        ControllerStart.stage.show();
    }


    public void initMenu(String path) throws IOException{
        URL menuBarUrl = null;
        try {
            menuBarUrl = getClass().getResource("/FXMLFILES/menus.fxml");
        } catch(NullPointerException e) {
            e.getMessage();
            e.printStackTrace();
        }
        MenuBar bar = FXMLLoader.load( menuBarUrl );

        URL paneOneUrl = getClass().getResource(path);
        ap_Principal = FXMLLoader.load( paneOneUrl );


        // constructing our scene using the static root

        ControllerTelaPrincipal.getRoot().setTop(bar);
        ControllerTelaPrincipal.getRoot().setCenter(ap_Principal);

        if(ControllerTelaPrincipal.getRoot().getScene() == null) {
            Scene scene = new Scene(ControllerTelaPrincipal.getRoot());
            stage.setScene(scene);
            stage.show();
        }else {
            stage.setScene(ControllerTelaPrincipal.root.getScene());
            stage.show();
        }
    }



    public void addEmptyValidation () {

    }

    /*public Stage getStage() {
        return ControllerStart.stage;
    }*/

}
