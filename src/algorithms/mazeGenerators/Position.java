package algorithms.mazeGenerators;

public class Position {
    private int rowIndex,columnIndex;
    public Position(int rowIndex,int columnIndex) {
        if (rowIndex >= 0)
            this.rowIndex = rowIndex;
        else
            this.rowIndex = 0;

        if (columnIndex >=0)
            this.columnIndex = columnIndex;
        else
            this.columnIndex = 0;
    }

    @Override
    public String toString() {
        return "{"+rowIndex+","+columnIndex+"}";
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }
}
