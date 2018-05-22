package controller;

import java.util.List;

import Services.FriendshipService;
import Services.SceneManager;
import Services.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import models.Friendship;
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
	@FXML private ListView<Friendship> freindLstVw;
	@FXML private ListView<User> peopleLstVw;
	
	public void initialize() {}
	
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
		
		setFriends(user);
		allPeople(user);
	}
	
	public void setProfile(Profile profile)
	{	
		String imageUrl = profile.get_picUrl();
		
		profileImg.setImage(getImage(imageUrl));
		
		String name = profile.get_firstname() + " " + profile.get_lastname();
		nameLbl.setText(name);
		genderLbl.setText(profile.get_gender());
		ageLbl.setText(Integer.toString(profile.get_age()));
		stateLbl.setText(profile.get_state());
		statusLbl.setText(profile.get_status());
	}
	
	private void setFriends(User user)
	{	
		ObservableList<Friendship> friends = freindLstVw.getItems();
		List<Friendship> frnds = FriendshipService.findByUsername(user.get_username());
		ObservableList<Friendship> frnlist = FXCollections.observableArrayList(frnds);
        friends.addAll(frnlist);
        freindLstVw.setCellFactory(new Callback<ListView<Friendship>, ListCell<Friendship>>() {
            @Override
            public ListCell<Friendship> call(ListView<Friendship> param) {
                return new FreindsListViewCell(user);
            }
        });  
	}
	
	private void allPeople(User user)
	{
		ObservableList<User> people = peopleLstVw.getItems();
        List<User> users = UserService.allUser();
        ObservableList<User> list = FXCollections.observableArrayList(users);
        people.addAll(list);
        peopleLstVw.setCellFactory(new Callback<ListView<User>, ListCell<User>>() {
            @Override
            public ListCell<User> call(ListView<User> param) {
                return new PeopleListViewCell(user);
            }
        });
	}

	private Image getImage(String imageUrl)
	{	
		Image img = null;
		try {
			img = new Image(imageUrl, true);
		} catch(Exception e) {
			img = new Image("file:img/default_user.png", true);
		}
		
		return img;
	}
}
