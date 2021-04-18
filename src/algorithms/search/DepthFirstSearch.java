package algorithms.search;

import java.util.*;

public class DepthFirstSearch extends ASearchingAlgorithm {

    protected Stack<AState> stack;
    protected HashMap<String, AState> states;

    public DepthFirstSearch() {
        Stack<AState> s = new Stack<>();
        this.stack = s;
        HashMap<String, AState> hashmap = new HashMap<String, AState>();
        this.states = hashmap;
    }

    @Override
    public Solution solve(ISearchable searchable) {
        if (searchable == null) return null;

        stack = new Stack<>();
        states = new HashMap<>();
        AState goalState = DFS(searchable);
//        if(goalState != null)
//            return backSolPath(goalState);
        if(goalState != null)
        {
            Solution s = backSolPath(goalState);
            this.sol = s;
            return s;
        }
        else
            return null;
    }

    private AState DFS(ISearchable searchable){
        stack.push(searchable.getStartState());
        states.put(searchable.getStartState().toString(), searchable.getStartState());

        while (!stack.isEmpty()){

            AState currentState = stack.pop();

            if (!states.containsKey(currentState.toString()))
                states.put(currentState.toString() , currentState);
            if (currentState.equals(searchable.getGoalState()))
                return currentState;
            ArrayList<AState> possibleStates = searchable.getAllPossibleStates(currentState);
            int i = 0;
            while (i < possibleStates.size()) {
                AState state = possibleStates.get(i);
                if (!states.containsKey(state.toString())) {
                    stack.push(state);
                }
                i++;
            }
        }
        return null;
    }
}
