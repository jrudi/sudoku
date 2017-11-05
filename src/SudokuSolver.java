public class SudokuSolver {

		static int getNextFreeIndex(SudokuBoard board) {
			for(int i = 0; i <= 80; i++){
				if(board.isFree(i)){
					return i;
				}
			}
		
		return -1;
	}

		static void printSolution(SudokuBoard board) {
			if(board.isSolved()) {
                board.printAndExit();
            }
            int k = getNextFreeIndex(board);
			if(k == -1) {
                System.out.println("-1");
                return;
            }
			int[] candidates = board.getCandidates(k);
		
				for(int i = 0; i < candidates.length; i++){
					SudokuBoard newBoard;
					newBoard = board.set(k, candidates[i]);
					printSolution(newBoard);
				}
			
		}
		static SudokuBoard findSolution(SudokuBoard board) {
			if(board.isSolved()){
				return board;
			}	
				
				int k = getNextFreeIndex(board);
				if(k == -1){
					return null;
				}
				
				
				int[] candidates = board.getCandidates(k);
				
				SudokuBoard sol = null;
					
					for(int i = 0; i < candidates.length; i++){
						SudokuBoard newBoard;
						newBoard = board.set(k, candidates[i]);
						
						if (sol == null){
								
							sol= findSolution(newBoard);		
						}
					}
					return sol;
					
		}

	
	public static void main(String[] args) {
        System.out.println(1);
        printSolution(new SudokuBoard(startValues1));
        System.out.println(2);
        printSolution(new SudokuBoard(startValues3));
        System.out.print(3);
        printSolution(new SudokuBoard(startValues2));
    }

	static int[][] startValues1 = {
                {8,0,0,0,0,0,0,0,0},
				{0,0,3,6,0,0,0,0,0},
				{0,7,0,0,9,0,2,0,0},
                {0,5,0,0,0,7,0,0,0},
				{0,0,0,0,4,5,7,0,0},
				{0,0,0,1,0,0,0,3,0},
				{0,0,1,0,0,0,0,6,8},
				{0,0,8,5,0,0,0,1,0},
				{0,9,0,0,0,0,4,0,0}};

	static int[][] startValues2 = {
				{0,7,0,0,0,6,0,0,0},
				{9,0,0,0,0,0,0,4,1},
				{0,0,8,0,0,9,0,5,0},
				{0,9,0,0,0,7,0,0,2},
				{0,0,3,0,0,0,8,0,0},
				{4,0,0,8,0,0,0,1,0},
				{0,8,0,3,0,0,9,0,0},
				{1,6,0,0,0,0,0,0,7},
				{0,0,0,5,0,0,0,8,0}};

	static int[][] startValues3 = {
				{5,3,0,0,7,0,0,0,0},
				{6,0,0,1,9,5,0,0,0},
				{0,9,8,0,0,0,0,6,0},
				{8,0,0,0,6,0,0,0,3},
				{4,0,0,8,0,3,0,0,1},
				{7,0,0,0,2,0,0,0,6},
				{0,6,0,0,0,0,2,8,0},
				{0,0,0,4,1,9,0,0,5},
				{0,0,0,0,8,0,0,7,9}};

}
