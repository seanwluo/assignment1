package main;

import java.sql.SQLException;

import Services.ProfileService;
import Services.SceneManager;
import Services.UserService;
import db.CreateTables;
import db.DBUtil;
import db.HSQLServer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import models.User;
import util.TransferFileToDB;

/**
 * @author 
 * 
 * The MiniNet class execute the program
 */
public class MiniNet  extends Application
{	
	private static HSQLServer hsqlServer;
	/*
	 * Program main method to run
	 * */
	public static void main(String[] args) throws Exception {
		Thread thread = new Thread(){
		    public void run(){
				hsqlServer = new HSQLServer();
				hsqlServer.start();
		    }
		  };
		  
		thread.start();
		DBUtil dbUtil = new DBUtil();
		dbUtil.connect();
		
		CreateTables.run(DBUtil.getConnection());
			
		TransferFileToDB.run();
		
        Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
	  try {	
			Scene scene = new Scene(new Pane());
			scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());

		    SceneManager sceneManager = new SceneManager(scene);
//		    User user = UserService.findByUsername("rzkmr");
		    sceneManager.showLoginScreen();
//		    sceneManager.authenticated(user);
		    
		    
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
	}

}
	