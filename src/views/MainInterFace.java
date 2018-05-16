package views;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
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
		vbox.setSpacing(20);
		vbox.setAlignment(Pos.CENTER);
		//pane.setPadding(new Insets(100,100,100,100));
	//	pane.setSpacing(10);
		Label label=new Label("Mininet");
		label.setFont(Font.font("Ambel cn",FontWeight.BOLD,30));
		label.setTextFill(Color.GREEN);
		//pane.getChildren().add(label);
		Button btlistall=new Button("list everyone");
		
		Button btselectone=new Button("select a person");
		Button btaddone=new Button("add a person");
		Button btconfirmfriend=new Button("are these two are friends");
		Button btexit=new Button("exit");
		
		vbox.getChildren().add(label);
		vbox.getChildren().add(btlistall);
		vbox.getChildren().add(btselectone);
		vbox.getChildren().add(btaddone);
		vbox.getChildren().add(btconfirmfriend);
		vbox.getChildren().add(btexit);
		
		
		Scene scene=new Scene(vbox,700,500);
		primaryStage.setTitle("Mininet");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		btlistall.setOnAction((ActionEvent e)->{
			//UsersView usr = new UsersView();
			//usr.listAllUser();
			ListEveryOne dis=new ListEveryOne();
			dis.start(primaryStage);
		});
		
		btselectone.setOnAction((ActionEvent e)->{
			SearchPerson spi=new SearchPerson();
			spi.start(primaryStage);
		});
		
		btaddone.setOnAction(e->{
			NormalDependent ND=new NormalDependent();
			ND.start(primaryStage);
		});
		
		btexit.setOnAction((e)->{
			System.exit(0);
		});
	}
	public static void main(String[] args) {
	       Application.launch(args);
	    }
}
