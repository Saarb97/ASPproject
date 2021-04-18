package algorithms.search;

import java.util.ArrayList;
import java.util.Collections;

//test
public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {

    protected Solution sol;

    public String getName() { return getClass().getName(); }

    @Override
    public int getNumberOfNodesEvaluated() { return sol.getSolutionPath().size(); }

    public Solution backSolPath(AState goalState){

        ArrayList<AState> solPath = new ArrayList<>();
        solPath.add(goalState);
        AState beforeState = goalState.getPrev();
        while(beforeState != null)
        {
            solPath.add(beforeState);
            beforeState = beforeState.getPrev();
        }
        Collections.reverse(solPath);
        return new Solution(solPath);
    }
}
