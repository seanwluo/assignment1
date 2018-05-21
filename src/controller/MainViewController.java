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

/**
 * 
 * @author sean
 *
 */
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
	
	public void LogOutManager(SceneManager loginService)
	{
		logOutBtn.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override public void handle(ActionEvent event)
			{	
				loginService.logout();
			}
		});
	}
	
	public void setFriendList(User user)
	{	
//		Profile profile = user.get_profile();
		
	}
	
	public void setProfile()
	{	
//		TODO: load from profile
//		profileImg.setImage(new Image("img/default_user.png"));
//		String name = profile.get_firstname() + " " + profile.get_lastname();
		String name = "Sean Luo";
		nameLbl.setText(name);
		genderLbl.setText("Male");
		ageLbl.setText("26");
		stateLbl.setText("VIC");
		statusLbl.setText("RMIT Student");
	}
}
