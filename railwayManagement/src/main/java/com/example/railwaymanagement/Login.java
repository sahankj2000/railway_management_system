package com.example.railwaymanagement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Login extends Application {
    static Stage reference;

    @Override public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("LoginFXML.fxml"));
        Scene Lscene=new Scene(root,800,600);

        /*FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("LoginFXML.fxml"));
        Scene Lscene = new Scene(fxmlLoader.load(), 800, 600);*/

        primaryStage.setScene(Lscene);
        reference=primaryStage;
        primaryStage.setTitle("Login");
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("train.png")));
        primaryStage.show();
    }
    public static void main(){ Application.launch(); }

    public static Stage getStage(){ return reference; }
}
