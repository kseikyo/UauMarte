package TelaCadastroCliente;

import ControllerClass.ControllerStart;
import DAO.PessoaDAO;
import TelaPrincipal.ControllerTelaPrincipal;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

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

    @FXML
    private Label lb_CPF;

    @FXML
    private Label lb_DtaNasc;

    @FXML
    private Label lb_Senha;

    @FXML
    private Label lb_ReSenha;

    @FXML
    private Label lb_UF;

    @FXML
    private Label lb_CEP;

    @FXML
    private Label lb_Check;

    private ArrayList<String> UFs = new ArrayList<String>();


    String padraocep   = "^\\d{5}[-]\\d{3}$";
    String padraoEmail = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*"
                         + "@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    String padraocpf   = "^\\d{3}[.]\\d{3}[.]\\d{3}[-]\\d{2}$";
    String padraoData  = "^\\d{2}[/]\\d{2}[/]\\d{4}$";
    String padraosenha = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}$";


    private ControllerStart controllerStart = new ControllerStart();

    public void start(Stage stage) throws IOException {
            controllerStart.initScreen("/FXMLFILES/TelaCadastro.fxml","Cadastre-se", controllerStart.getStage());
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.addUFS();
        RequiredFieldValidator validateEmpty = new RequiredFieldValidator();
        tf_CadastroNome.getValidators().add(validateEmpty);
        tf_CadastroCPF.getValidators().add(validateEmpty);
        tf_CadastroDataNasc.getValidators().add(validateEmpty);
        tf_CadastroEndereco.getValidators().add(validateEmpty);
        tf_CadastroEmail.getValidators().add(validateEmpty);
        tf_CadastroCidade.getValidators().add(validateEmpty);
        pf_CadastroSenha.getValidators().add(validateEmpty);
        pf_CadastroReSenha.getValidators().add(validateEmpty);
        tf_CadastroBairro.getValidators().add(validateEmpty);
        tf_CadastroNumCasa.getValidators().add(validateEmpty);
        tf_CadastroUF.getValidators().add(validateEmpty);
        tf_CadastroCEP.getValidators().add(validateEmpty);

        validateEmpty.setMessage("");

        tf_CadastroNome.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldvalue, Boolean newValue) {
                if (!newValue) {
                    tf_CadastroNome.validate();
                }
            }
        });

        tf_CadastroCPF.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldvalue, Boolean newValue) {
                if (!newValue) {
                    tf_CadastroCPF.validate();
                    try {
                        String aux = tf_CadastroCPF.getText();
                        String cpf;
                        if(aux.length() > 10) {
                            cpf = aux.substring(0, 3);
                            cpf += aux.substring(4, 7);
                            cpf += aux.substring(8, 11);
                            cpf += aux.substring(12, 14);
                            System.out.println(cpf);
                            PessoaDAO.pesquisarCPF(cpf, lb_CPF);
                        }
                    } catch (IOException e) {

                    }
                }
            }
        });

        tf_CadastroDataNasc.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldvalue, Boolean newValue) {
                if (!newValue) {
                    tf_CadastroDataNasc.validate();
                }
            }
        });

        tf_CadastroDataNasc.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches(padraoData))
                tf_CadastroDataNasc.setText(newValue.replaceAll("[^\\d/]", ""));

            if (newValue.length() > 10)
                tf_CadastroDataNasc.setText(newValue.substring(0, newValue.length() - 1));

            if(!newValue.matches(padraoData) && newValue.length() == 8) {
                tf_CadastroDataNasc.setText(newValue.replaceAll("^(\\d{2})(\\d{2})(\\d{4})$", "$1/$2/$3"));
            }

            if(newValue.length() < 9) {
                int count = 0;
                for(char c : newValue.toCharArray()){
                    if( c == '.' || c == '-' || c == '/') count++;
                }
                if(count > 2) {
                    Platform.runLater(() -> {
                        newValue.replaceAll(".", "");
                        newValue.replaceAll("-", "");
                        newValue.replaceAll("/", "");
                        tf_CadastroDataNasc.setText(newValue);
                    });
                }

            }
            if (newValue.length() == 10) {
                String year = newValue.substring(6, 10);
                String day = newValue.substring(0, 2);
                String month = newValue.substring(3, 5);
                Calendar dataAtual = Calendar.getInstance();
                Calendar dtNasc = Calendar.getInstance();
                dtNasc.set(Calendar.YEAR, Integer.parseInt(year));
                dtNasc.set(Calendar.MONTH, Integer.parseInt(month));
                dtNasc.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day));
                long diaMilissegundos = 86400000;
                long age = ((dataAtual.getTimeInMillis() - dtNasc.getTimeInMillis()) / diaMilissegundos) / 365;
                System.out.println(age);
                if (age < 16) {
                    lb_DtaNasc.setText("A idade mínima é 16 anos");
                }else
                    lb_DtaNasc.setText("");
            }

        });

        tf_CadastroEndereco.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldvalue, Boolean newValue) {
                if (!newValue) {
                    tf_CadastroEndereco.validate();
                }
            }
        });

        tf_CadastroEmail.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldvalue, Boolean newValue) {
                if (!newValue) {
                    if (!tf_CadastroEmail.getText().matches(padraoEmail)) lb_Email.setText("Email inválido");
                    else lb_Email.setText("");
                    if (!tf_CadastroEmail.validate()) lb_Email.setText("Por favor preencha o email");
                }
            }
        });

        tf_CadastroCidade.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldvalue, Boolean newValue) {
                if (!newValue) {
                    tf_CadastroCidade.validate();
                }
            }
        });

        pf_CadastroSenha.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldvalue, Boolean newValue) {
                if (!newValue) {
                    pf_CadastroSenha.validate();
                    if(pf_CadastroSenha.getText().matches(padraosenha))
                        lb_Senha.setText("");
                    else
                        lb_Senha.setText("Senha inválida");
                }
            }
        });

        pf_CadastroSenha.textProperty().addListener((ObservableValue<? extends String> observable, String oldvalue, String newValue) -> {
            if(!newValue.matches(padraosenha))
                pf_CadastroSenha.setText(newValue.replaceAll("^[^\\w]$", ""));

        });

        pf_CadastroReSenha.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldvalue, Boolean newValue) {
                if (!newValue) {
                    pf_CadastroReSenha.validate();
                    if (!pf_CadastroReSenha.getText().equals(pf_CadastroSenha.getText()))
                        lb_ReSenha.setText("Senha não condiz com a anterior");
                    else
                        lb_ReSenha.setText("");
                }
            }
        });

        tf_CadastroCPF.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*"))
                tf_CadastroCPF.setText(newValue.replaceAll("[^\\d- | ^\\d.]", ""));
            //tf_CadastroCPF.setText(newValue.replaceAll("^ ([^\\d-]) & ([^\\d.])$", ""));

            if (newValue.length() > 14)
                tf_CadastroCPF.setText(newValue.substring(0, newValue.length() - 1));


            if (newValue.length() < 14) {
                int count = 0;
                for (int i = 0; i < newValue.length(); i++) {
                    if (newValue.toCharArray()[i] == '.' || newValue.toCharArray()[i] == '-') count++;
                }
                if (count > 3) {
                    //This is just to prevent the SDK for throwing exceptions for thread exceptions
                    Platform.runLater(() -> {
                        tf_CadastroCPF.setText("");
                    });
                }
                if (newValue.length() == 11 && !newValue.matches(padraocpf)) {
                    /*StringBuilder sb = new StringBuilder(newValue)
                            .insert(3, ".")
                            .insert(7, ".")
                            .insert(11, "-");*/
                    tf_CadastroCPF.setText(newValue.replaceAll("^(\\d{3})(\\d{3})(\\d{3})(\\d{2})$",
                            "$1.$2.$3-$4"));
                }
            }
        });

        tf_CadastroCEP.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            //regex to only numbers. It replace everything that's not a number or the '-' character
            //with an empty string
            if (!newValue.matches("\\d*-"))
                tf_CadastroCEP.setText(newValue.replaceAll("^[^\\d-] $", ""));

            //Accepting input of 8 characters only
            if (newValue.length() > 9) tf_CadastroCEP.setText(newValue.substring(0, newValue.length() - 1));

            //Replacing the final string with the mask, e.g, 85770-000
            if (!newValue.matches(padraocep) && newValue.length() == 8)
                tf_CadastroCEP.setText(newValue.replaceAll("(\\d{5})(\\d{3})", "$1-$2"));


            //This checks how many '-' characters the CEP field has
            //if it has more than 1 it will erase all of them
            //and if it has only 1 and it's not on it's right position
            //it will also erase all of the '-' characters
            if (newValue.length() < 9) {
                int count = 0;
                for (int i = 0; i < newValue.length(); i++) {
                    if (newValue.toCharArray()[i] == '-' || newValue.toCharArray()[i] == '.') count++;
                }
                if (count == 1 && newValue.length() > 5) {
                    if (newValue.charAt(5) != '-') {
                        Platform.runLater(() -> {
                            tf_CadastroCEP.setText(newValue.replaceAll("-", ""));
                        });
                    }
                }
                if (count > 1) {
                    Platform.runLater(() -> {
                        tf_CadastroCEP.setText(newValue.replaceAll("-", ""));
                    });
                }
            }

        });

        tf_CadastroCEP.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldvalue, Boolean newValue) {
                if (!newValue) {
                    tf_CadastroCEP.validate();
                    tf_CadastroCEP.setText(tf_CadastroCEP.getText().replaceAll(" ", ""));
                    if(!tf_CadastroCEP.getText().matches(padraocep)) lb_CEP.setText("Cep inválido");
                    else lb_CEP.setText("");
                }
            }
        });

        tf_CadastroNumCasa.textProperty().addListener((ObservableValue<? extends String > observable, String oldvalue, String newValue) -> {
            if(!newValue.matches("\\d*"))
                tf_CadastroNumCasa.setText(newValue.replaceAll("^[\\D]$", ""));

            if(newValue.length() > 5 )
                tf_CadastroNumCasa.setText(newValue.substring(0, 6));

        });

        pf_CadastroReSenha.focusedProperty().addListener(new ChangeListener<Boolean> ()  {
            @Override
            public void changed(ObservableValue<? extends Boolean > observable, Boolean oldvalue, Boolean newValue) {
                if(!newValue)
                    pf_CadastroReSenha.validate();
            }
        });

        tf_CadastroUF.focusedProperty().addListener(new ChangeListener<Boolean> ()  {
            @Override
            public void changed(ObservableValue<? extends Boolean > observable, Boolean oldvalue, Boolean newValue) {
                if(!newValue)
                    tf_CadastroUF.validate();
            }
        });

        tf_CadastroNumCasa.focusedProperty().addListener(new ChangeListener<Boolean> ()  {
            @Override
            public void changed(ObservableValue<? extends Boolean > observable, Boolean oldvalue, Boolean newValue) {
                if(!newValue)
                    tf_CadastroNumCasa.validate();
            }
        });

        tf_CadastroBairro.focusedProperty().addListener(new ChangeListener<Boolean> ()  {
            @Override
            public void changed(ObservableValue<? extends Boolean > observable, Boolean oldvalue, Boolean newValue) {
                if(!newValue)
                    tf_CadastroBairro.validate();
            }
        });


        tf_CadastroUF.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldvalue, Boolean newValue) {
                if (!newValue) {
                    int val = 0;
                    tf_CadastroUF.setText(tf_CadastroUF.getText().toUpperCase());
                    for( String uf: UFs) {
                        if(tf_CadastroUF.getText().equals(uf)) {
                            lb_UF.setText("");
                            val++;
                            break;
                        }
                    }
                    if(val != 1) lb_UF.setText("UF inválida");

                }
            }
        });
        tf_CadastroUF.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if(!newValue.matches("\\D*"))
                tf_CadastroUF.setText(newValue.replaceAll("^[^\\D]$", ""));

            if(newValue.length() > 2) {
                Platform.runLater(() -> tf_CadastroUF.setText(newValue.substring(0, 2)));
            }
        });
    }




    @FXML
    void f_CadastrarCliente(ActionEvent event) throws InterruptedException {
        if(checkCampos(tf_CadastroNome.getText(), tf_CadastroCPF.getText(), tf_CadastroDataNasc.getText(),
                tf_CadastroEmail.getText(), pf_CadastroSenha.getText(),tf_CadastroCEP.getText(),
                tf_CadastroEndereco.getText(), tf_CadastroNumCasa.getText(),
                tf_CadastroBairro.getText(), tf_CadastroCidade.getText(), tf_CadastroUF.getText())) {

            PessoaDAO.cadastrarPessoa(tf_CadastroNome.getText(), tf_CadastroCPF.getText(), tf_CadastroDataNasc.getText(),
                    tf_CadastroEmail.getText(), pf_CadastroSenha.getText(), tf_CadastroCEP.getText(),
                    tf_CadastroEndereco.getText(), tf_CadastroNumCasa.getText(), tf_Complemento.getText(),
                    tf_CadastroBairro.getText(), tf_CadastroCidade.getText(), tf_CadastroUF.getText());
            //CADASTRO REALIZADO COM SUCESSO
            lb_Check.setText("Cadastro realizado com sucesso!");
            TimeUnit.SECONDS.sleep(4);
            ControllerTelaLogin controllerTelaLogin = new ControllerTelaLogin();
            controllerTelaLogin.start(this.controllerStart.getStage());
        }else
            lb_Check.setText("Preencha todos os campos obrigatórios");

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

    private void addUFS() {
        this.UFs.add("AC");this.UFs.add("AL");this.UFs.add("AM");this.UFs.add("AP");this.UFs.add("BA");
        this.UFs.add("CE");this.UFs.add("DF");this.UFs.add("ES");this.UFs.add("GO");this.UFs.add("MA");
        this.UFs.add("MG");this.UFs.add("MS");this.UFs.add("MT");this.UFs.add("PA");this.UFs.add("PB");
        this.UFs.add("PE");this.UFs.add("PI");this.UFs.add("PR");this.UFs.add("RJ");this.UFs.add("RN");
        this.UFs.add("RO");this.UFs.add("RR");this.UFs.add("RS");this.UFs.add("SC");this.UFs.add("SE");
        this.UFs.add("SP");this.UFs.add("TO");
    }

    private boolean checkCampos(String nome, String cpf, String data, String login, String senha,String cep,
                                String ender, String num_casa, String bairro, String cidade,
                                String uf)
    {
        if(nome.isEmpty() || cpf.isEmpty() || data.isEmpty() || login.isEmpty() || senha.isEmpty() || cep.isEmpty()
                || ender.isEmpty() || num_casa.isEmpty() || bairro.isEmpty() || cidade.isEmpty()
                || uf.isEmpty()) return false;

        return true;
    }

}
