package TelaCatalogo;

import ControllerClass.ControllerStart;
import DAO.ProdutoDAO;
import Objetos.Produto;
import TelaLogin.ControllerTelaLogin;
import com.jfoenix.controls.JFXButton;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

    private int pos = 0;
    private int once = 0;

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

        Image i = new Image("/FXMLFILES/uaumarte.png");

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

        //This event handlers are gonna be used to iterate over the linked list that has all the products images
        //As the screen will have 5 images, when the left arrow is clicked, it will change every image and
        //description

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
        pos--;
        System.out.println(pos);
        if(checkListLeft(pos)) {
            try {
                first = new Image(list.get(pos).getUrlImagem());
                img_first.setImage(first);
                second = new Image(list.get(pos+1).getUrlImagem());
                img_second.setImage(second);
                third = new Image(list.get(pos+2).getUrlImagem());
                img_third.setImage(third);
            }catch (NullPointerException e){
                e.getMessage();
            }

        }
    }

    @FXML
    private void f_LoadRight() {
        pos++;
        System.out.println(pos);
        if(checkListRight(pos)) {
            try {
                first = new Image(list.get(pos).getUrlImagem());
                img_first.setImage(first);

                second = new Image(list.get(pos + 1).getUrlImagem());
                img_second.setImage(second);

                third = new Image(list.get(pos + 2).getUrlImagem());
                img_third.setImage(third);

                fourth = new Image(list.get(pos + 3).getUrlImagem());
                img_fourth.setImage(fourth);
            }catch(NullPointerException e){
                e.getMessage();
            }
        }

    }

    private void initProdutos() {
        if(once > 0)
            return;
        list = ProdutoDAO.retornarTodosProdutos();

        try {
            first = new Image(list.get(pos).getUrlImagem());
            img_first.setImage(first);
        }catch (Exception e){

        }
        try {
            second = new Image(list.get(pos+1).getUrlImagem());
            img_second.setImage(second);
        }catch (Exception e){

        }
        try {
            third = new Image(list.get(pos+2).getUrlImagem());
            img_third.setImage(third);
        }catch (Exception e){

        }
        once++;
    }

    private boolean checkListLeft(int pos) {
        if(pos >= 1) return true;
        return false;
    }

    private boolean checkListRight(int pos) {
        try{
            if(pos+3 < list.size())
                return true;
        }catch (IndexOutOfBoundsException e) {
        }
        return false;
    }

    public LinkedList<Produto> getList() {
        return this.list;
    }

}
