package com.example.akrem;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Delete extends Stage {
    public Delete(){
        Label label = new Label("Enter the name of student");
        TextField textField = new TextField();

        Button button = new Button("Delete");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/stud", "root", ""
                    );
                    String sql = "DELETE FROM student WHERE  name=?";
                    PreparedStatement stmt = connection.prepareStatement(sql);
                    stmt.setString(1, textField.getText());

                    int rows = stmt.executeUpdate();
                    if(rows==1){
                        admin adminPage = new admin();
                        adminPage.start(new Stage());
                        System.out.println("student supprimer avec succes");
                        close();
                    }


                } catch (Exception e1) {
                    System.out.println(e1);
                }
            }
        });

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10));
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.getChildren().addAll(label, textField, button);


        Scene scene = new Scene(vBox, 250, 250);
        setScene(scene);
        setTitle("supprimer une produit");
        show();
    }
}
