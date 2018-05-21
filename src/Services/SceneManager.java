package Services;

import java.io.IOException;

import controller.LoginController;
import controller.MainViewController;
import controller.SignUpController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import models.User;

public class SceneManager {
	  private Scene scene;

	  public SceneManager(Scene scene) {
	    this.scene = scene;
	  }

	  /**
	   * Callback method invoked to notify that a user has been authenticated.
	   * Will show the main application screen.
	   */ 
	  public void authenticated(User user) {
	    showMainView(user);
	  }

	  /**
	   * Callback method invoked to notify that a user has logged out of the main application.
	   * Will show the login application screen.
	   */ 
	  public void logout() {
	    showLoginScreen();
	  }
	  
	  public void showLoginScreen() {
	    try {
	      FXMLLoader loader = new FXMLLoader(
	        getClass().getResource("/views/login.fxml")
	      );
	      scene.setRoot((Parent) loader.load());
	      LoginController controller = loader.<LoginController>getController();
	      controller.loginManager(this);
	      controller.switchToSignUp(this);
	    } catch (IOException e) {
//	    	Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, null, ex);
	    }
	  }

	 private void showMainView(User user) {
	    try {
	      FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/MainView.fxml"));
	      scene.setRoot((Parent) loader.load());
	      MainViewController controller = loader.<MainViewController>getController();
	      System.out.println(user);
	      System.out.println(user.get_profile());
	      controller.setProfile(user.get_profile());
	      controller.LogOutManager(this);
	    } catch (IOException e) {
//	      Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}

	 public void showSignUpView()
	 {
		 try {
			 FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/SignUp.fxml"));
		     scene.setRoot((Parent) loader.load());
		     SignUpController controller = loader.<SignUpController>getController();
		     controller.switchToLogin(this);
		     controller.signUpManager(this);
		 } catch (IOException e) {
			 
		 }
	 }
}
