package algorithms.maze3D;



import algorithms.mazeGenerators.Position;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class MyMaze3DGenerator extends AMaze3DGenerator {

    @Override
    public Maze3D generate(int depth,int rows, int columns) {
        // TODO ERROR HANDLING
        // min size 1X2X2
        int[][][] maze = new int[depth][rows][columns];
        FillMazeWithWalls(maze);

        return CreateMazeWithPrim(maze);
    }

    private void FillMazeWithWalls(int[][][] maze) {
        //TODO ERROR HANDLING
        if (maze != null) {
            for (int i = 0; i <= maze.length - 1; i++) {
                for (int j = 0; j <= maze[0].length - 1; j++) {
                    for (int k = 0; k <= maze[0][0].length - 1; k++) {
                        maze[i][j][k] = 1;
                    }
                }
            }
        }
    }
    private Maze3D CreateMazeWithPrim(int[][][] maze) {
        //setting random start point
        Position3D startPos = CalcRandomStartPoint(maze);
        ArrayList<Position3D>Walls = new ArrayList();
        maze[startPos.getDepthIndex()][startPos.getRowIndex()][startPos.getColumnIndex()] = 0;
        AddNeighbouringWallsToArray(Walls,startPos,maze);
        while(!Walls.isEmpty()) {
            int randomWallIndex = ThreadLocalRandom.current().nextInt(0, Walls.size());
            if (CheckWallAndNeighbours(Walls.get(randomWallIndex),maze)) {
                maze[Walls.get(randomWallIndex).getDepthIndex()][Walls.get(randomWallIndex).getRowIndex()][Walls.get(randomWallIndex).getColumnIndex()] = 0;
                AddNeighbouringWallsToArray(Walls,Walls.get(randomWallIndex),maze);
            }
            Walls.remove(randomWallIndex);
        }
        Position3D endPos = CalcEndPoint(maze,startPos);
        return new Maze3D(startPos,endPos,maze);
    }
        private Position3D CalcRandomStartPoint(int[][][] maze) {
            int depth = maze.length;
            int rows = maze[0].length;
            int columns = maze[0][0].length;
            int startDepth,startRow, startCol;

            startDepth = ThreadLocalRandom.current().nextInt(0, depth);
            startRow = ThreadLocalRandom.current().nextInt(0, rows); //deciding on a starting row
            if (startRow == 0 || startRow == rows - 1) // if the starting row is 0 or the last row, the starting column can be
                // any column, else, the starting column is the left or right wall
                startCol = ThreadLocalRandom.current().nextInt(0, columns);
            else {
                startCol = ThreadLocalRandom.current().nextInt(0, 2); //left or right walls of the maze
                if (startCol == 1)
                    startCol = columns - 1; //changing to the right side wall.
            }
            return new Position3D(startDepth,startRow,startCol);
        }
    private void AddNeighbouringWallsToArray(ArrayList array,Position3D pos,int[][][] maze) {
        //TODO ERROR HANDLING
        if (pos.getRowIndex()-1 >= 0 && maze[pos.getDepthIndex()][pos.getRowIndex()-1][pos.getColumnIndex()] == 1)
            array.add(new Position3D(pos.getDepthIndex(),pos.getRowIndex()-1,pos.getColumnIndex()));
        if (pos.getRowIndex()+1 <= maze[0].length-1 && maze[pos.getDepthIndex()][pos.getRowIndex()+1][pos.getColumnIndex()] == 1)
            array.add(new Position3D(pos.getDepthIndex(),pos.getRowIndex()+1,pos.getColumnIndex()));
        if (pos.getColumnIndex()-1 >= 0 && maze[pos.getDepthIndex()][pos.getRowIndex()][pos.getColumnIndex()-1] == 1)
            array.add(new Position3D(pos.getDepthIndex(),pos.getRowIndex(),pos.getColumnIndex()-1));
        if (pos.getColumnIndex()+1 <= maze[0][0].length-1 && maze[pos.getDepthIndex()][pos.getRowIndex()][pos.getColumnIndex()+1] == 1)
            array.add(new Position3D(pos.getDepthIndex(),pos.getRowIndex(),pos.getColumnIndex()+1));
        if(pos.getDepthIndex()-1 >= 0 && maze[pos.getDepthIndex()-1][pos.getRowIndex()][pos.getColumnIndex()] == 1)
            array.add(new Position3D(pos.getDepthIndex()-1,pos.getRowIndex(),pos.getColumnIndex()));
        if(pos.getDepthIndex()+1 <=maze.length-1  && maze[pos.getDepthIndex()+1][pos.getRowIndex()][pos.getColumnIndex()] == 1)
            array.add(new Position3D(pos.getDepthIndex()+1,pos.getRowIndex(),pos.getColumnIndex()));
    }
    private boolean CheckWallAndNeighbours(Position3D pos,int[][][] maze) {
        //TODO ERROR HANDLING

        // checking how many squares around the potential path are themselves squares.
        // if more than one square is a maze path already, the Position3D is disqualified and function returns false
        int counter = 0;
        if (pos.getRowIndex()-1 >= 0 && maze[pos.getDepthIndex()][pos.getRowIndex()-1][pos.getColumnIndex()] == 0)
            counter++;
        if (pos.getRowIndex()+1 <= maze[0].length-1 && maze[pos.getDepthIndex()][pos.getRowIndex()+1][pos.getColumnIndex()] == 0)
            counter++;
        if (pos.getColumnIndex()-1 >= 0 && maze[pos.getDepthIndex()][pos.getRowIndex()][pos.getColumnIndex()-1] == 0)
            counter++;
        if (pos.getColumnIndex()+1 <= maze[0][0].length-1 && maze[pos.getDepthIndex()][pos.getRowIndex()][pos.getColumnIndex()+1] == 0)
            counter++;
        if (pos.getDepthIndex()-1 >= 0 && maze[pos.getDepthIndex()-1][pos.getRowIndex()][pos.getColumnIndex()] == 0)
            counter++;
        if (pos.getDepthIndex()+1 <= maze.length-1 && maze[pos.getDepthIndex()+1][pos.getRowIndex()][pos.getColumnIndex()] == 0)
            counter++;
        if (counter == 1) //only one Neighbour of the wall is a path
            return true;
        return false;
    }
    /*
    returning the furthest end point possible in the current maze by distance from the start point
     */
    private Position3D CalcEndPoint(int[][][] maze,Position3D start) {
        ArrayList<Position3D>endPosCandidates = new ArrayList();
        // checking all the edges of the map and searching for possible end points
        // four for loops for four "walls" of the maze
        for(int j = 0;j <=maze.length-1;j++) {
            for (int i = 0; i <= maze[0][0].length - 1; i++) {
                if (maze[j][0][i] == 0)
                    endPosCandidates.add(new Position3D(j,0, i));
            }
        }
        for(int j = 0;j <=maze.length-1;j++) {
            for (int i = 0; i <= maze[0][0].length - 1; i++) {
                if (maze[j][maze.length - 1][i] == 0)
                    endPosCandidates.add(new Position3D(j,maze.length - 1, i));
            }
        }
        for(int j = 0;j <=maze.length-1;j++) {
            for (int i = 0; i <= maze[0].length - 1; i++) {
                if (maze[j][i][0] == 0)
                    endPosCandidates.add(new Position3D(j,i, 0));
            }
        }
        for(int j = 0;j <=maze.length-1;j++) {
            for (int i = 0; i <= maze[0].length - 1; i++) {
                if (maze[j][i][maze[0].length - 1] == 0)
                    endPosCandidates.add(new Position3D(j, i, maze[0].length - 1));
            }
        }
        int bestIndex = 0,bestValue = 0;
        int startRow = start.getRowIndex(); // to minimize heap calls
        int startCol = start.getColumnIndex();
        int startDepth = start.getDepthIndex();
        for (int i = 0;i < endPosCandidates.size();i++) {
            Position3D pos = endPosCandidates.get(i);
            if (Math.abs(pos.getRowIndex()-startRow)+Math.abs(pos.getColumnIndex()-startCol+Math.abs(pos.getDepthIndex()-startDepth))>bestValue) {
                bestValue = Math.abs(pos.getRowIndex()-startRow)+Math.abs(pos.getColumnIndex()-startCol);
                bestIndex = i;
            }
        }
        return endPosCandidates.get(bestIndex);
    }












}
