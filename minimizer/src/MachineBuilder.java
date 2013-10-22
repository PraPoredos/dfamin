/* MachineBuilder.java: Used to build a machine from text input.
 */

import java.util.Scanner;
import java.util.Vector;

public class MachineBuilder
{
	/* Build a machine from whatever you can read in from scan. This method is
	 * agnostic as to what the Scanner is attached to.
	 */
	public Machine getMachine(Scanner scan)
	{
		int numStarts = 0; // should be 1 in the end

		Vector<State> Q = new Vector<State>(), F = new Vector<State>();
		Vector<Character> alphabet = new Vector<Character>();
		State initial = new State(null);

		while (scan.hasNext())
		{
			String line = scan.nextLine();

			if (line.startsWith(Machine.START_INDICATOR))
			{
				numStarts++;

				if (numStarts > 1)
				{
					break;
				}	
				else
				{
					line = line.substring(Machine.START_INDICATOR.length());

					initial = getStateFromLine(Q, line);
				}
			}
			else if (line.startsWith(Machine.FINAL_INDICATOR))
			{
				line = line.substring(Machine.FINAL_INDICATOR.length());

				F.add(getStateFromLine(Q, line));
			}	
			else
				readTransition(Q, alphabet, line);
		}

		Machine machine = new Machine(Q, alphabet, initial, F);

		if (numStarts != 1)
			machine = null;

		return machine;
	}

	private State readTransition(Vector<State> Q, Vector<Character> alphabet,
			String line)
	{
		Scanner scan = new Scanner(line);

		State state = getStateFromVector(Q, new State(scan.next()));
		char transition = getCharFromAlphabet(alphabet, scan.next().charAt(0));
		State nextState = getStateFromVector(Q, new State(scan.next()));

		state.addTransition(transition, nextState);

		return state;
	}
	
	private State getStateFromLine(Vector<State> Q, String line)
	{
		Scanner scan = new Scanner(line);

		State state = getStateFromVector(Q, new State(scan.next()));

		return state;
	}

	/* If an equivalent state to state is in states, that state is returned,
	 * else the state is added to states and then returned. Either way, what
	 * is returned is an element of states.
	 */
	private State getStateFromVector(Vector<State> states, State state)
	{
		State found = new State(null);

		if (!states.contains(state))
			states.add(state);

		found = states.get(states.indexOf(state));

		return found;
	}

	private char getCharFromAlphabet(Vector<Character> alphabet, char ch)
	{
		if (!alphabet.contains(ch))
			alphabet.add(ch);

		return ch;
	}
}
