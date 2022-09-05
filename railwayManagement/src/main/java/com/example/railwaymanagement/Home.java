package com.example.railwaymanagement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Home extends Application {

    static Stage homeRef;

    @Override public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("HomeFXML.fxml"));
        primaryStage.setTitle("Home");
        Scene Hscene=new Scene(root,800,600);
        primaryStage.setScene(Hscene);
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image(Login.class.getResourceAsStream("train.png")));
        homeRef=primaryStage;
        primaryStage.show();
    }
    public static void main(String[] args){ Application.launch(args); }

    public static Stage getHome(){
        return homeRef;
    }

}
