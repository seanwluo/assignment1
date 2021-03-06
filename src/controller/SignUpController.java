package controller;

import Exception.NoAccountCreatedException;
import Exception.NoAvailableException;
import Exception.NoParentException;
import Exception.NotToBeCoupledException;
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

/**
 * 
 * @author sean
 *
 */
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
	@FXML private TextField parent1Txt;
	@FXML private TextField parent2Txt;
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
				if (!allValid()) {
					return;
				}
			
				String username = usernameTxt.getText();
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
				boolean createUser = false;
				
				if(age >= 16) {
					createUser = UserService.createAdult(username);
				} else {
					createUser = createChildren(username);
					if(!createUser) {
						return; //To show pop error message
					}
				}
				
				User user=null;
				try {
					user = UserService.findByUsername(username);
					ProfileService.create(user, firstname, lastname, gender, age, status, picUrl, state);
					loginService.authenticated(UserService.findByUsername(username));
				} catch (NoAvailableException | NoAccountCreatedException e) {
					Alert alert = new Alert(AlertType.ERROR, e.getMessage());
					alert.show();
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
	
	private boolean createChildren(String username)
	{	
		boolean createUser = false;
		if(checkEmpty(parent1Txt) && checkEmpty(parent2Txt))
		{
			createUser = false;
			Alert alert = new Alert(AlertType.ERROR, "Add parents");
			alert.show();
		}
		else{
			String[] result;
			try {
				result = UserService.createDependent(parent1Txt.getText(), parent2Txt.getText(), username);
				createUser = true;
			} catch (NoAvailableException | NoParentException | NoAccountCreatedException | NotToBeCoupledException e) {
				Alert alert = new Alert(AlertType.ERROR, e.getMessage());
				alert.show();
			}
		}
		
		return createUser;
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
