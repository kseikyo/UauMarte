package TelaCatalogo;

import ControllerClass.ControllerStart;
import TelaLogin.ControllerTelaLogin;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.jfoenix.transitions.hamburger.HamburgerNextArrowBasicTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class ControllerTelaCatalogo extends Application implements Initializable {

    LinkedList<Image> list = new LinkedList<>();

    @FXML
    private JFXHamburger ham_Back;

    @FXML
    private ImageView img_first;

    @FXML
    private ImageView img_Second;

    @FXML
    private ImageView img_Third;

    @FXML
    private JFXHamburger ham_Next;

    @FXML
    private JFXButton bt_leave;

    @FXML
    private JFXButton bt_user;

    public ControllerStart controllerStart = new ControllerStart();



    @Override
    public void start(Stage primaryStage) throws IOException {

        controllerStart.initScreen("/FXMLFILES/TelaCatalogo.fxml", "", controllerStart.getStage());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //This is just to set the hamburguer as left and right arrow
        //I'm using hamburguer so it has an event handler and it's easy to use
        HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(ham_Back);
        HamburgerNextArrowBasicTransition transition1 = new HamburgerNextArrowBasicTransition(ham_Next);
        transition1.setRate(1);
        transition.setRate(1);
        transition.play();
        transition1.play();



        Image i = new Image("/FXMLFILES/uaumarte.png");


        Image user = new Image(getClass().getResourceAsStream("/FXMLFILES/user.png"));
        bt_user.getStyleClass().removeAll("bt-leave, focus");
        bt_user.getStyleClass().add("bt-leave");
        bt_user.setGraphic(new ImageView(user));

        Image leave = new Image(getClass().getResourceAsStream("/FXMLFILES/leave.png"));
        bt_leave.getStyleClass().removeAll("bt-leave, focus");
        bt_leave.getStyleClass().add("bt-leave");

        bt_leave.setGraphic(new ImageView(leave));



        //This event handlers are gonna be used to iterate over the linked list that has all the products images
        //As the screen will have 5 images, when the left arrow is clicked, it will change every image and
        //description
        ham_Back.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {

        });

        ham_Next.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {

        });
    }

    @FXML
    private void f_goToLogin(ActionEvent event) {
        ControllerTelaLogin controllerTelaLogin = new ControllerTelaLogin();
        controllerTelaLogin.start(controllerStart.getStage());
    }

    @FXML
    private void f_openUserInfo(ActionEvent event) {

    }
}
