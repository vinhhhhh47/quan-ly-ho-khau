package com.example.quanly;

import com.example.quanly.controller.LoginController;
import com.example.quanly.controller.ho_khau.HoKhauController;
import com.example.quanly.models.HoKhau;
import com.example.quanly.models.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Đăng nhập");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Database.connect();
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        launch();
        Database.close();
    }
}