package com.example.akrem;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.*;

public class Update extends Stage {
    int id=0;
    public  Update(){

    // Create labels, textfields, and button
    Label label0 = new Label("libele du produit");
    TextField textField0 = new TextField();

    Button button0 = new Button("rechercher");

    Label label1 = new Label("libel");
    Label label2 = new Label("prix");
    Label label3 = new Label("stock");
    Label label4 = new Label("description ");

    TextField textField1 = new TextField();
    TextField textField2 = new TextField();
    TextField textField3 = new TextField();
    TextField textField4 = new TextField();

    Button button = new Button("mettre a jour ");

    // Create vertical box and add labels, textfields, and button to it
    VBox vBox = new VBox();
                vBox.setPadding(new Insets(10));
                vBox.setSpacing(10);
                vBox.setAlignment(Pos.TOP_CENTER);
                vBox.getChildren().addAll(label0,textField0,button0,label1, textField1, label2, textField2, label3, textField3, label4, textField4, button);

        button0.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/mydata", "root", ""
                );
                Statement statement = connection.createStatement();
                String req = "select * from produit where libele= ? ";

                PreparedStatement stmt = connection.prepareStatement(req);
                stmt.setString(1, textField0.getText());
                ResultSet rset = stmt.executeQuery();
                while (rset.next()) {
                    textField1.setText(rset.getString(2));
                    id=rset.getInt(1);
                    textField2.setText(rset.getString(3));
                    textField3.setText(rset.getString(4));
                    textField4.setText(rset.getString(5));
                }

            }
            catch (Exception e){
                System.err.println(e);
            }
        }
    });
        button.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/mydata", "root", ""
                );
                String sql = "update  produit set libele=? ,prix =?, stock =? , description=? where id=?";
                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setString(1, textField1.getText());
                stmt.setString(2,textField2.getText() );
                stmt.setString(3, textField3.getText());
                stmt.setString(4, textField4.getText());
                stmt.setInt(5, id);
                int rows = stmt.executeUpdate();
                if(rows==1){
                    admin adminPage = new admin();
                    adminPage.start(new Stage());
                    System.out.println("produit update avec succes");
                    close();
                }

            }
            catch (Exception e1) {
                System.out.println(e1);
            }
        };
    });
    // Create scene and set it on the stage
    Scene scene = new Scene(vBox, 400, 600);
    setScene(scene);
    setTitle("update interface");
    show();
}}

