package tiktactoe.model;

public class Move {
    private Cell cell;
    private Player player;

    public Move(final Cell cell, final Player player) {

        this.cell = cell;
        this.player = player;
    }
}
