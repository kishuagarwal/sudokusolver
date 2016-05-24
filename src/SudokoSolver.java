import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;



public class SudokoSolver{

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	static int sudoku[][] = new int[9][9];
	static int emptyPos = 0; 
	//static int tries = 0;
	public static void main(String[] args) throws FileNotFoundException {
			
		Scanner in = new Scanner(new File("sudoku.txt"));
		
		
		for(int i = 0; i < 9; i++)
		{
			for(int j = 0; j < 9; j++)
			{
				sudoku[i][j] = in.nextInt();
				if(sudoku[i][j] == 0)
					emptyPos++;
				
			}
		}
		boolean is = solve();
		System.out.println(is);
		for(int i = 0; i < 9; i++)
		{
			for(int j = 0; j < 9; j++)
			{
				System.out.print(sudoku[i][j]+" ");
				
			}
			System.out.println();
		}
		
		in.close();
		
		
	}

	private static boolean solve() {
		
		while(!isSolved() )
		{
		//	tries++;
		//	System.out.println(emptyPos);
			for(int i = 0; i < 9; i++)
			{
				for(int j = 0; j < 9; j++)
				{
					if(sudoku[i][j] == 0)
					{
						
						for(int k = 1; k <= 9 ; k++)
						{
							if(isValid(i,j,k))
							{
								
								sudoku[i][j] = k;
								emptyPos--;
								if(solve())
									return true;
								else
								{
									sudoku[i][j] = 0;
									emptyPos++;
								continue;
								}
								
							}
						}
						return false;
							
					}
				}
			}
		}
		return true;
		
	}

	public static boolean isSolved(){
		if(emptyPos == 0)
		{
			return true;
		}
		else
			return false;
	}
	
	public static boolean isValid(int x,int y,int number)
	{
		for(int i = 0; i < 9; i++)
		{
			if(sudoku[x][i] == number)
				return false;
		}
		for(int i = 0; i < 9; i++)
		{
			if(sudoku[i][y] == number)
				return false;
		}
		
		int lowestRow = (x/3)*3;
		int lowestCol = (y/3)*3;
		
		for(int i= lowestRow; i < lowestRow+3;i++)
		{
			for(int j = lowestCol;j < lowestCol+3; j++)
			{
				if(sudoku[i][j] == number)
					return false;
			}
		}
			
		return true;
	}
	

}
