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

public class AddDependent extends Application{
	public void start(Stage primaryStage) {
		GridPane pane=new GridPane();
		pane.setAlignment(Pos.CENTER);
		Button back=new Button("back");
		TextField name=new TextField();
		TextField gender=new TextField();
		TextField age=new TextField();
		TextField status=new TextField();
		TextField profile=new TextField();
		TextField parent1=new TextField();
		TextField parent2=new TextField();
		
		pane.setPadding(new Insets(11, 12, 13, 14));	
		pane.setHgap(5.5);
		pane.setVgap(5.5);
		
		pane.add(new Label("input name"), 0, 1);
		pane.add(name, 1, 1);
		pane.add(new Label("input gender"), 0, 2);
		pane.add(gender, 1 ,2);
		pane.add(new Label("input status"), 0, 3);
		pane.add(status, 1, 3);
		pane.add((new Label("input profile")), 0, 4);
		pane.add(profile, 1, 4);
		pane.add(new Label("input first parent"), 0, 5);
		pane.add(parent1, 1, 5);
		pane.add(new Label("input second parent"), 0, 6);
		pane.add(parent2, 1, 6);
		pane.add(back, 1, 8);
		
		Scene scene=new Scene(pane,700,500);
		primaryStage.setTitle("MiniNet"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show();
		
		back.setOnAction((ActionEvent e)->{
			NormalDependent ND=new NormalDependent();
			ND.start(primaryStage);
		});
	
	};
		
}
