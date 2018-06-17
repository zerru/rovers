package rovers;

import java.util.Scanner;

/******************
 *  Input Class
 ******************/
public class Input {
	
	/******************
	 *  Global variables
	 *  TODO: This may not be the best practice in order to share variables in a project
	 *  		(I only have worked with Java in Android Studio, where I solved this problem with a Builder Class)
	 ******************/
	// Plateau variables
	public static int plateauX;
	public static int plateauY;
	// Rover1 variables
	public static int pos1X;
	public static int pos1Y;
	public static char pos1Dir;
	public static String move1;
	// Rover2 variables
	public static int pos2X;
	public static int pos2Y;
	public static char pos2Dir;
	public static String move2;
	// TODO in case of newRover

	/******************
	 *  Other variables
	 ******************/
	private Scanner input;
	private int x;
	private int y;
	private String posDirection;
	
	
	/******************
	 *  Constructor.
	 *  Get the inputs
	 *  @param: roverNumber int. Number of rovers
	 ******************/
	public Input(int roverNumber) {
		Scanner input = null;
		try {
			// Get plateau coordinates
			getInputPlateau();
			
			// Get all rover's position
			for(int i = 1; i <= roverNumber; i++){
				getInputPosition(i);
				getInputMove(i);
	         }
		}
		finally {
		    if(input != null) {
		    	input.close();
		    }
		}
	}
	
	/******************
	 *  getInputPlateau
	 ******************/
	private void getInputPlateau() {
		// Start getting
	    input = new Scanner(System.in);
	    String plateau; 
	    boolean retry = true;
		System.out.printf("Plateau's coordinates: Enter the rectangle size (x, y): ");
		
		// Retry until any proper value is typed
		while(retry) {
			plateau = input.nextLine();
			retry = checkCoor(plateau);
			if(x == 0  || y == 0) {  				// (0, 0) coordinates not allowed
				retry = true;
			}
			
			// Check if OK
			if(retry) {
				System.out.printf("Error with the Plateau coordinates. Write 2 numbers between 1 and 100, separated by a space: ");
			}else {
				// Save the correct values
				plateauX = x;
				plateauY = y;
			}
		}
	}
	
	
	/******************
	 *  getInputPosition
	 *  @param: number int. Rover number
	 ******************/
	private void getInputPosition(int number) {
		// Start getting
	    input = new Scanner(System.in);
	    String position; 
	    boolean retry = true;
	    posDirection = " ";
		System.out.printf("Rover" + number + "'s input: Enter the position (x, y, N): ");
		
		// Retry until any proper value is typed
		while(retry) {
			position = input.nextLine();
			retry = checkPosition(position);
			
			// Check if OK
			if(retry) {
				System.out.printf("Error in the Rover" + number + " position! Write 2 numbers between 1 and 100 and the direction (N or S or W or E), separated by spaces: ");
			}
		}
		
		// Save the correct values
		switch(number) {
			case 1:
				pos1X = x;
				pos1Y = y;
				pos1Dir = posDirection.charAt(0);
				break;
			case 2:
				pos2X = x;
				pos2Y = y;
				pos2Dir = posDirection.charAt(0);
				break;
			// TODO in case of newRover
			default:
				break;
		}
		
	}
	
	/******************
	 *  getInputMove
	 *  @param: number int. Rover number
	 ******************/
	private void getInputMove(int number) {
		// Start getting
		input = new Scanner(System.in);
		String move = "";
	    boolean retry = true;
		System.out.printf("Rover" + number + "'s input: Enter the movements (L, R or M): ");
		
		// Retry until any proper value is typed
		while(retry) {
			retry = false;
			move = input.nextLine();
			int i = 0;
			for (char c : move.toCharArray()) {
			    if ((i > 20) && c != 'L' && c != 'R' && c != 'M') {			// Check plausible values
			    	retry = true;
			    	break;
			    }
			    i++;
			}
			
			// Check if OK
			if(retry) {
				System.out.printf("Error with the Rover" + number + " movements! The plausible movements are L, R or M (max 20): ");
			}
		}
		
		// Save the correct values
		switch(number) {
			case 1:
				move1 = move;
				break;
			case 2:
				move2 = move;
				break;
			// TODO in case of newRover
			default:
				break;
		}
	}
	
	/******************
	 *  checkCoor
	 *  @param: input String. This string shall be 2 numeric values separated by a space
	 *  @result: retry boolean. Must we retry the input?
	 ******************/
	private boolean checkCoor(String input) {
		boolean retry = false;
		int spaceCount = 0;
		
		// Is the string filled by space and integers?
		for (char c : input.toCharArray()) {
		    if (c == ' ') {
		    	// Space counter
		        spaceCount++;
		    }else if(!Character.isDigit(c)) {
		    	retry = true;
		    	break;
		    }
		}
		
		// Could be a proper value if there is 1 space and for now the retry is false
		if(spaceCount == 1 && !retry) {
			int localX = 0;
			int localY = 0;
			if(input.indexOf(" ") > 0 && input.indexOf(" ") + 1 < input.length()) { 		// Not starting or finishing with a space
				localX = Integer.parseInt(input.substring( 0, input.indexOf(" ")));
				localY = Integer.parseInt(input.substring(input.indexOf(" ") + 1, input.length()));
			}
			
			// Check boundaries
			if(localX >= 0 && localX <= 100 && localY >= 0 && localY <= 100) {
				// Save the correct values
				x = localX;
				y = localY;
			}else {
				retry = true;
			}
		}else {
			retry = true;
		}
		return retry;
	}
	
	/******************
	 *  checkPosition
	 *  @param: input String. This string shall be 2 numeric and Direction (N, S, E or W) values separated by spaces. 
	 *  @result: retry boolean. Must we retry the input?
	 ******************/
	public boolean checkPosition(String input) {
		boolean retry = false;
		int spaceCount = 0;
		int i = 0;
		for (char c : input.toCharArray()) {
		    if (c == ' ') {
		    	// Space counter
		        spaceCount++;
		    }else if(!Character.isDigit(c)) {
		    	if((input.length() != i+1) && c != 'N' && c != 'S' && c != 'W' && c != 'E') {   // Check plausible values
		    		retry = true;
		    		break;
		    	}
		    }
		    i++;
		}
		
		// Could be a proper value if there are 2 spaces and for now the retry is false
		if(spaceCount == 2 && !retry) {
			String posCoor = input.substring( 0, input.lastIndexOf(" "));
			posDirection = input.substring(input.lastIndexOf(" ") + 1, input.length());
			if(posDirection.length() == 1) {					// The length of the direction shall be 1
				retry = checkCoor(posCoor);
				if(x > plateauX || y > plateauY) {				// Bigger than plateau not allowed
					System.out.println("Error! Not existing position in the plateau. The Plateau's coordinates are smaller.");
					retry = true;
				}
			}else {
				retry = true;
			}
		}else {
			retry = true;
		}
		
		return retry;
	}
	
}

