/*
 *    Tan-Dat Lam	
 *    2336.004
 *	The GameBoard has position (1,1) as the top left corner.
 *  The program will prompt you until you have completed the game.
 *  Diagonals are not implemented
 *  Hard AI is not implemented
 *  A list of possible moves is not implemented
 */
import java.util.*;

public class Board  {
	private Scanner input= new Scanner(System.in); 
	private int NUM_OF_ROW;
	private int NUM_OF_COL;
	protected static gamePiece[][] board;
	
	//constructor for Board
	public Board(int row, int col) {
		checkEquality(row,col);	
		drawBoard();
	}
	
	//validation for board size
	public void checkEquality(int r, int c) {
		while(r!=c) {
			System.out.println("Your game board must be square!\nPlease reenter your desired\nRow size: ");
			r=input.nextInt();
			System.out.println("Column size: ");
			c=input.nextInt();
		}
		NUM_OF_ROW=r;
		NUM_OF_COL=c;
	}
	
	//creates the initial board of game pieces
	public void drawBoard() {

		//W at (i,j)->(mid,mid),(mid+1,mid+1)
		//B at (i,j)->(mid,mid+1),(mid+1,mid)
		board= new gamePiece[NUM_OF_ROW][NUM_OF_COL];
		int mid=(NUM_OF_ROW-1)/2;
		//i=0 i<=num_of_row-1
		//j=0 i<=num_of_col-1
		for(int i=0; i<=NUM_OF_ROW-1;i++) {
			for(int j=0; j<=NUM_OF_COL-1;j++) {
				if((i==mid && j==mid)||(i==mid+1 && j==(mid+1))){
					board[i][j] = new gamePiece('W');
				}else if((i==mid && j==mid+1)||(i==mid+1 && j==mid)) {
					board[i][j]= new gamePiece('B');
				}else {
					board[i][j]= new gamePiece('_');
				}
			}
		}
		System.out.println(gamePiece.printBoard(board));
	}
	
	//returns rows
	public int getRow() {
		return this.NUM_OF_ROW;
	}
	
	//returns columns
	public int getCol() {
		return this.NUM_OF_COL;
	}
}

