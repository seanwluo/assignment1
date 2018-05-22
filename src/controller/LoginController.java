package controller;

import Exception.NoAvailableException;
import Services.SceneManager;
import Services.UserService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import models.User;

/**
 * 
 * @author sean
 * 
 * Controllers for login screen
 */
public class LoginController {
	@FXML private TextField usernameTxt;
	@FXML private TextField passwordTxt;
	@FXML private Label errorLbl;
	@FXML private Button loginBtn;
	@FXML private Button signUpBtn;
	
	public void initialize() {}
	  
	public void loginManager(SceneManager loginService)
	{
	  loginBtn.setOnAction(new EventHandler<ActionEvent>()
	  {
		  @Override public void handle(ActionEvent event)
		  {
			  User user;
			try {
				user = authorize();
				loginService.authenticated(user);
			} catch (NoAvailableException e) {
				errorLbl.setVisible(true);
			}
		  }
	  }); 
	}
	
	public void switchToSignUp(SceneManager loginService)
	{
	  signUpBtn.setOnAction(new EventHandler<ActionEvent>()
	  {
		  @Override public void handle(ActionEvent event)
		  {
			  loginService.showSignUpView();
		  }
	  }); 
	}

	/**
	 * Check authorization credentials.
	 * 
	 * If accepted, return a sessionID for the authorized session
	 * otherwise, return null.
	 * @throws NoAvailableException 
	 */
	private User authorize() throws NoAvailableException
	{
		User user = UserService.findByUsername(usernameTxt.getText());
		
		if(user.get_password().equals(passwordTxt.getText()))
		{
			return user;
		} else {
			return null;
		}
	  }
		  
	}
