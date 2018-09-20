package TelaCadastroCliente;

import ControllerClass.ControllerStart;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import TelaLogin.ControllerTelaLogin;

public class ControllerTelaCadastroCliente extends Application implements Initializable {

    @FXML
    private ImageView img_ProfilePic;

    @FXML
    private JFXTextField tf_CadastroNome;

    @FXML
    private JFXTextField tf_CadastroCPF;

    @FXML
    private JFXTextField tf_CadastroDataNasc;

    @FXML
    private JFXTextField tf_CadastroEmail;

    @FXML
    private JFXPasswordField pf_CadastroSenha;

    @FXML
    private JFXPasswordField pf_CadastroReSenha;

    @FXML
    private JFXTextField tf_CadastroEndereco;

    @FXML
    private JFXTextField tf_CadastroBairro;

    @FXML
    private JFXTextField tf_CadastroCidade;

    @FXML
    private JFXTextField tf_CadastroCEP;

    @FXML
    private JFXTextField tf_CadastroNumCasa;

    @FXML
    private JFXTextField tf_CadastroUF;

    @FXML
    private JFXButton bt_CadastrarCliente;

    @FXML
    private JFXButton bt_CancelarCadastro;

    private ControllerStart controllerStart = new ControllerStart();

    public void start(Stage stage) {
        try {
            controllerStart.initScreen("/FXMLFILES/TelaCadastro.fxml","Cadastre-se", controllerStart.getStage());
        } catch(IOException e) { }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        RequiredFieldValidator validateEmpty = new RequiredFieldValidator();
        tf_CadastroNome.getValidators().add(validateEmpty);
        tf_CadastroCPF.getValidators().add(validateEmpty);
        tf_CadastroDataNasc.getValidators().add(validateEmpty);
        tf_CadastroEndereco.getValidators().add(validateEmpty);
        tf_CadastroEmail.getValidators().add(validateEmpty);
        tf_CadastroCidade.getValidators().add(validateEmpty);
        pf_CadastroSenha.getValidators().add(validateEmpty);
        
        
        validateEmpty.setMessage("");
        
        tf_CadastroNome.focusedProperty().addListener(new ChangeListener<Boolean>() {
        @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldvalue, Boolean newValue) {
               if(!newValue) {
                   tf_CadastroNome.validate();
               }
            }
        });
        
        tf_CadastroCPF.focusedProperty().addListener(new ChangeListener<Boolean>() {
        @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldvalue, Boolean newValue) {
               if(!newValue) {
                   tf_CadastroCPF.validate();
               }
            }
        });
        
        tf_CadastroDataNasc.focusedProperty().addListener(new ChangeListener<Boolean>() {
        @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldvalue, Boolean newValue) {
               if(!newValue) {
                   tf_CadastroDataNasc.validate();
               }
            }
        });
        
        tf_CadastroEndereco.focusedProperty().addListener(new ChangeListener<Boolean>() {
        @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldvalue, Boolean newValue) {
               if(!newValue) {
                   tf_CadastroEndereco.validate();
               }
            }
        });
        
        tf_CadastroEmail.focusedProperty().addListener(new ChangeListener<Boolean>() {
        @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldvalue, Boolean newValue) {
               if(!newValue) {
                   tf_CadastroEmail.validate();
               }
            }
        });
        
        tf_CadastroCidade.focusedProperty().addListener(new ChangeListener<Boolean>() {
        @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldvalue, Boolean newValue) {
               if(!newValue) {
                   tf_CadastroCidade.validate();
               }
            }
        });
        
        pf_CadastroSenha.focusedProperty().addListener(new ChangeListener<Boolean>() {
        @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldvalue, Boolean newValue) {
               if(!newValue) {
                   pf_CadastroSenha.validate();
               }
            }
        });
        
        /*tf_CadastroTelefone.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldvalue, Boolean newValue) -> {
            if(!newValue) {
                tf_CadastroTelefone.validate();
            }
        });*/
        
    }
    
    
    
    @FXML
    void f_CadastrarCliente(ActionEvent event) {
        
    }

    @FXML
    void f_GoToLogin(ActionEvent event) throws IOException {
        /* OLD CODE -- KEEPING IT JUST IN CASE THE ControllerStart start to be a problem,
         * even though it's probably not gonna be a problem
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/FXMLFILES/TelaLogin.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Uau!Marte");
        stage.show();
        */
        ControllerTelaLogin controllerTelaLogin = new ControllerTelaLogin();

        controllerTelaLogin.start(this.controllerStart.getStage());
        
    }

}
