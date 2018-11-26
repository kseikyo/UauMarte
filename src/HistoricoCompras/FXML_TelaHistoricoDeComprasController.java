/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HistoricoCompras;

import ControllerClass.ControllerStart;
import DAO.CompraDAO;
import static DAO.CompraDAO.ProdutoCompra;
import Objetos.Compra;
import Objetos.Produto;
import Objetos.ProdutoCompra;
import static TelaPrincipal.ControllerTelaPrincipal.getRoot;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author soart
 */
public class FXML_TelaHistoricoDeComprasController implements Initializable {

    private ControllerStart controllerStart = new ControllerStart();

    @FXML
    private TableView<Compra> tv_compras;

    @FXML
    private TableColumn<Compra, Timestamp> col_horario;

    @FXML
    private TableColumn<Compra, String> col_descricao;

    @FXML
    private TableView<ProdutoCompra> tv_produtos;

    @FXML
    private TableColumn<ProdutoCompra, String> col_produto;

    @FXML
    private TableColumn<ProdutoCompra, String> col_marca;

    @FXML
    private TableColumn<ProdutoCompra, String> col_preco;

    @FXML
    private TableColumn<?, ?> col_quantidade;
    
    @FXML
    void f_setCompra(MouseEvent event) {
        try{   
            System.out.println("oceanman"); 
            tv_produtos.getItems().setAll(CompraDAO.ProdutoCompra(tv_compras.getSelectionModel().getSelectedItem().getCodCompra()));
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        col_horario.setCellValueFactory(new PropertyValueFactory<>("horarioEntrega"));
        col_descricao.setCellValueFactory(new PropertyValueFactory<>("observacao"));
        ObservableList<Compra> historico = FXCollections.observableArrayList();
        tv_compras.setItems(historico);
        /////
        col_produto.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        col_marca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        col_preco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        col_quantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        ObservableList<ProdutoCompra> result = FXCollections.observableArrayList();
        tv_produtos.setItems(result);
        
        URL menuBarUrl = null;
        try {
            menuBarUrl = getClass().getResource("/FXMLFILES/menus.fxml");
            MenuBar bar = FXMLLoader.load( menuBarUrl );
            getRoot().setTop(bar);
        } catch(IOException e) {
            e.getMessage();
        }
        
        try {
            List<Compra> listahistorico = CompraDAO.HistoricoCompras();
            Iterator<Compra> i = listahistorico.iterator();
            while ( i.hasNext()) {
                tv_compras.getItems().add(i.next());
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXML_TelaHistoricoDeComprasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        // usar compra dao
    
    }

    public void start(Stage stage) throws IOException {
        controllerStart.initScreen("/FXMLFILES/FXML_TelaHistoricoDeCompras.fxml", "Uau!Marte",ControllerStart.stage);

    }
}
