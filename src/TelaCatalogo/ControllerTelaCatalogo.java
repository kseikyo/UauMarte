package TelaCatalogo;

import ControllerClass.ControllerStart;
import DAO.CompraDAO;
import DAO.ProdutoDAO;
import Objetos.Carrinho;
import Objetos.Produto;
import TelaLogin.ControllerTelaLogin;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class ControllerTelaCatalogo extends Application implements Initializable {

    private LinkedList<Produto> list = new LinkedList<>();

    @FXML
    private JFXButton bt_left;

    @FXML
    private JFXButton bt_right;

    @FXML
    private ImageView img_first;

    @FXML
    private ImageView img_second;

    @FXML
    private ImageView img_third;

    @FXML
    private ImageView img_fourth;

    @FXML
    private JFXButton bt_leave;

    @FXML
    private JFXButton bt_user;

    @FXML
    private Label lb_firsName;

    @FXML
    private Label lb_secondName;

    @FXML
    private Label lb_thirdName;

    @FXML
    private Label lb_fourthName;

    @FXML
    private Label lb_firstPrice;

    @FXML
    private Label lb_secondPrice;

    @FXML
    private Label lb_thirdPrice;

    @FXML
    private Label lb_fourthPrice;

    @FXML
    private JFXButton bt_addFirst;

    @FXML
    private JFXButton bt_addSecond;

    @FXML
    private JFXButton bt_addThird;

    @FXML
    private JFXButton bt_addFourth;

    @FXML
    private JFXButton bt_removeFirst;

    @FXML
    private JFXButton bt_removeSecond;

    @FXML
    private JFXButton bt_removeThird;

    @FXML
    private JFXButton bt_removeFourth;

    @FXML
    private TableView<Carrinho> tbv_carrinho;

    @FXML
    private TableColumn<Carrinho, String> col_nome;

    @FXML
    private TableColumn<Carrinho, Double> col_preco;

    @FXML
    private TableColumn<Carrinho, Double> col_quantidade;

    @FXML
    private JFXTimePicker tp_hora;

    @FXML
    private JFXDatePicker dp_data;

    @FXML
    private Label lb_finalizar;

    private LinkedList<Carrinho> carrinho = new LinkedList<>();
    private int pos = 0;
    private int pos2 = 1;
    private int pos3 = 2;
    private int pos4 = 3;

    private int once = 0;

    private Image first = null;
    private Image second = null;
    private Image third = null;
    private Image fourth = null;

    private ControllerStart controllerStart = new ControllerStart();

    private ObservableList<Carrinho> result ;


    @Override
    public void start(Stage primaryStage) throws IOException {
        controllerStart.initScreen("/FXMLFILES/TelaCatalogo.fxml", "Uau!Marte", controllerStart.getStage());

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.initProdutos();

        //Image user = new Image(getClass().getResourceAsStream("/FXMLFILES/user.png"));
        //bt_user.getStyleClass().removeAll("bt-leave, focus");
        //bt_user.getStyleClass().add("bt-leave");
        //bt_user.setGraphic(new ImageView(user));

        Image leave = new Image(getClass().getResourceAsStream("/FXMLFILES/leave.png"));
        bt_leave.getStyleClass().removeAll("bt-leave, focus");
        bt_leave.getStyleClass().add("bt-leave");
        bt_leave.setGraphic(new ImageView(leave));

        Image left = new Image(getClass().getResourceAsStream("/FXMLFILES/left.png"));
        bt_left.getStyleClass().removeAll("bt-leave, focus");
        bt_left.getStyleClass().add("bt-leave");
        bt_left.setGraphic(new ImageView(left));

        Image right = new Image(getClass().getResourceAsStream("/FXMLFILES/right.png"));
        bt_right.getStyleClass().removeAll("bt-leave, focus");
        bt_right.getStyleClass().add("bt-leave");
        bt_right.setGraphic(new ImageView(right));

        Image plus = new Image(getClass().getResourceAsStream("/FXMLFILES/plus.png"));
        bt_addFirst.getStyleClass().removeAll("bt-leave, focus");
        bt_addFirst.getStyleClass().add("bt-leave");
        bt_addFirst.setGraphic(new ImageView(plus));

        bt_addSecond.getStyleClass().removeAll("bt-leave, focus");
        bt_addSecond.getStyleClass().add("bt-leave");
        bt_addSecond.setGraphic(new ImageView(plus));

        bt_addThird.getStyleClass().removeAll("bt-leave, focus");
        bt_addThird.getStyleClass().add("bt-leave");
        bt_addThird.setGraphic(new ImageView(plus));

        bt_addFourth.getStyleClass().removeAll("bt-leave, focus");
        bt_addFourth.getStyleClass().add("bt-leave");
        bt_addFourth.setGraphic(new ImageView(plus));

        Image minus = new Image(getClass().getResourceAsStream("/FXMLFILES/minus.png"));

        bt_removeFirst.setGraphic(new ImageView(minus));

        bt_removeSecond.setGraphic(new ImageView(minus));

        bt_removeThird.setGraphic(new ImageView(minus));

        bt_removeFourth.setGraphic(new ImageView(minus));


        //Config of tableview

        col_nome.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        col_preco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        col_quantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        this.result = FXCollections.observableArrayList() ;
        tbv_carrinho.setItems(this.result);

    }

    @FXML
    private void f_goToLogin(ActionEvent event) {
        ControllerTelaLogin controllerTelaLogin = new ControllerTelaLogin();
        controllerTelaLogin.start(controllerStart.getStage());
    }

    @FXML
    private void f_openUserInfo(ActionEvent event) {

    }

    @FXML
    private void f_LoadLeft() {
        System.out.println(pos);
        if(checkListLeft(pos)) {
            pos--; pos2--; pos3--; pos4--;
            changeProd();
        }
    }

    @FXML
    private void f_LoadRight() {
        System.out.println(pos4);
        if(checkListRight()) {
            pos++; pos2++; pos3++; pos4++;
            changeProd();
        }

    }

    private void initProdutos() {
        if(once > 0)
            return;
        list = ProdutoDAO.retornarTodosProdutos();

        changeProd();
        once++;
    }

    private boolean checkListLeft(int pos) {
        try {
            if (pos >= 1) return true;
        }catch(NullPointerException e) { }
        return false;
    }

    private boolean checkListRight() {
        try{
            if(pos4 <= list.size()-2)
                return true;
        }catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("toca o barco");
        }
        return false;
    }

    public LinkedList<Produto> getList() {
        return this.list;
    }

    private void changeProd() {
        try {
            first = new Image("file:" +list.get(pos).getUrlImagem());
            img_first.setImage(first);
            lb_firsName.setText(list.get(pos).getDescricao());
            lb_firstPrice.setText(String.valueOf(list.get(pos).getPreco()));

            second = new Image("file:" +list.get(pos2).getUrlImagem());
            img_second.setImage(second);
            lb_secondName.setText(list.get(pos2).getDescricao());
            lb_secondPrice.setText(String.valueOf(list.get(pos2).getPreco()));

            third = new Image("file:" +list.get(pos3).getUrlImagem());
            img_third.setImage(third);
            lb_thirdName.setText(list.get(pos3).getDescricao());
            lb_thirdPrice.setText(String.valueOf(list.get(pos3).getPreco()));

            fourth = new Image("file:" +list.get(pos4).getUrlImagem());
            img_fourth.setImage(fourth);
            lb_fourthName.setText(list.get(pos4).getDescricao());
            lb_fourthPrice.setText(String.valueOf(list.get(pos4).getPreco()));
        }catch(NullPointerException e){
        }
    }

    @FXML
    void f_AddFirst(ActionEvent event) { addItem(pos); }

    @FXML
    void f_AddFourth(ActionEvent event) {
        addItem(pos4);
    }

    @FXML
    void f_AddSecond(ActionEvent event) {
        addItem(pos2);
    }

    @FXML
    void f_AddThird(ActionEvent event) { addItem(pos3); }

    @FXML
    void f_removeFirst(ActionEvent event) { removeItem(pos); }

    @FXML
    void f_removeFourth(ActionEvent event) {
        removeItem(pos4);
    }

    @FXML
    void f_removeSecond(ActionEvent event) {
        removeItem(pos2);
    }

    @FXML
    void f_removeThird(ActionEvent event) { removeItem(pos3); }

    @FXML
    void f_FinalizarCompra(ActionEvent event) throws SQLException {
        System.out.println(tp_hora.getValue());
        System.out.println(dp_data.getValue());
        Timestamp t = Timestamp.valueOf(dp_data.getValue().toString() + " " + tp_hora.getValue() + ":"+ tp_hora.getValue().getSecond());
        System.out.println(t);
        CompraDAO.CriarCompra(result, t);
        result.clear();
        lb_finalizar.setText("Compra finalizada com sucesso!");
    }

    private void addItem(int pos) {
        Carrinho c = new Carrinho();
        for(int i = 0 ; i < carrinho.size(); i++) {
            if(carrinho.get(i).getCod_prod() == list.get(pos).getCodigo()) {
                /*System.out.println("c" + carrinho.get(i).getCod_prod());
                 *System.out.println("l" + list.get(pos).getCodigo());
                 *System.out.println(tbv_carrinho.getItems().contains(carrinho.get(i)));
                 * this is working ↓↓↓↓↓ */
                tbv_carrinho.getItems().get(tbv_carrinho.getItems().indexOf(carrinho.get(i))).setQuantidade(carrinho.get(i).getQuantidade()+1);
                tbv_carrinho.getItems().set(tbv_carrinho.getItems().indexOf(carrinho.get(i)), tbv_carrinho.getItems().get(tbv_carrinho.getItems().indexOf(carrinho.get(i))));
                return;
                //carrinho.get(i).setQuantidade(carrinho.get(i).getQuantidade()+1);
                //this one it's worse but it works ↓↓↓↓
                //tbv_carrinho.getItems().get(i).setQuantidade(carrinho.get(i).getQuantidade()+1);
            }
        }

        c.setCod_prod(list.get(pos).getCodigo());
        c.setDescricao(list.get(pos).getDescricao());
        c.setPreco(list.get(pos).getPreco());
        c.setQuantidade(1);
        carrinho.add(c);

        tbv_carrinho.getItems().add(c);
    }

    private void removeItem(int pos) {
        for(int i = 0 ; i < carrinho.size(); i++) {
            if(carrinho.get(i).getCod_prod() == list.get(pos).getCodigo()) {
                if(carrinho.get(i).getQuantidade() > 0) {
                    tbv_carrinho.getItems().get(tbv_carrinho.getItems().indexOf(carrinho.get(i))).setQuantidade(carrinho.get(i).getQuantidade() - 1);
                    tbv_carrinho.getItems().set(tbv_carrinho.getItems().indexOf(carrinho.get(i)), tbv_carrinho.getItems().get(tbv_carrinho.getItems().indexOf(carrinho.get(i))));
                    return;
                }
            }
        }
    }

}
