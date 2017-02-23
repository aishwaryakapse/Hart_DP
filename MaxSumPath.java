public class MaxSumPath {

	public Parameters maxSumPath(int[][] num) {

		if (num == null || num.length == 0) // return null if matrix is null or
											// empty
			return null;

		int m = num.length;
		int n = num[0].length;

		int[][] dp = new int[m][n]; // take a new matrix

		dp[0][0] = num[0][0];

		// initialize top row
		for (int i = 1; i < n; i++) {
			dp[0][i] = dp[0][i - 1] + num[0][i];
		}

		// initialize left column
		for (int j = 1; j < m; j++) {
			dp[j][0] = dp[j - 1][0] + num[j][0];
		}

		// fill up the dp table
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				if (dp[i - 1][j] < dp[i][j - 1]) {
					dp[i][j] = dp[i][j - 1] + num[i][j];
				} else {
					dp[i][j] = dp[i - 1][j] + num[i][j];
				}
			}
		}

		/* scan the dp table to get max value and path */
		StringBuffer path = new StringBuffer(""); // take StringBuffer because it is mutable

		int max = dp[m - 1][n - 1];

		int x = dp.length;

		int i = 0;
		int j = 0;
		
		//for the path we start from top-left and end at bottom right
		for (int k = 0; k <= x; k++) {
			
			try {
				dp[i][j + 1] = dp[i][j + 1]; //to check if we need to take a turn and go down
			} catch (ArrayIndexOutOfBoundsException e) {
				path.append("↓");
				i++;
				continue;
			}
			try {
				dp[i + 1][j] = dp[i + 1][j]; //to check if we need to take a turn and go right
			} catch (ArrayIndexOutOfBoundsException e) {
				path.append("→");
				j++;
				continue;
			}
			if (dp[i][j + 1] >= dp[i + 1][j]) {
				path.append("→");
				j++;
			} else {
				path.append("↓");
				i++;
			}
			if (i == x - 1 && j == x - 1) { //exit loop if we have reached the last element of the matrix
				break;
			}
		}

		Parameters p = new Parameters();

		p.max = max;

		p.path = path.toString();

		return p;
	}

	/*Method to print the matrix*/
	public void printResult(int[][] num) {
		for (int i = 0; i < num.length; i++) {
			for (int j = 0; j < num[0].length; j++) {
				System.out.print(num[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void main(String[] args) {

		MaxSumPath msp = new MaxSumPath();

		int[][] num = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } }; //input matrix
		
		msp.printResult(num); // print the matrix

		Parameters x = msp.maxSumPath(num); //find the max value and path

		x.printParameters(); // print the max value and path 
	}
}