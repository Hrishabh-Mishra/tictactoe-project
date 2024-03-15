package tiktactoe.controller;

import java.util.List;

import tiktactoe.model.Game;
import tiktactoe.model.GameStatus;
import tiktactoe.model.Player;

public class GameController {
    public Game createGame(List<Player> players){
        return Game.getBuilder()
            .setPlayers(players)
            .build();
    }
    public void printBoard(Game game){
        game.printBoard();
    }
    public void makeMove(Game game){
        game.makeMove();
    }
    public GameStatus getGameStatus(Game game){
        return game.getGameStatus();
    }

    public Player getCurrentPlayer(Game game) {
        return game.getCurrentPlayer();
    }

    public void undoMove(Game game) {
        game.undoMove();
    }
}
