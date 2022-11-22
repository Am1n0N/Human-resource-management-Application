package com.hrm;

import com.hrm.Controllers.AccountController;
import com.hrm.Controllers.EmployeeController;
import com.hrm.DAO.AccountDAO;
import com.hrm.DAO.AccountDataAccessService;
import com.hrm.DAO.EmployeeDAO;
import com.hrm.DAO.EmployeeDataAccessService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {

    private static Stage primaryStage;

    private void setPrimaryStage(Stage stage) {
        Main.primaryStage = stage;
    }
    private static AccountDAO BuildAccountDAO() {
        return new AccountDataAccessService();
    }
    private static EmployeeDAO BuildEmployeeDAO() {
        return new EmployeeDataAccessService();
    }
    public static AccountController buildAccountController() {
        return new AccountController(BuildAccountDAO());
    }
    public static EmployeeController buildEmployeeController() {
        return new EmployeeController(BuildEmployeeDAO());
    }
    static public Stage getPrimaryStage() {
        return Main.primaryStage;
    }

    @Override
    public void start(Stage stage) throws IOException {
        setPrimaryStage(stage);
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 360);
        stage.setResizable(false);
        stage.setTitle("Login!");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
