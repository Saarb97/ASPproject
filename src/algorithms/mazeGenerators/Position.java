package algorithms.mazeGenerators;

import java.io.Serializable;
import java.util.Objects;

public class Position implements Serializable {
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

    public Position(Position pos) {
        if (pos != null) {
            this.rowIndex = pos.getRowIndex();
            this.columnIndex = pos.getColumnIndex();
        }
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

    public void setRow(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public void setColumn(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return rowIndex == position.rowIndex && columnIndex == position.columnIndex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rowIndex, columnIndex);
    }


}
