package tiktactoe.utils;

public class Pair<T,L> {
    T row;
    L col;

    public Pair(final T row, final L col) {
        this.row = row;
        this.col = col;
    }

    public T getRow() {

        return row;
    }

    public L getCol() {

        return col;
    }
}
