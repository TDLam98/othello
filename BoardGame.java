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

public class BoardGame{
	private static Scanner input= new Scanner(System.in);
	private static int NUM_OF_PLAYERS = 2;
	protected static Player[] pArray;
	protected static Board gameBoard;
	private static double turnCounter=1;//will maintain who's turn it is, evens-white, odds-black

//	• Color is either black “B” or “W”
			
	public static void main(String[] args) {
		pArray=new Player[NUM_OF_PLAYERS];
		promptUser();
		makeBoard();
		play();
		input.close();
	}
	
	//prompt to create the game board
	public static void makeBoard() {
		int row, col;
		System.out.println("What size do you want the board to be?\n*Please note the board must be square");
		System.out.println("Row size: ");
		row=input.nextInt();
		System.out.println("Col size: ");
		col=input.nextInt();
		gameBoard=new Board(row,col);
		
	} 
	
	//Function that will check to see if the desired team has already been chosen
	public boolean checkTeam(Player[] pArr, char team) {
		for(int i=0; i<pArr.length;i++) {
			if(pArr[i].getTeam() == team) {
				return false;
			}
		}
		return true;
	}

	//function to be called at the start of the game, sets up players, teams, and game type
	public static void promptUser() {
		char team;
		System.out.println("Welcome to Othello");
		System.out.println("You will be playing against a Computer.\nChoose your team.\nB: Black\nW: White");
		team=input.next().charAt(0);
		//Ensures that the player enters a valid team
		while(team != 'B' && team != 'W'){
			System.out.println("Invalid team, please either enter the character B for Black and W for White");
			team=input.next().charAt(0);
		}
		pArray[0]= new Player(team,"Player");
		//if the player choice of team was white team, there will be another player created that will 
		//be on the black team and will be controlled by the computer.
		if(team == 'W') {
			pArray[1]=new Player('B', "Computer");
		}
		else {
			//if the player picks the black team the computer will be assigned to the white team
			pArray[1]=new Player('W', "Computer");
		}
	}
	
	//function that will maintain the turns and prompt the user to get moves
	public static void play() {
	/*check to see who is controlling the black team, they play first
		if the player is on the black team, prompt them to input a desired move
		first validate that the move is valid, then move
	    if the computer is on the black team, run findValidMove, which will be passed to
	    the move function where the move will be actually made
	    turns will be maintained by a even and odd basis, black will maintain the odd turns starting from 1,
	    and white will maintain the even turns starting from 2
	*/
		int x,y;
		while(pArray[0].checkPath() || pArray[1].checkPath()) { //while there is a valid move for either player
			if(pArray[0].getTeam()=='B') {
				if(turnCounter%2!=0) {//maintains turns, black odd, white even
					if(pArray[0].checkPath()) {
						System.out.println("It's your turn!\nPlease enter the coordinates you would like to move to.");
						System.out.print("Enter the row you would like to move to: (1-"+Board.board.length+"): ");
						x=input.nextInt();
						System.out.print("Enter the column you would like to move to: (1-"+Board.board[0].length+"): ");
						y=input.nextInt();
						pArray[0].move(x-1, y-1);//the x-1, and y-1 are decremented to account for a easy choice on the board
						System.out.println(gamePiece.printBoard(Board.board));//prints the board after move
						score("Player");
					}else {
						System.out.println("You don't have a move");
					}
					turnCounter++; //turn over
				}else {
					//call a method that will find a move for the computer
					if(pArray[1].checkPath()) {
						pArray[1].moveComputer();
						System.out.println("Computer moved");
						System.out.println(gamePiece.printBoard(Board.board));//prints the board after move
						score("Player");
					}else
						System.out.println("Computer doesn't have a move");
					turnCounter++;//turn over
				}
			}else { //player is on the white team
				if(turnCounter%2!=0) {//maintains turns, black odd, white even
					//call a method that will find a move for the computer
					if(pArray[1].checkPath()) {
						pArray[1].moveComputer();
						System.out.println("Computer moved");
						System.out.println(gamePiece.printBoard(Board.board));//prints the board after move
						score("Computer");
					}else
						System.out.println("Computer doesn't have a move");
					turnCounter++; //turn over
				}else {
					if(pArray[0].checkPath()) {
						System.out.println("It's your turn!\nPlease enter the coordinates you would like to move to.");
						System.out.print("Enter the row you would like to move to: (1-"+Board.board.length+"): ");
						x=input.nextInt();
						System.out.print("Enter the column you would like to move to: (1-"+Board.board[0].length+"): ");
						y=input.nextInt();
						pArray[0].move(x-1, y-1);
						System.out.println(gamePiece.printBoard(Board.board));//prints the board after move
						score("Computer");
					}else {
						System.out.println("You don't have a move");
					}
					turnCounter++;//turn over
				}
			}
		}
		System.out.println("There are no more avalible spots");
		printScore();
	}
	
	//returns the score based upon the controller 
	public static void score(String controller) {
		if(controller.equals("Player")) {
			System.out.println("Score: Black: "+ pArray[0].getDisc()+" White: "+ pArray[1].getDisc());
		}else {
			System.out.println("Score: Black: "+ pArray[1].getDisc()+" White: "+ pArray[0].getDisc());

		}
	}

	public static void printScore() {
		if(pArray[0].getDisc()>pArray[1].getDisc()) {
			if(pArray[0].getTeam()=='B') {
				System.out.println("Black wins!\n"+"Final Score: White: "+pArray[1].getDisc()+"Black: "+pArray[0].getDisc());
			}else
				System.out.println("White wins!\n"+"Final Score: White: "+pArray[0].getDisc()+"Black: "+pArray[1].getDisc());
		}
		else if(pArray[0].getDisc()<pArray[1].getDisc()) {
			if(pArray[0].getTeam()=='B') {
				System.out.println("White wins!\n"+"Final Score: White: "+pArray[1].getDisc()+"Black: "+pArray[0].getDisc());
			}else
				System.out.println("Black wins!\n"+"Final Score: White: "+pArray[0].getDisc()+"Black: "+pArray[1].getDisc());
		}
		else {
			String output=(pArray[0].getTeam()=='B')? "It's a tie!\n White: "+ pArray[1].getDisc()+"Black: "+pArray[0].getDisc(): "It's a tie!\n White: "+ pArray[0].getDisc()+" Black: "+pArray[1].getDisc();
			System.out.println(output);
		}
	}
}

