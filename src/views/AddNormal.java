package views;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class AddNormal extends Application{
	public void start(Stage primaryStage) {
		GridPane pane=new GridPane();
		pane.setAlignment(Pos.CENTER);
		Button back=new Button("back");
		TextField name=new TextField();
		TextField usergender=new TextField();
		TextField userage=new TextField();
		TextField userstatus=new TextField();
		TextField profile=new TextField();
		
		pane.setPadding(new Insets(11, 12, 13, 14));	
		pane.setHgap(5.5);
		pane.setVgap(5.5);
		
		pane.add(new Label("input name"), 0, 1);
		pane.add(name, 1, 1);
		pane.add(new Label("input gender"), 0, 2);
		pane.add(usergender, 1 ,2);
		pane.add(new Label("input status"), 0, 3);
		pane.add(userstatus, 1, 3);
		pane.add((new Label("input profile")), 0, 4);
		pane.add(profile, 1, 4);
		pane.add(back, 1, 7);
		
		
		Scene scene=new Scene(pane,700,500);
		primaryStage.setTitle("MiniNet"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show();
		
		
		 String username, firstname, lastname, gender, status, picUrl;
		double age;
		
		/*name.setOnAction((ActionEvent e)->{
			username=name.getText();		
		});
		usergender.setOnAction(e->{
			gender=usergender.getText();
		});
		userstatus.setOnAction(e->{
			status=userstatus.getText();
		});
		profile.setOnAction(e->{
			picUrl=profile.getText();
		});
		userage.setOnAction(e->{
			//age=Age.getText();
		});
		
		//username=name.getText()*/
		
		
		
		
		
		
		
		
		
		back.setOnAction((ActionEvent e)->{
			NormalDependent ND=new NormalDependent();
			ND.start(primaryStage);
		});
	
	};
		
}



 