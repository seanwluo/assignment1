package main;

import java.sql.SQLException;

import Services.SceneManager;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * @author Raj
 * 
 * The MiniNet class execute the program
 */
public class MiniNet  extends Application
{	
	/*
	 * Program main method to run
	 * */
	public static void main(String[] args) throws SQLException {
		
        Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {			
				Scene scene = new Scene(new Pane());
				scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());

			    SceneManager loginService = new SceneManager(scene);
			    loginService.showLoginScreen();
			    
//			    primaryStage.setOnCloseRequest(e -> {
//			    	hsqlServer.stop();
//			    	Platform.exit();
//			    });
			    
			    primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			        @Override
			        public void handle(WindowEvent t) {
//			        	hsqlServer.stop();
			            Platform.exit();
			            System.exit(0);
			        }
			    });
			    
			    
				primaryStage.setTitle("MiniNet");
				primaryStage.setResizable(false);
				primaryStage.setScene(scene);
				primaryStage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
		
		
		
//		VBox vbox=new VBox();
//		//vbox.setPrefWidth(10);
//		vbox.setSpacing(20);
//		vbox.setAlignment(Pos.CENTER);
//		//pane.setPadding(new Insets(100,100,100,100));
//	//	pane.setSpacing(10);
//		Label label=new Label("Welcome To Mininet !");
//		label.setFont(Font.font("Ambel cn",FontWeight.BOLD,30));
//		label.setTextFill(Color.GREEN);
//		//pane.getChildren().add(label);
//		Button listall=new Button("list everyone");
//		Button selectone=new Button("select a person");
//		Button addone=new Button("add a person");
//		Button confirmfriend=new Button("are these two are friends");
//		Button PC=new Button("parents or children");
//		Button exit=new Button("exit");
//		
//		listall.setMaxWidth(230);
//		selectone.setMaxWidth(230);
//		addone.setMaxWidth(230);
//		confirmfriend.setMaxWidth(230);
//		PC.setMaxWidth(230);
//		exit.setMaxWidth(230);
//		
//		vbox.getChildren().add(label);
//		vbox.getChildren().add(listall);
//		vbox.getChildren().add(selectone);
//		vbox.getChildren().add(addone);
//		vbox.getChildren().add(confirmfriend);
//		vbox.getChildren().add(PC);
//		vbox.getChildren().add(exit);
//		
//		
//		Scene scene=new Scene(vbox,700,500);
//		primaryStage.setTitle("Mininet");
//		primaryStage.setScene(scene);
//		primaryStage.show();
//		
//		listall.setOnAction((ActionEvent e)->{
//			//UsersView usr = new UsersView();
//			//usr.listAllUser();
//			ListEveryOne dis=new ListEveryOne();
//			dis.start(primaryStage);
//		});
//		
//		selectone.setOnAction((ActionEvent e)->{
//			SearchPerson spi=new SearchPerson();
//			spi.start(primaryStage);
//		});
//		
//		addone.setOnAction(e->{
//			NormalDependent ND=new NormalDependent();
//			ND.start(primaryStage);
//		});
//		
//		confirmfriend.setOnAction((e)->{
//			AreTheyFriends ATF=new AreTheyFriends();
//			ATF.start(primaryStage);
//		});
//		
//		PC.setOnAction(e->{
//			SearchPerson spi=new SearchPerson();
//			spi.start(primaryStage);
//		});
//		
//		exit.setOnAction((e)->{
//			System.exit(0);
//		});
	}

}
	