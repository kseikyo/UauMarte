/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CadastroProdutos;

import ConexaoBanco.Conexao;
import Objetos.Produto;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author soart
 */
public class FXML_PesquisaProdutosController implements Initializable {

    /**
     * Initializes the controller class.
     */
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
    public void f_pesquisar(ActionEvent event) {
        if(tf_pesquisa.getText().isEmpty()){
            return;
        }
        
        if(tf_pesquisa.getText().matches("\\d*")){
            PreparedStatement ps;
            try {
                ps = Conexao.getConnection().prepareStatement("SELECT * FROM produto WHERE produto.codproduto = ?");
                ps.setInt(1, Integer.parseInt(tf_pesquisa.getText()));
                ResultSet rs = ps.executeQuery();
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
            //TODO            
        }else{
            
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        col_cod.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        col_marca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        col_nome.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        ObservableList<Produto> result = FXCollections.observableArrayList() ;
        tv_resultado.setItems(result);
        //Produto p  = new Produto(51,"toma", 0, "sa", "sa", "sa", "sa");
        //result.add(p);

}//TODO

}

