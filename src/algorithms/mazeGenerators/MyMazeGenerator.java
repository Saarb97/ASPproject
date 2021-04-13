package algorithms.mazeGenerators;
import java.io.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class MyMazeGenerator extends AMazeGenerator{

    @Override
    public Maze generate(int rows, int columns) {
        // TODO ERROR HANDLING
        // min size 2X2
        int[][] maze = new int[rows][columns];
        FillMazeWithWalls(maze);

        return CreateMazeWithPrim(maze);
    }

    private void FillMazeWithWalls(int[][] maze) {
        if (maze != null) {
            for (int i = 0; i <= maze.length - 1; i++) {
                for (int j = 0; j <= maze[0].length - 1; j++) {
                    maze[i][j] = 1;
                }
            }
        }
    }

    private Maze CreateMazeWithPrim(int[][] maze) {
        //setting random start point
        Position startPos = CalcRandomStartPoint(maze);
        ArrayList<Position>Walls = new ArrayList();
        maze[startPos.getRowIndex()][startPos.getColumnIndex()] = 0;
        AddNeighbouringWallsToArray(Walls,startPos,maze);
        while(!Walls.isEmpty()) {
            int randomWallIndex = ThreadLocalRandom.current().nextInt(0, Walls.size());
            if (CheckWallAndNeighbours(Walls.get(randomWallIndex),maze)) {
                maze[Walls.get(randomWallIndex).getRowIndex()][Walls.get(randomWallIndex).getColumnIndex()] = 0;
                AddNeighbouringWallsToArray(Walls,Walls.get(randomWallIndex),maze);
            }
            Walls.remove(randomWallIndex);
        }
        Position endPos = CalcEndPoint(maze,startPos);
        return new Maze(startPos,endPos,maze);
    }
    private Position CalcRandomStartPoint(int[][] maze) {
        int rows =maze.length;
        int columns = maze[0].length;
        int startRow,startCol;
        startRow= ThreadLocalRandom.current().nextInt(0, rows); //deciding on a starting row
        if (startRow == 0 || startRow == rows-1) // if the starting row is 0 or the last row, the starting column can be
            // any column, else, the starting column is the left or right wall
            startCol = ThreadLocalRandom.current().nextInt(0, columns);
        else {
            startCol = ThreadLocalRandom.current().nextInt(0, 2); //left or right walls of the maze
            if (startCol == 1)
                startCol = columns-1; //changing to the right side wall.
        }
        return new Position(startRow,startCol);
    }
    private void AddNeighbouringWallsToArray(ArrayList array,Position pos,int[][] maze) {
        //TODO ERROR HANDLING
        if (pos.getRowIndex()-1 >= 0 && maze[pos.getRowIndex()-1][pos.getColumnIndex()] == 1)
            array.add(new Position(pos.getRowIndex()-1,pos.getColumnIndex()));
        if (pos.getRowIndex()+1 <= maze.length-1 && maze[pos.getRowIndex()+1][pos.getColumnIndex()] == 1)
            array.add(new Position(pos.getRowIndex()+1,pos.getColumnIndex()));
        if (pos.getColumnIndex()-1 >= 0 && maze[pos.getRowIndex()][pos.getColumnIndex()-1] == 1)
            array.add(new Position(pos.getRowIndex(),pos.getColumnIndex()-1));
        if (pos.getColumnIndex()+1 <= maze[0].length-1 && maze[pos.getRowIndex()][pos.getColumnIndex()+1] == 1)
            array.add(new Position(pos.getRowIndex(),pos.getColumnIndex()+1));
    }
    private boolean CheckWallAndNeighbours(Position pos,int[][] maze) {
        int counter = 0;
        if (pos.getRowIndex()-1 >= 0 && maze[pos.getRowIndex()-1][pos.getColumnIndex()] == 0)
            counter++;
        if (pos.getRowIndex()+1 <= maze.length-1 && maze[pos.getRowIndex()+1][pos.getColumnIndex()] == 0)
            counter++;
        if (pos.getColumnIndex()-1 >= 0 && maze[pos.getRowIndex()][pos.getColumnIndex()-1] == 0)
            counter++;
        if (pos.getColumnIndex()+1 <= maze[0].length-1 && maze[pos.getRowIndex()][pos.getColumnIndex()+1] == 0)
            counter++;
        if (counter == 1) //only one Neighbour of the wall is a path
            return true;
        return false;
    }
    /*
    returning the furthest end point possible in the current maze by distance from the start point
     */
    private Position CalcEndPoint(int[][] maze,Position start) {
        ArrayList<Position>endPosCandidates = new ArrayList();
        // checking all the edges of the map and searching for possible end points
        // four for loops for four "walls" of the maze
        for(int i = 0;i <= maze[0].length-1;i++) {
            if (maze[0][i] == 0)
                endPosCandidates.add(new Position(0, i));
        }
        for(int i = 0;i <= maze[0].length-1;i++) {
            if (maze[maze.length-1][i] == 0)
                endPosCandidates.add(new Position(maze.length-1, i));
        }
        for(int i = 0;i <= maze.length-1;i++) {
            if (maze[i][0] == 0)
                endPosCandidates.add(new Position(i, 0));
        }
        for(int i = 0;i <= maze.length-1;i++) {
            if (maze[maze[0].length-1][0] == 0)
                endPosCandidates.add(new Position(i, maze[0].length-1));
        }

        int bestIndex = 0,bestValue = 0;
        int startRow = start.getRowIndex(); // to minimize heap calls
        int startCol = start.getColumnIndex();
        for (int i = 0;i < endPosCandidates.size();i++) {
            Position pos = endPosCandidates.get(i);
            if (Math.abs(pos.getRowIndex()-startRow)+Math.abs(pos.getColumnIndex()-startCol)>bestValue) {
                bestValue = Math.abs(pos.getRowIndex()-startRow)+Math.abs(pos.getColumnIndex()-startCol);
                bestIndex = i;
            }
        }
        return endPosCandidates.get(bestIndex);
    }












}
