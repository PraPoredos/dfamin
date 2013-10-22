/* Minimizer.java: Class that defines a single static method to minimize a DFA.
 */

import java.util.Vector;


public class Minimizer
{
	/* Minimize the DFA mach. Uses the table filling algorithm. */
	public static Machine minimize(Machine mach)
	{
		Vector<StatePair> pairs = new Vector<StatePair>();
		
		/* Build the list of all pairs (p,q) such that p and q are elements of
		 * Q.
		 */
		for (State p : mach.getQ())
		{
			for (State q : mach.getQ())
			{
				StatePair pair = new StatePair(p, q);
				
				if (!pairs.contains(pair))
				{
					pairs.add(pair);

					if (mach.getF().contains(p) && mach.getF().contains(q) ||
							!mach.getF().contains(p) && !mach.getF().contains(q))
						pair.indistinguishable = true;
				}
			}
		}
		
		/* For each pair (p,q) in Q, compute (delta(p,a), delta(q,a)) for each
		 * symbol a in the alphabet, and if that pair is distinguishable, make
		 * (p,q) distinguishable. Stop when a loop is completed with no state's
		 * distinguishability changed.
		 */
		boolean altered = true;
		while (altered)
		{	
			altered = false;
			
			for (StatePair pair : pairs)
			{	
				if (pair.indistinguishable)
				{
					for (char a : mach.getAlphabet())
					{
						StatePair nextPair = new StatePair(pair.p.nextState(a), 
								pair.q.nextState(a));

						// Convert this StatePair to its equivalent in the list
						nextPair = pairs.get(pairs.indexOf(nextPair));

						if (!nextPair.indistinguishable)
						{
							pair.indistinguishable = false;
							altered = true;
						}
					}
				}
			}
		}
		
		/* Print out the table of distinguishabilities */
		System.out.println("Table of distinguishabilities:");
		for (StatePair pair : pairs)
		{
			if (!pair.p.equals(pair.q))
				System.out.println(pair);
		}
		
		/* Now merge indistinguishable states. */
		for (StatePair pair : pairs)
		{
			if (pair.indistinguishable && !pair.p.equals(pair.q))
			{
				for (State state : mach.getQ())
				{
					// Change transitions to p to be to q
					for (char a : mach.getAlphabet())
					{
						if (state.nextState(a).equals(pair.p))
						{
							state.removeTransition(a);
							state.addTransition(a, pair.q);
						}
					}
				}
				
				mach.getQ().remove(pair.p);
			}
		}
		
		return mach;
	}
}
