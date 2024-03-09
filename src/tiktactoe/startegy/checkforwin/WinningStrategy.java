package tiktactoe.startegy.checkforwin;

import tiktactoe.model.Board;
import tiktactoe.model.Cell;

public interface WinningStrategy {
    public boolean checkForWin(Board board, Cell currentCell);
}
