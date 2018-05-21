package views;

import java.util.List;

import Services.UserService;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import models.User;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class MainInterFace extends Application {
	public void start(Stage primaryStage) {
		VBox vbox=new VBox();
		//vbox.setPrefWidth(10);
		vbox.setSpacing(20);
		vbox.setAlignment(Pos.CENTER);
		//pane.setPadding(new Insets(100,100,100,100));
	//	pane.setSpacing(10);
		Label label=new Label("Welcome To Mininet !");
		label.setFont(Font.font("Ambel cn",FontWeight.BOLD,30));
		label.setTextFill(Color.GREEN);
		//pane.getChildren().add(label);
		Button listall=new Button("list everyone");
		Button selectone=new Button("select a person");
		Button addone=new Button("add a person");
		Button confirmfriend=new Button("are these two are friends");
		Button PC=new Button("parents or children");
		Button exit=new Button("exit");
		
		listall.setMaxWidth(230);
		selectone.setMaxWidth(230);
		addone.setMaxWidth(230);
		confirmfriend.setMaxWidth(230);
		PC.setMaxWidth(230);
		exit.setMaxWidth(230);
		
		vbox.getChildren().add(label);
		vbox.getChildren().add(listall);
		vbox.getChildren().add(selectone);
		vbox.getChildren().add(addone);
		vbox.getChildren().add(confirmfriend);
		vbox.getChildren().add(PC);
		vbox.getChildren().add(exit);
		
		
		Scene scene=new Scene(vbox,700,500);
		primaryStage.setTitle("MiniNet");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		listall.setOnAction((ActionEvent e)->{
			//UsersView usr = new UsersView();
			//usr.listAllUser();
		
			ListEveryOne dis=new ListEveryOne();
			dis.start(primaryStage);
		});
		
		selectone.setOnAction((ActionEvent e)->{
			SearchPerson spi=new SearchPerson();
			spi.start(primaryStage);
		});
		
		addone.setOnAction(e->{
			NormalDependent ND=new NormalDependent();
			ND.start(primaryStage);
		});
		
		confirmfriend.setOnAction((e)->{
			AreTheyFriends ATF=new AreTheyFriends();
			ATF.start(primaryStage);
		});
		
		PC.setOnAction(e->{
			SearchPerson spi=new SearchPerson();
			spi.start(primaryStage);
		});
		
		exit.setOnAction((e)->{
			System.exit(0);
		});
	}
	public static void main(String[] args) {
	       Application.launch(args);
	    }
}
