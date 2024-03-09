package tiktactoe.model;

import java.util.ArrayList;
import java.util.List;

import tiktactoe.startegy.checkforwin.OrderOneWinningStrategy;
import tiktactoe.startegy.checkforwin.WinningStrategy;
import tiktactoe.utils.Pair;

public class Game {
    private Board board;
    private List<Move> moves;
    private List<Player> players;
    private int currPlayerIdx;
    private GameStatus gameStatus;
    private WinningStrategy winningStrategy;

    private Game(final Board board, final List<Move> moves, final List<Player> players, final int currPlayerIdx, GameStatus gameStatus, WinningStrategy winningStrategy) {
        this.board = board;
        this.moves = moves;
        this.players = players;
        this.currPlayerIdx = currPlayerIdx;
        this.gameStatus = gameStatus;
        this.winningStrategy = winningStrategy;
    }
    public static BoardBuilder getBuilder(){
        return new BoardBuilder();
    }

    public void printBoard() {
        this.board.printBoard();
    }

    public void makeMove() {
        Player player = players.get(currPlayerIdx);
        Pair<Integer, Integer> rowCol = player.makeMove();
        Cell moveOnCell = board.getCells().get(rowCol.getRow()).get(rowCol.getCol());
        while(moveOnCell.getStatus() != CellStatus.UNOCCUPIED){
            System.out.println("The chosen cell is unavailable for the move. Choose another cell.");
            rowCol = player.makeMove();
            moveOnCell = board.getCells().get(rowCol.getRow()).get(rowCol.getCol());
        }
        moveOnCell.setPlayer(player);
        moveOnCell.setStatus(CellStatus.OCCUPIED);
        this.moves.add(new Move(moveOnCell, player));
        if(checkForWin(moveOnCell)) {
            this.gameStatus = GameStatus.ENDED;
            return;
        } else if (checkForDraw()) {
            this.gameStatus = GameStatus.DRAWN;
            return;
        }
        this.currPlayerIdx = (this.currPlayerIdx + 1) % (this.board.getBoardSize() - 1);
    }

    private boolean checkForWin(final Cell moveOnCell) {
        return winningStrategy.checkForWin(this.board, moveOnCell);
    }

    private boolean checkForDraw() {
        int n = board.getBoardSize();
        return n*n == this.moves.size();
    }

    public GameStatus getGameStatus() {
        return this.gameStatus;
    }

    public Player getCurrentPlayer(){
        return players.get(currPlayerIdx);
    }

    public static class BoardBuilder{
        private Board board;
        private List<Player> players;
        public Game build(){
            board = new Board(this.players.size()+1);
            return new Game(board, new ArrayList<>(), this.players, 0, GameStatus.IN_PROGRESS, new OrderOneWinningStrategy(board.getBoardSize()));
        }
        public BoardBuilder setPlayers(final List<Player> players) {
            this.players = players;
            return this;
        }
    }
}
