package tiktactoe.model;

import java.util.ArrayList;
import java.util.List;

import tiktactoe.utils.Pair;

public class Game {
    private Board board;
    private List<Move> moves;
    private List<Player> players;
    private int currPlayerIdx;
    private GameStatus gameStatus;

    private Game(final Board board, final List<Move> moves, final List<Player> players, final int currPlayerIdx, GameStatus gameStatus) {
        this.board = board;
        this.moves = moves;
        this.players = players;
        this.currPlayerIdx = currPlayerIdx;
        this.gameStatus = gameStatus;
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
        moveOnCell.setPlayer(player);
        moveOnCell.setStatus(CellStatus.OCCUPIED);
        this.moves.add(new Move(moveOnCell, player));
        if(false) {//checkForWin
            this.gameStatus = GameStatus.ENDED;
            return;
        } else if (chekForDraw()) {
            this.gameStatus = GameStatus.DRAWN;
            return;
        }
        this.currPlayerIdx = (this.currPlayerIdx + 1) % (this.board.getBoardSize() - 1);
    }

    private boolean chekForDraw() {
        int n = board.getBoardSize();
        return n*n == this.moves.size();
    }

    public GameStatus getGameStatus() {
        return this.gameStatus;
    }

    public static class BoardBuilder{
        private Board board;
        private List<Player> players;
        public Game build(){
            return new Game(new Board(this.players.size()+1), new ArrayList<>(), this.players, 0, GameStatus.IN_PROGRESS);
        }
        public BoardBuilder setPlayers(final List<Player> players) {
            this.players = players;
            return this;
        }
    }
}
