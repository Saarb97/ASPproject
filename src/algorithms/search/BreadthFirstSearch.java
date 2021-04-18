package algorithms.search;

import java.util.*;

public class BreadthFirstSearch extends ASearchingAlgorithm{

    protected Queue<AState> visited;
    protected HashMap<String, AState> states;
    public BreadthFirstSearch() {
        nodesEvaluated = 0;
        Queue<AState> q = new LinkedList<>();
        this.visited = q;
        HashMap<String, AState> hashmap = new HashMap<String, AState>();
        this.states = hashmap;
    }

    public Solution solve(ISearchable searchable) {
        if (searchable == null)
            return null;
        AState goalState = BFS(searchable);

        if(goalState != null)
        {
            this.sol = backSolPath(goalState);
            return sol;
        }
        else
            return null;
    }

    private AState BFS(ISearchable searchable){
        if (searchable == null)
            return null;
        visited.add(searchable.getStartState());
        states.put(searchable.getStartState().toString(),searchable.getStartState());

        while (!visited.isEmpty()) {
            nodesEvaluated++;
            AState currentState = visited.poll();
            if (currentState.equals(searchable.getGoalState()))
                return currentState;
            ArrayList<AState> possibleStates = searchable.getAllSuccessors(currentState);
            int i = 0;
            while (i < possibleStates.size()) {
                AState state = possibleStates.get(i);
                if (!states.containsKey(state.toString())){
                    states.put(state.toString(),state);
                    visited.add(state);
                }
                else if (currentState.getPrev() != state){
                    state.setPrev(currentState);
                }
                i++;
            }
        }
        return null;
    }
}