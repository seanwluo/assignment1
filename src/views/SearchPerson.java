package views;

import Services.UserService;
import java.util.Scanner;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import models.User;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;

public class SearchPerson extends Application{
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
		
		
		
		/*pane.getChildren().addAll(new Label("input name:"), new TextField());
		tfMi.setPrefColumnCount(10);
		pane.getChildren().add(tfMi);
		pane.getChildren().add(back);*/
		Scene scene = new Scene(pane, 700, 500);
		primaryStage.setTitle("MiniNet"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show();
		
		tfMi.setOnAction((ActionEvent e)->{
			String username=tfMi.getText();
			User user = UserService.findByUsername(username);
			if(user != null) {
				SelectedUserViewGui sv=new SelectedUserViewGui();
				sv.start(primaryStage);
			} ;
			if(user == null){
				UserNotExist unf=new UserNotExist();
				unf.start(primaryStage);
			}
		});
		
		
		back.setOnAction((ActionEvent e)->{
			MainInterFace mf=new MainInterFace();
			mf.start(primaryStage);
		});
	}
	
		
}
		
		
		/*
		 public User selectUser() {
		System.out.print("\nEnter username: ");
		String username = reader.nextLine();
		
		User user = UserService.findByUsername(username);
		if(user == null) {
			System.out.println("\nUser not found!");
		} else {
			System.out.println("\nUser found");
		}
		
		return user;
		 *
		 *
		 *
		 */

		
		
		


