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

public class UserNotExist extends Application {
	public void start(Stage primaryStage) {
		VBox vbox=new VBox();
		vbox.setSpacing(20);
		vbox.setAlignment(Pos.CENTER);
		//pane.setPadding(new Insets(100,100,100,100));
	//	pane.setSpacing(10);
		Label label=new Label("Users not find");
		Button back=new Button("back");
		label.setFont(Font.font("Ambel cn",FontWeight.BOLD,30));
		label.setTextFill(Color.RED);
		//pane.getChildren().add(label);
		
		
		vbox.getChildren().add(label);
		vbox.getChildren().add(back);
		
		Scene scene=new Scene(vbox,700,500);
		primaryStage.setTitle("MiniNet");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		back.setOnAction((ActionEvent e)->{
			MainInterFace fin=new MainInterFace();
			fin.start(primaryStage);
		});
	}
	
}
