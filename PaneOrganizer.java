package Tetris;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 *This class models a PaneOrganizer, and is the top-level class.
 */
public class PaneOrganizer{
	private BorderPane _root;
	
	/*
	 * This method constructs a PaneOrganizer, which is responsible for graphical elements that affect the 
	 * entire application. 
	 */
	public PaneOrganizer() {
		_root = new BorderPane();
		Game game = new Game();
		_root.setCenter(game.getgamePane());
		this.setupButtons();
		
	}
	
	/*
	 * This method sets up the HBox pane that graphically contains the quit button, and also 
	 * initializes the button and associates its pane with the root pane. 
	 */
	private void setupButtons() {
		HBox buttonPane = new HBox();
		_root.setBottom(buttonPane);
		Button quit = new Button("Quit!");
		quit.setStyle("-fx-base: #5F9EA0");
		quit.setTextFill(Color.ANTIQUEWHITE);
		quit.setFocusTraversable(false);
		buttonPane.getChildren().add(quit);
		buttonPane.setAlignment(Pos.BOTTOM_CENTER);
		quit.setOnAction(new ClickHandler());
	}
	
	/**
	 *This class models a ClickHandler that implements the interface EventHandler<ActionEvent>, and determines\
	 *what happens when the quit button is clicked.
	 */
	
	private class ClickHandler implements EventHandler<ActionEvent> {
		/*
		 * This method determines the changes that occur when the button is clicked; in this case,
		 * the application is exited. 
		 */
		public void handle(ActionEvent e) {
			Platform.exit();
		}
	}
	
	/*
	 * This method returns the root pane (a BorderPane), and was created so that the Scene
	 * could be associated with the root node within the App class.
	 */
	public BorderPane getRoot() {
		return _root;
	}
}

