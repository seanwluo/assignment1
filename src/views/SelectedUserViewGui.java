package views;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class SelectedUserViewGui  extends Application {
	public void start(Stage primaryStage) {
		VBox vbox=new VBox();
		vbox.setSpacing(20);
		vbox.setAlignment(Pos.CENTER);
		//pane.setPadding(new Insets(100,100,100,100));
	//	pane.setSpacing(10);
		Label label=new Label("User find !");
		label.setFont(Font.font("Ambel cn",FontWeight.BOLD,30));
		label.setTextFill(Color.GREEN);
		//pane.getChildren().add(label);
		Button viewprofile=new Button("View profile");
		
		Button editprofile=new Button("edit profile");
		Button deleteprofile=new Button("Delete profile");
		Button connection=new Button("Connect to person");
		Button friendlist=new Button("List friends");
		Button back=new Button("back");
		
		vbox.getChildren().add(label);
		vbox.getChildren().add(viewprofile);
		vbox.getChildren().add(editprofile);
		vbox.getChildren().add(connection);
		vbox.getChildren().add(friendlist);
		vbox.getChildren().add(back);
		
		
		Scene scene=new Scene(vbox,700,500);
		primaryStage.setTitle("Mininet");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
		back.setOnAction((ActionEvent e)->{
			SearchPerson spi=new SearchPerson();
			spi.start(primaryStage);
		});
	
	
	}

	
	
	
	
		
	}