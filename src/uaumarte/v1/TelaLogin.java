package uaumarte.v1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
//import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * @author Lucas
 */
public class TelaLogin extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/FXMLFILES/TelaLogin.fxml"));
        Parent root = loader.load();
        
        Scene scene = new Scene(root);
        stage.setTitle("Uau!Marte");
      //stage.getIcons().add(new Image("C:/Users/Lucas/Downloads/icon.png"));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
