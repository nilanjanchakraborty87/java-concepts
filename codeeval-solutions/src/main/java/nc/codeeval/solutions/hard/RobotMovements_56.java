package nc.codeeval.solutions.hard;

/**
 * A robot is located in the upper-left corner of a 4×4 grid. The robot can move
 * either up, down, left, or right, but cannot go to the same location twice.
 * The robot is trying to reach the lower-right corner of the grid. Your task is
 * to find out the number of unique ways to reach the destination.
 * 
 * INPUT SAMPLE:
 * 
 * There is no input for this program.
 * 
 * OUTPUT SAMPLE:
 * 
 * Print out the number of unique ways for the robot to reach its destination.
 * The number should be printed out as an integer greater than 0.
 * 
 * @author nchak2
 *
 */

public class RobotMovements_56 {

	private static int uniqueWays = 0;
	private static final int X_DIMENSION = 4;
	private static final int Y_DIMENSION = 4;
	
	public static void main(String[] args) {
		
		boolean[][] grid = new boolean[4][4];
		letsMove(grid, 0, 0);
		System.out.println(uniqueWays);
	}
	
	static void letsMove(boolean[][] grid, int x, int y )
	{
		if ( x < 0 || y < 0 || x >= X_DIMENSION || y >= Y_DIMENSION ) return;
		if ( grid[x][y]  ) return;
		if ( x == (X_DIMENSION - 1) && y == (Y_DIMENSION - 1) ) uniqueWays++;

		grid[x][y] = true;
		letsMove(grid, x, y+1);
		letsMove(grid, x+1, y);
		letsMove(grid, x, y-1);
		letsMove(grid, x-1, y);
		
		grid[x][y] = false;
	}
}
