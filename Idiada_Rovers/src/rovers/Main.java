package rovers;

/**
 *********** CREATE .jar FILE ***********
 * manifest file should be created, and inside it main java class (including the package)
 * When Export is done to create the .jar file, choose "use existing manifest from workspace" in the last window.
 * 
 *********** RUN .jar FILE ***********
 * In order to run the .jar write where the file is located:
 * java -jar rovers.jar
 */


/**
 * Main Class
 */
public class Main {
	/******************
	 *  Modifiable variables
	 ******************/
	static private int ROVER_NUMBER = 2;  // TODO in case of newRover
	
	
	/**
	 * main
	 */
	public static void main(String[] args) {
		
		// Create an input object
		Input in = new Input(ROVER_NUMBER);

        // Process the movements
        Output out = new Output();
        String rover1 = out.get(1);
        String rover2 = out.get(2);
        // TODO in case of newRover
        
        // Check & show the results
        if(!in.checkPosition(rover1)) {
        	System.out.println(rover1);
        }
        if(!in.checkPosition(rover2)) {
        	System.out.println(rover2);
        }
        // TODO in case of newRover
        
        // Finished
        System.out.printf("Finished!");
    }
}

