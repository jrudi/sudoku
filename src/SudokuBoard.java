import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.BitSet;

public class SudokuBoard {
	private int[][] board;

	public static final int ROWS = 9;
	public static final int COLS = 9;

	public SudokuBoard() {
		board = new int[ROWS][COLS];
	}
	
	public SudokuBoard(int[][] board) {
		if(board.length != ROWS || board[0].length != COLS)
			throw new InvalidParameterException(
					"Das bereits vorgegebene Sukodu muss die "
				  + "Groesse " + ROWS + "x" + COLS + " haben!");
			
		this.board = board;
	}
	
	private void checkIndex(int index) {
		int maxIndex = (ROWS*COLS) - 1;
		if(index < 0 || index > maxIndex)
			throw new InvalidParameterException(
					"Ungueltiger Index: " + index + ".\n"
				  + "Der Index darf nicht kleiner 0 und nicht groesser " + maxIndex + " sein!");
	}
	
	public SudokuBoard set(int index, int number) {
		checkIndex(index);
		return set(index / COLS, index % COLS, number);
	}
	
	public SudokuBoard set(int row, int col, int number) {
		if(!isFree(row, col))
			throw new InvalidParameterException("An der Stelle ("
					+ row + "," + col + ") steht bereits eine Zahl!");
		
		int[][] newBoard = new int[board.length][];
		for(int i = 0; i < board.length; i++)
			newBoard[i] = board[i].clone();
		
		newBoard[row][col] = number;
		
		return new SudokuBoard(newBoard);
	}

	public int[] getCandidates(int index) {
		checkIndex(index);
		return getCandidates(index / COLS, index % COLS);
	}
	
	private int[] getCandidates(int row, int col) {
		if(board[row][col] != 0)
			return new int[0];
		
		BitSet isCandidate = new BitSet(9);
		
		int blockRow = row / 3;
		int blockCol = col / 3;
		
		for(int number = 0; number < 9; number++) {
			board[row][col] = number + 1;
			if(isRowValid(row) && isColValid(col) &&
					isBlockValid(blockRow, blockCol)) {
				isCandidate.set(number);
			}
		}
		
		board[row][col] = 0;
		
		int numCandidates = isCandidate.cardinality();
		int candidates[] = new int[numCandidates];
		
		int arrayIdx = 0;
		for (int bitIndex = isCandidate.nextSetBit(0); bitIndex >= 0;
				 bitIndex = isCandidate.nextSetBit(bitIndex + 1)) {
			candidates[arrayIdx] = bitIndex + 1;
			arrayIdx++;
		 }
		
		return candidates;
	}

	public boolean isFree(int index) {
		checkIndex(index);
		return isFree(index / COLS, index % COLS);
	}

	private boolean isFree(int row, int col) {
		return board[row][col] == 0;
	}

	public boolean isSolved() {
		return isFull() && isValid();
	}
	
	public boolean isFull() {
		for(int row = 0; row < ROWS; row++)
			for(int col = 0; col < COLS; col++)
				if(board[row][col] == 0)
					return false;

		return true;
	}

	public boolean isValid() {
		return isValid(false);
	}
	
	public boolean isValid(boolean printErrors) {
		boolean isValid = true;
		
		for(int row = 0; row < ROWS; row++)
			if(!isRowValid(row)) {
				System.err.println("Zeile " + row + " ist ung�ltig!");
				isValid = false;
			}

		for(int col = 0; col < COLS; col++)
			if(!isColValid(col)) {
				System.err.println("Spalte " + col + " ist ungueltig!");
				isValid = false;
			}

		for(int blockRow = 0; blockRow < ROWS / 3; blockRow++)
			for(int blockCol = 0; blockCol < COLS / 3; blockCol++)
				if(!isBlockValid(blockRow, blockCol)) {
					System.err.println("Block (" + blockRow + "," + blockCol + ") ist ungueltig!");
					isValid = false;
				}

		return isValid;
	}

	private boolean isRowValid(int row) {
		int[] occurrences = new int[9];
		
		for(int col = 0; col < COLS; col++) {
			int number = board[row][col];
			if(number > 0)
				occurrences[number - 1]++;
		}
		
		for(int number = 0; number < 9; number++) {
			if(occurrences[number] > 1)
				return false;
		}
		
		return true;
	}

	private boolean isColValid(int col) {
		int[] occurrences = new int[9];
		
		for(int row = 0; row < ROWS; row++) {
			int number = board[row][col];
			if(number > 0)
				occurrences[number - 1]++;
		}
		
		for(int number = 0; number < 9; number++) {
			if(occurrences[number] > 1)
				return false;
		}
		
		return true;
	}

	private boolean isBlockValid(int blockRow, int blockCol) {
		int[] occurrences = new int[9];
		
		for(int row = blockRow * 3; row < (blockRow + 1) * 3; row++)
			for(int col = blockCol * 3; col < (blockCol  + 1) * 3; col++) {			
				int number = board[row][col];
				if(number > 0)
					occurrences[number - 1]++;
			}
		
		for(int number = 0; number < 9; number++) {
			if(occurrences[number] > 1)
				return false;
		}
		
		return true;
	}
	
	public void print() {

		for (int row = 0; row < ROWS; row++) {
			
			if(row % 3 == 0)
				System.out.println("-------------------");
					
			for (int col = 0; col < COLS; col++) {
				if(col % 3 == 0)
					 System.out.print("|");
				else System.out.print(" ");
				
				int number = board[row][col]; 
				if(number > 0)
					 System.out.print(number);
				else System.out.print(" ");
			}
				
				
			System.out.println("|");
		}
		
		System.out.println("-------------------");
		System.out.println();
	}
	
	public void printAndExit() {
		print();
		System.exit(0);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		
		if (getClass() != obj.getClass())
			return false;
		
		SudokuBoard other = (SudokuBoard) obj;
		if (!Arrays.deepEquals(board, other.board))
			return false;
		
		return true;
	}
}
