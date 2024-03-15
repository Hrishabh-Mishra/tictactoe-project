package tiktactoe.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
    public static GameBuilder getBuilder(){
        return new GameBuilder();
    }

    public void printBoard() {
        this.board.printBoard();
    }

    public void makeMove() {
        Player player = players.get(currPlayerIdx);
        Pair<Integer, Integer> rowCol = player.makeMove(board);
        Cell moveOnCell = board.getCells().get(rowCol.getRow()).get(rowCol.getCol());
        while(moveOnCell.getStatus() != CellStatus.UNOCCUPIED){
            System.out.println("The chosen cell is unavailable for the move. Choose another cell.");
            rowCol = player.makeMove(board);
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
    public void undoMove() {
        Move toUndoMove = this.moves.get(moves.size()-1);
        Player prevPlayer = toUndoMove.getPlayer();
        if(this.gameStatus != GameStatus.IN_PROGRESS){
            return;
        }
        if(prevPlayer instanceof HumanPlayer){
            HumanPlayer prevHumanPlayer = (HumanPlayer) prevPlayer;
            int allowedUndoCount = prevHumanPlayer.getTotalAllowedUndo();
            if(allowedUndoCount <= 0){
                return;
            }
            System.out.println("Do you want to undo? Y/N");
            Scanner sc = new Scanner(System.in);
            char c = sc.next().charAt(0);
            if(c != 'Y' && c!= 'y'){
                return;
            }
            prevHumanPlayer.setTotalAllowedUndo(--allowedUndoCount);
            Cell toUndoCell = toUndoMove.getCell();
            winningStrategy.updateOnUndo(this.board, toUndoCell);
            toUndoCell.setStatus(CellStatus.UNOCCUPIED);
            toUndoCell.setPlayer(null);
            this.moves.remove(this.moves.size()-1);
            this.currPlayerIdx = (this.currPlayerIdx - 1) % (this.board.getBoardSize() - 1);
            if(this.currPlayerIdx < 0){
                this.currPlayerIdx = this.players.size() - 1;
            }
        }
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

    public void replayGame() {
        //creat a fresh board and play all the moves in it
        Board replayBoard = new Board(this.players.size() + 1);
        for(Move move : moves){
            Cell cell = move.getCell();
            int x = cell.getX();
            int y = cell.getY();
            Player player = move.getPlayer();
            replayBoard.getCells().get(x).get(y).setPlayer(player);
            replayBoard.getCells().get(x).get(y).setStatus(CellStatus.OCCUPIED);
            System.out.println(player.getName()+" played at "+x+", "+y);
            replayBoard.printBoard();
        }
    }

    public static class GameBuilder {
        private Board board;
        private List<Player> players;
        public Game build(){
            board = new Board(this.players.size()+1);
            return new Game(board, new ArrayList<>(), this.players, 0, GameStatus.IN_PROGRESS, new OrderOneWinningStrategy(board.getBoardSize()));
        }
        public GameBuilder setPlayers(final List<Player> players) {
            this.players = players;
            return this;
        }
    }
}
