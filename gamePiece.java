/*
 *    Tan-Dat Lam	
 *    2336.004
 *	The GameBoard has position (1,1) as the top left corner.
 *  The program will prompt you until you have completed the game.
 *  Diagonals are not implemented
 *  Hard AI is not implemented
 *  A list of possible moves is not implemented
 */
public class gamePiece {

	private char color;
	
	//constructor for gamePiece
	public gamePiece(char color) {
		this.color=color;
	}
	
	//returns color
	public void setColor(char color) {
		this.color=color;
	}
	
	//returns color
	public char getColor() {
		return color;
	}
	
	//prints the board of a given array of gamePieces
	public static String printBoard(gamePiece[][] gArr) {
		  String build = "";
		  for(int row = 0; row < gArr.length; row++) {
		     for(int col = 0; col < gArr[row].length; col++) {
		        build += " " + gArr[row][col].getColor();
		     }
		     build+="\n";
		  }
		  return build;
	}
}
