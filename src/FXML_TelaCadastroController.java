/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CadastroProdutos;


import ConexaoBanco.Conexao;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javax.swing.JFileChooser;



/**
 * FXML Controller class
 *
 * @author soart
 */
public class FXML_TelaCadastroController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private String urlImagem;
    @FXML
    private AnchorPane pane;
    @FXML
    private Button bt_imagem;
    @FXML
    private Button bt_salvar;
    @FXML
    private Button bt_voltar;
    @FXML
    private Button bt_pesquisar;
    @FXML
    private TextField tf_codigo;
    @FXML
    private TextField tf_produto;
    @FXML
    private TextField tf_preco;
    @FXML
    private TextField tf_marca;
    @FXML
    private TextArea ta_observacoes;
    @FXML
    private TextField tf_pesquisar;
    @FXML
    private ImageView iv_imagem;
    @FXML
    private ChoiceBox cb_vendaPor;
    /*
    //O PROBLEMA È ESSE JFXTEXTFIELD
    //DO JFOENIX
    */
 //   @FXML
   // private void 
    @FXML
    private void f_escolheImagem(ActionEvent e){
        /*VAI ROLAR N */      
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
                
            Image imagem = new Image("file:"+selectedFile.getAbsolutePath(),100,100, false, true);
            urlImagem = selectedFile.getAbsolutePath();
            iv_imagem.setImage(imagem);
        }
    }
    
    @FXML
    private void f_voltar(ActionEvent e){
        return;//FAZER ISSO QUANDO TIVER A TELA PRINCIPAL
    }
    
    @FXML
    private void f_salvar(ActionEvent e){
        System.out.println(tf_codigo.getText());
        if(!checarValores().isEmpty()){
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Problema ao salvar!");
            alert.setHeaderText(null);
            alert.setContentText(checarValores());
            alert.showAndWait();
            return;
        }
        Statement stmt = null;
        
        try {
            System.out.println(f_criarPreparedStatemant());
            PreparedStatement ps = f_criarPreparedStatemant();
            ps.execute();
 
        } catch (SQLException ex) {
            Logger.getLogger(FXML_TelaCadastroController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tf_codigo.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
             if (!newValue.matches("\\d*")) {
                 tf_codigo.setText(newValue.replaceAll("[^\\d]", ""));
             }  
         });
        tf_preco.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
             if (!newValue.matches("(\\d+\\.\\d{1,2})")) {
                 tf_preco.setText(newValue.replaceAll("[^(\\d+\\.\\d{1,2})]*", ""));
             }
             tf_preco.setText(tf_preco.getText().replace(',', '.'));
         });
        //iv_imagem.setImage(null);
        
        tf_codigo.setDisable(true);
        
        
        cb_vendaPor.getItems().addAll("UN","KG","MG","LT"," M","CM");
        cb_vendaPor.setValue("UN");
       // bt_imagem.setVisible(false);//DESABILITA O BOTAO DA IMAGEM
    }
    
    private String checarValores(){
        String msg;
        msg = "";
        if(tf_produto.getText().isEmpty()) msg+="Produto\n";
        if(tf_preco.getText().isEmpty()) msg+="Preco\n";
        if(cb_vendaPor.getValue()==null) msg+="vendaPor\n";
        if(tf_marca.getText().isEmpty()) msg+="marca\n";
        if(!msg.isEmpty()){
            msg = "Os seguintes campos estão vazios:\n" + msg;
        }
        if(Double.parseDouble(tf_preco.getText())>99) {
            msg+="O campo preco ultrapassa o limite de 99 reais";
        }
        return msg;
    }
    
    private PreparedStatement f_criarPreparedStatemant(){

        try {
            PreparedStatement ps = Conexao.f_GetConnection().prepareStatement("INSERT INTO Produto (Descricao,Preco,VendaPor,Marca,Observacao,Imagem) VALUES (?,?,?,?,?,?)");
            ps.setString(1, tf_produto.getText());
            ps.setDouble(2, Double.parseDouble(tf_preco.getText()));
            ps.setString(3, cb_vendaPor.getValue().toString());
            ps.setString(4,tf_marca.getText() );
            ps.setString(5,ta_observacoes.getText() );
            ps.setString(6,urlImagem);
            return ps;
        } catch (IOException | ClassNotFoundException | SQLException ex) {
            Logger.getLogger(FXML_TelaCadastroController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
