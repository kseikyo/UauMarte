package TelaCadastroCliente;

import ControllerClass.ControllerStart;
import DAO.PessoaDAO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import TelaLogin.ControllerTelaLogin;
import javafx.scene.control.Label;

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

    @FXML
    private JFXTextField tf_Complemento;

    @FXML
    private Label lb_Email;

    String padraocep = "\\d{5}[-]\\d{3}";
    String padraoEmail = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*"
                         + "@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    String padraocpf = "\\d{3}[.]\\d{3}[.]\\d{3}[-]\\d{2}";
    String padraoData= "\\d{2}[/]\\d{2}[/]\\d{4}";






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
        tf_CadastroCEP.getValidators().add(validateEmpty);
        
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
                   try {
                       PessoaDAO.pesquisarCPF(tf_CadastroCPF.getText());
                   } catch (IOException e) {

                   }
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

        tf_CadastroDataNasc.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches(padraoData))
                tf_CadastroDataNasc.setText(newValue.replaceAll("[^\\d/]", ""));

            if (newValue.length() > 10)
                tf_CadastroDataNasc.setText(newValue.substring(0, newValue.length() - 1));

            if(newValue.length() == 10) {
                String year = newValue.substring(6, 10);
                System.out.println(year);
                int age = 2002;
                if(Integer.parseInt(year) > age) {
                    System.out.println("Se fodeu");
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
                   if(!tf_CadastroEmail.getText().matches(padraoEmail)) lb_Email.setText("Email inválido");
                   else lb_Email.setText("");
                   if(!tf_CadastroEmail.validate()) lb_Email.setText("Por favor preencha o email");
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

        pf_CadastroReSenha.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldvalue, Boolean newValue) {
                if(!newValue) {
                    pf_CadastroReSenha.validate();
                    if(!pf_CadastroReSenha.getText().equals(pf_CadastroSenha.getText()))
                        System.out.println("ERROU");
                }
            }
        });

        tf_CadastroCPF.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*"))
                tf_CadastroCPF.setText(newValue.replaceAll("[^\\d- | ^\\d.]", ""));

            if (newValue.length() > 14)
                tf_CadastroCPF.setText(newValue.substring(0, newValue.length() - 1));


            if(newValue.length() < 14) {
                int count = 0;
                for(int i = 0; i < newValue.length(); i++) {
                    if(newValue.toCharArray()[i] == '.' || newValue.toCharArray()[i] == '-' ) count++;
                }
                if(count > 3) {
                    //This is just to prevent the SDK for throwing exceptions for thread exceptions
                    Platform.runLater(() -> {
                        tf_CadastroCPF.setText("");
                    });
                }
                if(newValue.length() == 11 && !newValue.matches(padraocpf)) {
                    StringBuilder sb = new StringBuilder(newValue)
                            .insert(3, ".")
                            .insert(7, ".")
                            .insert(11, "-");
                    tf_CadastroCPF.setText(sb.toString());
                }
            }
        });

        tf_CadastroCEP.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            //regex to only numbers. It replace everything that's not a number or the '-' character
            //with an empty string
            if (!newValue.matches("\\d*-"))
                tf_CadastroCEP.setText(newValue.replaceAll("^[^\\d-] $",""));

            //Accepting input of 8 characters only
            if(newValue.length() > 9 ) tf_CadastroCEP.setText(newValue.substring(0, newValue.length()-1));

            //Replacing the final string with the mask, e.g, 85770-000
            if(!newValue.matches(padraocep) && newValue.length() == 8)
                tf_CadastroCEP.setText(newValue.replaceAll("(\\d{5})(\\d{3})", "$1-$2"));


            //This checks how many '-' characters the CEP field has
            //if it has more than 1 it will erase all of them
            //and if it has only 1 and it's not on it's right position
            //it will also erase all of the '-' characters
            if(newValue.length() < 9) {
                int count = 0;
                for(int i = 0; i < newValue.length(); i++) {
                    if(newValue.toCharArray()[i] == '-' || newValue.toCharArray()[i] == '.') count++;
                }
                if(count == 1 && newValue.length()>5) {
                    if(newValue.charAt(5) != '-') {
                        Platform.runLater(() -> {
                            tf_CadastroCEP.setText(newValue.replaceAll("-", ""));
                        });
                    }
                }
                if(count > 1) {
                    Platform.runLater(() -> {
                        tf_CadastroCEP.setText(newValue.replaceAll("-", ""));
                    });
                }
            }

        });

        tf_CadastroCEP.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldvalue, Boolean newValue) {
                if(!newValue) {
                    tf_CadastroCEP.validate();

                }
            }
        });
        
    }




    @FXML
    void f_CadastrarCliente(ActionEvent event) {
        PessoaDAO.cadastrarPessoa(tf_CadastroNome.getText(), tf_CadastroCPF.getText(), tf_CadastroDataNasc.getText(),
                tf_CadastroEmail.getText(), pf_CadastroSenha.getText(), tf_CadastroEndereco.getText(), tf_CadastroBairro.getText(),
                tf_CadastroCidade.getText());
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
