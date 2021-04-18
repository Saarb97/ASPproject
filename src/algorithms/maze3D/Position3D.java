package algorithms.maze3D;

import java.util.Objects;

public class Position3D {
    private int depthIndex,rowIndex,columnIndex;
    public Position3D(int depthIndex,int rowIndex, int columnIndex) {
        // TODO ERROR HANDLING
        if (rowIndex >= 0)
            this.rowIndex = rowIndex;
        else
            this.rowIndex = 0;

        if (columnIndex >=0)
            this.columnIndex = columnIndex;
        else
            this.columnIndex = 0;
        if (depthIndex>=0)
            this.depthIndex = depthIndex;
        else
            this.depthIndex = 0;
    }

    public Position3D(Position3D pos) {
        this.rowIndex = pos.getRowIndex();
        this.columnIndex = pos.getColumnIndex();
        this.depthIndex = pos.getDepthIndex();
    }

    @Override
    public String toString() {
        return "{"+depthIndex+","+rowIndex+","+columnIndex+"}";
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public void setRow(int rowIndex) {
        if (rowIndex >= 0)
            this.rowIndex = rowIndex;
    }

    public void setColumn(int columnIndex) {
        if (columnIndex >= 0)
        this.columnIndex = columnIndex;
    }

    public int getDepthIndex() {
        return depthIndex;
    }

    public void setDepthIndex(int depthIndex) {
        if (depthIndex >= 0)
        this.depthIndex = depthIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position3D)) return false;
        Position3D that = (Position3D) o;
        return depthIndex == that.depthIndex && rowIndex == that.rowIndex && columnIndex == that.columnIndex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(depthIndex, rowIndex, columnIndex);
    }
}
