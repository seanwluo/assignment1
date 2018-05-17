package views;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AreTheyFriends  extends Application{
	public void start(Stage primaryStage) {
		GridPane pane=new GridPane();
		pane.setAlignment(Pos.CENTER);
		TextField tfmi1=new TextField();
		TextField tfmi2=new TextField();
		Button back=new Button("Back");
		
		pane.setPadding(new Insets(11, 12, 13, 14));
		pane.setHgap(5.5);
		pane.setVgap(5.5);
		
		pane.add(new Label("input first person's name"),0,0);
		pane.add(tfmi1,0,2);
		pane.add(new Label("input next person's name"),0,3);
		pane.add(tfmi2, 0, 4);
		pane.add(back,0,6);
		
		Scene scene = new Scene(pane, 700, 500);
		primaryStage.setTitle("Mininet"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show();
		
		back.setOnAction((ActionEvent e)->{
		MainInterFace fin=new MainInterFace();
			fin.start(primaryStage);
			}
		);
		
		
		
		
	}

	
	
	
	
	
}




/*
 * public class SearchPerson extends Application{
	public void start(Stage primaryStage) {
		GridPane pane=new GridPane();
		pane.setAlignment(Pos.CENTER);
		Button back=new Button("back");
		TextField tfMi = new TextField();
		pane.setPadding(new Insets(11, 12, 13, 14));	
		pane.setHgap(5.5);
		pane.setVgap(5.5);
		
		pane.add(new Label("input name"),0,0);
		pane.add(tfMi,1,0);
		pane.add(back, 1, 4);
		
 * 
 * 
 * 
 * */
