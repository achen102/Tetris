package Tetris;


import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 *This class models the Tetris game, and is responsible for the majority of animation and user input.
 */
public class Game{
	private Pane _gamepane;
	private TetrisPiece _piece;
	private TetrisSquare[][]_board;
	private Timeline _timeline;
	private Label _pause;
	/*
	 * This method constructs Tetris, which contains the Tetris pieces (made up of an array of TetrisSquares) and the board array(also made up of TetrisSquares).
	 */
	public Game() {
		_piece = new TetrisPiece();
		_gamepane = new Pane();
		_gamepane.setPrefSize(Constants.BOARD_WIDTH, Constants.BOARD_HEIGHT);
		_gamepane.setStyle("-fx-background-color: #000000;");
		this.generateBoard();
		_piece.factoryPattern(_gamepane, _board, _timeline);
		this.setupTimeline();
		_gamepane.setOnKeyPressed(new KeyHandler());
		
		_gamepane.setFocusTraversable(true);
		
		
	}
	
	/*
	 * This method constructs the Board, which is a 24x14 array of TetrisSquares, and sets the border of the board.
	 */
	public void generateBoard() {
		_board = new TetrisSquare[Constants.NUMBER_COLUMNS][Constants.NUMBER_ROWS];
		for (int i = 0; i < _board.length; i++) {
			for (int j = 0; j< _board[0].length; j++) {
				 if (i<=1 || i>=12 || j<=1 || j>=22) {
					 TetrisSquare bordersquare = new TetrisSquare();
					 Rectangle newsquare = bordersquare.getTetrisSquare();
					 newsquare.setFill(Color.MIDNIGHTBLUE);
					 newsquare.setStroke(Color.WHITE);
					 newsquare.setX(i*Constants.SQUARE_SIZE);
					 newsquare.setY(j*Constants.SQUARE_SIZE);
					 _gamepane.getChildren().add(newsquare);
					 _board [i] [j] =bordersquare; 
				 }
			}
		}
		
	}
	
	/**
	 *This class models a TimeHandler that implements the interface EventHandler<ActionEvent>, and determines\
	 *what happens when the Timeline is started. 
	 */
	private class TimeHandler implements EventHandler<ActionEvent> {
		
		/*
		 * This method constructs a TimeHandler.
		 */
		public TimeHandler() {
		
		}
		
		/*
		 * This method determines the changes that occur when the Timeline is initiated.
		 */
		public void handle(ActionEvent event) {
			_piece.moveDown(_board, _gamepane, _timeline);
			Game.this.clearLines(_gamepane);
			_piece.checkgameOver(_board,_timeline,_gamepane);
		}
	}
	
	/*
	 * This method creates a new TimeHandler, and a timeline through which the keyframe is passed; it is played every time this method is called.
	 */
	public void setupTimeline() {
	KeyFrame kf = new KeyFrame(
			Duration.seconds(Constants.DURATION),
			new TimeHandler());
		_timeline = new Timeline(kf);
		_timeline.setCycleCount(Animation.INDEFINITE);
		_timeline.play();
		
	}
	
	private class KeyHandler implements EventHandler<KeyEvent>{
		
		/* 
		 * This method constructs a KeyHandler.
		 */
		public KeyHandler() {
			
		}
		/*
		 * This method determines the changes that occur when a key is pressed.
		 */
		public void handle(KeyEvent e) {
			KeyCode keyPressed = e.getCode();
			
			//this if statement rotates the currently falling piece 90 degrees counterclockwise every time the up arrow key is pressed.
			if (keyPressed == KeyCode.UP && _timeline.getStatus()==Animation.Status.RUNNING) {
				_piece.rotate(_board, _gamepane);
			}
			//this if statement moves the currently falling piece left every time the left arrow key is pressed.
			if (keyPressed == KeyCode.LEFT && _timeline.getStatus()==Animation.Status.RUNNING) {
				_piece.moveLeft(_board,_gamepane);
			}
			//this if statement moves the currently falling piece right every time the right arrow key is pressed.
			if (keyPressed == KeyCode.RIGHT && _timeline.getStatus()==Animation.Status.RUNNING) {
				_piece.moveRight(_board,_gamepane);
			}
			//this if statement moves the currently falling piece down every time the down arrow key is pressed, and also checks if lines need to be cleared and if the game should end.
			if (keyPressed == KeyCode.DOWN && _timeline.getStatus()==Animation.Status.RUNNING) {
				_piece.moveDown(_board, _gamepane, _timeline);
				Game.this.clearLines(_gamepane);
				_piece.checkgameOver(_board,_timeline,_gamepane);
			}
			//this if statement moves the currently falling piece down to the lowest position it can be on the board without overlapping existing pieces when the spacebar is pressed.
			if (keyPressed == KeyCode.SPACE&& _timeline.getStatus()==Animation.Status.RUNNING) {
				_piece.drop(_board, _gamepane);
				Game.this.clearLines(_gamepane);
				_piece.checkgameOver(_board,_timeline,_gamepane);
			}
			//this if statement pauses or plays the game when p is pressed.
			if (keyPressed == KeyCode.P ) {
				Game.this.playpause();
			}
		
			e.consume();
		}
	}
	
	/*
	 * This method pauses the game if the timeline is running, and plays the game if the timeline has stopped (when the game is paused). In 
	 * addition, it visually adds text to the game saying that the game has paused when it is paused. This method is called when
	 * the key p is pressed.
	 */
	public void playpause() {
		
		_gamepane.getChildren().remove(_pause);
		_pause = new Label("paused!! press p to play!");
		
		if (_timeline.getStatus()==Animation.Status.RUNNING) {
			_gamepane.setFocusTraversable(false);
			_timeline.pause();
			_gamepane.getChildren().add(_pause);
			_pause.setTextFill(Color.WHITE);
			_pause.setLayoutX(Constants.LABEL_WIDTH);
			_pause.setLayoutY(Constants.BOARD_HEIGHT/2);
			
	}
		else {
			_timeline.play();
			_gamepane.setFocusTraversable(true);
			
		}
	}
	
	/*
	 * This boolean method determines whether a row on the board is full, and returns true when it is full.
	 */
	public boolean isrowFull(int j) {
		
			for (int i =0; i<_board.length; i++) {
				
			if (_board[i][j]==null) {
				
			return false;
			
			}
			
		}
		
		return true;
	}
	
	/*
	 * This method clears the lines on the board when a row is full, and logically & graphically assigns the squares in the row above to the row that has been cleared.
	 */
	public void clearLines(Pane _gamepane) {
		
		for (int j= 2; j<22; j++) {
			if (isrowFull(j)==true) {
				for (int i=2; i<12;i++) {
				_gamepane.getChildren().remove(_board[i][j].getTetrisSquare());
				_board[i][j]=null;
				}
				for (int clearrow=j; clearrow>2; clearrow--) {
					for (int col=2; col<12; col++) {
						if (_board[col][clearrow-1]!=null) {
						_board[col][clearrow-1].getTetrisSquare().setY(_board[col][clearrow-1].getTetrisSquare().getY()+Constants.SQUARE_SIZE);
						
						}
						_board[col][clearrow]=_board[col][clearrow-1];
					}
				}
			}
		}
	}
	
	
	/*
	 * This method returns the Pane that contains the game, and was created so that it could be accessed in the PaneOrganizer class and added to the root BorderPane.
	 */
	public Pane getgamePane() {
		return _gamepane;
	}
}