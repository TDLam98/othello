/*
 *    Tan-Dat Lam	
 *    2336.004
 *	The GameBoard has position (1,1) as the top left corner.
 *  The program will prompt you until you have completed the game.
 *  Diagonals are not implemented
 *  Hard AI is not implemented
 *  A list of possible moves is not implemented
 */
import java.util.Scanner;

/*
 * The players color will be represented as a char value
 * 'B'-black, 'W'-white
 * The number of Discs the player is holding will be represented by an integer numDisc
 * The controller of the object will be represented by whosInControl and will either be "Computer" or "Player"
 * 
 */
public class Player {
	private static Scanner input= new Scanner(System.in);
	private char team; 
	private int numDisc=2;
	private String whosInControl;
	private gamePiece piece;
	
	//constructor for Player
	public Player(char team,String cont) {
		piece= new gamePiece(team);
		this.team=team;
		whosInControl=cont;
	}
	
	//constructor for Player
	public Player(char team) {
		piece= new gamePiece(team);
		this.team=team;
	}
	
	//after the move is complete you will call the changeDiscs method for both
	//computer and player, the change will be stored in playerCounter and comp counter
	public void move(int i, int j) {
		int playerCounter=0;//change in players discs
		int compCounter=0;//change in computers discs
		char team= this.getTeam();//controllers team
		String opponent=(this.getController()=="Player")? "Computer": "Player";//stores opponents team based upon controller
		int path= this.findPath(i,j);// either 1,2,3,4,0
		while(!this.validateMove(i, j) && this.checkPath()) {
			System.out.println("Invalid move");
			System.out.print("Enter the row you would like to move to: (1-"+Board.board.length+"): ");
			i=input.nextInt();
			System.out.print("Enter the column you would like to move to: (1-"+Board.board[0].length+"): ");
			j=input.nextInt();
		}
		
		if(this.validateMove(i,j) && this.checkPath()) {
			//Board.board[i][j].setColor(this.getTeam());
			//Board.board[i][j]=new gamePiece(this.getTeam());
			Board.board[i][j].setColor(team);
			switch(path) {
			case 1: //fill path below
				Board.board[i+1][j].setColor(team);
				//Board.board[i+1][j]= new gamePiece(this.getTeam());
				if(opponent.equals("Computer")) {
					compCounter--;//computer losing a disc
					playerCounter+=2;;//player adding two discs
					BoardGame.pArray[0].changeDiscs(playerCounter);
					BoardGame.pArray[1].changeDiscs(compCounter);
				}	
				else {
					playerCounter--;//player losing one disc
					compCounter+=2;//computer adding two discs
					BoardGame.pArray[0].changeDiscs(playerCounter);
					BoardGame.pArray[1].changeDiscs(compCounter);		
				}
				break;
			case 2://fill path to the left
				Board.board[i][j-1].setColor(team);
				//Board.board[i][j-1]=new gamePiece(this.getTeam());
				if(opponent.equals("Computer")) {
					compCounter--;
					playerCounter+=2;
					BoardGame.pArray[0].changeDiscs(playerCounter);
					BoardGame.pArray[1].changeDiscs(compCounter);
				}	
				else {
					playerCounter--;
					compCounter+=2;
					BoardGame.pArray[0].changeDiscs(playerCounter);
					BoardGame.pArray[1].changeDiscs(compCounter);		
				}
				break;
			case 3://fill path above
				Board.board[i-1][j].setColor(team);
				//Board.board[i-1][j]=new gamePiece(this.getTeam());
				if(opponent.equals("Computer")) {
					compCounter--;
					playerCounter+=2;
					BoardGame.pArray[0].changeDiscs(playerCounter);
					BoardGame.pArray[1].changeDiscs(compCounter);
				}	
				else {
					playerCounter--;
					compCounter+=2;
					BoardGame.pArray[0].changeDiscs(playerCounter);
					BoardGame.pArray[1].changeDiscs(compCounter);		
				}
				break;
			case 4://fill path to the right
				Board.board[i][j+1].setColor(team);
				//Board.board[i][j+1]=new gamePiece(this.getTeam());
				if(opponent.equals("Computer")) {
					compCounter--;
					playerCounter+=2;
					BoardGame.pArray[0].changeDiscs(playerCounter);
					BoardGame.pArray[1].changeDiscs(compCounter);
				}	
				else {
					playerCounter--;
					compCounter+=2;
					BoardGame.pArray[0].changeDiscs(playerCounter);
					BoardGame.pArray[1].changeDiscs(compCounter);		
				}
				break;
			}
		}else {
			System.out.println("You have no valid moves.");
		}
	}

	//you will check the index that you will move to, checks if that team has a piece on any of the surrounding sides
	public boolean validateMove(int i, int j) {

		char team= this.getTeam();
		char color= Board.board[i][j].getColor();
		if(color=='_' && this.findPath(i,j)>0) {
			if((i+1<=Board.board.length-1 && Board.board[i+1][j]!=null) ? Board.board[i+1][j].getColor()!=team: false ) {//checking directly below coordinate
				return true;
			}else if((i-1>=0 && Board.board[i-1][j]!=null) ? Board.board[i-1][j].getColor()!=team: false) {//checking directly above coordinate
				return true;
			}else if((j-1>=0 && Board.board[i][j-1]!=null) ? Board.board[i][j-1].getColor()!=team : false) {//checking directly to the left of the coordinate
				return true;
			}else if((j+1<=Board.board[i].length-1 && Board.board[i][j+1]!=null) ? Board.board[i][j+1].getColor()!=team : false) {//checking directly to the right of the coordinate 
				return true;
			}
		}
	return false;
}
	
	//function that will return a value based upon whether there is a valid pathway
	// 1- below
	// 2- left
	// 3- above
	// 4- left
	public int findPath(int i, int j) {
	//	char color= Board.board[i][j].getColor();
		char team= this.getTeam();
		char opponentTeam=(this.getTeam()=='B')?'W':'B';
		if(((i+2)<=Board.board.length-1)? Board.board[i+2][j].getColor()==team && Board.board[i+1][j].getColor()==opponentTeam: false) {//checking a straight line can be made below vertically
			return 1; //below
		}else if(((i-2)>=0)? Board.board[i-2][j].getColor()==team && Board.board[i-1][j].getColor()==opponentTeam:false){//checking if a straight line can be made upwards vertically
			return 3;//above
		}else if(((j-2)>=0)? Board.board[i][j-2].getColor()==team && Board.board[i][j-1].getColor()==opponentTeam:false) {
			return 2;//left
		}else if(((j+2)<=Board.board[i].length-1)? Board.board[i][j+2].getColor()==team && Board.board[i][j+1].getColor()==opponentTeam:false) {
			return 4;//right
		}
		return 0;
	}
	
	//checks to see if there are any moves on the board
	public boolean checkPath() {
		for(int row=0; row<Board.board.length; row++) {
			for(int col=0; col<Board.board[row].length;col++) {
				if(this.validateMove(row,col)) {
					return true;//move found
				}
			}
		}
		return false;
	}
	
	//function that finds a valid move and then moves the computer to the correct location
	public void moveComputer() {
		int playerCounter=0;//change in players discs
		int compCounter=0;//change in computers discs
		boolean moved=false;//limits the computer to move once
		char team=BoardGame.pArray[1].getTeam();//stores the team of the computer
		for(int i=0; i<Board.board.length && !moved; i++) {
			for(int j=0; j<Board.board[i].length  && !moved;j++) {
				if(BoardGame.pArray[1].validateMove(i,j)) {
					Board.board[i][j].setColor(team);
					System.out.println("The computer moved to the location: ("+(i+1)+","+(j+1)+")");
					moved=true;
					switch(findPath(i,j)) {
					case 1: //fill path below
						Board.board[i+1][j].setColor(team);
							playerCounter--;
							BoardGame.pArray[0].changeDiscs(playerCounter);
							BoardGame.pArray[1].changeDiscs(compCounter+=2);			
							break;
					case 2://fill path to the left
						Board.board[i][j-1].setColor(team);
						playerCounter--;
						BoardGame.pArray[0].changeDiscs(playerCounter);
						BoardGame.pArray[1].changeDiscs(compCounter+=2);	
						break;
					case 3://fill path above
						Board.board[i-1][j].setColor(team);
						playerCounter--;
						BoardGame.pArray[0].changeDiscs(playerCounter);
						BoardGame.pArray[1].changeDiscs(compCounter+=2);		
						break;
					case 4://fill path to the right
						Board.board[i][j+1].setColor(team);
						playerCounter--;
						BoardGame.pArray[0].changeDiscs(playerCounter);
						BoardGame.pArray[1].changeDiscs(compCounter+=2);	
						break;
					}
			}
		}
	}
}
	//set function for the controller of the team
	public void setController(String cont) {

		whosInControl=cont;
	}
	//returns the players controller
	public String getController() {

		return whosInControl;
	}
	//sets the team of the controller
	public void setTeam(char t) {
		piece= new gamePiece(team);
		team=t;
	}
	//set function that allows us to increase or decrease the number of discs
	public void changeDiscs(int d) {
		this.numDisc+=d;
	}
	//returns team
	public char getTeam() {
		return team;
	}
	//returns the number of discs the controller has
	public int getDisc() {
		return this.numDisc;
	}
	
}
