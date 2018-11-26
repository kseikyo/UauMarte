package ControllerClass;

/*
 *This is a generic class so I just call the method to initialize a new screen
 *So I don't need to keep copying and pasting on every class
 *I set this stage to be the root stage, on the need to change a screen
 *Just call initScreen passing the path to the new FXML file, the title of the screen and the ControllerStart.getStage()
*/


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;


public class ControllerStart {
    private static Stage stage;

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

        //zap zap
        stage.getIcons().add(new Image("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSX3gawRNp-MGONj-ItirR5vHOUy0KElJbeRmUp45RJPJCtWN2tVA"));
        ControllerStart.stage = stage;
        Scene scene = new Scene(root);
        ControllerStart.stage.setTitle(title);
        //ControllerStart.stage.setMaximized(true);
        //ControllerStart.stage.setHeight(stage.getMaxHeight());
        //ControllerStart.stage.setWidth(stage.getMaxWidth());
        ControllerStart.stage.setResizable(false);


        ControllerStart.stage.setScene(scene);
        ControllerStart.stage.show();
    }


    public void addEmptyValidation () {

    }

    public Stage getStage() {
        return ControllerStart.stage;
    }

}
