package views;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.Scene;


public class NormalDependent extends Application {
	public void start(Stage primaryStage) {
		GridPane pane=new GridPane();
		pane.setAlignment(Pos.CENTER);
		Label label=new Label("choose type");
		label.setFont(Font.font("Ambel cn",FontWeight.BOLD,20));
		label.setTextFill(Color.BLACK);
		Button normal=new Button("NORMAL");
		Button dependent=new Button("DEPENDENT");
		Button back=new Button("Back");
		
		normal.setMaxWidth(180);
		dependent.setMaxWidth(180);
		
		pane.setPadding(new Insets(11,12,13,14));
		pane.setVgap(5.5);
		pane.setHgap(5.5);
		
		pane.add(label,1,1);
		pane.add((normal), 1, 3);
		pane.add((dependent), 1, 4);
		pane.add(back, 1, 8);
		
		Scene scene=new Scene(pane,700,500);
		primaryStage.setTitle("MiniNet"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show();
		
		
		
		normal.setOnAction((ActionEvent e)->{
			AddNormal ad=new AddNormal();
			ad.start(primaryStage);
		});
		
		dependent.setOnAction(e->{
			AddDependent AD=new AddDependent();
			AD.start(primaryStage);
		});
		
		back.setOnAction( e->{
			MainInterFace fin=new MainInterFace();
			fin.start(primaryStage);
			}
		);
		
	}
	

}


