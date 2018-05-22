package controller;

import Services.FriendshipService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import models.Friendship;
import models.User;

/**
 * 
 * @author sean
 * 
 * to handle listview change and add element ot list
 */
public class FreindsListViewCell extends ListCell<Friendship>{
	private HBox hbox = new HBox();
    private Label label = new Label("(empty)");
    private Pane pane = new Pane();
    private Button frndBtn = new Button("X");
    private Friendship lastFrnship;
    private User loginUser;
    
	public FreindsListViewCell(User loginUser)
	{
		super();
		this.loginUser = loginUser;
        hbox.getChildren().addAll(label, pane, frndBtn);
        HBox.setHgrow(pane, Priority.ALWAYS);
        
        frndBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	boolean create = lastFrnship.delete();
            	
            	if(create) {
            		updateItem(lastFrnship, true);
            	} else {
            		Alert alert = new Alert(AlertType.ERROR, 
            				"Sorry! we could not remove connection");
					alert.show();
            	}
            }
        });
	}
	
	@Override
    protected void updateItem(Friendship frnship, boolean empty) {
        super.updateItem(frnship, empty);
        setText(null);  // No text in label of super class
        if (empty) {
        	lastFrnship = null;
            setGraphic(null);
        } else {
            lastFrnship = frnship;
        	if(frnship !=null) {
        		label.setText(frnship.getUsers()[1].get_username());
        		setGraphic(hbox);
        	} else {
        		setGraphic(null);
        	}
            
        }
    }
}
