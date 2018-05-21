package controller;

import Services.ProfileService;
import Services.SceneManager;
import Services.UserService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import models.User;

public class SignUpController {
	@FXML private TextField usernameTxt;
	@FXML private TextField fnameTxt;
	@FXML private TextField lnameTxt;
	@FXML private ToggleGroup toggleGroup;
	@FXML private RadioButton maleRadio;
	@FXML private RadioButton femaleRadio;
	@FXML private TextField ageTxt;
	@FXML private TextField urlTxt;
	@FXML private TextField statusTxt;
	@FXML private TextField stateTxt;
	@FXML private Button loginBtn;
	@FXML private Button signUpBtn;
	
	public void switchToLogin(SceneManager loginService)
	{
		loginBtn.setOnAction(new EventHandler<ActionEvent>()
		  {
			  @Override public void handle(ActionEvent event)
			  {
				  loginService.showLoginScreen();
			  }
		  });
		
	}
	
	public void signUpManager(SceneManager loginService)
	{
		signUpBtn.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override public void handle(ActionEvent event)
			{
				if (allValid())
				{	String username = usernameTxt.getText();
					String firstname = fnameTxt.getText();
					String lastname = lnameTxt.getText();
					String gender = null;
					if (toggleGroup.getSelectedToggle() != null) {

						gender = toggleGroup.getSelectedToggle().getUserData().toString();

			         }
					
					int age = Integer.parseInt(ageTxt.getText());
					String picUrl = urlTxt.getText();
					String status = statusTxt.getText();
					String state = stateTxt.getText();
					
					boolean createUser = UserService.createAdult(username);
					User user = UserService.findByUsername(username);
					if(createUser)
					{	
						boolean createProfile = ProfileService.create(user, firstname, lastname, gender, age, status, picUrl, state);
						if(createProfile) {
							loginService.authenticated(UserService.findByUsername(username));
						} else {
							Alert alert = new Alert(AlertType.ERROR, "Something went wrong. Profile not created.");
							alert.show();
						}
					} else {
						Alert alert = new Alert(AlertType.ERROR, "Something went wrong. Account not created.");
						alert.show();
					}
				}
			}
		});
		
	}
	
	@FXML
	public void CheckNumber() {
		try {
			Integer.parseInt(ageTxt.getText().trim());
			ageTxt.getStyleClass().remove("error");
		}catch (Exception e) {
			ageTxt.getStyleClass().add("error");
		}
	}
	
	private boolean allValid()
	{	
		boolean good = false;
		
		if (!checkEmpty(usernameTxt) && !checkEmpty(fnameTxt) && !checkEmpty(lnameTxt)  &&
				!checkEmpty(ageTxt))
		{
			good = true;
		}
		
		return good;
	}
	
	private boolean checkEmpty(TextField txt)
	{
		if (txt.getText().isEmpty())
		{	
			txt.getStyleClass().add("error");
			return true;
		} else {
			txt.getStyleClass().remove("error");
			return false;
		}
	}

}
