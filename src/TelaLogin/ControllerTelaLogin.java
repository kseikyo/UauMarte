package TelaLogin;

import TelaCadastroCliente.ControllerTelaCadastroCliente;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import DAO.PessoaDAO;
import java.io.IOException;
import java.sql.SQLException;
import javafx.scene.control.Label;
import ControllerClass.ControllerStart;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Node;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
//import javafx.stage.Stage;

/*
 * @author Lucas
 */
public class ControllerTelaLogin extends Application implements Initializable {
    
    @FXML
    private AnchorPane LoginPane;
    
    @FXML
    private Button SignUpB;


    @FXML
    private Label loginResult;

    @FXML
    private Button LoginButton;

    @FXML
    private JFXTextField username;

    @FXML
    private JFXPasswordField password;

    private ControllerStart controllerStart = new ControllerStart();

    public ControllerTelaLogin() {
        
    }

    @Override
    public void start(Stage stage) {
        try {
            this.controllerStart.initScreen("/FXMLFILES/TelaLogin.fxml", "Uau!Marte", this.controllerStart.getStage());
        } catch(IOException e) { }
    }
    
    @FXML
    void f_SignUpButtonClick(ActionEvent event) throws IOException {
        /*FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/FXMLFILES/TelaCadastro.fxml"));
        AnchorPane pane = loader.load();
        this.LoginPane.getChildren().setAll(pane);
        */
        ControllerTelaCadastroCliente controllerTelaCadastroCliente = new ControllerTelaCadastroCliente();
        controllerTelaCadastroCliente.start(this.controllerStart.getStage());
        
    }

    
    @FXML
    private void checkLogin(ActionEvent event) throws IOException  {
        if(f_CheckEmpty(this.loginResult)) {
            PessoaDAO.pesquisarPessoa(this.username.getText(), this.password.getText(), this.loginResult);
        }
    }
    
    /*@FXML
    private void clearFields(ActionEvent event) {
        this.username.setText("");
        this.password.setText("");
    }*/
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    //Validations are done when the field is not focused, i.e, when the user didn't click
    //to type on it
    //PS: It ONLY validates after the user clicked at least once at the textfield
        
        RequiredFieldValidator validateEmpty = new RequiredFieldValidator();
        
        username.getValidators().add(validateEmpty);
        validateEmpty.setMessage("Este campo é obrigatório");

        password.getValidators().add(validateEmpty);


        username.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldvalue, Boolean newValue) {
               if(!newValue) {
                   username.validate();

               }
            }
        });
        
        password.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldvalue, Boolean newValue) {
               if(!newValue) {
                   password.validate();
               }
            }
        });
        
    }


    
    private boolean f_CheckEmpty(Label label) {
        String user = this.username.getText();
        String pass = this.password.getText();


        if(user.isEmpty() || pass.isEmpty()) {
            label.setText("Por favor, preencha os campos obrigatórios");
            return false;
        }
        return true;
    }
    
}
