package com.example.railwaymanagement;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.SQLException;

public class ticketAlter extends Application {

    static Stage tkareff;
    static MySQL be=new MySQL("ticketAlter");
    static int addORupdate;
    static String id,name,age,gender,plat,sclass,sroute,sdate,samt,sno;
    @FXML TextField tkID,psName,psAge,seatNo,date;
    @FXML ChoiceBox psGender,platform,tkClass,tkRoute;
    @FXML Button tkAUButton;
    @FXML Text tkAUHeader,amt;

    public void setAll(ticket tk){
        id=tk.getTkID();
        name=tk.getPsName();
        age=tk.getPsAge();
        gender=tk.getPsGender();
        plat=tk.getPlatform();
        sclass=tk.getTkClass();
        sroute=tk.getTkRTID();
        sdate=tk.getTkDate();
        samt=tk.getAmt();
        sno=tk.getSeatNo();
    }

    public static int getAddORupdate() {
        return addORupdate;
    }

    public static void setAddORupdate(int addORupdate) {
        ticketAlter.addORupdate = addORupdate;
    }

    @FXML public void initialize() throws SQLException {
        if(addORupdate==1){
            tkAUHeader.setText("Add ticket");
            tkAUButton.setText("Add");
            tkRoute.setItems(be.getRoutesList());
            psGender.setItems(be.getGenderList());
            platform.setItems(be.getPlatformsList());
            tkClass.setItems(be.getTKClassList());
            tkRoute.setItems(be.getRoutesList());
            date.setText(be.getTodaysDate());
        }
        else{
            tkAUHeader.setText("Update ticket");
            tkAUButton.setText("Update");
            tkRoute.setItems(be.getRoutesList());
            psGender.setItems(be.getGenderList());
            platform.setItems(be.getPlatformsList());
            tkClass.setItems(be.getTKClassList());
            tkID.setText(id);
            psName.setText(name);
            psAge.setText(age);
            seatNo.setText(sno);
            date.setText(sdate);
            amt.setText(samt);
            tkRoute.setValue(be.getRouteWithID(sroute));
            psGender.setValue(gender);
            platform.setValue(plat);
            tkClass.setValue(sclass);
        }
    }

    @Override public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("ticketAlter.fxml"));
        Scene Lscene=new Scene(root,800,600);
        primaryStage.setScene(Lscene);
        primaryStage.setTitle("Add/Update Ticket");
        primaryStage.setResizable(false);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.getIcons().add(new Image(Login.class.getResourceAsStream("train.png")));
        tkareff=primaryStage;
        primaryStage.show();
    }

    public static Stage getTKA(){ return tkareff; }

    public void alterTicket() throws SQLException {
        if(addORupdate==1){
            String tid=tkID.getText();
            String tpsname=psName.getText();
            String tpsage=psAge.getText();
            String tdate=date.getText();
            String tseat=seatNo.getText();
            String tamt=amt.getText();
            if(isBlank(tid,tpsname,tpsage,tdate,tamt,tseat)){
                System.out.println("One or more fields are blank");
                Alert a=new Alert(Alert.AlertType.INFORMATION);
                a.setHeaderText("One or more fields are blank");
                a.show();
            }
            else{
                String trid=extractID((String)tkRoute.getValue());
                String tgen=((String)psGender.getValue());
                String tplat=((String)platform.getValue());
                String tclass=((String)tkClass.getValue());
                be.addTicket(tid,tpsname,tpsage,tgen,tplat,tclass,tamt,tdate,trid,tseat);
                tkID.setText("");
                psName.setText("");
                psAge.setText("");
                date.setText("");
                psGender.setValue("");
                platform.setValue("");
                tkClass.setValue("");
                amt.setText("");
                tkRoute.setValue("");
                seatNo.setText("");
            }
        }
        else if(addORupdate==2){
            genAmt();
            String tid=tkID.getText();
            String tpsname=psName.getText();
            String tpsage=psAge.getText();
            String tdate=date.getText();
            String tamt=amt.getText();
            String tseat=seatNo.getText();
            if(isBlank(tid,tpsname,tpsage,tdate,tamt,tseat)){
                System.out.println("One or more fields are blank");
                Alert a=new Alert(Alert.AlertType.INFORMATION);
                a.setHeaderText("One or more fields are blank");
                a.show();
            }
            else{
                String trid=extractID((String)tkRoute.getValue());
                String tgen=((String)psGender.getValue());
                String tplat=((String)platform.getValue());
                String tclass=((String)tkClass.getValue());
                be.updateTicket(id,tid,tpsname,tpsage,tgen,tplat,tclass,tamt,tdate,trid,tseat);
                closeTKA();
            }
        }
    }

    public boolean isBlank(String a,String b,String c,String d,String e,String f){
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
        if(psGender.getValue()==null || tkClass.getValue()==null)
            return true;
        if(tkRoute.getValue()==null || platform.getValue()==null)
            return true;
        return false;
    }

    public String extractID(String in){
        String out="";
        int index=0;
        if(in==null){
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

    public void genAmt() throws SQLException {
        String cl=(String)tkClass.getValue();
        String id=(String)tkRoute.getValue();
        try {
            int fare = be.getFare((extractID(id)));
            System.out.println(fare);
            double iamt = 0;
            if (cl.equals("1A"))
                iamt = fare * 4;
            else if (cl.equals("2A"))
                iamt = fare * 3.5;
            else if (cl.equals("3A"))
                iamt = fare * 3;
            else if (cl.equals("FC"))
                iamt = fare * 2.5;
            else if (cl.equals("CC"))
                iamt = fare * 2.0;
            else if (cl.equals("SL"))
                iamt = fare * 1.5;
            else
                iamt = fare;
            amt.setText(""+iamt);

        }catch(Exception e){
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setHeaderText("Route or Class is not selected");
                System.out.println(e.getMessage());
                a.show();
        }
    }

    public void closeTKA(){ tkareff.close(); }
}
