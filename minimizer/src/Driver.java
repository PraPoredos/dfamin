/* Driver.java: driver class for the DFA minimization program.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Driver
{
	public static void main(String[] args)
	{
		MachineBuilder builder = new MachineBuilder();
		
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Enter the name of the file containing the DFA: ");
		String filename = keyboard.nextLine();
		
		Machine machine = null;
		
		try
		{
			machine = builder.getMachine(new Scanner(new File(filename)));
			System.out.println("Read in:\n" + machine);
		}
		catch (FileNotFoundException e)
		{
			System.out.println("File not found.");
			System.exit(1);
		}
		
		machine = Minimizer.minimize(machine);
		
		System.out.println("Minimized machine:\n" + machine);
	}
}
