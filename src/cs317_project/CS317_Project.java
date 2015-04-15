package cs317_project;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CS317_Project extends Application {
    
    @Override
    public void start(Stage primaryStage) 
    {
        GridPane pane = new GridPane();
        modifyPane(pane);
        setDisplay(pane);
        addButtons(pane);
        
        Scene scene = new Scene(pane, 425, 525);
        scene.setFill(Color.LIGHTBLUE);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Calculator v0.0.1");
        primaryStage.show();
        
    }   
    public static void modifyPane(GridPane pane)
    {
        pane.setPadding(new Insets(10));
        pane.setHgap(5);
        pane.setVgap(5);
        pane.setAlignment(Pos.CENTER);
    }
    public static void setDisplay(GridPane pane)
    {
        TextField display = new TextField();
        display.setPrefWidth(175);
        display.setPrefHeight(80);
        display.setPromptText("0");
        pane.setColumnSpan(display, 5);
        pane.setRowSpan(display, 2);
        pane.add(display, 0, 0);
        pane.setHalignment(display, HPos.CENTER);
    }
    public static void addButtons(GridPane pane)
    {
        Button[][] btn = new Button[5][5];
        btn[0][0] = new Button("SqRt");
        btn[0][1] = new Button("log");
        btn[0][2] = new Button("pow");
        btn[0][3] = new Button("sin");
        btn[0][4] = new Button("cos");
        int count = 1;
        for(int i = 3; i > 0; i--)
        {
            for(int j = 0; j < 3; j++)
            {
                btn[i][j] = new Button("" + count);
                count++;
            }
        }
        btn[1][3] = new Button("/");
        pane.setColumnSpan(btn[1][3], 2);
        btn[2][3] = new Button("*");
        pane.setColumnSpan(btn[2][3], 2);
        btn[3][3] = new Button("-");
        pane.setColumnSpan(btn[3][3], 2);
        btn[4][3] = new Button("+");
        pane.setColumnSpan(btn[4][3], 2);
        btn[4][2] = new Button("=");
        pane.setColumnSpan(btn[4][2], 2);
        btn[4][0] = new Button("0");
        pane.setColumnSpan(btn[4][0], 2);
        btn[4][1] = new Button(".");
        for(int i = 0; i < btn.length; i++)
        {
            for(int j = 0; j < btn.length; j++)
            {
                if(btn[i][j] != null)
                {   
                    if(i == 0)
                        btn[i][j].setStyle("-fx-background-color: brown; -fx-text-fill: white; -fx-font: bold italic 14pt \"Arial\";");
                    else if( j == 3)
                        btn[i][j].setStyle("-fx-background-color: cornflowerblue; -fx-text-fill: white; -fx-font: bold 20pt \"Arial\";");
                    else
                        btn[i][j].setStyle("-fx-font: bold 20pt \"Arial\";");
                    btn[i][j].setPrefSize(75,75);
                    pane.add(btn[i][j],j,i+2);
                    
                }
            }
        }
    }
}