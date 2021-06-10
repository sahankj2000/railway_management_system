package Login;

import Home.Home;
import backend.MySQL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
    static Home hobj=new Home();
    static Login lobj=new Login();

    static MySQL be=new MySQL("LoginController");

    @FXML Button loginButton,cancelButton;
    @FXML TextField userID,password;

    @FXML public void handleLogin(ActionEvent event) throws Exception{
        if(be.login(userID.getText(),password.getText())) {
            System.out.println("Logged in with id : "+userID.getText());
            lobj.getStage().close();
            hobj.start(new Stage());
        }
        else{
            System.out.println("Wrong User Name or Password");
            Alert a=new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText("Wrong User Name or Password");
            a.show();
        }
    }

    @FXML public void handleCancel(ActionEvent event){
        System.exit(0);
    }

}
