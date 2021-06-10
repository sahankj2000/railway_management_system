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

import java.sql.SQLException;


public class stationAlter extends Application {
    static Stage sareff;
    static MySQL be=new MySQL("stationAlter");
    static int addORupdate; // 1 is for adding and 2 for update
    static String id,name,tel;
    @FXML TextField stIDF,stNameF,stTelF;
    @FXML Text stAlterHeader;
    @FXML Button stAUButton;

    public int getAddORupdate(){return addORupdate;}

    public void setAddORupdate(int x){ addORupdate=x; }

    public void setALL(backend.station st){
        id=st.getStID();
        name=st.getStName();
        tel=st.getStTel();
    }

    @FXML public void initialize(){
        if(addORupdate==1) {
            stAlterHeader.setText("Add Station");
            stAUButton.setText("Add");
        }
        else{
            stAlterHeader.setText("Update Train");
            stAUButton.setText("Update");
            stIDF.setText(id);
            stNameF.setText(name);
            stTelF.setText(tel);
        }
    }

    @Override public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("stationAlter.fxml"));
        Scene Lscene=new Scene(root,800,600);
        primaryStage.setScene(Lscene);
        primaryStage.setTitle("Adding Train");
        primaryStage.setResizable(false);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.getIcons().add(new Image(Login.class.getResourceAsStream("train.png")));
        sareff=primaryStage;
        primaryStage.show();
    }

    public static Stage getSA(){ return sareff; }

    @FXML public void alterStation() throws SQLException {
        if(addORupdate==1){
            String tid=stIDF.getText();
            String tname=stNameF.getText();
            String ttel=stTelF.getText();
            if(isBlank(tid, tname,ttel)){
                System.out.println("One or more fields are blank");
                Alert a=new Alert(Alert.AlertType.INFORMATION);
                a.setHeaderText("One or more fields are blank");
                a.show();
            }
            else {
                be.addStation(tid,tname,ttel);
                stIDF.setText("");
                stNameF.setText("");
                stTelF.setText("");
            }
        }
        else if(addORupdate==2){
            String tid=stIDF.getText();
            String tname=stNameF.getText();
            String ttel=stTelF.getText();
            if(isBlank(tid, tname,ttel)){
                System.out.println("One or more fields are blank");
                Alert a=new Alert(Alert.AlertType.INFORMATION);
                a.setHeaderText("One or more fields are blank");
                a.show();
            }
            else {
                be.updateStation(id,tid,tname,ttel);
                stIDF.setText("");
                stNameF.setText("");
                stTelF.setText("");
                closeSA();
            }
        }
        else{
            System.out.println("Error");
            Alert a=new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText("Error");
            a.show();
        }
    }

    public boolean isBlank(String a,String b,String c){
        if(a.equals(""))
            return true;
        if(b.equals(""))
            return true;
        if(c.equals(""))
            return true;
        return false;
    }

    @FXML public void closeSA() { sareff.close(); }
}
