package Tetris;

import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 *This class models the Tetris pieces that are used in the game, which are made up of squares.
 */
public class TetrisPiece{
	private TetrisSquare[]_tetrispiece;
	private int _ycoord;
	private int _xcoord;
	/*
	 * This method constructs a Tetris piece, which is an array of four squares.
	 */
	public TetrisPiece() {
		_tetrispiece= new TetrisSquare[4];
	}
			
	/*
	 * This method uses a switch statement to randomly create one of seven types of tetris pieces. 
	 */
	public void factoryPattern(Pane _gamepane, TetrisSquare[][] _board, Timeline _timeline) {
			
			int rand = (int)(Math.random()*7);
			
			switch(rand) {
			case 0:
			for (int i = 0; i< _tetrispiece.length; i++) {
			
				_tetrispiece[i]= new TetrisSquare();
				 _tetrispiece[i].getTetrisSquare().setFill(Color.PINK);
				 _tetrispiece[i].getTetrisSquare().setStroke(Color.WHITE);
				 _tetrispiece[i].getTetrisSquare().setX(Constants.PIECE1[i][0]+Constants.SQUARE_XOFFSET);
				 _tetrispiece[i].getTetrisSquare().setY(Constants.PIECE1[i][1]+Constants.PIECE_YOFFSET);
			
			}
			break;
			
			case 1:
				for (int i = 0; i< _tetrispiece.length; i++) {
					
					_tetrispiece[i]= new TetrisSquare();
					_tetrispiece[i].getTetrisSquare().setFill(Color.RED);
					_tetrispiece[i].getTetrisSquare().setStroke(Color.WHITE);
					_tetrispiece[i].getTetrisSquare().setX(Constants.PIECE2[i][0]+Constants.PIECE_XOFFSET);
					_tetrispiece[i].getTetrisSquare().setY(Constants.PIECE2[i][1]+Constants.PIECE_YOFFSET);
					
					
						
					}
				
			break;
			
			case 2:
				for (int i = 0; i< _tetrispiece.length; i++) {
					
					_tetrispiece[i]= new TetrisSquare();
					_tetrispiece[i].getTetrisSquare().setFill(Color.ORANGE);
					_tetrispiece[i].getTetrisSquare().setStroke(Color.WHITE);
					_tetrispiece[i].getTetrisSquare().setX(Constants.PIECE3[i][0]+Constants.PIECE_XOFFSET);
					_tetrispiece[i].getTetrisSquare().setY(Constants.PIECE3[i][1]+Constants.PIECE_YOFFSET);
				
					
						
					}
						
				break;
			
			case 3:
				for (int i = 0; i< _tetrispiece.length; i++) {
					
					_tetrispiece[i]= new TetrisSquare();
					_tetrispiece[i].getTetrisSquare().setFill(Color.YELLOW);
					_tetrispiece[i].getTetrisSquare().setStroke(Color.WHITE);
					_tetrispiece[i].getTetrisSquare().setX(Constants.PIECE4[i][0]+Constants.PIECE_XOFFSET);
					_tetrispiece[i].getTetrisSquare().setY(Constants.PIECE4[i][1]+Constants.PIECE_YOFFSET);
				
					
						
					}
						
				break;
			
			case 4:
				for (int i = 0; i< _tetrispiece.length; i++) {
					
					_tetrispiece[i]= new TetrisSquare();
					_tetrispiece[i].getTetrisSquare().setFill(Color.GREEN);
					_tetrispiece[i].getTetrisSquare().setStroke(Color.WHITE);
					_tetrispiece[i].getTetrisSquare().setX(Constants.PIECE5[i][0]+Constants.PIECE_XOFFSET);
					_tetrispiece[i].getTetrisSquare().setY(Constants.PIECE5[i][1]+Constants.PIECE_YOFFSET);
				
					 
						
					}
						
				break;
			
			case 5:
				for (int i = 0; i< _tetrispiece.length; i++) {
					
					_tetrispiece[i]= new TetrisSquare();
					_tetrispiece[i].getTetrisSquare().setFill(Color.BLUE);
					_tetrispiece[i].getTetrisSquare().setStroke(Color.WHITE);
					_tetrispiece[i].getTetrisSquare().setX(Constants.PIECE6[i][0]+Constants.PIECE_XOFFSET);
					_tetrispiece[i].getTetrisSquare().setY(Constants.PIECE6[i][1]+Constants.PIECE_YOFFSET);
				
					 
						
					}
						
				break;
			case 6:
				for (int i = 0; i< _tetrispiece.length; i++) {
					
					_tetrispiece[i]= new TetrisSquare();
					_tetrispiece[i].getTetrisSquare().setFill(Color.PURPLE);
					_tetrispiece[i].getTetrisSquare().setStroke(Color.WHITE);
					_tetrispiece[i].getTetrisSquare().setX(Constants.PIECE7[i][0]+Constants.PIECE_XOFFSET);
					_tetrispiece[i].getTetrisSquare().setY(Constants.PIECE7[i][1]+Constants.PIECE_YOFFSET);
					
					 
						
					}
						
				break;
			
			}
		
			/*
			 * this if statement checks if the piece can be added to the board, and if so, adds it graphically. Otherwise, it starts the gameOver
			 * method which ends the game. It is placed here to check before each new piece is added graphically so that pieces at
			 * the top of the board do not overlap just before the game ends.
			 */
			if (moveValidity(_board)==true) {
				for (int i=0; i<_tetrispiece.length; i++) {
				 _gamepane.getChildren().add(_tetrispiece[i].getTetrisSquare());
				}
			}
			
			else {
				
				TetrisPiece.this.checkgameOver(_board, _timeline, _gamepane);
			
			}
			
	}
	
	/*
	 * This method checks if the game is over by checking if there are any squares in the top row. When the game is over, it stops the timeline and creates a label 
	 * with text that visually tells the user that the game is over, and makes the game unresponsive to key presses.
	 */
	public void checkgameOver(TetrisSquare[][]_board, Timeline _timeline, Pane _gamepane) {
		
		for (int i= 2; i<12; i++) {
			if( _board[i][2]!=null){
				_timeline.stop();
				Label gameover = new Label("GAME OVER!! >:0");
				gameover.setTextFill(Color.WHITE);
				gameover.setLayoutX(Constants.LABEL_WIDTH);
				gameover.setLayoutY(Constants.BOARD_HEIGHT/2);
				_gamepane.getChildren().add(gameover);
				_gamepane.setFocusTraversable(false);
			}
			}
	}
	
	/*
	 * This method moves the tetris piece down if it is able to move down (move validity is true), and saves the squares of the piece to the board + creates a 
	 * new piece if it is not able to move down. 
	 */
	public void moveDown(TetrisSquare[][] _board, Pane _gamepane, Timeline _timeline) {
		
		if (downmoveValidity(_board)==true) {
			
			for (int i=0; i<_tetrispiece.length; i++) {
				
				_ycoord= (int)(_tetrispiece[i].getTetrisSquare().getY()+Constants.SQUARE_SIZE)/Constants.SQUARE_SIZE;
				_xcoord = (int)( _tetrispiece[i].getTetrisSquare().getX())/Constants.SQUARE_SIZE;
				
				
					_tetrispiece[i].getTetrisSquare().setY((_ycoord)*Constants.SQUARE_SIZE);	
					
			}
		}
		
		else {
			for (int i=0; i<_tetrispiece.length; i++) {
				if (_tetrispiece[i]!=null) {
				
			_ycoord= (int)(_tetrispiece[i].getTetrisSquare().getY()+Constants.SQUARE_SIZE)/Constants.SQUARE_SIZE;
			_xcoord = (int)( _tetrispiece[i].getTetrisSquare().getX())/Constants.SQUARE_SIZE;
			System.out.println(_ycoord);
			System.out.println(_xcoord);
			_board[_xcoord][_ycoord-1]= _tetrispiece[i];
			
				}
		}
			_gamepane.setFocusTraversable(false);
			this.factoryPattern(_gamepane, _board, _timeline);
		}
		
		}
	
	/*
	 * This method moves the currently active piece to the left is the move validity is true.
	 */
	public void moveLeft(TetrisSquare[][] _board, Pane _gamepane) {
		if (leftmoveValidity(_board)==true) {
			
			for (int i=0; i<_tetrispiece.length; i++) {
				
				_ycoord= (int)(_tetrispiece[i].getTetrisSquare().getY()/Constants.SQUARE_SIZE);
				_xcoord = (int)( _tetrispiece[i].getTetrisSquare().getX()-Constants.SQUARE_SIZE)/Constants.SQUARE_SIZE;
				
				
					_tetrispiece[i].getTetrisSquare().setX((_xcoord)*Constants.SQUARE_SIZE);	
					
			}
		}
		
		else {
			_gamepane.setFocusTraversable(false);
			
				}
		}
	
	/*
	 * This method moves the currently active piece to the right is the move validity is true.
	 */
	public void moveRight(TetrisSquare[][] _board, Pane _gamepane) {
		if (rightmoveValidity(_board)==true) {
			
			for (int i=0; i<_tetrispiece.length; i++) {
				
				_ycoord= (int)(_tetrispiece[i].getTetrisSquare().getY()/Constants.SQUARE_SIZE);
				_xcoord = (int)( _tetrispiece[i].getTetrisSquare().getX()+Constants.SQUARE_SIZE)/Constants.SQUARE_SIZE;
				
				
					_tetrispiece[i].getTetrisSquare().setX((_xcoord)*Constants.SQUARE_SIZE);	
					
			}
		}
		
		else {
			_gamepane.setFocusTraversable(false);
			
				}
		}
			
			
	/*
	 * This method rotates the piece 90 degrees counterclockwise if the move validity is true.
	 */
	public void rotate(TetrisSquare[][] _board, Pane _gamepane) {
		if(rotateValidity(_board)==true) {
		
		for (int i= 0; i<_tetrispiece.length;i++) {
			double xcenter = _tetrispiece[2].getTetrisSquare().getX();
			double ycenter = _tetrispiece[2].getTetrisSquare().getY();
			
				if (_tetrispiece[i]!=null && _tetrispiece[i].getTetrisSquare().getFill()!=Color.PINK) {
					double xloc =_tetrispiece[i].getTetrisSquare().getX();
					double yloc = _tetrispiece[i].getTetrisSquare().getY();
					_xcoord = (int) ( xcenter - ycenter+ yloc);
					_ycoord= (int) (ycenter +xcenter - xloc);
					_tetrispiece[i].getTetrisSquare().setX(_xcoord);
					_tetrispiece[i].getTetrisSquare().setY(_ycoord);
				}
			
		}
	}
		else {
			_gamepane.setFocusTraversable(false);
		}
	}
	
	/*
	 * This method drops the piece down to the lowest possible position it can go without overlapping existing pieces; it uses a while statement so that it keeps
	 * moving the piece down while move validity is true until it returns false.
	 */
	public void drop(TetrisSquare[][]_board, Pane _gamepane) {
		
			while (downmoveValidity(_board)==true) {
				
				for (int i=0; i<_tetrispiece.length; i++) {
			
				
				_ycoord= (int)(_tetrispiece[i].getTetrisSquare().getY()+Constants.SQUARE_SIZE)/Constants.SQUARE_SIZE;
				_xcoord = (int)( _tetrispiece[i].getTetrisSquare().getX())/Constants.SQUARE_SIZE;
				
				
					_tetrispiece[i].getTetrisSquare().setY((_ycoord)*Constants.SQUARE_SIZE);	
				}
			}
				
				
			}
			
	/*
	 * This method checks if the piece can rotate in its current place on the board.
	 */
	private boolean rotateValidity(TetrisSquare[][]_board) {
		for (int i=0; i<_tetrispiece.length; i++) {
			double xcenter = _tetrispiece[2].getTetrisSquare().getX();
			double ycenter = _tetrispiece[2].getTetrisSquare().getY();
			if (_tetrispiece[i]!=null) {
				double xloc =_tetrispiece[i].getTetrisSquare().getX();
				double yloc = _tetrispiece[i].getTetrisSquare().getY();
				_xcoord = (int) ( xcenter - ycenter+ yloc)/Constants.SQUARE_SIZE;
				_ycoord= (int) (ycenter +xcenter - xloc)/Constants.SQUARE_SIZE;
				
				if (_board[_xcoord][_ycoord]!=null){
					
					return false;
				}
			}
			
		}
		return true;
		
	}
	
	/*
	 * This method checks if the piece can move down in its current place on the board.
	 */
	private boolean downmoveValidity(TetrisSquare[][]_board) {
		for (int i=0; i<_tetrispiece.length; i++) {
			if (_tetrispiece[i]!=null) {
				_ycoord= (int)(_tetrispiece[i].getTetrisSquare().getY()+Constants.SQUARE_SIZE)/Constants.SQUARE_SIZE;
				_xcoord = (int)( _tetrispiece[i].getTetrisSquare().getX())/Constants.SQUARE_SIZE;
				
				if (_board[_xcoord][_ycoord]!=null){
					
					return false;
				}
			}
			
		}
		return true;
		
	}
	
	/*
	 * This method checks if the piece can be created in its place on the board (it is called before each piece is graphically added to the board)
	 */
	private boolean moveValidity(TetrisSquare[][]_board) {
		for (int i=0; i<_tetrispiece.length; i++) {
			if (_tetrispiece[i]!=null) {
				_ycoord= (int)(_tetrispiece[i].getTetrisSquare().getY())/Constants.SQUARE_SIZE;
				_xcoord = (int)( _tetrispiece[i].getTetrisSquare().getX())/Constants.SQUARE_SIZE;
				
				if (_board[_xcoord][_ycoord]!=null){
					
					return false;
				}
			}
			
		}
		return true;
		
	}
	
	/*
	 * This method checks if the piece can move left in its current place on the board.
	 */
	private boolean leftmoveValidity(TetrisSquare[][]_board) {
		for (int i=0; i<_tetrispiece.length; i++) {
			if (_tetrispiece[i]!=null) {
				_ycoord= (int)(_tetrispiece[i].getTetrisSquare().getY())/Constants.SQUARE_SIZE;
				_xcoord = (int)( _tetrispiece[i].getTetrisSquare().getX()-Constants.SQUARE_SIZE)/Constants.SQUARE_SIZE;
				
				if (_board[_xcoord][_ycoord]!=null){
					
					return false;
				}
			}
			
		}
		return true;
		
	}
	
	/*
	 * This method checks if the piece can move right in its current place on the board.
	 */
	private boolean rightmoveValidity(TetrisSquare[][]_board) {
		for (int i=0; i<_tetrispiece.length; i++) {
			if (_tetrispiece[i]!=null) {
				_ycoord= (int)(_tetrispiece[i].getTetrisSquare().getY())/Constants.SQUARE_SIZE;
				_xcoord = (int)( _tetrispiece[i].getTetrisSquare().getX()+Constants.SQUARE_SIZE)/Constants.SQUARE_SIZE;
				
				if (_board[_xcoord][_ycoord]!=null){
					
					return false;
				}
			}
			
		}
		return true;
		
	}
}