/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CadastroProdutos;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 *
 * @author soart
 */
public class TelaCadastro {
    public Scene getScene() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/FXMLFILES/FXML_TelaCadastro.fxml"));
        Scene scene = new Scene(root);
        return scene;
    }
    
}
