package controller;

import Services.FriendshipService;
import Services.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import models.User;

public class PeopleListViewCell extends ListCell<User>{
	private HBox hbox = new HBox();
    private Label label = new Label("(empty)");
    private Pane pane = new Pane();
    private Button frndBtn = new Button("+");
    private User lastUser;
    private User loginUser;
    
	public PeopleListViewCell(User loginUser)
	{

		super();
		this.loginUser = loginUser;
		
		ObservableList<String> options = FXCollections.observableArrayList( "Freinds", 
				"Classmate",
			    "Colleague");
		ComboBox<String> comboBx = new ComboBox<String>(options);
		comboBx.setPromptText("Choose Relation");
		
		hbox.getChildren().addAll(label, pane, comboBx, frndBtn);
        HBox.setHgrow(pane, Priority.ALWAYS);
        
        frndBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	Object frnType = comboBx.getValue();
            	
            	String frnship;
            	if(frnType == null) {
            		Alert alert = new Alert(AlertType.ERROR, "Select a relation");
					alert.show();
					return;
            	} else {
            		frnship = frnType.toString();
                	processConnection(lastUser, frnship);
            	}
            }
        });
	}
	
	@Override
    protected void updateItem(User user, boolean empty) {
        super.updateItem(user, empty);
        setText(null);  // no text in the label of super class
        if (empty) {
            lastUser = null;
            setGraphic(null);
        } else {
            lastUser = user;
            label.setText(user!=null ? user.get_username() : null);
            setGraphic(hbox);
        }
    }
	
	private void processConnection(User user, String frnType)
	{
		if(!user.isChildren() && frnType.equals("Colleague")) {
    		Alert alert = new Alert(AlertType.ERROR, 
    				"Sorry! you can not select coleague");
			alert.show();
    	} else {
    		makeFreindship(user, frnType);
    	}
    	
    	
	}
	
	private void makeFreindship(User user, String frnType)
	{
//		returns String[reuslt, msg] e.g. ["error", "Could not connect"]
		String[] result = loginUser.connect(user, frnType);
    	
    	if(result[0].equals("success")) {
    		updateItem(lastUser, true);
    		Alert alert = new Alert(AlertType.INFORMATION, result[1]);
			alert.show();
    	} else {
    		Alert alert = new Alert(AlertType.ERROR, result[1]);
			alert.show();
    	}
	}
}
