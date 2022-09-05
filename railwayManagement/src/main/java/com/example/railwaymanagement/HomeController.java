package com.example.railwaymanagement;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML Button test,test2;
    @FXML TableView<user> userTable;
    @FXML TableView<train> trainTable;
    @FXML TableView<station> stTable;
    @FXML TableView<route> rtTable;
    @FXML TableView<ticket> tkTable;
    @FXML TableColumn<user,String> userID,userName,userMail;
    @FXML TableColumn<train,String> trID,trName;
    @FXML TableColumn<station,String> stID,stName,stTel;
    @FXML TableColumn<route,String> rtID,dist,fares,depTime,startSTID,startSTName,endSTID,endSTName,trainID,trainName;
    @FXML TableColumn<ticket,String> tkID,psName,psAge,psGender,platform,tkClass,tkDate,amt,tkRTID,tkStartST,tkEndST,seatNO;
    @FXML TextField trSearchBox,stSearchField,rtSearchBox,tkSearchBox;

    static ObservableList usersOL,trainsOL,stOL,rtOL,tkOL;

    static MySQL be=new MySQL("HomeController");

    UserAdd ua=new UserAdd();
    trainAlter ta=new trainAlter();
    stationAlter sa=new stationAlter();
    routeAlter ra=new routeAlter();
    ticketAlter tka=new ticketAlter();

    @FXML public void userAdd() throws Exception{
        ua.start(new Stage());
    }

    @FXML public void trainAlter() throws Exception {
        ta.setAddORupdate(1);
        ta.start(new Stage());
    }

    @FXML public void trainUpdate() throws Exception {
        if(ta.getAddORupdate()==2) {
            ta.start(new Stage());
        }
        else{
            Alert a=new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText("Select a row to edit");
            System.out.println("Select a row to edit");
            a.show();
        }
    }

    @FXML public void trainDelete() throws Exception {
        if(ta.getAddORupdate()==2) {
            try {
                be.deleteTrain(trainTable.getSelectionModel().getSelectedItem().getTrID());
            }catch (NullPointerException e){
                Alert a=new Alert(Alert.AlertType.INFORMATION);
                a.setHeaderText("Select a row to delete");
                System.out.println("Select a row to delete");
                a.show();
            }
        }
        else{
            Alert a=new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText("Select a row to delete");
            System.out.println("Select a row to delete");
            a.show();
        }
    }

    @FXML public void stationAlter() throws Exception {
        sa.setAddORupdate(1);
        sa.start(new Stage());
    }

    @FXML public void stationUpdate() throws Exception {
        if(sa.getAddORupdate()==2) {
            sa.start(new Stage());
        }
        else{
            Alert a=new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText("Select a row to edit");
            System.out.println("Select a row to edit");
            a.show();
        }
    }

    @FXML public void stationDelete() throws Exception {
        if(sa.getAddORupdate()==2) {
            try {
                be.deleteStation(stTable.getSelectionModel().getSelectedItem().getStID());
            }catch (NullPointerException e){
                Alert a=new Alert(Alert.AlertType.INFORMATION);
                a.setHeaderText("Select a row to delete");
                System.out.println("Select a row to delete");
                a.show();
            }
        }
        else{
            Alert a=new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText("Select a row to delete");
            System.out.println("Select a row to delete");
            a.show();
        }
    }


    @FXML public void routeAlter() throws Exception {
        ra.setAddORupdate(1);
        ra.start(new Stage());
    }

    @FXML public void routeUpdate() throws Exception {
        if(ra.getAddORupdate()==2) {
            ra.start(new Stage());
        }
        else{
            Alert a=new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText("Select a row to edit");
            System.out.println("Select a row to edit");
            a.show();
        }
    }

    @FXML public void routeDelete() throws Exception {
        if(ra.getAddORupdate()==2) {
            try {
                be.deleteRoute(rtTable.getSelectionModel().getSelectedItem().getRtID());
            }catch (NullPointerException e){
                Alert a=new Alert(Alert.AlertType.INFORMATION);
                a.setHeaderText("Select a row to delete");
                System.out.println("Select a row to delete");
                a.show();
            }
        }
        else{
            Alert a=new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText("Select a row to delete");
            System.out.println("Select a row to delete");
            a.show();
        }
    }

    @FXML public void ticketAlter() throws Exception {
        tka.setAddORupdate(1);
        tka.start(new Stage());
    }

    @FXML public void ticketUpdate() throws Exception {
        if(tka.getAddORupdate()==2) {
            tka.start(new Stage());
        }
        else{
            Alert a=new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText("Select a row to edit");
            System.out.println("Select a row to edit");
            a.show();
        }
    }

    @FXML public void ticketDelete() throws SQLException {
        if(tka.getAddORupdate()==2) {
            try {
                be.deleteTicket(tkTable.getSelectionModel().getSelectedItem().getTkID());
            }catch (NullPointerException e){
                Alert a=new Alert(Alert.AlertType.INFORMATION);
                a.setHeaderText("Select a row to delete");
                System.out.println("Select a row to delete");
                a.show();
            }
        }
        else{
            Alert a=new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText("Select a row to delete");
            System.out.println("Select a row to delete");
            a.show();
        }
    }

    @FXML public void funTest2(){
        try {
            be.login("418076","bla");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override public void initialize(URL url, ResourceBundle rs){
        try {
            //initUserTable();
            initTrainTable();
            initStationTable();
            initRouteTable();
            initTicketTable();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void initUserTable() throws SQLException {
        userID.setCellValueFactory(new PropertyValueFactory<user,String>("userID"));
        userName.setCellValueFactory(new PropertyValueFactory<user,String>("userName"));
        userMail.setCellValueFactory(new PropertyValueFactory<user,String>("userMail"));
        usersOL=be.getUsers();
        userTable.setItems(usersOL);
        userTable.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        userTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                    if(mouseEvent.getClickCount() == 2){
                        System.out.println("Double clicked");
                    }
                }
            }
        });
    }

    public void initTrainTable() throws SQLException {
        trID.setCellValueFactory(new PropertyValueFactory<train,String>("trID"));
        trName.setCellValueFactory(new PropertyValueFactory<train,String>("trName"));
        trainsOL=be.getTrains();
        trainTable.setItems(trainsOL);
        trainTable.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        trainTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                    if(mouseEvent.getClickCount() == 2){
                        ta.setAddORupdate(2);
                        ta.setIDName(trainTable.getSelectionModel().getSelectedItem());
                        try {
                            ta.start(new Stage());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    else{
                        ta.setAddORupdate(2);
                        ta.setIDName(trainTable.getSelectionModel().getSelectedItem());
                    }
                }
            }
        });
        FilteredList<train> filteredTrain=new FilteredList<>(trainsOL,p->true);
        trSearchBox.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredTrain.setPredicate(train -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (String.valueOf(train.getTrID()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(train.getTrName()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
        SortedList<train> sortedData = new SortedList<>(filteredTrain);
        sortedData.comparatorProperty().bind(trainTable.comparatorProperty());
        trainTable.setItems(sortedData);
    }

    public void initStationTable() throws SQLException {
        stID.setCellValueFactory(new PropertyValueFactory<station,String>("stID"));
        stName.setCellValueFactory(new PropertyValueFactory<station,String>("stName"));
        stTel.setCellValueFactory(new PropertyValueFactory<station,String>("stTel"));
        stOL=be.getStations();
        stTable.setItems(stOL);
        stTable.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        stTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                    if(mouseEvent.getClickCount() == 2){
                        sa.setAddORupdate(2);
                        sa.setALL(stTable.getSelectionModel().getSelectedItem());
                        try {
                            sa.start(new Stage());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    else{
                        sa.setAddORupdate(2);
                        sa.setALL(stTable.getSelectionModel().getSelectedItem());
                    }
                }
            }
        });
        FilteredList<station> filteredTrain=new FilteredList<>(stOL,p->true);
        stSearchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredTrain.setPredicate(train -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (String.valueOf(train.getStID()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(train.getStName()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }else if (String.valueOf(train.getStTel()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
        SortedList<station> sortedData = new SortedList<>(filteredTrain);
        sortedData.comparatorProperty().bind(stTable.comparatorProperty());
        stTable.setItems(sortedData);
    }

    public void initRouteTable() throws SQLException {
        rtID.setCellValueFactory(new PropertyValueFactory<route,String>("rtID"));
        dist.setCellValueFactory(new PropertyValueFactory<route,String>("dist"));
        fares.setCellValueFactory(new PropertyValueFactory<route,String>("fares"));
        depTime.setCellValueFactory(new PropertyValueFactory<route,String>("depTime"));
        startSTID.setCellValueFactory(new PropertyValueFactory<route,String>("startSTID"));
        startSTName.setCellValueFactory(new PropertyValueFactory<route,String>("startSTName"));
        endSTID.setCellValueFactory(new PropertyValueFactory<route,String>("endSTID"));
        endSTName.setCellValueFactory(new PropertyValueFactory<route,String>("endSTName"));
        trainID.setCellValueFactory(new PropertyValueFactory<route,String>("trainID"));
        trainName.setCellValueFactory(new PropertyValueFactory<route,String>("trainName"));
        rtOL=be.getRoutes();
        rtTable.setItems(rtOL);
        rtTable.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        rtTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                    if(mouseEvent.getClickCount() == 2){
                        ra.setAddORupdate(2);
                        ra.setALL(rtTable.getSelectionModel().getSelectedItem());
                        try {
                            ra.start(new Stage());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    else{
                        ra.setAddORupdate(2);
                        ra.setALL(rtTable.getSelectionModel().getSelectedItem());
                    }
                }
            }
        });
        FilteredList<route> filteredRoute=new FilteredList<>(rtOL,p->true);
        rtSearchBox.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredRoute.setPredicate(route -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (String.valueOf(route.getRtID()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(route.getFares()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(route.getDist()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(route.getStartSTName()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(route.getEndSTName()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(route.getDepTime()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(route.getTrainID()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(route.getTrainName()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
        SortedList<route> sortedData = new SortedList<>(filteredRoute);
        sortedData.comparatorProperty().bind(rtTable.comparatorProperty());
        rtTable.setItems(sortedData);
    }

    public void initTicketTable() throws SQLException {
        tkID.setCellValueFactory(new PropertyValueFactory<ticket,String>("tkID"));
        psName.setCellValueFactory(new PropertyValueFactory<ticket,String>("psName"));
        psAge.setCellValueFactory(new PropertyValueFactory<ticket,String>("psAge"));
        psGender.setCellValueFactory(new PropertyValueFactory<ticket,String>("psGender"));
        platform.setCellValueFactory(new PropertyValueFactory<ticket,String>("platform"));
        tkClass.setCellValueFactory(new PropertyValueFactory<ticket,String>("tkClass"));
        tkDate.setCellValueFactory(new PropertyValueFactory<ticket,String>("tkDate"));
        amt.setCellValueFactory(new PropertyValueFactory<ticket,String>("amt"));
        tkRTID.setCellValueFactory(new PropertyValueFactory<ticket,String>("tkID"));
        tkStartST.setCellValueFactory(new PropertyValueFactory<ticket,String>("tkStartST"));
        tkEndST.setCellValueFactory(new PropertyValueFactory<ticket,String>("tkEndST"));
        seatNO.setCellValueFactory(new PropertyValueFactory<ticket,String>("seatNo"));
        tkOL=be.getTicket();
        tkTable.setItems(tkOL);
        tkTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                    if(mouseEvent.getClickCount() == 2){
                        tka.setAddORupdate(2);
                        tka.setAll(tkTable.getSelectionModel().getSelectedItem());
                        try {
                            tka.start(new Stage());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    else{
                        tka.setAddORupdate(2);
                        tka.setAll(tkTable.getSelectionModel().getSelectedItem());
                    }
                }
            }
        });
        FilteredList<ticket> filteredTicket=new FilteredList<>(tkOL,p->true);
        tkSearchBox.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredTicket.setPredicate(ticket -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (String.valueOf(ticket.getTkID()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(ticket.getPsName()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(ticket.getPsAge()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(ticket.getTkRTID()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(ticket.getPlatform()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(ticket.getPlatform()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(ticket.getTkStartST()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(ticket.getTkEndST()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
        SortedList<ticket> sortedData = new SortedList<>(filteredTicket);
        sortedData.comparatorProperty().bind(tkTable.comparatorProperty());
        tkTable.setItems(sortedData);
    }

    public void refresh() throws SQLException {
        //userTable.getItems().clear();
        //initUserTable();
        initTrainTable();
        initStationTable();
        initRouteTable();
        initTicketTable();
        System.out.println("Refreshed");
    }


}
