/* Machine.java: Class representing a single deterministic finite state
 * machine, with sets of all and final states, a character set alphabet, and an
 * initial state. The delta function is defined at the state level; here it's
 * just state.nextState(char input).
 */

import java.util.Vector;


public class Machine
{
	// Markers for start and end states, for string representation convenience
	public static final String START_INDICATOR = "->";
	public static final String FINAL_INDICATOR = "*";
	
	// All and final states
	private Vector<State> Q, F;
	// Alphabet
	private Vector<Character> alphabet;
	// State the machine starts in
	private State initial;
	
	// Current state of the machine
	private State current;
	
	public Machine(Vector<State> Q, Vector<Character> alphabet, State initial,
			Vector<State> F)
	{
		this.Q = Q;
		this.alphabet = alphabet;
		this.initial = initial;
		this.F = F;
		
		current = initial;
	}
	
	/* Execute the machine on a given word. Returns true if the word is in the
	 * language accepted by this machine, false otherwise.
	 */
	public boolean wordIsInLanguage(String input)
	{
		boolean wordIsInLanguage = false;
		
		try
		{
			for (Character ch : input.toCharArray())
			{
				nextState(ch);
			}
			
			if (F.contains(current))
				wordIsInLanguage = true;
		}
		catch (Exception ex)
		{
			System.err.println("Invalid input.");
		}
		
		return wordIsInLanguage;
	}
	
	/* Move to the next state. Implemented largely by the current state's
	 * nextState method.
	 */
	public void nextState(Character input) throws Exception
	{
		if (alphabet.contains(input))
			current = current.nextState(input);
		else
			throw new Exception("Element not in alphabet.");
	}

	public Vector<State> getQ()
	{
		return Q;
	}

	public void setQ(Vector<State> Q)
	{
		this.Q = Q;
	}

	public Vector<Character> getAlphabet()
	{
		return alphabet;
	}

	public void setAlphabet(Vector<Character> alphabet)
	{
		this.alphabet = alphabet;
	}

	public State getInitial()
	{
		return initial;
	}

	public void setInitial(State initial)
	{
		this.initial = initial;
	}

	public Vector<State> getF()
	{
		return F;
	}

	public void setF(Vector<State> F)
	{
		this.F = F;
	}

	public State getCurrentState()
	{
		return current;
	}

	public void setCurrentState(State current)
	{
		this.current = current;
	}
	
	public String toString()
	{
		String stringRep = START_INDICATOR + ' ' + initial + '\n';
		
		for (State q : Q)
		{
			for (char ch : alphabet)
			{
				stringRep += q.toString() + ' ' + ch + ' ' + q.nextState(ch) + '\n';
			}
		}
		
		for (State f : F)
		{
			stringRep += FINAL_INDICATOR + ' ' + f + '\n';
		}
		
		return stringRep;
	}
}
