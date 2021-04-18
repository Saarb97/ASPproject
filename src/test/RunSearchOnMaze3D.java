package test;

import algorithms.maze3D.*;
import algorithms.search.*;


public class RunSearchOnMaze3D {
    static boolean x = true;

    public static void main(String[] args) {
        IMaze3DGenerator mg = new MyMaze3DGenerator();
        while(x) {
            Maze3D maze = mg.generate(100,100,100);
            SearchableMaze3D searchableMaze = new SearchableMaze3D(maze);
            long startTime = System.currentTimeMillis();
            solveProblem(searchableMaze, new BreadthFirstSearch()); //BFS
            long finishTime = System.currentTimeMillis();
            System.out.print("Time: ");
            System.out.println(finishTime - startTime);

            startTime = System.currentTimeMillis();
            solveProblem(searchableMaze, new DepthFirstSearch()); //DFS
            finishTime = System.currentTimeMillis();
            System.out.print("Time: ");
            System.out.println(finishTime - startTime);

            startTime = System.currentTimeMillis();
            solveProblem(searchableMaze, new BestFirstSearch()); //Best
            finishTime = System.currentTimeMillis();
            System.out.print("Time: ");
            System.out.println(finishTime - startTime);

            x = false;
        }


    }
    private static void solveProblem(ISearchable domain, ISearchingAlgorithm
            searcher) {
//Solve a searching problem with a searcher
        SearchableMaze3D SM = (SearchableMaze3D)domain;
        Solution solution = searcher.solve(domain);
        if (solution == null) {
            SM.getMaze().print();
            System.out.println("------------------------------------------");
            x = false;
        }
        else
            System.out.println(String.format("'%s' algorithm - nodes evaluated: %s", searcher.getName(), searcher.getNumberOfNodesEvaluated()));
//Printing Solution Path
/*
                System.out.println("Solution path:");
        ArrayList<AState> solutionPath = solution.getSolutionPath();
        for (int i = 0; i < solutionPath.size(); i++) {
            System.out.println(String.format("%s. %s",i,solutionPath.get(i)));
        }*/
    }
}
