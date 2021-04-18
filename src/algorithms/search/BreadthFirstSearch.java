package algorithms.search;

import java.util.*;

public class BreadthFirstSearch extends ASearchingAlgorithm{

    protected Queue<AState> visited;
    protected HashMap<String, AState> states;
    public BreadthFirstSearch() {
        Queue<AState> q = new LinkedList<>();
        this.visited = q;
        HashMap<String, AState> hashmap = new HashMap<String, AState>();
        this.states = hashmap;
    }

    public Solution solve(ISearchable searchable) {
        if (searchable == null)
            return null;
        AState goalState = BFS(searchable);
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

    private AState BFS(ISearchable searchable){
        visited.add(searchable.getStartState());
        states.put(searchable.getStartState().toString(),searchable.getStartState());

        while (!visited.isEmpty()) {

            AState currentState = visited.poll();

            if (currentState.equals(searchable.getGoalState()))
                return currentState;
            ArrayList<AState> possibleStates = searchable.getAllPossibleStates(currentState);
            int i = 0;
            while (i < possibleStates.size()) {
                AState state = possibleStates.get(i);
                if (!states.containsKey(state.toString())){
                    states.put(state.toString(),state);
                    visited.add(state);
                }
                i++;
            }
        }
        return null;
    }
}