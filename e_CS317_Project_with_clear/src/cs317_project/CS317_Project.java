package cs317_project;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CS317_Project extends Application {
    static Button[][] btn = new Button[5][5];
    static TextField display = new TextField();
    static String enteredNumber="";
 
    static double number1=-1;
    static double number2=-1;
    static String oldSign=null;
    static String newSign=null;
    static double storeNum=-1;
    static String storeSign=null;
    static boolean start=true;
    
    
    @Override
    public void start(Stage primaryStage) 
    {
        GridPane pane = new GridPane();
        modifyPane(pane);
        display = new TextField();
      
        setDisplay(pane,"0");
        intializeButtons();
        setButtonDisplay(pane); 
        Scene scene = new Scene(pane, 425, 525);
        scene.setFill(Color.LIGHTBLUE);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Calculator v0.0.1");
        primaryStage.show();
        
    //attaching numbers to events
       for (int row=1;row<=4;row++){
            for( int col=0;col<=2;col++){
                if((row==4&&col==2)==false)
              btn[row][col].setOnAction(e->{
                  Object o=e.getSource();
                  Button b=(Button)o;
             enterNumber(b.getText());
              });
            }
        }
       
       //attaching arithmetic operation to events
       
      for (int row=1;row<=4;row++){
               
              btn[row][3].setOnAction(e->{
                  Object o=e.getSource();
                  Button b=(Button)o;
             calculateSigns(b.getText());
              });
            }
        
       btn[4][2].setOnAction(e->{
               
             calculate();
              });
       btn[0][0].setOnAction(e->{
           clear();
       });
    }
    
    
    public static void clear()
    {
        display.setText("");
        enteredNumber="";
        number1=-1;
        number2=-1;
        oldSign=null;
        newSign=null;
        storeNum=-1;
        storeSign=null;
        start=true;
    }
    public static void calculate(){
        if(!enteredNumber.equals("")){
        number2=Double.parseDouble(enteredNumber);
        if(newSign.equals("*")||newSign.equals("/")){
            double n;
            if(newSign.equals("*"))
            n=number1*number2;
            else
            n=number1/number2;
            if(storeSign!=null){
            if(storeSign.equals("+"))
            display.setText(Double.toString(storeNum+n)); 
            if(storeSign.equals("-"))
            display.setText(Double.toString(storeNum-n)); 
          
            }
            else
           display.setText(Double.toString(n)); 
            
             
        }
       
        else if (newSign.equals("+")){
           display.setText(Double.toString(number1+number2)); 
        }
        else if (newSign.equals("-")){
          display.setText(Double.toString(number1-number2));
        }
        }
    }
   
    public static void calculateSigns(String sign){
        
        if(start && enteredNumber.equals("")){
            if(sign.equals("-"))
            enteredNumber="-";
            display.setText(enteredNumber);
        }
        else{
            if(start){
                 number1=Double.parseDouble(enteredNumber);
                 start=false;
            }

            else
                number2=Double.parseDouble(enteredNumber);

          //make new sign null if pressed again
            enteredNumber="";
            oldSign=newSign;
            newSign=sign;

            if(oldSign!=null){
                System.out.println(oldSign+newSign);
                if(((oldSign.equals("+")||oldSign.equals("-"))&&(newSign.equals("+")||newSign.equals("-")))||(oldSign.equals("*")||oldSign.equals("/"))){
                    System.out.print("invoked1");
                    if(oldSign.equals("+"))
                    number1=number1+number2;
                   else if (oldSign.equals("-"))
                    number1=number1-number2;
                   else if (oldSign.equals("*"))
                     number1=number1*number2;
                   else
                    number1=number1/number2;
                }



                else if((oldSign.equals("+")||oldSign.equals("-"))&&(newSign.equals("*")||newSign.equals("/"))){
                    System.out.print("invoked2");
                    storeNum=number1;
                    storeSign=oldSign;
                    number1=number2;
                }

                if(storeSign!=null&&((oldSign.equals("*")||oldSign.equals("/"))&&(newSign.equals("+")||newSign.equals("-")))){
                    System.out.print("invoked3"); 
                    if(storeSign.equals("+"))
                    number1=storeNum+number1;
                    else
                    number1=storeNum-number1;

                    storeSign=null;
                    storeNum=-1;

                }
                display.setText(Double.toString(number1));
            number2=-1;
          }
        }
    }
    public static void enterNumber(String num){
        enteredNumber=enteredNumber+num;
        display.setText(enteredNumber);
    }
    public static void modifyPane(GridPane pane)
    {
        pane.setPadding(new Insets(10));
        pane.setHgap(5);
        pane.setVgap(5);
        pane.setAlignment(Pos.CENTER);
    }
    public static void setDisplay(GridPane pane, String n)
    {
        //TextField display = new TextField();
        display.setPrefWidth(175);
        display.setPrefHeight(80);
        display.setPromptText(n);
        pane.setColumnSpan(display, 5);
        pane.setRowSpan(display, 2);
        pane.add(display, 0, 0);
        pane.setHalignment(display, HPos.CENTER);
    }
  
   
    public static void intializeButtons(){
           
      final ImageView squareRoot = new ImageView(
      new Image("file:../cs317_project/src/cs317_project/square_root.png")
    );
     final ImageView back = new ImageView(
      new Image("file:../cs317_project/src/cs317_project/back.png")
    );
       
     
   
        btn[0][0] = new Button("C");
        btn[0][1] = new Button("Sin");
        btn[0][2] = new Button("Cos");
        btn[0][3] = new Button("Tan");
        btn[0][4] = new Button("",back);
        
        btn[1][4]= new Button("log2");
        btn[2][4]= new Button("log10");
        btn[3][4]= new Button("", squareRoot);
        btn[4][4]= new Button("ON");



       
        int count = 1;
        //initializing the numbers
        for(int i = 3; i > 0; i--)
        {
            for(int j = 0; j < 3; j++)
            {
                btn[i][j] = new Button("" + count);
                count++;
            }
        }
        btn[1][3] = new Button("/");
       // pane.setColumnSpan(btn[1][3], 2);
        btn[2][3] = new Button("*");
        //pane.setColumnSpan(btn[2][3], 2);
        btn[3][3] = new Button("-");
        //pane.setColumnSpan(btn[3][3], 2);
        btn[4][3] = new Button("+");
        //pane.setColumnSpan(btn[4][3], 2);
        btn[4][2] = new Button("=");
        //pane.setColumnSpan(btn[4][2], 2);
        btn[4][0] = new Button("0");
        //pane.setColumnSpan(btn[4][0], 2);
        btn[4][1] = new Button(".");
    }
    
    
    
     public static void setButtonDisplay(GridPane pane){
        for(int i = 0; i < btn.length; i++)
        {
            for(int j = 0; j < btn.length; j++)
            {
                if(btn[i][j] != null)
                {   
                    
                    if(i == 0){
                        if(j==0)
                        btn[i][j].setStyle("-fx-background-color: brown; -fx-text-fill: white; -fx-font: bold italic 20pt \"Arial\";");
                        else
                        btn[i][j].setStyle("-fx-background-color: brown; -fx-text-fill: white; -fx-font: bold italic 14pt \"Arial\";");

                        
                    }
                    else if( j == 3)
                        btn[i][j].setStyle("-fx-background-color: cornflowerblue; -fx-text-fill: white; -fx-font: bold 20pt \"Arial\";");
                     else if( j == 4)
                     btn[i][j].setStyle("-fx-background-color: darkGray; -fx-text-fill: black; -fx-font: bold 14pt \"Arial\";");

                    else
                        btn[i][j].setStyle("-fx-font: bold 20pt \"Arial\";");
                    btn[i][j].setPrefSize(75,75);
                    pane.add(btn[i][j],j,i+2);
                    
                }
            }   
        }
    }
     
     
     
     
}