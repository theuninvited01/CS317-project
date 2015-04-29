
package calculator;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 *
 * @author amnairfan
 */
public class Calculator extends Application {
    
    static Button[][] btn = new Button[5][5];
    static TextField display = new TextField();    
    static double storeNum=-1;
    static String storeSign=null;
    static String enteredNum;
    static Double num;
    static String sign;
    static boolean started;
    static double backupNum1,backupNum2;
    static String backupSign;
    static double backupStoreNum;
    static String backupStoreSign;
    static boolean atSingleCalc;
    static boolean atEqualCalc;
    static boolean atExponential;
    static int expNum;
    static boolean nan;
    
    @Override
    public void start(Stage primaryStage) 
    {
       
        GridPane pane = new GridPane();
        modifyPane(pane);
        display = new TextField();
        display.setEditable(false);
        display.setAlignment(Pos.CENTER_RIGHT);
        setDisplay(pane,"0");
        intializeButtons();
        setButtonDisplay(pane); 
        Scene scene = new Scene(pane, 425, 525);
        scene.setFill(Color.LIGHTBLUE);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Calculator v0.0.1");
        primaryStage.show();
        clear();
        keyPressEvents();
        buttonClickEvents();
     
    }
    
    public static void buttonClickEvents(){
        for (int row=1;row<=4;row++){
        for( int col=0;col<=2;col++){
        if((row==4&&col==2)==false)
        btn[row][col].setOnAction(e->{
        Object o=e.getSource();
        Button b=(Button)o;
        enterNumber(b.getText());});
        }
        }
       
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

        btn[0][4].setOnAction(e->{  
            back();
        });
 
        btn[0][0].setOnAction(e->{
            clear();
        });
         
        
        btn[4][4].setOnAction(e->{
            Object o=e.getSource();
            Button b=(Button)o;  
            powerMode(b.getText());
        });
       
        for( int col=1;col<=3;col++){
            btn[0][col].setOnAction(e->{
            Object o=e.getSource();
            Button b=(Button)o;
             singleCalculation(b.getText());
            });   
         }
        for( int row=1;row<=3;row++){
            btn[row][4].setOnAction(e->{
            Object o=e.getSource();
            Button b=(Button)o;
            singleCalculation(b.getText());
            });
        }
    }
    
    
    public static void keyPressEvents(){
        display.setOnKeyPressed(new EventHandler<KeyEvent>() {
        public void handle(KeyEvent ke) {
    
        String keyText=ke.getText();
        String keyCode=ke.getCode().toString();

        boolean isNum;
        try  
        {  
        double d = Double.parseDouble(keyText);  
        isNum=true;
        }  
        catch(NumberFormatException nfe)  
        {  
        isNum=false;  
        }  
        if(isNum||keyText.equals("."))
        enterNumber(keyText);
        if(keyText.equals("+")||keyText.equals("*")||keyText.equals("-")||keyText.equals("/"))
        calculateSigns(keyText);
        if(keyText.equals("=")||keyCode.equals("ENTER"))
        calculate();
        if(keyCode.equals("BACK_SPACE"))
         back();
        }
        });    
    }
    
    
    public static void powerMode(String mode){
        if(mode.equals("OFF")){
        btn[4][4].setText("ON");
        for (int row=0;row<=4;row++){
        for( int col=0;col<=4;col++){
        if((row==4&&col==4)==false)
        btn[row][col].setDisable(true);
        }
        clear();
        }
        }
        else{
        btn[4][4].setText("OFF");  
        for (int row=0;row<=4;row++){
        for( int col=0;col<=4;col++){
        if((row==4&&col==4)==false)
        btn[row][col].setDisable(false);
        }
        }
        }
      
    }
    
    public static void clear(){
        display.requestFocus();
        storeNum=-1;
        storeSign=null;
        enteredNum="";
        num=0.0;
        sign="+";
        started=false;
        backupNum1=0;
        backupNum2=0;
        backupSign=null;
        backupStoreNum=0;
        backupStoreSign=null;
        atSingleCalc=false;
        atEqualCalc=false;
        atExponential=false;
        display.setText("0");
        nan=false;
        
    }
    
    
   
    public static void singleCalculation(String signType){
    //chnages the current number to a differnt number with calcualtions
    display.requestFocus();
    if(enteredNum.equals("")){
    if(signType.equals("Cos"))
    enteredNum=Double.toString(Math.cos(0));
    else if(signType.equals("Sin"))
    enteredNum=Double.toString(Math.sin(0));
    else if (signType.equals("log2"))
    enteredNum=Double.toString(Math.log10(0)/Math.log10(2));
    else if (signType.equals("log10"))
    enteredNum=Double.toString(Math.log10(0));
    else if(signType.equals("Exp")){
    expNum=Integer.parseInt(enteredNum);
    atExponential=true;
    }

    else{
    enteredNum=Double.toString(Math.sqrt(0));
    if(sign.equals("-"))
    nan=true;
    }
    }
    else{
        if(signType.equals("Cos"))
         enteredNum=Double.toString(Math.cos(Double.parseDouble(enteredNum)));
        if(signType.equals("Sin"))
         enteredNum=Double.toString(Math.sin(Double.parseDouble(enteredNum)));
        else if (signType.equals("log2"))
         enteredNum=Double.toString(Math.log10(Double.parseDouble(enteredNum))/Math.log10(2));
        else if (signType.equals("log10"))
         enteredNum=Double.toString(Math.log10(Double.parseDouble(enteredNum)));
        else if(signType.equals("Exp")){
             expNum=Integer.parseInt(enteredNum);
            atExponential=true;

        }
        else{
        enteredNum=Double.toString(Math.sqrt(Double.parseDouble(enteredNum)));
        if(sign.equals("-"))
                nan=true;

        }

    }

    //sets new number as entered number and puts it on screen
   if(!atExponential)
    atSingleCalc=true;

    if(nan)
        display.setText("NaN");
     else
    displayResults(enteredNum);
        
    }
    
    
    public static void back(){
        display.requestFocus();
        
        if(!enteredNum.equals("")){
            enteredNum=enteredNum.substring(0,enteredNum.length()-1);
            displayResults(enteredNum);
        }
        
    }
    
    public static void displayResults(String n){
         display.setText(format(n));
    }
    
  
   public static void calculate(){
        if(!atExponential)
             atExponential=true;
        
       display.requestFocus();
       if(enteredNum.equals(""))
           enteredNum=Double.toString(num);
       
       if(sign.equals("+"))
           num=num+Double.parseDouble(enteredNum);
       else if(sign.equals("-"))
            num=num-Double.parseDouble(enteredNum);
       else if(sign.equals("/"))
           num=num/Double.parseDouble(enteredNum);
       else
           num=num*Double.parseDouble(enteredNum);
       
       if(storeSign!=null){
           if(storeSign.equals("+"))
               num=storeNum+num;
           if(storeSign.equals("-"))
               num=storeNum-num;
           
           storeNum=-1;
           storeSign=null;
       }
           
       sign="+";
       enteredNum="0";
       displayResults(Double.toString(num));
       atEqualCalc=true;
       
     
    }
   public static void restoreValues(String enteredSign){
       //gather 3 variables for calculation
     
       num=backupNum1;
       enteredNum=Double.toString(backupNum2);
       sign=backupSign;
       if(enteredSign.equals("/")||enteredSign.equals("*")){
       storeSign=backupStoreSign;
       storeNum=backupStoreNum; 
       }
       
               
   }
   
    public static void calculateSigns(String enteredSign){
        //once sign is pressed continues with num produced by tan sin or equals etc as an operand
        //example 3Sin+5
        if(atSingleCalc){
           atSingleCalc=false;
        }
        if(atEqualCalc){
           atEqualCalc=false;
        }
        
        if(atExponential)
            atExponential=false;
        //this method predicts the next step.
        //based on 5+3-    (num)(sign)(enteredNum)(enteredSign)
        //the entered sign and sign both decide the next move
        //the starting value of num is 0 ad sign is +.
        //every calcualation in the begining will be 0+enteredNum,enteredSign
        display.requestFocus();
       
        //Case#1: starts with a number and then a sign
        //0+enteredNum,enteredSign
        //usual way
        if(!started && !enteredNum.equals("")){
            started=true;
           
        }
        
      
        //Case#2: it starts with a sign
        //Soultion: Since there is no entered number we make enteredNum as a 0
        //0+0,enteredSign
        //example - should result into 0-
        //in future when someone enters a number it will be 0-9 which is equal to -9!!
        else if (!started){
            started=true;
            enteredNum="0";
            
        }
        
        //what if the user presses the - and then *
        //Detection way: enterednum will be empty
        //prediction changes, in that case restore to the last values with new entered sign
        //if they do in the beginging too like - and then + it works because enteredValue has changed to 0
        /*Check: start calculator type - then change it to * and type 9 and click on equals, it will give
        you a 0 not a -9
        */
        /*else: if not changing signs, save the num and sign and entered num to the backup incase the 
        user changes sign*/
      
        if(enteredNum.equals(""))
            restoreValues(enteredSign);
        else{
            backupNum1=num;
            backupNum2=Double.parseDouble(enteredNum);
            backupSign=sign;
        }
     
        //Begining of predictions
        //Case#1: When both signs are + or -, just calcualte
        //when noth signs are * or /, just calcualte
        //8 different combination work in this case - -, + +, - +, + -, * /, /*, * *, / /
        if(enteredSign.equals(sign)||enteredSign.equals("+")||enteredSign.equals("-")||(sign.equals("/")&&enteredSign.equals("*"))||(sign.equals("*")&&enteredSign.equals("/"))){
                    
                    if(sign.equals("+"))
                    num=num+Double.parseDouble(enteredNum);
                   else if (sign.equals("-"))
                    num=num-Double.parseDouble(enteredNum);
                   else if (sign.equals("*"))
                     num=num*Double.parseDouble(enteredNum);
                   else
                    num=num/Double.parseDouble(enteredNum);
                }
        //Case#2: where sign is + or - and new sign is a multiplication
        //in this case  hold calculations
        //store the num and sign to use it after wards
        // and make entered number the num
        //Example. 3+5* (cannot be calculated as 5 can have more numbers be multiplied to it 
        //5+7*7= 5+49
        //the stored 5 will be added later
        
        
        //Case#3: when its the time for the holding number to be added or subtracted
        //When user enters + or - it means its no longer waiting for a new value
        //2+3*5+ (2 was stored before becuase of pemdas)
        //3*5 was multipled as qualified case#1, num now equals to 15
        //since 5 preceded an + or maybe equal or -, holding value can be added
        //2+15 now equals 17!
        
          else if((sign.equals("+")||sign.equals("-"))&&(enteredSign.equals("*")||enteredSign.equals("/"))){
                  
                    storeNum=num;
                    storeSign=sign;
                    num=Double.parseDouble(enteredNum);
                }

                if(storeSign!=null&&((sign.equals("*")||sign.equals("/"))&&(enteredSign.equals("+")||enteredSign.equals("-")))){
                    
                    if(storeSign.equals("+"))
                    num=storeNum+num;
                    else
                    num=storeNum-num;
                    backupStoreNum=storeNum;
                    backupStoreSign=storeSign;
                    storeSign=null;
                    storeNum=-1;
                    
                }
        
        //first time for - values
        sign=enteredSign;
        enteredNum="";
        displayResults(num.toString());
    }
    public static void enterNumber(String num){
    
        display.requestFocus();
        if(nan)
           clear();
           
        
          
        //resets the calculator if presses a number instead of a sign after Sin cos etc
        if(atSingleCalc){
            clear();
           
           
        }
        if(atEqualCalc){
            clear();
           
        }
         if(atExponential){
             atExponential=false;
             
           
                Double n=Math.pow(expNum,Integer.parseInt(num));
                
              enteredNum=Double.toString(n);
           
              displayResults(enteredNum);
              expNum=0;
        }
         else{
            boolean allowDecimal=true;
            for(int i=0;i<enteredNum.length()&&allowDecimal;i++){
               if(enteredNum.charAt(i)=='.')
                    allowDecimal=false;  
            }
            //for decimals
            if(num.equals(".")&&enteredNum.length()==0)
           enteredNum="0";
               if(allowDecimal||!num.equals(".")) {
                   enteredNum=enteredNum+num;
                   display.setText(enteredNum);

               }
             
         }
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
       
         
        display.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        display.setStyle("-fx-text-inner-color: black;");
  
       
        pane.setColumnSpan(display, 5);
        pane.setRowSpan(display, 2);
        pane.add(display, 0, 0);
        pane.setHalignment(display, HPos.CENTER);
    }
  
    public static void intializeButtons(){
           
      final ImageView squareRoot = new ImageView(
      new Image("file:../calculator/src/calculator/square_root.png")
    );
     final ImageView back = new ImageView(
      new Image("file:../calculator/src/calculator/back.png")
    );
       
     
   
        btn[0][0] = new Button("C");
        btn[0][1] = new Button("Sin");
        btn[0][2] = new Button("Cos");
        btn[0][3] = new Button("Exp");
        btn[0][4] = new Button("",back);
        
        btn[1][4]= new Button("log2");
        btn[2][4]= new Button("log10");
        btn[3][4]= new Button("", squareRoot);
        btn[4][4]= new Button("OFF");



       
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
     
     
 public static String format(String s){
      if(s.equals(""))
         return s;
     else if(s.length()!=1&&s.charAt(s.length()-1)=='0'&&s.charAt(s.length()-2)=='.')
          return s.substring(0, s.length()-2);
     
     else
         return s;
  }    
}
