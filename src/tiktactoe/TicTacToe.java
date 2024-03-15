package tiktactoe;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import tiktactoe.controller.GameController;
import tiktactoe.model.BotDifficultyLevel;
import tiktactoe.model.BotPlayer;
import tiktactoe.model.Game;
import tiktactoe.model.GameStatus;
import tiktactoe.model.HumanPlayer;
import tiktactoe.model.Player;
import tiktactoe.model.Symbol;

public class TicTacToe {

    public static void main(String[] args) {

        GameController gameController = new GameController();

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of human players");
        int noOfHumanPlayers = sc.nextInt();

        System.out.println("Enter the total numbers of undo moves allowed");
        int allowedUndo = sc.nextInt();

        List<Player> players = new ArrayList<>();

        //handling human players
        Set<Character> symbolSet = new HashSet<>();
        for(int i=0; i<noOfHumanPlayers; i++){
            System.out.println("Enter name and symbol for player "+(i+1));
            String name = sc.next();
            char c = sc.next().charAt(0);
            while(symbolSet.contains(c)){
                System.out.println("This symbol is taken. Choose another symbol");
                c = sc.next().charAt(0);
            }
            symbolSet.add(c);
            players.add(new HumanPlayer(name, new Symbol(c), allowedUndo));
        }

        //handling bot players
        System.out.println("Enter the number of bots");
        int noOfBots = sc.nextInt();
        String allowedSymbols = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        Random random = new Random();
        for(int i=0; i<noOfBots; i++){
            //randomly choose any symbol from the pool for bot
            System.out.println("Choose bot difficulty level: E/M/D");
            char diffLevel = sc.next().charAt(0);
            BotDifficultyLevel botDifficultyLevel;
            switch (diffLevel){
                case 'E':
                    botDifficultyLevel = BotDifficultyLevel.EASY;
                    break;
                case 'M':
                    botDifficultyLevel = BotDifficultyLevel.MEDIUM;
                    break;
                case 'D':
                    botDifficultyLevel = BotDifficultyLevel.DIFFICULT;
                    break;
                default:
                    System.out.println("Invalid choice for difficulty. Choosing EASY as default.");
                    botDifficultyLevel = BotDifficultyLevel.EASY;
            }

            char c = allowedSymbols.charAt(random.nextInt(allowedSymbols.length()));
            while(symbolSet.contains(c)){
                c = allowedSymbols.charAt(random.nextInt(allowedSymbols.length()));
            }
            symbolSet.add(c);
            BotPlayer botPlayer = new BotPlayer("Bot"+(i+1), new Symbol(c), botDifficultyLevel);
            players.add(botPlayer);
            System.out.println(botPlayer.getName()+": "+botPlayer.getBotDifficultyLevel()+": "+botPlayer.getSymbol().getS());
        }

        Game game = gameController.createGame(players);
        while(gameController.getGameStatus(game) == GameStatus.IN_PROGRESS){
            gameController.printBoard(game);
            gameController.makeMove(game);
            gameController.undoMove(game);
        }
        gameController.printBoard(game);
        if(gameController.getGameStatus(game) == GameStatus.ENDED){
            Player winner = gameController.getCurrentPlayer(game);
            System.out.println("Winner: "+winner.getName()+"🥇");
        }
        else{
            System.out.println("This game has drawn");
            gameController.printBoard(game);
        }
    }
}
