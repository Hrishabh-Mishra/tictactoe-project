package tiktactoe.startegy.checkforwin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import tiktactoe.model.Board;
import tiktactoe.model.Cell;

public class OrderOneWinningStrategy implements WinningStrategy{

    List<HashMap<Character, Integer>> rowMaps;
    List<HashMap<Character, Integer>> colMaps;
    HashMap<Character, Integer> diagonal;
    HashMap<Character, Integer> revDiagonal;
    public OrderOneWinningStrategy(int boardSize) {
        rowMaps = new ArrayList<>();
        colMaps = new ArrayList<>();
        for (int i=boardSize; i>0; i--) {
            rowMaps.add(new HashMap<>());
            colMaps.add(new HashMap<>());
        }
        diagonal = new HashMap<>();
        revDiagonal = new HashMap<>();
    }

    @Override
    public boolean checkForWin(final Board board, final Cell currentCell) {
        //update the maps
        int row = currentCell.getX();
        int col = currentCell.getY();
        HashMap<Character, Integer> rowMap = rowMaps.get(row);
        HashMap<Character, Integer> colMap = colMaps.get(col);
        char c = currentCell.getPlayer().getSymbol().getS();
        rowMap.put(c, rowMap.getOrDefault(c, 0)+1);
        colMap.put(c, colMap.getOrDefault(c, 0)+1);
        if(isCellOnDiagonal(row, col)){
            diagonal.put(c, diagonal.getOrDefault(c,0)+1);
        }
        if(isCellOnRevDiagonal(row, col, board.getBoardSize())){
            revDiagonal.put(c, revDiagonal.getOrDefault(c,0)+1);
        }

        //check for win condition
        if(rowMap.get(c) == board.getBoardSize() || colMap.get(c) == board.getBoardSize()){
            return true;
        }
        if(isCellOnDiagonal(row, col) && diagonal.get(c) == board.getBoardSize()){
            return true;
        }
        if(isCellOnRevDiagonal(row, col, board.getBoardSize()) && revDiagonal.get(c) == board.getBoardSize()){
            return true;
        }
        //the code will never reach here
        //todo  throw and exception instead of returning false
        return false;
    }

    @Override
    public void updateOnUndo(final Board board, final Cell currentCell) {
        //update the maps
        int row = currentCell.getX();
        int col = currentCell.getY();
        HashMap<Character, Integer> rowMap = rowMaps.get(row);
        HashMap<Character, Integer> colMap = colMaps.get(col);
        char c = currentCell.getPlayer().getSymbol().getS();
        rowMap.put(c, rowMap.get(c)-1);
        colMap.put(c, colMap.get(c)-1);
        if(isCellOnDiagonal(row, col)){
            diagonal.put(c, diagonal.get(c)-1);
        }
        if(isCellOnRevDiagonal(row, col, board.getBoardSize())){
            revDiagonal.put(c, revDiagonal.get(c)-1);
        }
    }

    private boolean isCellOnRevDiagonal(final int row, final int col, final int boardSize) {
        return row + col == boardSize - 1;
    }

    private boolean isCellOnDiagonal(final int row, final int col) {
        return row == col;
    }
}
