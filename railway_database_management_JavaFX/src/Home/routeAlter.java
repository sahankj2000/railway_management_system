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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.SQLException;

public class routeAlter extends Application {
    static Stage rareff;
    static MySQL be=new MySQL("routeAlter");
    static int addORupdate; // 1 is for adding and 2 for update
    static String id,di,fa,dep,stID,stName,enID,enName,trainID,trainName;
    @FXML TextField rtID,dist,fares,depTime;
    @FXML ChoiceBox startST,endST,trainSelect;
    @FXML Button rtAUButton;
    @FXML Text rtAUHeader;

    public void setALL(backend.route rt){
        id=rt.getRtID();
        di=rt.getDist();
        fa=rt.getFares();
        dep=rt.getDepTime();
        stID=rt.getStartSTID();
        stName=rt.getStartSTName();
        enID=rt.getEndSTID();
        enName=rt.getEndSTName();
        trainID=rt.getTrainID();
        trainName=rt.getTrainName();
    }

    public static int getAddORupdate() {
        return addORupdate;
    }

    public static void setAddORupdate(int addORupdate) {
        routeAlter.addORupdate = addORupdate;
    }

    @FXML public void initialize() throws SQLException {
        if(addORupdate==1) {
            rtAUHeader.setText("Add Route");
            rtAUButton.setText("Add");
            startST.setItems(be.getStationList());
            endST.setItems(be.getStationList());
            trainSelect.setItems(be.getTrainList());
        }
        else{
            rtAUHeader.setText("Update Route");
            rtAUButton.setText("Update");
            startST.setItems(be.getStationList());
            endST.setItems(be.getStationList());
            trainSelect.setItems(be.getTrainList());
            rtID.setText(id);
            dist.setText(di);
            fares.setText(fa);
            depTime.setText(dep);
            startST.setValue(stID+"   "+stName);
            endST.setValue(enID+"   "+enName);
            trainSelect.setValue(trainID+"   "+trainName);
        }
    }

    @Override public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("routeAlter.fxml"));
        Scene Lscene=new Scene(root,800,600);
        primaryStage.setScene(Lscene);
        primaryStage.setTitle("Add/Update Route");
        primaryStage.setResizable(false);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.getIcons().add(new Image(Login.class.getResourceAsStream("train.png")));
        rareff=primaryStage;
        primaryStage.show();
    }

    public static Stage getRA(){ return rareff; }

    @FXML public void alterRoute() throws SQLException {
        if(addORupdate==1){
            String tid=rtID.getText();
            String tdist=dist.getText();
            String tfares=fares.getText();
            String tdepTime=depTime.getText();
            if(isBlank(tid,tdist,tfares,tdepTime)){
                System.out.println("One or more fields are blank");
                Alert a=new Alert(Alert.AlertType.INFORMATION);
                a.setHeaderText("One or more fields are blank");
                a.show();
            }
            else{
                String tsid=extractID((String)startST.getValue());
                String teid=extractID((String)endST.getValue());
                String ttrain=extractID((String)trainSelect.getValue());
                be.addRoute(tid,tdist,tfares,tdepTime,tsid,teid,ttrain);
                rtID.setText("");
                dist.setText("");
                fares.setText("");
                depTime.setText("");
                startST.setValue("");
                endST.setValue("");
                trainSelect.setValue("");
            }
        }
        else if(addORupdate==2){
            String tid=rtID.getText();
            String tdist=dist.getText();
            String tfares=fares.getText();
            String tdepTime=depTime.getText();
            if(isBlank(tid,tdist,tfares,tdepTime)){
                System.out.println("One or more fields are blank");
                Alert a=new Alert(Alert.AlertType.INFORMATION);
                a.setHeaderText("One or more fields are blank");
                a.show();
            }
            else{
                String tsid=extractID((String)startST.getValue());
                String teid=extractID((String)endST.getValue());
                String ttrain=extractID((String)trainSelect.getValue());
                be.updateRoute(id,tid,tdist,tfares,tdepTime,tsid,teid,ttrain);
                closeRA();
            }
        }
    }

    public boolean isBlank(String a,String b,String c,String d){
        if(a.equals(""))
            return true;
        if(b.equals(""))
            return true;
        if(c.equals(""))
            return true;
        if(d.equals(""))
            return true;
        if(startST.getValue()==null || endST.getValue()==null)
            return true;
        if(trainSelect.getValue()==null)
            return true;
        return false;
    }

    public String extractID(String in){
        String out="";
        int index=0;
        if(in.equals("")||in==null){
            System.out.println("Error in extractID input string was null or empty");
        }
        else{
               while(in.charAt(index)!=' '){
                   out+=in.charAt(index);
                   index++;
               }
        }
        return out;
    }

    public void closeRA(){ rareff.close(); }
}
