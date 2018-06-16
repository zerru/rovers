package rovers;

import java.util.Scanner;

public class Input {
	
	//public void Write() {
	public Input() {
		Scanner input = null;
		try {
		    input = new Scanner(System.in);
		    String plateau; 
			System.out.println("Please, enter the plateau coordinates:");
			plateau = input.next(); 
			System.out.println("Plateau : " + plateau);
		}
		finally {
		    if(input!=null)
		    	input.close(); //d
		}
	}
	
}
