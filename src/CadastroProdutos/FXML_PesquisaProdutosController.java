/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CadastroProdutos;

import ConexaoBanco.Conexao;
import Objetos.Produto;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author soart
 */
public class FXML_PesquisaProdutosController implements Initializable {

    private Produto produtoSelecionado;

    @FXML
    private FXML_TelaCadastroController TelaCadastroController;

            
    @FXML
    private Parent root;
    
    @FXML
    private AnchorPane pane;
    
    @FXML
    private Button bt_pesquisar;

    @FXML
    private TextField tf_pesquisa;

    @FXML
    private TableView<Produto> tv_resultado;

    @FXML
    private TableColumn<Produto, Integer> col_cod;

    @FXML
    private TableColumn<Produto, String> col_nome;

    @FXML
    private TableColumn<Produto, String> col_marca;

    @FXML
    private Button bt_voltar;

    
    //PESQUISAR
    @FXML
    private void f_pesquisar(ActionEvent event) {
       pesquisar();
    }
    
    //FECHAR ESTA TELA
    @FXML
    void f_voltar(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }
    
    //SELECIONAR OS PRODUTOS
    @FXML
    private void f_seleciona(MouseEvent event) throws IOException {
        //SE SELECIONADO POR DUPLO-CLIQUE RETORNAR O RESULTADO PARA A TELA DE CADASTRO PRODUTOS
        if (event.getClickCount() == 2){
            produtoSelecionado = tv_resultado.getSelectionModel().getSelectedItem();
            System.out.println("oceanman");
             
            try{   
           
            TelaCadastroController.f_setProduto(produtoSelecionado);
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
            
            
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //CONFIGURAR OS CAMPOS DA TABELA DE RESULTADO DA PESQUISA
        col_cod.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        col_marca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        col_nome.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        ObservableList<Produto> result = FXCollections.observableArrayList() ;
        tv_resultado.setItems(result);
    }
    
    //PESQUISAR (METODO PUBLICO PARA SER USADO NA TELA DE CADASTRO) 
    public void setPesquisa(String pqs){
        tf_pesquisa.setText(pqs);
        pesquisar();
    }
    
    //METODO PARA CONSEGUIR O ACESSO AO CONTROLLER DA TELA DE CADASTRO
    public void setTelaCadastroController(FXML_TelaCadastroController TelaCadastroController) {
        this.TelaCadastroController = TelaCadastroController;
    }
    
    //OS DOIS METODOS DE PESQUISA CHAMAM ESTE METODO
    private void pesquisar(){
        if(tf_pesquisa.getText().isEmpty()){
            return;
        }
        PreparedStatement ps;
        
        try {
            //CHECAR SE ESTA PROCURANDO DIRETAMENTE O CODIGO DO PRODUTO OU O NOME
            if(tf_pesquisa.getText().matches("\\d*")){
                ps = Conexao.getConnection().prepareStatement("SELECT * FROM produto WHERE produto.codproduto = ?");
                ps.setInt(1, Integer.parseInt(tf_pesquisa.getText()));
            }else{
                ps = Conexao.getConnection().prepareStatement("SELECT * FROM produto WHERE produto.descricao ~* ?");
                ps.setString(1, tf_pesquisa.getText());
            }
                ResultSet rs = ps.executeQuery();
                tv_resultado.getItems().clear();
                while (rs.next()){
                    Produto p = new Produto(rs.getInt("codproduto"),rs.getString("descricao"),rs.getDouble("preco"),rs.getString("vendapor"),rs.getString("marca"),rs.getString("observacao"),rs.getString("imagem"));
                    tv_resultado.getItems().add(p);
                }
        } catch (SQLException ex) {
                Logger.getLogger(FXML_PesquisaProdutosController.class.getName()).log(Level.SEVERE, null, ex);
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Problema ao pesquisar no banco!");
                alert.setHeaderText(null);
                alert.setContentText("Erro ao realizar procura!\n"+ex.getMessage());
                alert.showAndWait();
        } 
    }
}