package Services;

import java.io.IOException;

import controller.LoginController;
import controller.MainViewController;
import controller.SignUpController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import models.User;

/**
 * 
 * @author sean
 * Scene Manager to handle 3 different scene login, signup and main view
 */
public class SceneManager {
	  private Scene scene;

	  public SceneManager(Scene scene) {
	    this.scene = scene;
	  }

	  public void authenticated(User user) {
	    showMainView(user);
	  }

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
	      
	      controller.initialize(user);
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
