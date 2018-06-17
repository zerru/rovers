package rovers;

/******************
 *  Output Class
 ******************/
public class Output {
	
	/******************
	 *  Variables
	 ******************/
	private int posX;
	private int posY;
	private char posDir;
	private String move;

	
	/******************
	 *  get
	 *  Get and analyse the inputs and write the output
	 *  @param: number int. Rover number
	 *  @result: newPosition String. The new position of the rover
	 ******************/
	public String get(int number) {
		String newPosition = "";
		// Get variables
		getVaribales(number);
		
		// Process the movements
		for (char c : move.toCharArray()) {
			switch(c) {
				case 'R':
					turn(number, true);
					break;
				case 'L':
					turn(number, false);
					break;
				case 'M':
					go(number);
					break;
				default:
					// Error
					System.out.println("Here Rover" + number + ": I don´t know which movement is " + c);
					break;
			}
		}
		
		// Set the new position in order to not to crash
		switch(number) {
			case 1:
				Input.pos1X = posX;
				Input.pos1Y = posY;
				break;
			case 2:
				Input.pos2X = posX;
				Input.pos2Y = posY;
				break;
			// TODO in case of newRover
			default:
				// Error
				System.out.println("Here Rover" + number + ": Error with myself. Am I anything?");
				break;
		}
		
		// Write & return the output
		newPosition += posX;
		newPosition += " ";
		newPosition += posY;
		newPosition += " ";
		newPosition += posDir;
		return newPosition;
	}
	
	/******************
	 *  getVaribales
	 *  Get the inputs
	 *  @param: number int. Rover number
	 ******************/
	private void getVaribales(int number) {
		switch(number) {
			case 1:
				posX = Input.pos1X;
				posY = Input.pos1Y;
				posDir = Input.pos1Dir;
				move = Input.move1;
				break;
			case 2:
				posX = Input.pos2X;
				posY = Input.pos2Y;
				posDir = Input.pos2Dir;
				move = Input.move2;
				break;
			// TODO in case of newRover
			default:
				// Error
				System.out.println("Here Rover" + number + ": Error with myself. Am I anything?");
				break;
		}
	}
	
	
	
	/******************
	 *  turn
	 *  Turn 90º, R or L
	 *  @param: number int. Rover number
	 *  @param: isClockwise boolean. Which direction is turning the rover. R: Clockwise. L: Anticlockwise
	 ******************/
	private void turn(int number, boolean isClockwise) {
		switch(posDir) {
			case 'N':
				if(isClockwise) {
					posDir = 'E';
				}else {
					posDir = 'W';
				}
				break;
			case 'E':
				if(isClockwise) {
					posDir = 'S';
				}else {
					posDir = 'N';
				}
				break;
			case 'S':
				if(isClockwise) {
					posDir = 'W';
				}else {
					posDir = 'E';
				}
				break;
			case 'W':
				if(isClockwise) {
					posDir = 'N';
				}else {
					posDir = 'S';
				}
				break;
			default:
				// Error
				System.out.println("Here Rover" + number + ": Error with direction " + posDir);
				break;
		}
	}
	
	/******************
	 *  go.
	 *  Move forwards one position if possible. Rover shall continue inside the plateau and shall not crash to another rover
	 *  @param: number int. Rover number
	 ******************/
	private void go(int number) {
		boolean remain4Plateau = true;
		switch(posDir) {
			case 'N':
				if(posY < Input.plateauY) {
					remain4Plateau = false;
					posY++;
					checkCrash(number, posDir);
				}
				break;
			case 'E':
				if(posX < Input.plateauX) {
					remain4Plateau = false;
					posX++;
					checkCrash(number, posDir);
				}
				break;
			case 'S':
				if(posY > 0) {
					remain4Plateau = false;
					posY--;
					checkCrash(number, posDir);
				}
				break;
			case 'W':
				if(posX > 0) {
					remain4Plateau = false;
					posX--;
					checkCrash(number, posDir);
				}
				break;
			default:
				// Error
				System.out.println("Here Rover" + number + ": Error with direction " + posDir);
				break;
		}
		
		// Is the end of the plateau in front?
		if(remain4Plateau) {
			// Error
			System.out.println("Here Rover" + number + ": I can´t go to " + posDir + " direction! I will remain in (" + posX + ", " + posY + ") position");
		}
	}
	
	/******************
	 *  checkCrash
	 *  Check if another rover is in the next position
	 *  @param: number int. Rover number
	 *  @result: willCrash boolean. There is another rover in front of the actual
	 ******************/
	private void checkCrash(int number, char posDir) {
		boolean willCrash = false;
		int crashWith = 0;
		
		// Compare the future position with the actual positions of other rovers
		switch(number) {
			case 1:
				if(posX == Input.pos2X && posY == Input.pos2Y) {	// TODO in case of newRover. More conditions
					willCrash = true;
					crashWith = 2;
				}
				break;
			case 2:
				if(posX == Input.pos1X && posY == Input.pos1Y) {	// TODO in case of newRover. More conditions
					willCrash = true;
					crashWith = 1;
				}
				break;
			// TODO in case of newRover
			default:
				// Error
				System.out.println("Here Rover" + number + ": Error with myself. Am I anything?");
				break;
		}
		
		// Is another rover in front?
		if(willCrash) {
			switch(posDir) {
			case 'N':
				posY--;
				break;
			case 'E':
				posX--;
				break;
			case 'S':
				posY++;
				break;
			case 'W':
				posX++;
				break;
			default:
				break;
			}
			// Error
			System.out.println("Here Rover" + number + ": It seems not to be more dimensions on Mars, I have my brother Rover" + crashWith +" in front of me. I will remain in (" + posX + ", " + posY + ") position");
		}
	}
	
}

