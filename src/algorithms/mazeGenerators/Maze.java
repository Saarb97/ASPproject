package algorithms.mazeGenerators;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;


public class Maze {
    private Position start;
    private Position end;
    private int[][] maze;
    public Maze(Position start,Position end,int[][] maze) {
        if(start != null && end != null && maze != null)
        {
            this.start = start;
            this.end = end;
            this.maze = maze;
        }
    }

    public Maze(int rows, int columns) {
        if (rows >= 2 && columns >= 2) {
            this.maze = new int[rows][columns];
            setRandomStartAndEndPos();
        }
    }
    public Maze(byte[] byteArr) {
        int rows,columns,startRow,startCol,endRow,endCol;
        rows = ((byteArr[0] & 0xFF) << 8) | (byteArr[1] & 0xFF);
        columns = ((byteArr[2] & 0xFF) << 8) | (byteArr[3] & 0xFF);
        startRow = ((byteArr[4] & 0xFF) << 8) | (byteArr[5] & 0xFF);
        startCol = ((byteArr[6] & 0xFF) << 8) | (byteArr[7] & 0xFF);
        endRow = ((byteArr[8] & 0xFF) << 8) | (byteArr[9] & 0xFF);
        endCol = ((byteArr[10] & 0xFF) << 8) | (byteArr[11] & 0xFF);

        this.maze = new int[rows][columns];
        start = new Position(startRow,startCol);
        end = new Position(endRow,endCol);

        int counter = 12;
        for (int i = 0; i <= maze.length - 1; i++) {
            for (int j = 0; j <= maze[0].length - 1; j++) {
                maze[i][j] = byteArr[counter++];
            }
        }

    }


    public void print() {
        for (int i = 0; i <= maze.length - 1; i++) {
            System.out.print("{ ");
            for (int j = 0; j <= maze[i].length-1; j++) {
                if (start.getRowIndex() == i & start.getColumnIndex() == j)
                    System.out.print("S ");
                else if (end.getRowIndex() == i & end.getColumnIndex() == j)
                    System.out.print("E ");

                else
                    System.out.print(maze[i][j] + " ");
            }
            System.out.println("}");
        }
    }

    public Position getStartPosition() {
        return new Position(start);
    }

    public Position getGoalPosition() {
        return new Position(end);
    }

    public Position RandomPositionOnEdge(int rows,int columns) {
        int row = ThreadLocalRandom.current().nextInt(0, rows);
        int column = ThreadLocalRandom.current().nextInt(0, columns);

        return null;
    }

    public void SetPos (int i, int j,int value)  {
        if((value == 0 || value == 1) && (0 <= i && i <= maze.length - 1) && ((0 <= j && j <= maze[0].length - 1))){
            maze[i][j] = value;
        }
    }
    public void SetPos (Position pos,int value) {
        if ((pos != null) && (value == 0 || value == 1)) {
            maze[pos.getRowIndex()][pos.getColumnIndex()] = value;
        }
    }
    public int getValueAtPos(int row,int col) {
        if (row >= maze.length || col >= maze[0].length || row < 0 || col < 0)
            return 1;
        return maze[row][col];
    }


    public void setRandomStartAndEndPos () {
        int rows =maze.length;
        int columns = maze[0].length;
        int startRow,startCol,endRow,endCol;
        startRow= ThreadLocalRandom.current().nextInt(0, rows); //deciding on a starting row
        if (startRow == 0 || startRow == rows-1) // if the starting row is 0 or the last row, the starting column can be
                                                // any column, else, the starting column is the left or right wall
            startCol = ThreadLocalRandom.current().nextInt(0, columns);
        else {
            startCol = ThreadLocalRandom.current().nextInt(0, 2); //left or right walls of the maze
            if (startCol == 1)
                startCol = columns-1; //changing to the right side wall.
        }
        while (true) {
            endRow = ThreadLocalRandom.current().nextInt(0, rows);
            if (Math.abs(endRow-startRow) >= (rows/10)*5)  //searching for a end point which is at least
                break;                                       // 50% up\down the Maze from the start point (relative to maze size)
        }
        if (endRow == 0 || endRow == rows-1) // same as starting row and starting column positioning
            endCol = ThreadLocalRandom.current().nextInt(0, columns);
        else {                      // put the end position on the other side of the maze from the start position
            if (startCol == 0)
                endCol = columns-1;
            else
                endCol = 0;

        }
        this.start = new Position(startRow,startCol);
        this.end = new Position(endRow,endCol);
    }

    public byte[] toByteArray() {
        byte rowSize,colSize;
        ArrayList<Byte>array = new ArrayList<>();

        //adding basic info to byte array
        addIntToByteArray(maze.length,array);
        addIntToByteArray(maze[0].length,array);
        addIntToByteArray(start.getRowIndex(), array);
        addIntToByteArray(start.getColumnIndex(), array);
        addIntToByteArray(end.getRowIndex(), array);
        addIntToByteArray(end.getColumnIndex(), array);
        //adding maze to byte array
        for (int i = 0; i <= maze.length - 1; i++) {
            for (int j = 0; j <= maze[0].length - 1; j++) {
                array.add((byte)maze[i][j]);
            }
        }

        //converting arrayList to normal array for return
        byte[] returnArray = new byte[array.size()];
        for(int i=0;i<array.size();i++)
            returnArray[i] = array.get(i);

        return returnArray;
    }

    private void addIntToByteArray (int val,ArrayList array) {
        //converting int into 2 bytes and adding to the byte array.
        //no maze size will exceed the max of 2 bytes 65536.
        byte[] bytes = new byte[2];
        bytes[1] = (byte) (val & 0xFF);
        bytes[0] = (byte) ((val >> 8) & 0xFF);
        array.add(bytes[0]);
        array.add(bytes[1]);

    }





}
