package com.example.railwaymanagement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class MySQL {

    static Connection con;
    static PreparedStatement pst;
    static String mysql="com.mysql.jdbc.Driver";
    static String mysqlURL="jdbc:mysql://localhost:3306/Railway";
    static String mariadb="org.mariadb.jdbc.Driver";
    static String mariadbURL="jdbc:mariadb://localhost/Railway";
    static String user="root";
    static String password="";

    ResultSet rs;

    static String[] userID;

    public MySQL(String className) {
        try {
            Class.forName(mysql);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            con = DriverManager.getConnection(mysqlURL,user,password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("Connection Established with "+className+" : "+con);
    }

    public void addUser(String id,String name,String password,String mail,String address,String phone,String uid) throws SQLException {
        pst=con.prepareStatement("insert into User(user_id,user_name,user_pass,user_mail,user_add,user_phone,user_uid)values(?,?,?,?,?,?,?)");
        pst.setString(1,id);
        pst.setString(2,name);
        pst.setString(3,password);
        pst.setString(4,mail);
        pst.setString(5,address);
        pst.setString(6,phone);
        pst.setString(7,uid);
        try {
            int k = pst.executeUpdate();
            if (k == 1) {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setHeaderText("User Added Successfully");
                System.out.println("User Added Successfully");
                a.show();
            } else {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setHeaderText("User Adding Failed");
                System.out.println("User Adding Failed");
                a.show();
            }
        }catch (Exception e){
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText("User Adding Failed : Probable Error : ( ID Already exist!!! )");
            System.out.println("User Adding Failed : Probable Error : ( ID Already exist!!! )");
            a.show();
        }
    }

    public void addTrain(String tID,String tName) throws SQLException {
        pst=con.prepareStatement("insert into Train values(?,?)");
        pst.setString(1,tID);
        pst.setString(2,tName);
        try {
            int k = pst.executeUpdate();
            if (k == 1) {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setHeaderText("Train Added Successfully");
                System.out.println("Train Added Successfully");
                a.show();
            } else {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setHeaderText("Train Adding Failed");
                System.out.println("Train Adding Failed");
                a.show();
            }
        }catch(Exception e){
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText("Train Adding Failed : Probable Error : ( ID Already exist!!! )");
            System.out.println("Train Adding Failed : Probable Error : ( ID Already exist!!! )");
            a.show();
        }
    }

    public void updateTrain(String sid,String did,String name) throws SQLException {
        pst=con.prepareStatement("update Train set Tr_name=?,Tr_id=? where Tr_id=? ");
        pst.setString(1,name);
        pst.setString(2,did);
        pst.setString(3,sid);
        int k=pst.executeUpdate();
        if(k==1){
            Alert a=new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText("Train Updated Successfully");
            System.out.println("Train Updated Successfully");
            a.show();
        }
        else{
            Alert a=new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText("Train Update Failed");
            System.out.println("Train Update Failed");
            a.show();
        }
    }

    public void deleteTrain(String id) throws SQLException {
        pst=con.prepareStatement("delete from Train where Tr_id=?");
        pst.setString(1,id);
        int k=pst.executeUpdate();
        if(k==1){
            Alert a=new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText("Train Deleted Successfully");
            System.out.println("Train Deleted Successfully");
            a.show();
        }
        else{
            Alert a=new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText("Train Deleted Failed");
            System.out.println("Train Deleted Failed");
            a.show();
        }

    }

    public void addStation(String sid,String sname,String stel) throws SQLException {
        pst=con.prepareStatement("insert into Station values(?,?,?)");
        pst.setString(1,sid);
        pst.setString(2,sname);
        pst.setString(3,stel);
        try {
            int k = pst.executeUpdate();
            if(k==1){
                Alert a=new Alert(Alert.AlertType.INFORMATION);
                a.setHeaderText("Station Added Successfully");
                System.out.println("Station Added Successfully");
                a.show();
            }
            else{
                Alert a=new Alert(Alert.AlertType.INFORMATION);
                a.setHeaderText("Station Adding Failed");
                System.out.println("Station Adding Failed");
                a.show();
            }
        }catch (Exception e){
            Alert a=new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText("Station Adding Failed : Probable Error : ( ID Already exist!!! )");
            System.out.println("Station Adding Failed : Probable Error : ( ID Already exist!!! )");
            a.show();
        }
    }

    public void updateStation(String sid,String nid,String name,String tel) throws SQLException {
        pst=con.prepareStatement("update Station set st_id=?,st_name=?,st_tel=? where st_id=?");
        pst.setString(1,nid);
        pst.setString(2,name);
        pst.setString(3,tel);
        pst.setString(4,sid);
        int k=pst.executeUpdate();
        if(k==1){
            Alert a=new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText("Station Updated Successfully");
            System.out.println("Station Updated Successfully");
            a.show();
        }
        else{
            Alert a=new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText("Station Update Failed");
            System.out.println("Station Update Failed");
            a.show();
        }
    }

    public void deleteStation(String id) throws SQLException {
        pst=con.prepareStatement("delete from Station where st_id=?");
        pst.setString(1,id);
        int k=pst.executeUpdate();
        if(k==1){
            Alert a=new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText("Station Deleted Successfully");
            System.out.println("Station Deleted Successfully");
            a.show();
        }
        else{
            Alert a=new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText("Station Deleted Failed");
            System.out.println("Station Deleted Failed");
            a.show();
        }
    }

    public void addRoute(String id,String di,String fa,String dep,String st,String en,String tr) throws SQLException {
        pst=con.prepareStatement("insert into Routes values(?,?,?,?,?,?,?)");
        pst.setString(1,id);
        pst.setString(2,di);
        pst.setString(3,fa);
        pst.setString(4,dep);
        pst.setString(5,st);
        pst.setString(6,en);
        pst.setString(7,tr);
        try {
            int k = pst.executeUpdate();
            if(k==1){
                Alert a=new Alert(Alert.AlertType.INFORMATION);
                a.setHeaderText("Route Added Successfully");
                System.out.println("Route Added Successfully");
                a.show();
            }
            else{
                Alert a=new Alert(Alert.AlertType.INFORMATION);
                a.setHeaderText("Route Adding Failed");
                System.out.println("Route Adding Failed");
                a.show();
            }
        }catch (Exception e){
            Alert a=new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText("Route Adding Failed : Probable Error : ( ID Already exist!!! ) or ( Error in time format )");
            System.out.println("Route Adding Failed : Probable Error : ( ID Already exist!!! ) or ( Error in time format )");
            a.show();
        }
    }

    public void updateRoute(String sid,String id,String di,String fa,String dep,String st,String en,String tr) throws SQLException {
        pst=con.prepareStatement("update Routes set rt_id=?,dist=?,fares=?,dep_time=?,s_stID=?,e_stID=?,tr_id=? where rt_id=?");
        pst.setString(1,id);
        pst.setString(2,di);
        pst.setString(3,fa);
        pst.setString(4,dep);
        pst.setString(5,st);
        pst.setString(6,en);
        pst.setString(7,tr);
        pst.setString(8,sid);
        try {
            int k = pst.executeUpdate();
            if(k==1){
                Alert a=new Alert(Alert.AlertType.INFORMATION);
                a.setHeaderText("Route Updated Successfully");
                System.out.println("Route Updated Successfully");
                a.show();
            }
            else{
                Alert a=new Alert(Alert.AlertType.INFORMATION);
                a.setHeaderText("Route Updating Failed");
                System.out.println("Route Updating Failed");
                a.show();
            }
        }catch (Exception e){
            Alert a=new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText("Route Updating Failed : Probable Error : ( ID Already exist!!! ) or ( Error in time format )");
            System.out.println("Route Updating Failed : Probable Error : ( ID Already exist!!! ) or ( Error in time format )");
            a.show();
        }
    }

    public void deleteRoute(String id) throws SQLException {
        pst=con.prepareStatement("delete from Routes where rt_id=?");
        pst.setString(1,id);
        int k=pst.executeUpdate();
        if(k==1){
            Alert a=new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText("Route Deleted Successfully");
            System.out.println("Route Deleted Successfully");
            a.show();
        }
        else{
            Alert a=new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText("Route Deletion Failed");
            System.out.println("Route Deletion Failed");
            a.show();
        }
    }

    public void addTicket(String tkID,String psName,String psAge,String psGender,String platform,String tkClass,String amt,String tkDate,String tkRTID,String seatNo) throws SQLException {
        pst=con.prepareStatement("insert into Tickets values(?,?,?,?,?,?,?,?,?,?)");
        pst.setString(1,tkID);
        pst.setString(2,psName);
        pst.setString(3,psAge);
        pst.setString(4,psGender);
        pst.setString(5,platform);
        pst.setString(6,tkClass);
        pst.setString(7,amt);
        pst.setString(8,tkDate);
        pst.setString(9,tkRTID);
        pst.setString(10,seatNo);
        try {
            int k = pst.executeUpdate();
            if(k==1){
                Alert a=new Alert(Alert.AlertType.INFORMATION);
                a.setHeaderText("Ticket Added Successfully");
                System.out.println("Ticket Added Successfully");
                a.show();
            }
            else{
                Alert a=new Alert(Alert.AlertType.INFORMATION);
                a.setHeaderText("Ticket Adding Failed");
                System.out.println("Ticket Adding Failed");
                a.show();
            }
        }catch (Exception e){
            Alert a=new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText("Ticket Adding Failed : Probable Error : ( ID Already exist!!! ) or ( Error in date format )");
            System.out.println("Ticket Adding Failed : Probable Error : ( ID Already exist!!! ) or ( Error in date format )");
            a.show();
        }
    }

    public void updateTicket(String sid,String tkID,String psName,String psAge,String psGender,String platform,String tkClass,String amt,String tkDate,String tkRTID,String seatNo) throws SQLException {
        pst=con.prepareStatement("update Tickets set tk_id=?,ps_name=?,ps_age=?,ps_gender=?,platform=?,tk_class=?,amt=?,tk_date=?,rt_id=?,seat_no=? where tk_id=?");
        pst.setString(1,tkID);
        pst.setString(2,psName);
        pst.setString(3,psAge);
        pst.setString(4,psGender);
        pst.setString(5,platform);
        pst.setString(6,tkClass);
        pst.setString(7,amt);
        pst.setString(8,tkDate);
        pst.setString(9,tkRTID);
        pst.setString(10,seatNo);
        pst.setString(11,sid);
        try {
            int k = pst.executeUpdate();
            if(k==1){
                Alert a=new Alert(Alert.AlertType.INFORMATION);
                a.setHeaderText("Ticket Updated Successfully");
                System.out.println("Ticket Updated Successfully");
                a.show();
            }
            else{
                Alert a=new Alert(Alert.AlertType.INFORMATION);
                a.setHeaderText("Ticket Updation Failed");
                System.out.println("Ticket Updation Failed");
                a.show();
            }
        }catch (Exception e){
            Alert a=new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText("Ticket Updation Failed : Probable Error : ( ID Already exist!!! ) or ( Error in date format )");
            System.out.println("Ticket Updation Failed : Probable Error : ( ID Already exist!!! ) or ( Error in date format )");
            a.show();
        }
    }

    public void deleteTicket(String id) throws SQLException {
        pst=con.prepareStatement("delete from Tickets where tk_id=?");
        pst.setString(1,id);
        int k=pst.executeUpdate();
        if(k==1){
            Alert a=new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText("Ticket Deleted Successfully");
            System.out.println("Ticket Deleted Successfully");
            a.show();
        }
        else{
            Alert a=new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText("Ticket Deletion Failed");
            System.out.println("Ticket Deletion Failed");
            a.show();
        }
    }

    public boolean login(String id,String pass) throws SQLException {
        if(!(isValidID(id))){
            return false;
        }
        pst=con.prepareStatement("select user_pass from User where user_id="+id);
        rs=pst.executeQuery();
        //ResultSetMetaData rsd=rs.getMetaData();
        String rsPass="";
        while (rs.next()){
            rsPass=rs.getString("user_pass");
        }
        if(rsPass.equals("")){
            return false;
        }
        if(rsPass.equals(pass)){
            return true;
        }
        return false;
    }

    public boolean isValidID(String st){
        if(st.equals("")||st.length()>10){
            return false;
        }
        char[] ch=st.toCharArray();
        for(char x:ch){
            switch(x){
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9': continue;
                default:return false;

            }
        }
        return true;
    }

    public int getUserCount() throws SQLException {
        pst=con.prepareStatement("select count(*) from User");
        rs=pst.executeQuery();
        int x=0;
        while (rs.next()){
            x=Integer.parseInt(rs.getString(1));
        };
        return x;
    }

    public ObservableList getUsers() throws SQLException {
        pst=con.prepareStatement("select * from User");
        rs=pst.executeQuery();
        ObservableList<user> people= FXCollections.observableArrayList();
        while (rs.next()){
            people.add(new user(rs.getString("user_id"),rs.getString("user_name"),rs.getString("user_pass"),rs.getString("user_mail"),rs.getString("user_add"),rs.getString("user_phone"),rs.getString("user_uid")));
        }
        return people;
    }

    public ObservableList getTrains() throws SQLException {
        pst=con.prepareStatement("select * from Train");
        rs=pst.executeQuery();
        ObservableList<train> trains=FXCollections.observableArrayList();
        while(rs.next()){
            trains.add(new train(rs.getString("Tr_id"),rs.getString("Tr_name")));
        }
        return trains;
    }

    public ObservableList getStations() throws SQLException {
        pst=con.prepareStatement("select * from Station");
        rs=pst.executeQuery();
        ObservableList<station> stations=FXCollections.observableArrayList();
        while (rs.next()){
            stations.add(new station(rs.getString("st_id"),rs.getString("st_name"),rs.getString("st_tel")));
        }
        return stations;
    }

    public ObservableList getStationList() throws SQLException {
        pst=con.prepareStatement("select * from Station");
        rs=pst.executeQuery();
        ObservableList st=FXCollections.observableArrayList();
        while(rs.next()){
            st.add((rs.getString("st_id"))+"   "+(rs.getString("st_name")));
        }
        return st;
    }

    public ObservableList getTrainList() throws SQLException {
        pst=con.prepareStatement("select * from Train");
        rs=pst.executeQuery();
        ObservableList tr=FXCollections.observableArrayList();
        while(rs.next()){
            tr.add((rs.getString("Tr_id"))+"   "+(rs.getString("Tr_name")));
        }
        return tr;
    }

    public ObservableList getRoutes() throws SQLException {
        pst=con.prepareStatement("select * from rtView");
        rs=pst.executeQuery();
        ObservableList<route> routes=FXCollections.observableArrayList();
        while (rs.next()){
            routes.add(new route(rs.getString("rt_id"),rs.getString("dist"),rs.getString("fares"),rs.getString("dep_time"),rs.getString("startID"),rs.getString("startName"),rs.getString("endID"),rs.getString("endName"),rs.getString("trainID"),rs.getString("trainName")));
        }
        return routes;
    }

    public ObservableList getRoutesList() throws SQLException {
        pst=con.prepareStatement("select startName,endName,rt_id,dep_time from rtView");
        rs= pst.executeQuery();
        ObservableList rt=FXCollections.observableArrayList();
        while(rs.next()){
            rt.add(rs.getString("rt_id")+"   "+rs.getString("startName")+" to "+rs.getString("endName")+" at "+rs.getString("dep_time"));
        }
        return rt;
    }

    public ObservableList getGenderList(){
        ObservableList gen=FXCollections.observableArrayList();
        gen.addAll("M","F","O");
        return gen;
    }

    public ObservableList getPlatformsList(){
        ObservableList plat=FXCollections.observableArrayList();
        plat.addAll("1","2","3","4");
        return plat;
    }

    public ObservableList getTKClassList(){
        ObservableList tkClass=FXCollections.observableArrayList();
        tkClass.addAll("1A","2A","3A","FC","CC","SL","2S");
        return tkClass;
    }

    public String getTodaysDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    public int s(String id) throws SQLException {
        pst=con.prepareStatement("select fares from Routes where rt_id=?");
        pst.setString(1,id);
        rs=pst.executeQuery();
        rs.next();
        return strTOint(rs.getString("fares"));
    }
    public int strTOint(String num){
        int len=num.length();
        int i=0,n=0;
        while(i<len){
            switch(num.charAt(i)){
                case '0': n=n*10+0;
                break;
                case '1': n=n*10+1;
                break;
                case '2': n=n*10+0;
                break;
                case '3': n=n*10+1;
                break;
                case '4': n=n*10+0;
                break;
                case '5': n=n*10+1;
                break;
                case '6': n=n*10+0;
                break;
                case '7': n=n*10+1;
                break;
                case '8': n=n*10+0;
                break;
                case '9': n=n*10+1;
                break;
                default:System.out.println("Error in strToint");
            }
            i++;
        }
        return n;
    }

    public ObservableList getTicket() throws SQLException {
        pst=con.prepareStatement("select * from tkView");
        rs=pst.executeQuery();
        ObservableList<ticket> tickets=FXCollections.observableArrayList();
        while (rs.next()){
            tickets.add(new ticket(rs.getString("tk_id"),rs.getString("ps_name"),rs.getString("ps_age"),rs.getString("ps_gender"),rs.getString("platform"),rs.getString("tk_class"),rs.getString("tk_date"),rs.getString("amt"),rs.getString("rt_id"),rs.getString("start_st"),rs.getString("end_st"),rs.getString("seat_no")));
        }
        return tickets;
    }

    public String getRouteWithID(String id) throws SQLException {
        pst=con.prepareStatement("select startName,endName,rt_id,dep_time from rtView where rt_id=?");
        pst.setString(1,id);
        rs=pst.executeQuery();
        rs.next();
        return rs.getString("rt_id")+"   "+rs.getString("startName")+" to "+rs.getString("endName")+" at "+rs.getString("dep_time");
    }

    public int getFare(String id) throws SQLException {
        pst=con.prepareStatement("select fares from Routes where rt_id=?");
        pst.setString(1,id);
        rs=pst.executeQuery();
        rs.next();
        return Integer.parseInt(rs.getString("fares"));
    }
}
