package views;


import java.util.List;

import Services.UserService;
import javafx.application.Application;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import models.User;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.FontPosture;
import javafx.scene.control.TextField;
import javafx.scene.control.ListCell;  
import javafx.scene.control.ListView;
import javafx.collections.ObservableList; 
import javafx.collections.FXCollections; 


public class ListEveryOne extends Application{
	
	public void start(Stage primaryStage) {
		VBox vbox=new VBox();
		vbox.setSpacing(10);
		vbox.setAlignment(Pos.CENTER);
		Text text1=new Text(20,20,"username");
		text1.setFill(Color.GOLD);
		text1.setFont(Font.font("Courier",FontWeight.EXTRA_BOLD,20));
		vbox.getChildren().add(text1);
		
	     ListView<String> list = new ListView<>();  
	     ObservableList<String> data = FXCollections.observableArrayList(  
			            "ryjhn", "lihi", "arist", "samwel", "paust",  
			            "wifo", "vike", "alist", "chjhn", "rosoros",  
			            "alhen"); 
		
		vbox.getChildren().add(list);
		
		 list.setItems(data); 
		  
		 
		
		Button btback=new Button("BACK");
		vbox.getChildren().add(btback);
		btback.setOnAction((ActionEvent e)->{
			MainInterFace fin=new MainInterFace();
			fin.start(primaryStage);
		});
		Scene scene=new Scene(vbox,700,500);
		primaryStage.setTitle("MiniNet");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	
	}
	public static void main(String[] args) {
	       Application.launch(args);
	    }
	//}
}
