package controller;

import Services.SceneManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.Profile;
import models.User;

public class MainViewController {
	@FXML private Button logOutBtn;
	@FXML private ImageView profileImg;
	@FXML private Label nameLbl;
	@FXML private Label genderLbl;
	@FXML private Label ageLbl;
	@FXML private Label stateLbl;
	@FXML private Label statusLbl;
	
	private User user;
	
	public void initialize(User user) {
		this.user = user;
	}
	
	public void LogOutManager(SceneManager sceneManager)
	{
		logOutBtn.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override public void handle(ActionEvent event)
			{	
				sceneManager.logout();
			}
		});
	}
	
	public void setFriendList(User user)
	{	
//		Profile profile = user.get_profile();
		
	}
	
	public void setProfile(Profile profile)
	{
		profileImg.setImage(new Image("file:img/default_user.png", true));
		String name = profile.get_firstname() + " " + profile.get_lastname();
		nameLbl.setText(name);
		genderLbl.setText(profile.get_gender());
		ageLbl.setText(Integer.toString(profile.get_age()));
		stateLbl.setText(profile.get_state());
		statusLbl.setText(profile.get_status());
	}
}
