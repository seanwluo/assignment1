package views;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.layout.VBox;

public class searchpersoninterface extends Application{
	public void start(Stage primaryStage) {
		FlowPane pane=new FlowPane(Orientation.VERTICAL,5,5);
		Button back=new Button("back");
		TextField tfMi = new TextField();
		pane.setPadding(new Insets(11, 12, 13, 14));
		
		pane.getChildren().addAll(new Label("input name:"), new TextField());
		
		tfMi.setPrefColumnCount(10);
		pane.getChildren().add(tfMi);
		pane.getChildren().add(back);
	
		Scene scene = new Scene(pane, 700, 500);
		primaryStage.setTitle("ShowFlowPane"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage
		
		back.setOnAction((ActionEvent e)->{
			maininterface mf=new maininterface();
			mf.start(primaryStage);
		});
	}
	
}
