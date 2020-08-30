package Tetris;

import javafx.scene.shape.Rectangle;

/**
 *This class models the squares(rectangle shapes) that make up each Tetris piece and each square of the board.
 */
public class TetrisSquare{
	private Rectangle _tetrissquare;
	/*
	 * This method constructs a TetrisSquare, which is a 30x30 square.
	 */
	public TetrisSquare() {
		_tetrissquare = new Rectangle(Constants.SQUARE_SIZE, Constants.SQUARE_SIZE);
	}
	
	/*
	 * This accessor method returns a rectangle TetrisSquare, and was made so that the shape node could be accessed in the TetrisPiece class.
	 */
	public Rectangle getTetrisSquare() {
		return _tetrissquare;
	}
}

