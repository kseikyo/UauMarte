package TelaCatalogo;

import ControllerClass.ControllerStart;
import DAO.ProdutoDAO;
import Objetos.Carrinho;
import Objetos.Produto;
import TelaLogin.ControllerTelaLogin;
import com.jfoenix.controls.JFXButton;
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
    private TableView<Carrinho> tbv_carrinho;

    @FXML
    private TableColumn<Carrinho, String> col_nome;

    @FXML
    private TableColumn<Carrinho, Double> col_preco;

    @FXML
    private TableColumn<Carrinho, Double> col_quantidade;

    private LinkedList<Carrinho> carrinho = new LinkedList<>();
    private int pos = 0;
    private int pos2 = 1;
    private int pos3 = 2;
    private int pos4 = 3;

    private static int once = 0;

    private Image first = null;
    private Image second = null;
    private Image third = null;
    private Image fourth = null;

    public ControllerStart controllerStart = new ControllerStart();



    @Override
    public void start(Stage primaryStage) throws IOException {
        controllerStart.initScreen("/FXMLFILES/TelaCatalogo.fxml", "", controllerStart.getStage());

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.initProdutos();


        //ALL THIS LINES ARE USED TO SET THE BUTTONS TO BE SOME ICON
            Image user = new Image(getClass().getResourceAsStream("/FXMLFILES/user.png"));
            bt_user.getStyleClass().removeAll("bt-leave, focus");
            bt_user.getStyleClass().add("bt-leave");
            bt_user.setGraphic(new ImageView(user));

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

            //Config of tableview

        col_nome.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        col_preco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        col_quantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        ObservableList<Carrinho> result = FXCollections.observableArrayList() ;
        tbv_carrinho.setItems(result);

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
        pos++;
        pos2++;
        pos3++;
        pos4++;
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
    void f_AddFirst(ActionEvent event) {

        Carrinho c = new Carrinho();
        c.setCod_prod(list.get(pos).getCodigo());
        c.setDescricao(list.get(pos).getDescricao());
        c.setPreco(list.get(pos).getPreco());
        c.setQuantidade(1);
        //carrinho.add()
        //tbv_carrinho.getItems().add()
    }

    @FXML
    void f_AddFourth(ActionEvent event) {

    }

    @FXML
    void f_AddSecond(ActionEvent event) {

    }

    @FXML
    void f_AddThird(ActionEvent event) {

    }

}
