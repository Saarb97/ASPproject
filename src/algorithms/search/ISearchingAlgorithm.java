package algorithms.search;

public interface ISearchingAlgorithm {

    Solution solve(ISearchable searchable);
    int getNumberOfEvaluatedNodes();
    String getName();
}
