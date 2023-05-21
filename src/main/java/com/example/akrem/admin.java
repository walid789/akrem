package com.example.akrem;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class admin extends Application {

    @Override
    public void start(Stage stage)  {

        Button button1 = new Button("ADD PRODUCT");
        button1.setOnAction(e->add(stage));
        Button button2 = new Button("DELETE PRODUCT");
        button2.setOnAction(e->delete(stage));
        Button button3 = new Button("UPDATE PRODUCT");
        button3.setOnAction(e->update(stage));



        VBox layout = new VBox(button1, button2, button3);
        Scene scene = new Scene(layout, 400, 400);
        stage.setTitle("admin interface ");
        stage.setScene(scene);
        stage.show();

    }
    public void add(Stage stage){
        Add add =new Add();
        add.show();
        stage.close();
    }


    public void delete(Stage stage){
        Delete add =new Delete();
        add.show();
        stage.close();
    }
    public void update(Stage stage){
        Update add =new Update();
        add.show();
        stage.close();
    }


    public static void main(String args[]){
        launch();
    }
}

