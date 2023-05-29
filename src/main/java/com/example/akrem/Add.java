package com.example.akrem;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Add extends Stage {
public Add() {
    Label label1 = new Label("name");
    Label label2 = new Label("age");
    Label label3 = new Label("classe");
    Label label4 = new Label("phone number ");

    TextField textField1 = new TextField();
    TextField textField2 = new TextField();
    TextField textField3 = new TextField();
    TextField textField4 = new TextField();
    Button b1 = new Button("Add the student");
    b1.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/stud", "root", ""
                );
                String sql = "INSERT INTO student (name,age,class,phone) VALUES (?, ?,?, ?)";
                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setString(1, textField1.getText());
                stmt.setString(2, textField2.getText());
                stmt.setString(3, textField3.getText());
                stmt.setString(4, textField4.getText());

                int rows = stmt.executeUpdate();
                if (rows == 1) {
                    admin adminPage = new admin();
                    adminPage.start(new Stage());
                    System.out.println("student add successfully");
                    close();
                }

            } catch (Exception e1) {
                System.out.println(e1);
            }
        }
    });
    GridPane gridPane = new GridPane();
    gridPane.setPadding(new Insets(10));
    gridPane.setVgap(10);
    gridPane.setHgap(10);
    gridPane.add(label1, 0, 0);
    gridPane.add(textField1, 1, 0);
    gridPane.add(label2, 0, 1);
    gridPane.add(textField2, 1, 1);
    gridPane.add(label3, 0, 2);
    gridPane.add(textField3, 1, 2);
    gridPane.add(label4, 0, 3);
    gridPane.add(textField4, 1, 3);
    gridPane.add(b1, 1, 5);

    // Create scene and set it on the stage
    Scene scene = new Scene(gridPane, 400, 400);
    setScene(scene);
    setTitle("add student");
    show();
}
}
