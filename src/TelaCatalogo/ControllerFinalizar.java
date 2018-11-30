package TelaCatalogo;

import ControllerClass.ControllerStart;
import DAO.CompraDAO;
import DAO.PessoaDAO;
import TelaPrincipal.ControllerTelaPrincipal;
import com.jfoenix.controls.JFXCheckBox;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerFinalizar extends Application implements Initializable {
    @FXML
    private JFXCheckBox cb_Money;

    @FXML
    private JFXCheckBox cb_VisaDeb;

    @FXML
    private JFXCheckBox cb_MasterDeb;

    @FXML
    private JFXCheckBox cb_HiperDeb;

    @FXML
    private JFXCheckBox cb_EloDeb;

    @FXML
    private JFXCheckBox cb_Banri;

    @FXML
    private JFXCheckBox cb_VisaCred;

    @FXML
    private JFXCheckBox cb_MasterCred;

    @FXML
    private JFXCheckBox cb_HiperCred;

    @FXML
    private JFXCheckBox cb_EloCred;

    @FXML
    private JFXCheckBox endereco;

    @Override
    public void start(Stage stage) throws IOException {
        ControllerStart c = new ControllerStart();
        c.initScreen("/FXMLFILES/TelaFinalizando.fxml", "Finalizando", ControllerStart.stage);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        endereco.setText(PessoaDAO.pesquisarEnder());
    }

    @FXML
    void f_Cancelar(ActionEvent event) throws IOException {
        ControllerTelaPrincipal c = new ControllerTelaPrincipal();
        c.start(ControllerStart.stage);
    }

    @FXML
    void f_Finalizar(ActionEvent event) {
        //System.out.println(f_checkSelected());
        if(f_checkSelected().equals("false")) return;
        //System.out.println(endereco.getText());
        try {
        CompraDAO.inserirFinal(f_checkSelected(), endereco.getText());
        System.out.println("Compra finalizada!!!!");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Compra finalizada!");
        alert.setHeaderText(null);
        alert.setContentText("Tudo certo!\nAguarde sua compra.");
        alert.showAndWait();
        ControllerTelaPrincipal controllerTelaPrincipal = new ControllerTelaPrincipal();
        controllerTelaPrincipal.start(ControllerStart.stage);
        }catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Problema ao pesquisar no banco!");
            alert.setHeaderText(null);
            alert.setContentText("Erro ao finalizar a compra!");
            alert.showAndWait();
        }
    }

    @FXML
    private void f_disableOthers(ActionEvent event) {
        String text = ((JFXCheckBox) event.getSource()).getText();
        disable(text);
    }

    private void disable(String text) {
        cb_Banri.setSelected(false);
        cb_EloCred.setSelected(false);
        cb_EloDeb.setSelected(false);
        cb_HiperCred.setSelected(false);
        cb_HiperDeb.setSelected(false);
        cb_MasterCred.setSelected(false);
        cb_MasterDeb.setSelected(false);
        cb_Money.setSelected(false);
        cb_VisaCred.setSelected(false);
        cb_VisaDeb.setSelected(false);

        if(text.equals("Dinheiro")) {
            cb_Money.setSelected(true);
        }
        else if (text.equals("Visa Débito")) {
            cb_VisaDeb.setSelected(true);
        }
        else if (text.equals("Mastercard Débito")) {
            cb_MasterDeb.setSelected(true);
        }
        else if (text.equals("Hipercard Débito")) {
            cb_HiperDeb.setSelected(true);
        }
        else if (text.equals("Visa Crédito")) {
            cb_VisaCred.setSelected(true);
        }
        else if (text.equals("Elo Débito")) {
            cb_EloDeb.setSelected(true);
        }
        else if (text.equals("Banricompras")) {
            cb_Banri.setSelected(true);
        }
        else if (text.equals("Mastercard Crédito")) {
            cb_MasterCred.setSelected(true);
        }
        else if (text.equals("Hipercard Crédito")) {
            cb_HiperCred.setSelected(true);
        }
        else {
            cb_EloCred.setSelected(true);
        }
    }

    private String f_checkSelected() {
        if(cb_Banri.isSelected())
            return cb_Banri.getText();
        else if(cb_EloCred.isSelected())
            return cb_EloCred.getText();
        else if(cb_EloDeb.isSelected())
            return cb_EloDeb.getText();
        else if(cb_HiperCred.isSelected())
            return cb_HiperCred.getText();
        else if(cb_HiperDeb.isSelected())
            return cb_HiperDeb.getText();
        else if(cb_MasterCred.isSelected())
            return cb_MasterCred.getText();
        else if(cb_MasterDeb.isSelected())
            return cb_MasterDeb.getText();
        else if(cb_VisaCred.isSelected())
            return cb_VisaCred.getText();
        else if(cb_VisaDeb.isSelected())
            return cb_VisaDeb.getText();
        else if(cb_Money.isSelected())
            return cb_Money.getText();
        else
            return "false";
    }
}
