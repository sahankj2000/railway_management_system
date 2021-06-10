package Home;

import Login.Login;
import backend.MySQL;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class trainAlter extends Application {
    static Stage tareff;
    static MySQL be=new MySQL("trainAlter");
    static int addORupdate; // 1 is for adding and 2 for update
    static String id,name;
    @FXML TextField trIDF,trNameF;
    @FXML Text trAlterHeader;
    @FXML Button trAUbutton;

    public int getAddORupdate(){return addORupdate;}

    public void setAddORupdate(int x){ addORupdate=x; }

    public void setIDName(backend.train tr){
        id=tr.getTrID();
        name=tr.getTrName();
    }

    @FXML public void initialize(){
        if(addORupdate==1) {
            trAlterHeader.setText("Add Train");
            trAUbutton.setText("Add");
        }
        else{
            trAlterHeader.setText("Update Train");
            trAUbutton.setText("Update");
            trIDF.setText(id);
            trNameF.setText(name);
        }
    }

    @Override public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("trainAlter.fxml"));
        Scene Lscene=new Scene(root,800,600);
        primaryStage.setScene(Lscene);
        primaryStage.setTitle("Adding Train");
        primaryStage.setResizable(false);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.getIcons().add(new Image(Login.class.getResourceAsStream("train.png")));
        tareff=primaryStage;
        primaryStage.show();
    }

    public static Stage getTA(){
        return tareff;
    }

    @FXML public void alterTrain() throws SQLException {
        if(addORupdate==1){
            String tid=trIDF.getText();
            String tname=trNameF.getText();
            if(isBlank(tid, tname)){
                System.out.println("One or more fields are blank");
                Alert a=new Alert(Alert.AlertType.INFORMATION);
                a.setHeaderText("One or more fields are blank");
                a.show();
            }
            else {
                be.addTrain(tid,tname);
                trIDF.setText("");
                trNameF.setText("");
            }
        }
        else if(addORupdate==2){
            String tid=trIDF.getText();
            String tname=trNameF.getText();
            if(isBlank(tid, tname)){
                System.out.println("One or more fields are blank");
                Alert a=new Alert(Alert.AlertType.INFORMATION);
                a.setHeaderText("One or more fields are blank");
                a.show();
            }
            else {
                be.updateTrain(id,tid,tname);
                trIDF.setText("");
                trNameF.setText("");
                closeTA();
            }
        }
        else{
            System.out.println("Error");
            Alert a=new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText("Error");
            a.show();
        }
    }

    public boolean isBlank(String a,String b){
        if(a.equals(""))
            return true;
        if(b.equals(""))
            return true;
        return false;
    }

    @FXML public void closeTA(){
        tareff.close();
    }
}
