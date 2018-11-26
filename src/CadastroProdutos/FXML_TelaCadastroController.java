/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CadastroProdutos;


import ConexaoBanco.Conexao;
import ControllerClass.ControllerStart;
import Objetos.Produto;
import TelaPrincipal.ControllerTelaPrincipal;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
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
    private Button bt_limpar;
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
    
    @FXML
    //LIMPAR CAMPOS
    private void f_limpar(ActionEvent event){
        tf_codigo.setText("");
        tf_produto.setText("");
        tf_preco.setText("");
        cb_vendaPor.setValue("");
        tf_marca.setText("");
        urlImagem = null;
        ta_observacoes.setText("");
        iv_imagem.setImage(null);
    }
    
    @FXML
    //ABRIR TELA DE PESQUISA SEM FECHAR A TELA DE CADASTRO
    private void f_pesquisar(ActionEvent event) throws IOException{
       //USANDO NODE PARA CONSEGUIR O STAGE DESTA TELA
        Node nodePrimario = (Node) event.getSource();
        Stage stagePrimario = (Stage) nodePrimario.getScene().getWindow();
        
        //USANDO O LOADER PARA CARREGAR A TELA DE PEQUISA
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/FXMLFILES/FXML_PesquisaProdutos.fxml"));
        Parent root = loader.load();
        Scene subWindow = new Scene(root);
        
        //CRIANDO UM STAGE (UMA TELA) PARA PESQUISAR PRODUTOS
        Stage subStage = new Stage();
        subStage.setScene(subWindow);
        subStage.setTitle("Pesquisar");
        subStage.initModality(Modality.APPLICATION_MODAL);
        subStage.initOwner(stagePrimario);
        subStage.show();
        
        //A PARTIR DO LOADER USADO, CONSEGUIR O CONTROLLER DA TELA DE PESQUISA
        //ASSIM A TELA DE PESQUISA JA APARECE COM OS RESULTADOS PESQUISADOS (CASO O CAMPO PESQUISA ESTEJA PREENCHIDO)
            FXML_PesquisaProdutosController con = (FXML_PesquisaProdutosController) loader.getController();
            //INFORMANDO O CONTROLLER DESTA TELA PARA A TELA DE PESUQUISA
            con.setTelaCadastroController(this);
        if(!tf_pesquisar.getText().isEmpty()){            
            con.setPesquisa(tf_pesquisar.getText());
        }
    }
    
    //USAR O JFILECHOOSER PARA SELECIONAR A IMAGEM
    @FXML
    private void f_escolheImagem(ActionEvent e){
        /*VAI ROLAR N */      
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            //PRINTAR O CAMINHO DA IMAGEM
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
            
            //DEPOIS DE CONSEGUIR O CAMINHO DA IMAGEM SETAR A IMAGEM
            Image imagem = new Image("file:"+selectedFile.getAbsolutePath(),100,100, false, true);
            urlImagem = selectedFile.getAbsolutePath();
            iv_imagem.setImage(imagem);
        }
    }
    
    // VOLTAR A TELA PRINCIPAL
    @FXML 
    private void f_voltar(ActionEvent e) throws IOException{
        //ControllerStart controllerStart = new ControllerStart();
        ControllerTelaPrincipal telaPrincipal = new ControllerTelaPrincipal();
        telaPrincipal.start(ControllerStart.stage);
    }
    
    //SALVAR OU DAR UPDATE NOS DADOS INSERIDOS
    @FXML 
    private void f_salvar(ActionEvent e){
        //METODO CHECAR VALORES PARA VER SE TEM VALORES INSERIDOS
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
            
            //IMPRIMIR O STATEMANT USADO
            System.out.println(f_criarPreparedStatemant()); 
            //METODO PARA CRIAR A STRING DE SQL
            PreparedStatement ps = f_criarPreparedStatemant(); 
            ps.execute();
 
        } catch (SQLException ex) {
            Logger.getLogger(FXML_TelaCadastroController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //CRIANDO MASCARAS PARA PREÇO E CODIGO
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
        
        //O USUARIO N PODE ALTERAR O CODIGO DIRETAMENTE
        tf_codigo.setDisable(true); 
        
        //SETANDO OS VALORES DE VENDA POR
        cb_vendaPor.getItems().addAll("UN","KG","MG","LT"," M","CM"); 
        cb_vendaPor.setValue("UN");
    }
    
    //CHECAR SE TODOS OS CAMPOS OBRIGATORIOS ESTAO PREENCHIDOS, SE NAO, MOSTRAR MENSAGEM DE ERRO
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
        
        if (!tf_preco.getText().isEmpty()){
            if(Double.parseDouble(tf_preco.getText())>99) {
                msg+="O campo preco ultrapassa o limite de 99 reais";
            }
        }
        return msg;
    }
    
        //CRIANDO O PreparedStatemant, UPDATE ou INSERT DEPENDENDO SE O CODPRODUTO ESTIVER PREENCHIDO
    private PreparedStatement f_criarPreparedStatemant(){   
        
        PreparedStatement  ps;
        
        try {
            
            if(tf_codigo.getText().isEmpty()){
                ps = Conexao.getConnection().prepareStatement("INSERT INTO Produto (Descricao,Preco,VendaPor,Marca,Observacao,Imagem) VALUES (?,?,?,?,?,?)");
            }else{
                ps = Conexao.getConnection().prepareStatement("UPDATE Produto SET Descricao = ?,Preco = ?,VendaPor = ?,Marca = ?,Observacao = ?,Imagem = ? WHERE CodProduto = ?");
                ps.setInt(7, Integer.parseInt(tf_codigo.getText()));
            }
            //PREENCHENDO OS CAMPOS DO PreparedStatemant
            ps.setString(1, tf_produto.getText());
            ps.setDouble(2, Double.parseDouble(tf_preco.getText()));
            ps.setString(3, cb_vendaPor.getValue().toString());
            ps.setString(4,tf_marca.getText() );
            ps.setString(5,ta_observacoes.getText() );
            ps.setString(6,urlImagem);
            
            return ps;
        } catch (SQLException ex) {
            Logger.getLogger(FXML_TelaCadastroController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    //METODO PUBLICO PARA SETAR O PRODUTO A PARTIR DE OUTRAS TELAS (USADO NA TELA DE PesquisaProduto)
    public void f_setProduto(Produto p){
        
        tf_codigo.setText(Integer.toString(p.getCodigo()));
        tf_produto.setText(p.getDescricao());
        tf_preco.setText(Double.toString(p.getPreco()));
        cb_vendaPor.setValue(p.getVendaPor());
        tf_marca.setText(p.getMarca());
        urlImagem = p.getUrlImagem();
        ta_observacoes.setText(p.getObservacao());
        //SETAR A IMAGEM
        Image imagem = new Image("file:"+urlImagem,100,100, false, true);
        iv_imagem.setImage(imagem);
        System.out.println("help");
    }

}
