Tetris README

Handin: Final Handin

Overview:
	My Tetris project has 5 main classes: App, PaneOrganizer, Game, TetrisPiece, and TetrisSquare. It also has the imported classes Timeline, TimeHandler, KeyHandler, ClickHandler
	BorderPane,Pane, Button, Label,and Rectangle. They interact in the following ways:
	 -App, which starts the application, contains an instance of PaneOrganizer and graphically adds the root BorderPane to the Scene graph.
    -PaneOrganizer, the top-level class, contains an instance of the Game class and the Quit button + ClickHandler; this button is graphically added to the root pane.
    -Game models the Tetris game, and contains a board made out of an Array of TetrisSquares. It also contains a Gamepane, to which all the squares and the labels are graphically added.
    Lastly, it also contains the Timeline, TimeHandler, KeyHandler, and pause Label. 
    -TetrisPiece models 7 types of pieces, each made of an 1D array of four TetrisSquares. It also contains the gameover Label. Both the squares in the pieces and the label are graphically
    added to the gamepane.
    -TetrisSquare contains an instance of the Rectangle shape, which is a 30x30 square.

  Design Choices:
    In my project, Arrays are used as a way to store both the squares in each Tetris piece and also store the squares on the board. 
    I used a 4x2 array of constants to set the coordinates of each of the squares in each of the seven types of pieces. Out of seven shapes, 
    a random shape is generated each time the previous piece can no longer move; a switch statement was used for this random generation. 
    For each move method in TetrisPiece, I also created a corresponding boolean method that checked the validity of the move; I did this as I found 
    it made the method less messy (less nested if statements) through delegation of the validity checking to a separate method. This logic of checking /if statements
    as a result of checking is major theme throughout this project; the if statements nested within for statements allowed for each square to be checked for a 
    specific column/row/both, and logically presented two pathways as a result of the if statement nested within the for loop. 
  
  
  Known Bugs: none :)