package tiktactoe.model;

public class Cell {
    private final int x;
    private final int y;

    public CellStatus getStatus() {

        return status;
    }

    public void setStatus(final CellStatus status) {

        this.status = status;
    }

    private CellStatus status;
    private Player player;

    public Cell(final int x, final int y) {
        this.x = x;
        this.y = y;
        this.status = CellStatus.UNOCCUPIED;
    }

    public Player getPlayer() {

        return player;
    }

    public void setPlayer(final Player player) {

        this.player = player;
    }
}
