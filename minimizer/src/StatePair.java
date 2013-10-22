/* StatePair.java: models a pair of states for use in the table filling
 * algorithm.
 */

public class StatePair
{
	public State p, q;
	public boolean indistinguishable;
	
	public StatePair(State p, State q)
	{
		this.p = p;
		this.q = q;
		
		indistinguishable = false;
	}
	
	/* Returns true if both states in this pair are also in the pair sp. */
	@Override
	public boolean equals(Object sp)
	{
		if (this == sp) return true;
		if (!(sp instanceof StatePair)) return false;
		
		StatePair statePair = (StatePair) sp;
		return (this.p.equals(statePair.p) && this.q.equals(statePair.q)) || 
				(this.p.equals(statePair.q) && this.q.equals(statePair.p));
	}
	
	@Override
	public String toString()
	{
		return p + ", " + q + ": " + (indistinguishable ? "indistinguishable" : "distinguishable");
	}
}
