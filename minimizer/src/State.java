/* State.java: models a state of a finite state machine, consisting of a name
 * and a set of transitions to other states, represented by a hashtable.
 */

import java.util.Hashtable;

public class State
{
	/* This hashtable stores an entry for each character in the machine's
	 * alphabet, and the state the machine should move to after receiving that
	 * character as input while in this state.
	 */
	private Hashtable<Character, State> table;
	/* An identifier for this state */
	private String name;
	
	public State(String name)
	{
		this.name = name;
		table = new Hashtable<Character, State>();
	}
	
	/* Returns what state to move to with a given input. Looks up the input
	 * character in this state's hashtable and returns the corresponding state.
	 */
	public State nextState(Character input)
	{
		return table.get(input);
	}
	
	/* Add a transition to the hashtable. */
	public void addTransition(Character input, State next)
	{
		table.put(input, next);
	}
	
	/* Remove a transition from the hashtable. */
	public void removeTransition(Character input)
	{
		table.remove(input);
	}
	
	/* Returns true if the State s is "equal" to this one. The only property
	 * that's compared between the two is the name, not the set of transitions
	 * to other states.
	 */
	@Override
	public boolean equals(Object s)
	{
		if (this == s) return true;
		if (!(s instanceof State)) return false;
		
		State state = (State) s;
		return name.equals(state.getName());
	}
	
	public String getName()
	{
		return name;
	}
	
	public String toString()
	{
		return name;
	}
}
