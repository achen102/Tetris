package Tetris;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *This class models an Application, which contains the top-level object, a PaneOrganizer named organizer.
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
      
    	PaneOrganizer organizer = new PaneOrganizer();
    	Scene scene = new Scene(organizer.getRoot(), Constants.BOARD_WIDTH, Constants.BOARD_HEIGHT);
    	stage.setScene(scene);
    	stage.show();
    }

  
    public static void main(String[] argv) {
       
        launch(argv);
    }
}
