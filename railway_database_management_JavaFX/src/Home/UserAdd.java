package Home;

import Login.Login;
import backend.MySQL;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.*;

public class UserAdd extends Application{

    static Stage uaRef;
    static MySQL be=new MySQL("UserAdd");

    @FXML
    TextField id,name,pass,mail,address,phone,uid;

    @Override public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("UserAdd.fxml"));
        Scene Lscene=new Scene(root,800,600);
        primaryStage.setScene(Lscene);
        primaryStage.setTitle("Adding User");
        primaryStage.setResizable(false);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.getIcons().add(new Image(Login.class.getResourceAsStream("train.png")));
        uaRef=primaryStage;
        primaryStage.show();
    }

    public static Stage getUA(){
        return uaRef;
    }

    @FXML public void addUser() throws SQLException {
        String sid=id.getText();
        String sname=name.getText();
        String spass=pass.getText();
        String smail=mail.getText();
        String sadd=address.getText();
        String sphone=phone.getText();
        String suid=uid.getText();
        if(isBlank(sid, sname, spass, smail, sadd, sphone, suid)){
            System.out.println("One or more fields are blank");
            Alert a=new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText("One or more fields are blank");
            a.show();
        }
        else {
            be.addUser(sid, sname, spass, smail, sadd, sphone, suid);
            id.setText("");
            name.setText("");
            pass.setText("");
            mail.setText("");
            address.setText("");
            phone.setText("");
            uid.setText("");
            id.requestFocus();
        }
    }

    public boolean isBlank(String a,String b,String c,String d,String e,String f,String g){
        if(a.equals(""))
            return true;
        if(b.equals(""))
            return true;
        if(c.equals(""))
            return true;
        if(d.equals(""))
            return true;
        if(e.equals(""))
            return true;
        if(f.equals(""))
            return true;
        if(g.equals(""))
            return true;
        return false;
    }

    @FXML public void closeUA(){
        uaRef.close();
    }

}
